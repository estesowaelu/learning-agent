import java.io.IOException;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Random;

public class Agent {

	private static Hashtable corpDMap;
	private static Hashtable corpUMap;
	private static Hashtable corpCMap;

	private static int health;
	private static int strength;
	private static int stamina;
	private static int speed;
	private static int sanity;
	private static int mana;
	private static int wealth;

	private static boolean[] visited;
	private static boolean[] visitable;
	static int location;

	static Random rand;

	public Agent(int eSize) {
		corpDMap = new Hashtable();
		corpUMap = new Hashtable();
		corpCMap = new Hashtable();

		visited = new boolean[eSize];
		visitable = new boolean[eSize];

		for(int i = 0; i < eSize; i++) {
			visited[i] = false;
			visitable[i] = false;
		}

		rand = new Random();

		health = 10;
		strength = 10;
		stamina = 10;
		speed = 10;
		sanity = 10;
		mana = 10;
		wealth = 10;
	}

	private static void processCorpus(Hashtable map, String file) throws IOException {
	    In inFile = new In(file);
	    while(!inFile.isEmpty()) {
	        String word = inFile.readString();
	        addWord(map, word, 1, "add");
	    }
	}

	private static void addWord(Map map, String word, int freq, String com) {
	    Object obj = map.get(word);
	    int i = 0;
	    if (obj == null) {
	        i = freq;
	        map.put(word, freq);
	    } else {
	        i = ((Integer) obj).intValue() + freq;
	        map.put(word, i);
	    }
	    System.out.println(com + " word to hash: " + word + " (index " + i + ")");
	}

	private static void addWord(Map map, String word, double freq, String com) {
	    Object obj = map.get(word);
	    double d = 0;
	    if (obj == null) {
	        d = freq;
	        map.put(word, freq);
	    } else {
	        d = ((Double) obj).intValue() + freq;
	        map.put(word, d);
	    }
	    System.out.println(com + " word to hash: " + word + " (index " + d + ")");
	}

	private static Hashtable compMap(Hashtable DMap, Hashtable UMap) {
	    Hashtable sHash = new Hashtable();
	    Enumeration enumD = DMap.keys();
	    Enumeration enumU = UMap.keys();

	    // add elements from Desirables
	    while(enumD.hasMoreElements()) {
	        // get the next desirable effect
	        String element = (String)enumD.nextElement();
	        // pull it out of the hash to look at it
	        Object objD = DMap.get(element);
	        // get its key
	        int i = ((Integer) objD).intValue();
	        addWord(sHash, element, i*2, "transfer");
	    }
	    System.out.println("=========================");

	    // add elements from Undesirables
	    while(enumU.hasMoreElements()) {
	        String element = (String)enumU.nextElement();
	        //pull it out of the hash to look at it
	        Object objU = UMap.get(element);
	        //get its key
	        int i = ((Integer) objU).intValue();
	        addWord(sHash, element, i, "transfer");
	    }
	    System.out.println("=========================");

	    Hashtable tHash = new Hashtable();
	    Enumeration enumS = sHash.keys();

	    // copy elements from sHash to tHash,
	    //  keying them based on their occurance in DMap and UMap
	    while(enumS.hasMoreElements()) {
	        String element = (String)enumS.nextElement();
	        Object objS = sHash.get(element);
	        int i = ((Integer) objS).intValue();
	        if(i >=5 ) {
	            double j;
	            if(DMap.contains(objS) && !UMap.contains(objS)) {
	                j = 0.01;
	            }
	            else if(!DMap.contains(objS) && UMap.contains(objS)) {
	                j = 0.99;
	            }
	            else {
	                j = 1.0/(double)i;
	            }
	            addWord(tHash, element, j, "refactor");
	        }
	    }

	    return tHash;
	}

