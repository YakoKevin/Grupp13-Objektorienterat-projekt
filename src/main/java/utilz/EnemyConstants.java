package utilz;

public class EnemyConstants {
    public static final int SKELETON = 0;
    public static final int MONSTER = 1;

    public static final int IDLE = 0;
    public static final int RUNNING = 1;
    public static final int ATTACK = 2;
    public static final int HIT = 3;
    public static final int DEAD = 4;

    public static int GetSpriteAmount(int enemyType, int enemyState){

        if (enemyType == SKELETON){
            if(enemyState == IDLE)
                return 3;
            else if (enemyState == RUNNING)
                return 1;
            else if (enemyState == ATTACK)
                return 1;
        }
        return 0;
    }
}
