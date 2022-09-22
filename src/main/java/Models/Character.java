package Models;

abstract public class Character {
    private double x,y;
    public double healthPoints;
    private double attackPoints;
    private int keyItem;
    private double movementSpeed;
    private double attackRange;

    public Character(double healthPoints, double attackPoints, int keyItem, double movementSpeed, double attackRange){
        this.healthPoints=healthPoints;
        this.attackPoints=healthPoints;
        this.keyItem=keyItem;
        this.movementSpeed=movementSpeed;
        this.x=0;
        this.y=0;
        this.attackRange = attackRange;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAttackPoints() {
        return attackPoints;
    }

    public void setHealthPoints(double hp){}
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
}
