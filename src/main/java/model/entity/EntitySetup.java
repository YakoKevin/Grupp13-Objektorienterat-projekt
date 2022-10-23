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

    public Coordinate getStartCoordinate() {
        return startingCoordinate;
    }

    public int getStartX(){
        return startingCoordinate.getX();
    };

    public int getStartY(){
        return startingCoordinate.getY();
    };

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }
}