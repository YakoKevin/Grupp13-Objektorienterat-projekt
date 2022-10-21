package model.entity;

import static model.entity.EntitySetup.ENEMY;

public class EnemyFactory {

    public Enemy createSkeleton(){
        Enemy enemy = new Skeleton(ENEMY.getStartCoordinate());
        return enemy;
    }
}
