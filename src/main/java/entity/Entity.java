package entity;

import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;
import view.Animation;

import java.awt.*;

public abstract class Entity {
    protected float finePositionX;
    protected float finePositionY;
    protected Coordinate position;
    protected int width, height;
    protected Rectangle hitbox;
    protected boolean isAlive;
    protected CardinalDirection dir;

    public Entity(Coordinate startCoordinate, int width, int height){
        this.width = width;
        this.height = height;
        this.dir = CardinalDirection.EAST;
        this.position = startCoordinate; //TODO: gör att position inte finns och fixa så att getPosition ger en Coordinate efter beräkning
        finePositionX = startCoordinate.getX();
        finePositionY = startCoordinate.getY();

        inititateHitbox();
    }

    private void inititateHitbox() {
        hitbox = new Rectangle(position.getX(), position.getY(), width, height);
    }

    public void tick(){
        updateHitbox();
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

    public float getX(){
        return finePositionX;
    }
    public float getY(){
        return finePositionY;
    }

    public Coordinate getPosition() {
        return position;
    }

    //TODO: fix some float-to-coordinate algoritm instead that all classes can use
    public void setX(float x) {
        this.finePositionX = x;
        position = new Coordinate((int)x/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(), position.getY());
    }

    public void setY(float y) {
        this.finePositionY = y;
        position = new Coordinate(position.getX(), (int)y/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize());
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
}
