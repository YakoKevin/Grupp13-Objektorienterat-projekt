package model.entity;

import static model.entity.EntitySetup.ENEMY;

/**
 * Factory for creating the enemies.
 */
public class EnemyFactory {

    public Enemy createSkeleton(){
        Enemy enemy = new Skeleton(ENEMY.getStartCoordinate());
        return enemy;
    }
}
