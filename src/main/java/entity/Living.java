package entity;

import model.AttackModel;
import model.Movement;
import utilz.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity implements Attackable{
    protected boolean isAlive = true;
    protected LivingStates state;
    protected Movement movement;
    protected AttackModel attack;
    protected double movementSpeed;
    private double maximumHealthPoints;
    private ArrayList<Coordinate> obstructionCoordinates = new ArrayList<>();

    public Living(Coordinate startCoordinate, int width, int height, Movement movement, AttackModel attackModel) {
        super(startCoordinate, width, height);
        this.movement = movement;
        this.attack = attackModel;
        this.healthPoints = 100;
        this.setMaximumHealthPoints(100);
    }

    //Denna ska ActionController kalla på (och i Playerklassen finns koden sedan.)
    public boolean checkCollision(){
        for (Coordinate obsCoord : obstructionCoordinates){
            Rectangle r = new Rectangle(obsCoord.getX(),obsCoord.getY(),42,42);
            if(this.hitbox.contains(r)){
                System.out.println("Collision");
                return true;
            }
        }
        return false;
    }
    public boolean checkIfInScreenX(){
        if(finePositionX> GameConstants.GameSizes.WIDTH.getSize()){
            return false;
        }
        if(finePositionX<0){
            return false;
        }
        return true;
    }

    public boolean checkIfInScreenY(){
        if(finePositionY>GameConstants.GameSizes.HEIGHT.getSize()-this.height){
            return false;
        }
        if(finePositionY<0){
            return false;
        }
        return true;
    }

    public void getHit(Rectangle atkRect, double atkP) {
        if(isAlive && atkRect.intersects(this.hitbox)){
            this.setHealthPoints(this.healthPoints-atkP);
        }
        if(this.healthPoints<=0){
            this.setAlive(false); //ska kanske ändras till ett state som sagt
            this.state = LivingStates.DEAD;
        }
    }
    

    public void setMovementSpeed(double speed){
        this.movementSpeed=speed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    //TODO: fixa till vad som behövs. Ska ActionController kalla denna tro?
    public void updateMovement(){
        //System.out.println("x och y" + this.x + this.y);
        //ystem.out.println("Riktningen: " + getDirection());

        float newPosition[] = movement.updatePosition(finePositionX, finePositionY, movementSpeed,this.getDirection());
        finePositionX = newPosition[0];
        finePositionY = newPosition[1];
        //System.out.println("x och y efter: " + this.x + this.y);
    }

    public double getMaximumHealthPoints(){
        return this.maximumHealthPoints;
    }

    public void setMaximumHealthPoints(double maximumHealthPoints) {
        this.maximumHealthPoints = maximumHealthPoints;
    }

    public Movement getMovement(){
        return movement;
    }

    protected void setAlive(boolean aliveStatus){
        this.isAlive=aliveStatus;
    }
    public void giveObstructionList(ArrayList<Coordinate> obstructionCoordinates){
        this.obstructionCoordinates = obstructionCoordinates;
    }
    public AttackModel getAttack(){
        return attack;
    }
    public double getAttackRange(){
        return attack.getAttackRange();
    }
    public abstract ImageServer.AnimationIds getAnimationId();
    public abstract ImageServer.DeathId getDeadId();

    public Rectangle getAttackRec(){
        return attack.getAttackRectangle(hitbox,dir);
    }

    public boolean isAlive(){
        return isAlive;
    }
}
