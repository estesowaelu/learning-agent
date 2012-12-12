import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

public class Learner {
    private static Hashtable corpDMap;
    private static Hashtable corpUMap;
    private static Hashtable corpCMap;

    public Learner() {
        corpDMap = new Hashtable();
        corpUMap = new Hashtable();
        corpCMap = new Hashtable();
    }

    public static void processCorpus(Hashtable map, String file) throws IOException {
        In inFile = new In(file);
        while(!inFile.isEmpty()) {
            String word = inFile.readString();
            addWord(map, word, 1, "add");
        }
        /*
        String line;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                processLine(line, map);
            }
        }
        catch(IOException e01) {
        }
        */
    }

    public static void dumpCorpus(Hashtable dump, String file) throws IOException {
        Enumeration e = dump.keys();
        try {
            while(e.hasMoreElements()) {
                String element = (String)e.nextElement();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
            }
        }
        catch(IOException e02) {
        }
    }

    private static void processLine(String line, Map map) {
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            addWord(map, st.nextToken(), 1, "add");
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
    public static Hashtable compositeMap(Hashtable DMap, Hashtable UMap) {
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

    public static void main(String[] args) {
        Learner al = new Learner();
        try {
            processCorpus(corpDMap, "corpD.txt");
            System.out.println("=========================");
            processCorpus(corpUMap, "corpU.txt");
            System.out.println("=========================");
        }
        catch(IOException e01) {
            System.out.println("Error reading corpus files.");
        }

        corpCMap = compositeMap(corpDMap, corpUMap);
/*
        try {
            dumpCorpus(corpCMap, "aMem.txt");
        }
        catch(IOException e02) {
            System.out.println("Error writing memory file.");
        } */
    }
}

