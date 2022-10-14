package entity;

import model.AttackModel;
import utilz.Coordinate;

import java.awt.*;

public abstract class Enemy extends Living implements Hostile {
    private Brain brain;
    private AttackModel attackModel;
    //private EnemyStates enemyState = IDLE;

    public Enemy(Coordinate startPosition, int width, int height, float sightRange, AttackModel attack) {
        super(startPosition, width, height);
        this.brain = new Brain(sightRange);
        this.attackModel = attack;
    }


    public void tick() {
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
        private float friendlyLastSeenX;
        private float friendlyLastSeenY;
        private final float sightRange;

        public Brain(float sightRange){
            this.sightRange = sightRange;
        }

        public void think() {
            if(Math.abs(friendlyLastSeenX - getX()) > sightRange &&
                    Math.abs(friendlyLastSeenY - getY()) > sightRange){
                moveRandomly();
            } else if(Math.abs(friendlyLastSeenX - getX()) > attackModel.getAttackRange() &&
                    Math.abs(friendlyLastSeenY - getY()) > attackModel.getAttackRange()){
                moveTowardsFriendly();
            }else
                attackFriendly();
        }

        private void moveRandomly(){

        }

        private void moveTowardsFriendly(){

        }

        private void attackFriendly(){
            Rectangle attackRectangle = attackModel.getAttackRectangle(position, width);
            friendly.getHit(attackRectangle, attackModel.getAttackDamage());
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
