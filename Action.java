import java.util.Random;

public class Action {

    protected String[] strFactors;
    protected int[] intFactors;

    public Action() {

        Random rand = new Random();

        int max = 10;
        int min = -10;

        strFactors = new String[7];
        intFactors = new int[7];

        for(int i = 0; i < 7; i++) {
            intFactors[i] = (rand.nextInt(max - min + 1) + min);
        }

        strFactors[0]   = "hea" +   intFactors[0];
        strFactors[1]   = "str" +   intFactors[1];
        strFactors[2]   = "sta" +   intFactors[2];
        strFactors[3]   = "spe" +   intFactors[3];
        strFactors[4]   = "san" +   intFactors[4];
        strFactors[5]   = "man" +   intFactors[5];
        strFactors[6]   = "wea" +   intFactors[6];

    }

}
