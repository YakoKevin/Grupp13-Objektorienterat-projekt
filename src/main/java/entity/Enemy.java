package entity;

import model.AttackModel;
import model.Movement;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.LivingStates;

import java.awt.*;
import java.time.LocalTime;
import java.util.Random;

public abstract class Enemy extends Living implements Hostile {
    private Brain brain;

    public Enemy(Coordinate startPosition, int width, int height, float sightRange, Movement movement, AttackModel attack) {
        super(startPosition, width, height, movement, attack);
        this.brain = new Brain(sightRange);
    }


    public void tick() {
        if(isAlive) {
            updateHitbox();
            brain.think();
        }
    }

    public void addFriendly(Friendly friendly) {
        brain.addFriendly(friendly);
    }

    public void giveFriendlyCoordinates(float x, float y){
        brain.giveFriendlyCoordinates(x, y);
    }

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
            }
        }

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
            }
        }

        private void attackFriendly(){
            //TODO: make enemy rotate towards friendly
            Rectangle attackRectangle = attack.getAttackRectangle(hitbox, dir);
            friendly.getHit(attackRectangle, attack.getAttackDamage());
            attack.coolDownReset();
        }

        public void giveFriendlyCoordinates(float x, float y){
            this.friendlyLastSeenX = x;
            this.friendlyLastSeenY = y;
        }

        public void addFriendly(Friendly friendly) {
            this.friendly = friendly;
        }

        private boolean isCloseToFriendly(float range){
            return (Math.abs(friendlyLastSeenX - finePositionX) < range && Math.abs(friendlyLastSeenY - finePositionY) <  range);
        }
    }
}
