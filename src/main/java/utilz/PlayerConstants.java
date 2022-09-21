package utilz;

public class PlayerConstants {

    public static final int IDLE = 0;
    public static final int RUNNING = 1;
    public static final int ATTACK = 2;


    public static int getSpriteAmount(int playerAction){
        if (playerAction == 0)
            return 3;
        else if (playerAction == 1)
            return 1;
        else
            return 1;
    }
}
