package model.entity;

import model.CardinalDirection;
import model.Coordinate;

import java.awt.*;
import java.time.LocalTime;
import java.util.Random;

/**
 * Enemy is the abstract class of all enemies. Extends {@code Living} since it has various states and can die. Implements {@code Hostile}
 * and can therefore hit friendlies. Has a {@code Brain} which it uses to do calculations on what to do and when to do them.
 *
 * @see Living
 * @see Hostile
 * @see Friendly
 * @see Brain
 */
public abstract class Enemy extends Living implements Hostile {
    private Brain brain;

    /**
     * Setting up enemy
     * @param startPosition
     * @param width
     * @param height
     * @param sightRange
     * @param movement
     * @param attack
     */
    public Enemy(Coordinate startPosition, int width, int height, float sightRange, Movement movement, AttackModel attack) {
        super(startPosition, width, height, movement, attack);
        this.brain = new Brain(sightRange);
    }

    /**
     * Extra tick functionality. Checking if close to player every tick and acting accordingly.
     */
    public void tickExtra() {
        if(isAlive) {
            brain.think();
        }
    }

    /**
     * Adding friendly to enemy
     * @param friendly is the player of the game
     */
    public void addFriendly(Friendly friendly) {
        brain.addFriendly(friendly);
    }

    /**
     * Giving enemy the friendly's coordinates, x and y.
     * @param x
     * @param y
     */
    public void giveFriendlyCoordinates(float x, float y){
        brain.giveFriendlyCoordinates(x, y);
    }

    /**
     * Class for computing when and where to move and attack.
     */
    private class Brain {
        private Friendly friendly;
        private float friendlyLastSeenX = Float.MIN_VALUE;
        private float friendlyLastSeenY = Float.MIN_VALUE;
        private final float sightRange;
        private boolean readyToAttack;
        private LocalTime lastRenderTime = LocalTime.now();

        public Brain(float sightRange){
            this.sightRange = sightRange;
        }

        public void think() {
            if(readyToAttack && isCloseToFriendly(attack.getAttackRange())) {
                attackFriendly();
            }
            else if(isCloseToFriendly(sightRange)) {
                moveTowardsFriendly();
            }else {
                moveRandomly();
            }
            readyToAttack = attack.coolDown();
        }

        /**
         * If player is not nearby, enemy is moving randomly.
         */
        private void moveRandomly(){
            LocalTime now = LocalTime.now();
            if(!(now.isAfter(lastRenderTime) && now.minusSeconds(1).isBefore(lastRenderTime))) {
                lastRenderTime = now;
                int enemyMoveDistance = 15;
                int random = new Random().nextInt(4);
                if(random == 0) {
                    finePositionX += enemyMoveDistance;
                }else if(random == 1) {
                    finePositionY += enemyMoveDistance;
                }else if(random == 2) {
                    finePositionX -= enemyMoveDistance;
                }else if(random == 3) {
                    finePositionY -= enemyMoveDistance;
                }
                setDirection(CardinalDirection.values()[new Random().nextInt(CardinalDirection.values().length)]);
                setState(LivingStates.RUNNING);
                if(dir == CardinalDirection.EAST) {
                    setVelX(-1);
                }else if(dir == CardinalDirection.NORTH) {
                    setVelY(1);
                }else if(dir == CardinalDirection.SOUTH) {
                    setVelY(-1);
                }else if(dir == CardinalDirection.WEST) {
                    setVelX(-1);
                }
                attackFriendly();
            }
        }

        /**
         * Moving towards friendly if in range of friendly.
         */
        private void moveTowardsFriendly(){
            LocalTime now = LocalTime.now();
            if(!(now.isAfter(lastRenderTime) && now.minusSeconds(1).isBefore(lastRenderTime))) {
                lastRenderTime = now;
                int enemyMoveDistance = 15;
                if(Math.abs(friendlyLastSeenX - finePositionX) > Math.abs(friendlyLastSeenY - finePositionY)) {
                    if(finePositionX > friendlyLastSeenX) {
                        finePositionX -= enemyMoveDistance;
                    }else {
                        finePositionX += enemyMoveDistance;
                    }
                }else {
                    if(finePositionY > friendlyLastSeenY) {
                        finePositionY -= enemyMoveDistance;
                    }else {
                        finePositionY += enemyMoveDistance;
                    }
                }
                setDirection(CardinalDirection.values()[new Random().nextInt(CardinalDirection.values().length)]);
                setState(LivingStates.RUNNING);
                if(dir == CardinalDirection.EAST) {
                    setVelX(-1);
                }else if(dir == CardinalDirection.NORTH) {
                    setVelY(1);
                }else if(dir == CardinalDirection.SOUTH) {
                    setVelY(-1);
                }else if(dir == CardinalDirection.WEST) {
                    setVelX(-1);
                }
                attackFriendly();
            }
        }

        /**
         * Attacking friendly if close enough.
         */
        private void attackFriendly(){
            Rectangle attackRectangle = attack.getAttackRectangle(hitbox, dir);
            setDirectionTowardFriendly(friendlyLastSeenX,friendlyLastSeenY);
            friendly.getHit(attackRectangle, attack.getAttackDamage());
            attack.coolDownReset();
        }

        /**
         * Turning enemy direction towards friendly.
         * @param friendlyX
         * @param friendlyY
         */
        public void setDirectionTowardFriendly(float friendlyX,float friendlyY){
            if(finePositionX>friendlyX){
                dir = CardinalDirection.WEST;
            }
            else{
                dir = CardinalDirection.EAST;
            }
        }

        /**
         * Giving enemy the friendly's coordinates.
         * @param x
         * @param y
         */
        public void giveFriendlyCoordinates(float x, float y){
            this.friendlyLastSeenX = x;
            this.friendlyLastSeenY = y;
        }

        /**
         * Getting the friendly from level.
         * @param friendly
         */
        public void addFriendly(Friendly friendly) {
            this.friendly = friendly;
        }

        /**
         * Computing if close enough to friendly
         * @param range
         * @return
         */
        private boolean isCloseToFriendly(float range){
            return (Math.abs(friendlyLastSeenX - finePositionX) < range && Math.abs(friendlyLastSeenY - finePositionY) <  range);
        }


    }
}
