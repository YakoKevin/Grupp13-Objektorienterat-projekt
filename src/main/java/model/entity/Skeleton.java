package model.entity;

import model.Coordinate;
import view.ImageServer;

/**
 * A type of enemy with its unique variables.
 */
public class Skeleton extends Enemy{
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.ENEMY;
    private ImageServer.DeathId deathIdentification = ImageServer.DeathId.ENEMY;

    /**
     * Skeleton is set up.
     * @param startPosition
     */
    public Skeleton(Coordinate startPosition){
        super(startPosition, EntitySetup.ENEMY.getHitBoxWidth(), EntitySetup.ENEMY.getHitBoxHeight(), 500, new Movement(), new AttackModel(10,50));
        this.setHealthPoints(50);
        this.setMaximumHealthPoints(50);
    }

    /**
     * Getting image for skeleton.
     * @return
     */
    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    /**
     * Getting death image for skeleton.
     * @return
     */
    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }
}
