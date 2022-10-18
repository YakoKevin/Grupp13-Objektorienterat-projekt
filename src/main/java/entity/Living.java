package entity;

import com.almasb.fxgl.physics.HitBox;
import model.AttackModel;
import model.Movement;
import utilz.*;
import view.Animation;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity implements Attackable{
    protected boolean isAlive; //TODO: borde vara en EntityState?
    protected EntityStates state;
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
    public abstract void attack();

    public void getHit(Rectangle atkRect, double atkP) {
        if(isAlive && atkRect.intersects(this.hitbox)){
            this.setHealthPoints(this.healthPoints-atkP);
        }
        if(this.healthPoints<=0){
            this.setAlive(false); //ska kanske ändras till ett state som sagt
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

    public Rectangle getAttackRec(){
        return attack.getAttackRectangle(finePositionX,finePositionY,dir);
    }
}
