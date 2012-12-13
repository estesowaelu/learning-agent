import java.util.Random;

public class Action {

    private int aHealth;
    private int aStrength;
    private int aStamina;
    private int aSpeed;
    private int aSanity;
    private int aMana;
    private int aWealth;

    public Action() {
        Random rand = new Random();
        int max = 10;
        int min = -10;

        aHealth = rand.nextInt(max - min + 1) + min;
        aStrength = rand.nextInt(max - min + 1) + min;
        aStamina = rand.nextInt(max - min + 1) + min;
        aSpeed = rand.nextInt(max - min + 1) + min;
        aSanity = rand.nextInt(max - min + 1) + min;
        aMana = rand.nextInt(max - min + 1) + min;
        aWealth = rand.nextInt(max - min + 1) + min;

    }

    public Action(int h, int s1, int s2, int s3, int s4, int m, int w) {
        aHealth = h;
        aStrength = s1;
        aStamina = s2;
        aSpeed = s3;
        aSanity = s4;
        aMana = m;
        aWealth = w;
    }

}
