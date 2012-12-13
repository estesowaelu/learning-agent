import java.util.Random;

public class Environment {

    Random rand;
    Graph graph;
    Action[] actions;

    public Environment(int cSize, int eSize) {
        genRandCorpus(cSize);
        genRandGraph(eSize);
        genRandActions(eSize);
    }

    private void genRandCorpus(int cSize) {
        Out dFile = new Out("data/corpD.txt");
        Out uFile = new Out("data/corpU.txt");

        rand = new Random();

        int min;
        int max;

        // print series of desirable states to corpD.txt
        min = -7;
        max = 10;
        for(int i = 0; i < cSize; i++) {
            printStats(max, min, dFile);
        }

        // print series of undesirable states to corpU.txt
        min = -10;
        max = 7;
        for(int i = 0; i < cSize; i++) {
            printStats(max, min, uFile);
        }

    }

    private void printStats(int max, int min, Out file) {
        file.println(
            "hea" + (rand.nextInt(max - min + 1) + min) + " " +
            "str" + (rand.nextInt(max - min + 1) + min) + " " +
            "sta" + (rand.nextInt(max - min + 1) + min) + " " +
            "spe" + (rand.nextInt(max - min + 1) + min) + " " +
            "san" + (rand.nextInt(max - min + 1) + min) + " " +
            "man" + (rand.nextInt(max - min + 1) + min) + " " +
            "wea" + (rand.nextInt(max - min + 1) + min));
    }

    private void genRandGraph(int eSize) {
        // create graph of integer nodes
        graph = new Graph(eSize, eSize);
    }

    private void genRandActions(int eSize) {
        //create array of actions (corresponding to nodes in graph)
        actions = new Action[eSize];

        for(int i = 0; i < eSize; i++) {
            actions[i] = new Action();
        }
    }
}
