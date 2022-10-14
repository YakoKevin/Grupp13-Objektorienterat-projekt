package entity;

import model.AttackModel;
import model.Movement;
import utilz.*;
import view.Animation;

import java.awt.*;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity implements Attackable{
    protected boolean isAlive; //TODO: borde vara en EntityState?
    protected EntityStates state;
    protected CardinalDirection direction;
    protected Movement movement;
    protected AttackModel attack;
    protected double movementSpeed;

    public Living(Coordinate startCoordinate, int width, int height, Movement movement, AttackModel attackModel) {
        super(startCoordinate, width, height);
        this.movement = movement;
        this.attack = attackModel;
    }

    //Denna ska ActionController kalla på (och i Playerklassen finns koden sedan.)
    public abstract void attack();

    public void getHit(Rectangle atkRect, double atkP) {
        if(atkRect.contains(this.hitbox)){
            this.setHealthPoints(this.healthPoints-atkP);
        }
        if(this.healthPoints<=0){
            this.setAlive(false); //ska kanske ändras till ett state som sagt
        }
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double speed){
        this.movementSpeed=speed;
    }

    //TODO: fixa till vad som behövs. Ska ActionController kalla denna tro?
    public void updateMovement(){
        //System.out.println("x och y" + this.x + this.y);
        //ystem.out.println("Riktningen: " + getDirection());
        float newPosition[] = movement.updatePosition(finePositionX, finePositionY,this.getMovementSpeed(),this.getDirection());
        finePositionX = newPosition[0];
        finePositionY = newPosition[1];
        position = new Coordinate((int)finePositionX/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(), (int)finePositionY/GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize());
        //System.out.println("x och y efter: " + this.x + this.y);
    }

    public Movement getMovement(){
        return movement;
    }

    public AttackModel getAttack(){
        return attack;
    }
}
