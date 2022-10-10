package entity;

import utilz.CardinalDirection;
import utilz.Coordinate;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity{
    protected boolean isAlive; //TODO: borde vara en EntityState
    protected CardinalDirection direction;

    public Living(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate.getX(), startCoordinate.getY(), width, height);
    }
}
