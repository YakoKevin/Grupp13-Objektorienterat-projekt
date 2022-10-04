package entity;

public class EnemyFactory {

    public Enemy createSkeleton(){
        return new Skeleton(0,0);
    }
}
