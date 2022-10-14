package entity;

import utilz.CardinalDirection;
import utilz.Coordinate;

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
        this.position = startCoordinate;

        inititateHitbox();
    }

    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void inititateHitbox() {
        hitbox = new Rectangle(position.getX(), position.getY(), width, height);
    }

    public void tick(){
        updateHitbox();
    }

    protected void updateHitbox(){ //TODO: Viktigt - måste göra varje update() för att undvika fel.
        hitbox.x = position.getX();
        hitbox.y = position.getY();
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

    /*public void setX(float x) {
        this.x = (int)x;
    } //TODO: Borde inte finnas, vi använder ju Movement för detta!

    public void setY(float y) {
        this.y = (int)y;
    } *///TODO: Borde inte finnas, vi använder ju Movement för detta!

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
