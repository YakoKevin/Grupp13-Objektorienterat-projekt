package entity;

import model.AttackModel;
import utilz.Coordinate;

import java.awt.*;

public class Skeleton extends Enemy{

    public Skeleton(Coordinate startPosition){
        super(startPosition, 0, 30, 60, new AttackModel(10,20));
        this.setHealthPoints(50);
    }

    @Override
    public void attack() {

    }

    @Override
    public void getHit(Rectangle r, double atkD) {

    }

    @Override
    public void addFriendly(Friendly friendly) {

    }
}
