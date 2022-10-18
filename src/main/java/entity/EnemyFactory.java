package entity;

import static utilz.EntitySetup.ENEMY;

public class EnemyFactory {

    public Enemy createSkeleton(){
        Enemy enemy = new Skeleton(ENEMY.getStartCoordinate());
        return enemy;
    }
}
