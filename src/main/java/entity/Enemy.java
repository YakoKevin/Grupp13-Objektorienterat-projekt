package entity;

import model.AttackModel;
import model.Movement;
import utilz.Coordinate;

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
        updateHitbox();
        brain.think();
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

        public void think() { //går kanske att ha ett mer beskrivande namn :)
            if(Math.abs(friendlyLastSeenX - finePositionX) < 150 && Math.abs(friendlyLastSeenY - finePositionY) < 150) {
                moveTowardsFriendly();
            }else {
                moveRandomly();
            }
//        	System.out.println(friendlyLastSeenX + " : " + finePositionX + " : " + finePositionX + velX);
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
    }
}
