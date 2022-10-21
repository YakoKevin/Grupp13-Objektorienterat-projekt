package model.entity;

import model.Coordinate;
import view.ImageServer;

public class Skeleton extends Enemy{
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.ENEMY;
    private ImageServer.DeathId deathIdentification = ImageServer.DeathId.ENEMY;

    public Skeleton(Coordinate startPosition){
        super(startPosition, EntitySetup.ENEMY.getHitBoxWidth(), EntitySetup.ENEMY.getHitBoxHeight(), 500, new Movement(), new AttackModel(10,50));
        this.setHealthPoints(50);
    }

    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }
}