	private static void writeMemory(Hashtable map, String file) throws IOException {
	    Enumeration e = map.keys();
	    Out outFile = new Out(file);
	    while(e.hasMoreElements()) {
	        String key = (String) e.nextElement();
	        outFile.println(key + " : " + map.get(key));
	    }
	}

	public static void learn(Environment environment) {
		try {
		    processCorpus(corpDMap, "data/corpD.txt");
		    System.out.println("=========================");
		    processCorpus(corpUMap, "data/corpU.txt");
		    System.out.println("=========================");

		    corpCMap = compMap(corpDMap, corpUMap);

		    writeMemory(corpCMap, "data/aMem.txt");
		}
		catch (IOException ioe) {
		}

	}

	public static void choose(Environment environment, int moves) {

		// drop agent at random node
		location = rand.nextInt(environment.size);
		System.out.println("\ndropping agent at: " + location);

		printStats("Initial");

		Action tmpAction = new Action();				// action currently being looked at
		Object obj;										// obj being looked up in corpCMap
		int currActIndex = 0;							// graph index currently being looked at
		int bestActIndex = 0;							// best local graph index
		int backupActIndex = 0;
		double currFactor = 0.0;						// factor currently being looked at
		double bestFactor = 0.0;						// best local factor

		for(int i = 0; i < moves; i++) {								// make however many moves the user wanted
			Iterable it = environment.graph.adj(location);				// get ready to look around a location
			Iterator iter = it.iterator();

			while(iter.hasNext()) {										// while there are more edges to explore
				currActIndex = (Integer)iter.next();					// set the action index to the next one
				System.out.println("\nconsidering node #" + currActIndex);
				tmpAction = environment.actions[currActIndex];			// get the action located at that index

				for(int j = 0; j < 7; j++) {							// cycle through the attributes of that action
					obj = corpCMap.get(tmpAction.strFactors[j]);		// for each attribute, see if it shows up on the corpCMap
					if(obj != null) {
						currFactor += tmpAction.intFactors[j];			// if it does, add it to the value factor
					}
				}

				// run through the available nodes
				// for each one, if it is better, and not visited, make it optimal
				// if it has been visited, but is better, mark it for backup
				// once we've looked at them all, if we haven't found an optimal one, make the backup optimal

				visitable[currActIndex] = true;

				System.out.println("factor " + " = " + currFactor);

				if(currFactor > bestFactor) {		// if its better than all previous edges on the node
					if(!visited[currActIndex]) {
						bestFactor = currFactor;								// set it as the highest factor
						System.out.println("The best action factor has gotten better: " + bestFactor);
						bestActIndex = currActIndex;							// and mark that index as the best choice (so far)
						System.out.println("The best action to choose has changed to: " + bestActIndex);
					}
					else {
						backupActIndex = currActIndex;
					}
				}

				currFactor = 0.0;
			}

			bestFactor = -71.0;

			if(bestActIndex < 0) {
				bestActIndex = backupActIndex;
			}

			System.out.print("\nTraveling from " + location + " to " + bestActIndex + ".\n");
			location = bestActIndex;									// once all edges have been looked at, move to the best one
			visited[bestActIndex] = true;

			currActIndex = -1;
			bestActIndex = -1;
			backupActIndex = -1;

			// update agent stats
			health += tmpAction.intFactors[0];
			strength += tmpAction.intFactors[1];
			stamina += tmpAction.intFactors[2];
			speed += tmpAction.intFactors[3];
			sanity += tmpAction.intFactors[4];
			mana += tmpAction.intFactors[5];
			wealth += tmpAction.intFactors[6];

			if(i == (moves - 1)) {
				printStats("Final");
			}
			else {
				printStats("New");
			}
		}
	}

	public static void printStats(String type) {
		System.out.println("\n" + type + " stats:"
								+ " hea" + health
								+ " str" + strength
								+ " sta" + stamina
								+ " spe" + speed
								+ " san" + sanity
								+ " man" + mana
								+ " wea" + wealth);
	}

}