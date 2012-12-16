import java.util.Random;

public class Environment {

    protected Graph graph;
    protected Action[] actions;
    protected int size;

    public Environment(int cSize, int eSize) {
        genRandCorpus(cSize);
        size = eSize;
        genRandGraph();
        genRandActions();
    }

    private void genRandCorpus(int cSize) {
        Out dFile = new Out("data/corpD.txt");
        Out uFile = new Out("data/corpU.txt");

        for(int i = 0; i < cSize; i++) {
            // print series of desirable states to corpD.txt
            printStats(10, -7, dFile);
            // print series of undesirable states to corpU.txt
            printStats(7, -10, uFile);
        }
    }

    private void printStats(int max, int min, Out file) {
        Random rand = new Random();

        file.println(
            "hea" + (rand.nextInt(max - min + 1) + min) + " " +
            "str" + (rand.nextInt(max - min + 1) + min) + " " +
            "sta" + (rand.nextInt(max - min + 1) + min) + " " +
            "spe" + (rand.nextInt(max - min + 1) + min) + " " +
            "san" + (rand.nextInt(max - min + 1) + min) + " " +
            "man" + (rand.nextInt(max - min + 1) + min) + " " +
            "wea" + (rand.nextInt(max - min + 1) + min));
   }

    private void genRandGraph() {
        // create graph of integer nodes
        graph = new Graph(size, 2 * size);
        System.out.println("ACTION GRAPH CREATED");
    }

    private void genRandActions() {
        //create array of actions (corresponding to nodes in graph)
        actions = new Action[size];

        for(int i = 0; i < size; i++) {
            actions[i] = new Action();
            System.out.println("new possible action available!");
        }
    }
}
