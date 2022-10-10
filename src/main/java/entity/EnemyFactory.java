package entity;

import static utilz.EntitySetup.SKELETON;

public class EnemyFactory {

    public Enemy createSkeleton(){
        return new Skeleton(SKELETON.getStartX(), SKELETON.getStartX());
    }
}
