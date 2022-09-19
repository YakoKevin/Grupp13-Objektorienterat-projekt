package Models;

abstract public class CharacterModel {
    private double x,y;
    public double healthPoints;
    private double attackPoints;
    private int keyItem;
    private double movementSpeed;

    public CharacterModel(double healthPoints, double attackPoints, int keyItem, double movementSpeed){
        this.healthPoints=healthPoints;
        this.attackPoints=healthPoints;
        this.keyItem=keyItem;
        this.movementSpeed=movementSpeed;
        this.x=0;
        this.y=0;
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

    public double getHealthPoints() {
        return healthPoints;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public int getKeyItem() {
        return keyItem;
    }
}
