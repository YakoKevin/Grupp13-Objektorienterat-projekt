package entity;

import utilz.CardinalDirection;

import java.awt.*;

public abstract class Entity {
    protected float x; //TODO: gör om till Coordinate ist.
    protected float y;
    protected int width, height;
    protected Rectangle hitbox;
    protected boolean isAlive;
    protected CardinalDirection dir;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dir = CardinalDirection.EAST;

        inititateHitbox();
    }

    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void inititateHitbox() {
        hitbox = new Rectangle((int)x, (int)y, width, height);
    }

    protected void updateHitbox(){ //TODO: Viktigt - måste göra varje update() för att undvika fel.
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    } //TODO: OBS borde inte returnera referensen till rectangle, borde kopiera ist!

    //private double x,y;
    public double healthPoints;
    private double attackPoints; //TODO: borde vara i Attack inte i entity
    private int keyItem;
    private double movementSpeed;
    private double attackRange; //TODO: borde vara i Attack inte i entity

    public Entity(double healthPoints, double attackPoints, int keyItem, double movementSpeed, double attackRange){
        this.healthPoints=healthPoints;
        this.attackPoints=healthPoints;
        this.keyItem=keyItem;
        this.movementSpeed=movementSpeed;
        this.x=0;
        this.y=0;
        this.attackRange = attackRange;
        this.isAlive = true;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
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
    public double getMovementSpeed() {
        return movementSpeed;
    }

    public int getKeyItem() {
        return keyItem;
    }

    public double getAttackRange(){return attackRange;}
    public boolean getAlive(){
        return this.isAlive;
    }
    protected void setAlive(boolean aliveStatus){
        this.isAlive=aliveStatus;
    }

    //TODO: update() metod borde finnas i denna klass för att uppdatera logik.
}
