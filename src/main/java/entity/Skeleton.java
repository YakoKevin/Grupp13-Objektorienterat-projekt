package entity;

import model.AttackModel;
import model.Movement;
import utilz.Coordinate;
import utilz.EntitySetup;
import utilz.ImageServer;

import java.awt.*;

public class Skeleton extends Enemy{
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.ENEMY;

    public Skeleton(Coordinate startPosition){
        super(startPosition, EntitySetup.ENEMY.getHitBoxWidth(), EntitySetup.ENEMY.getHitBoxHeight(), 120, new Movement(), new AttackModel(10,20));
        this.setHealthPoints(50);
    }

    @Override
    public void attack() {

    }

    @Override
    public void getHit(Rectangle r, double atkD) {

    }

    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }
}
