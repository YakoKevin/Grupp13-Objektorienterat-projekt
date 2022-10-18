package entity;

import utilz.*;
import view.Animation;

import java.awt.*;

public abstract class Entity {
    protected float velX,velY;
    protected float finePositionX;
    protected float finePositionY;
    protected int width, height;
    protected Rectangle hitbox;
    protected boolean isAlive;
    protected CardinalDirection dir;
    protected EntityStates state = EntityStates.IDLE;


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

    public void tick(){
        updateHitbox();
        finePositionX+=velX;
        finePositionY+=velY;
    }

    protected void updateHitbox(){ //TODO: Viktigt - måste göra varje update() för att undvika fel.
        hitbox.x = (int)finePositionX;
        hitbox.y = (int)finePositionY;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    } //TODO: OBS borde inte returnera referensen till rectangle, borde kopiera ist!

    //private double x,y;
    public double healthPoints;
    private double attackPoints; //TODO: borde vara i Attack inte i entity

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
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
        return new Coordinate((int)finePositionX/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(),
                (int)finePositionY/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize());
    }

    //TODO: fix some float-to-coordinate algorithm instead that all classes can use
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

    public double getAttackPoints() {
        return attackPoints;
    }
    public void setAttackPoints(double atkP){
        this.attackPoints=atkP;
    }

    public void setHealthPoints(double hp){
        this.healthPoints=hp;
    }
    public double getHealthPoints() {
        return healthPoints;
    }

    public void setDirection(CardinalDirection d){
        this.dir = d;
    }
    public CardinalDirection getDirection(){
        return this.dir;
    }
    protected void setAlive(boolean aliveStatus){
        this.isAlive=aliveStatus;
    }

    public void setState(EntityStates state){
        this.state = state;
    }
    public EntityStates getState() {
        return state;
    }

}
