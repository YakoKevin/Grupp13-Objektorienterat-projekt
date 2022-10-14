package entity;

import utilz.ImageServer;
import view.Animation;

import static utilz.EntitySetup.SKELETON;

public class EnemyFactory {

    public Enemy createSkeleton(){
        Enemy enemy = new Skeleton(SKELETON.getStartCoordinate());
        Animation.addEntity(enemy, ImageServer.Ids.ENEMY);
        return enemy;
    }
}
