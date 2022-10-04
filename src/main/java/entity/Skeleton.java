package entity;

import utilz.Coordinate;

import static utilz.EnemyConstants.*;

public class Skeleton extends Enemy{

    public Skeleton(float x, float y) {
        super(x, y, 0, 0);
        setHealthPoints(50);
    }
}
