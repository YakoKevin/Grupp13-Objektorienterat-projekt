package entity;

import model.AttackModel;
import model.Movement;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.EntityStates;
import utilz.ImageServer;
import view.Animation;

import java.awt.*;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity implements Attackable{
    protected boolean isAlive; //TODO: borde vara en EntityState?
    protected EntityStates state;
    protected CardinalDirection direction;
    protected Movement movement = new Movement(new Animation(ImageServer.Ids.PLAYER, null), null, null);
    protected double movementSpeed;

    public Living(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height);
    }

    //Denna ska ActionController kalla på (och i Playerklassen finns koden sedan.)
    public abstract void attack();

    public void gettingHit(Rectangle atkRect, double atkP) {
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
        Coordinate c = movement.updatePosition(position.getX(), position.getY(),this.getMovementSpeed(),this.getDirection());
        position = c;
        //System.out.println("x och y efter: " + this.x + this.y);
    }
}
