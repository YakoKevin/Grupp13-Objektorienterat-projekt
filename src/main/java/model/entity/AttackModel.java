package model.entity;


import model.CardinalDirection;
import model.Coordinate;

import java.awt.*;

/**
 * AttackModel handles the computations of where the attack area is to be placed. It also has a timer of how often one can attack.
 */
public class AttackModel { //

    private int attackDamage;
    private int attackRange;
    private int coolDown;
    private final int COOL_DOWN_TIME = 15;

    /**
     *
     * @param attackDamage
     * @param attackRange
     */
    public AttackModel(int attackDamage, int attackRange){
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
    }

    /**
     * This computes where to place the coordinate of the attack-rectangle.
     * @param atkOffSetX
     * @param atkOffSetY
     * @param dir
     * @param width
     * @param height
     * @return
     */
    public Coordinate getAttackCoordinate(float atkOffSetX, float atkOffSetY, CardinalDirection dir, int width, int height){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad

        //TODO: ska lägga till för fyra andra riktningar
        if(dir == CardinalDirection.WEST) { //left
            atkOffSetX -= height;
        }
        else if(dir==CardinalDirection.NORTH){
            atkOffSetX-=width;
            atkOffSetY-=height;
        }
        else if(dir== CardinalDirection.SOUTH){
            atkOffSetX-=width;
            atkOffSetY+=height;
        }
        else if(dir==CardinalDirection.EAST){
            atkOffSetX+=width;
        }

        if(atkOffSetX<0){
            atkOffSetX=0;
        }
        if(atkOffSetY<0){
            atkOffSetY=0;
        }
        return new Coordinate(0,0);
    }

    /**
     * Getting the attack rectangle.
     * @param hitbox
     * @param direction
     * @return
     */
    public Rectangle getAttackRectangle (Rectangle hitbox, CardinalDirection direction) {
        return new Rectangle(hitbox.x - hitbox.width + hitbox.width*direction.getXOffset(), hitbox.y - 15 + 30*direction.getYOffset(),attackRange, attackRange);
    }

    /**
     * Get attack range
     * @return
     */
    public float getAttackRange() {
        return attackRange;
    }

    /**
     * Get attack damage
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Cooldown for when attacking
     * @return
     */
    public boolean coolDown() {
        if(coolDown > 0) {
            coolDown--;
            return false;
        }
        return true;
    }

    /**
     * Cooldown reset when attacking
     */
    public void coolDownReset() {
        this.coolDown = COOL_DOWN_TIME;
    }
}
