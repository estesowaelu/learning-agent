import java.util.Random;

public class Action {

    String[] strFactors;
    int[] intFactors;
/*
    private String sHealth;
    private String sStrength;
    private String sStamina;
    private String sSpeed;
    private String sSanity;
    private String sMana;
    private String sWealth;

    private int iHealth;
    private int iStrength;
    private int iStamina;
    private int iSpeed;
    private int iSanity;
    private int iMana;
    private int iWealth;
*/

    public Action() {
        Random rand = new Random();
        int max = 10;
        int min = -10;

        intFactors[0]   = (rand.nextInt(max - min + 1) + min);
        intFactors[1]   = (rand.nextInt(max - min + 1) + min);
        intFactors[2]   = (rand.nextInt(max - min + 1) + min);
        intFactors[3]   = (rand.nextInt(max - min + 1) + min);
        intFactors[4]   = (rand.nextInt(max - min + 1) + min);
        intFactors[5]   = (rand.nextInt(max - min + 1) + min);
        intFactors[6]   = (rand.nextInt(max - min + 1) + min);

        strFactors[0]   = "hea" +   intFactors[0];
        strFactors[1]   = "str" +   intFactors[1];
        strFactors[2]   = "sta" +   intFactors[2];
        strFactors[3]   = "spe" +   intFactors[3];
        strFactors[4]   = "san" +   intFactors[4];
        strFactors[5]   = "man" +   intFactors[5];
        strFactors[6]   = "wea" +   intFactors[6];

    }

}
