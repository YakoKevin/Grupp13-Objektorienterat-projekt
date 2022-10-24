package model.entity;

import model.Coordinate;

/**
 * Constants used for the initialization of entities.
 */
public enum EntitySetup {
    PLAYER(100, 250, 30, 30),
    ENEMY(60, 60, 30, 30);
    Coordinate startingCoordinate;
    int hitBoxWidth;
    int hitBoxHeight;

    EntitySetup(int startX, int startY, int hitBoxWidth, int hitBoxHeight){
        this.startingCoordinate = new Coordinate(startX, startY);
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
    }

    /**
     * Get start coordinate
     * @return
     */
    public Coordinate getStartCoordinate() {
        return startingCoordinate;
    }

    /**
     * Get initial x-position
     * @return
     */
    public int getStartX(){
        return startingCoordinate.getX();
    }

    /**
     * Get initial y-position
     * @return
     */
    public int getStartY(){
        return startingCoordinate.getY();
    }

    /**
     * Get the hitbox height
     * @return
     */
    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    /**
     * Get the hitbox width
     * @return
     */
    public int getHitBoxWidth() {
        return hitBoxWidth;
    }
}