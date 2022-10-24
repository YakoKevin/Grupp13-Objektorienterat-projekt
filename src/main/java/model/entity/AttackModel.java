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
