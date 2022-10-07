package entity;

import java.awt.*;

public abstract class Entity {
    protected float x;
    protected float y;
    protected int width, height;
    protected Rectangle hitbox;
    protected boolean isAlive;

    public Entity(float hitBoxX, float hitBoxY, int width, int height){
        this.x = hitBoxX;
        this.y = hitBoxY;
        this.width = width;
        this.height = height;

        inititateHitbox();
    }

    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void inititateHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    } //TODO: OBS borde inte returnera referensen till rectangle, borde kopiera ist!

    //private double x,y;
    public double healthPoints;
    private double attackPoints;
    private int keyItem;
    private double movementSpeed;
    private double attackRange;

    public Entity(double healthPoints, double attackPoints, int keyItem, double movementSpeed, double attackRange){
        this.healthPoints=healthPoints;
        this.attackPoints=healthPoints;
        this.keyItem=keyItem;
        this.movementSpeed=movementSpeed;
        this.x =0;
        this.y =0;
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

}
