import java.util.Random;

public class FileFactory {

    public FileFactory() {
        Out dFile = new Out("corpD.txt");
        Out uFile = new Out("corpU.txt");
        Out eFile = new Out("env.txt");

        Random rand = new Random();
        int min = 50;
        int max = 150;

        int dSize = rand.nextInt(max - min + 1) + min;
        int uSize = rand.nextInt(max - min + 1) + min;
        int eSize = rand.nextInt(max - min + 1) + min;

        // print series of desirable states to corpD.txt
        min = -7;
        max = 10;
        for(int i = 0; i < dSize; i++) {
            printStats(max, min, dFile, rand);
        }

        // print series of undesirable states to corpU.txt
        min = -10;
        max = 7;
        for(int i = 0; i < uSize; i++) {
            printStats(max, min, uFile, rand);
        }

        // print series of action-choices into env.txt
        for(int i = 0; i < eSize; i++) {
            int blockSize = rand.nextInt(10) + 1;
            for(int j = 0; j < blockSize; j++) {
                printStats(10, -10, eFile, rand);
            }
            eFile.println("-");
        }
    }

    private static void printStats(int max, int min, Out file, Random rand) {
        file.println(
            "hea" + (rand.nextInt(max - min + 1) + min) + " " +
            "str" + (rand.nextInt(max - min + 1) + min) + " " +
            "sta" + (rand.nextInt(max - min + 1) + min) + " " +
            "spe" + (rand.nextInt(max - min + 1) + min) + " " +
            "san" + (rand.nextInt(max - min + 1) + min) + " " +
            "man" + (rand.nextInt(max - min + 1) + min) + " " +
            "wea" + (rand.nextInt(max - min + 1) + min));
    }
/*
    public static void main(String[] args) {
        FileFactory al = new FileFactory();
    } */
}

