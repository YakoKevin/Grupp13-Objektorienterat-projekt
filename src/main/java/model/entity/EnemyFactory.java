package model.entity;

import static model.entity.EntitySetup.ENEMY;

/**
 * Factory for creating the enemies.
 */
public class EnemyFactory {
    /**
     * Creating skeletons
     * @return
     */
    public Enemy createSkeleton(){
        Enemy enemy = new Skeleton(ENEMY.getStartCoordinate());
        return enemy;
    }
}
