package entity;

import utilz.Coordinate;

import java.awt.*;

public class Skeleton extends Enemy{

    public Skeleton(Coordinate startPosition){
        super(startPosition, 0, 30);
        this.setHealthPoints(50);
    }

    @Override
    public void gettingHit(Rectangle r, double atkD) {

    }

    @Override
    public void addFriendly(Friendly friendly) {

    }
}
