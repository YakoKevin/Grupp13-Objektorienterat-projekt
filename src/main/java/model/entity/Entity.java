package model.entity;

import model.CardinalDirection;
import model.Coordinate;
import model.GameConstants;

import java.awt.*;

/**
 * General Entity. Objects that can move and have a position, hit box and direction. Updates logic with the {@link #tick() tick()}
 * function, specifically the hitbox position. To extend this function, use {@link #tickExtra() tickExtra()} instead.
 *
 * @see CardinalDirection
 * @see Coordinate
 * @see Living
 * @see Enemy
 */
public abstract class Entity {
    protected float velX,velY;
    protected float finePositionX;
    protected float finePositionY;
    protected int width, height;
    protected Rectangle hitbox;
    protected CardinalDirection dir;

    public Entity(Coordinate startCoordinate, int width, int height){
        this.width = width;
        this.height = height;
        this.dir = CardinalDirection.EAST;
        finePositionX = startCoordinate.getX();
        finePositionY = startCoordinate.getY();

        inititateHitbox();
    }

    private void inititateHitbox() {
        hitbox = new Rectangle((int)finePositionX, (int)finePositionY, width, height);
    }

    public final void tick(){
        updateHitbox();
        tickExtra();
    }

    protected abstract void tickExtra();

    protected void updateHitbox(){
        hitbox.x = (int)finePositionX;
        hitbox.y = (int)finePositionY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getX(){
        return finePositionX;
    }
    public float getY(){
        return finePositionY;
    }

    public Coordinate getPosition() {
        return new Coordinate((int)finePositionX/ GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(),
                (int)finePositionY/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize());
    }

    public void setX(float x) {
        this.finePositionX = x;
    }

    public void setY(float y) {
        this.finePositionY = y;
    }

    public void setCoordinate(Coordinate coordinate){
        this.finePositionX = coordinate.getX()*GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        this.finePositionY = coordinate.getY()*GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
    }

    public int getWidth() { return (int)width; }

    public int getHeight()
    {
        return (int)height;
    }

    public void setDirection(CardinalDirection d){
        this.dir = d;
    }
    public CardinalDirection getDirection(){
        return this.dir;
    }
}
