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
    protected AttackModel attack = new AttackModel();

    public Living(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate.getX(), startCoordinate.getY(), width, height);
    }

    public Living(int i, int j, int k, int l) {
        super(i,j,k,l);
    }

    //Denna ska ActionController kalla på (och i Playerklassen finns koden sedan.)
    public abstract void attack();

    public abstract void tick();

    public void gettingHit(Rectangle atkRect, double atkP) {
        if(atkRect.contains(this.hitbox)){
            this.setHealthPoints(this.healthPoints-atkP);
        }
        if(this.healthPoints<=0){
            this.setAlive(false); //ska kanske ändras till ett state som sagt
        }
    }
    //TODO: fixa till vad som behövs. Ska ActionController kalla denna tro?
    public void updateMovement(){
        movement.updatePosition();
    }
}
