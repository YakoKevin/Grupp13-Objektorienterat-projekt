package entity;

import java.awt.*;

public class Skeleton extends Enemy{

    public Skeleton(int x, int y){
        super(x, y, 0, 30, 100);
        this.setHealthPoints(50);
    }

    @Override
    public void gettingHit(Rectangle r, double atkD) {

    }

    @Override
    public void addFriendly(Friendly friendly) {

    }
}
