package entity;

import model.AttackModel;
import model.Movement;
import utilz.Coordinate;

import java.awt.*;

public abstract class Enemy extends Living implements Hostile {
    private Brain brain;
    //private EnemyStates enemyState = IDLE;

    public Enemy(Coordinate startPosition, int width, int height, float sightRange, Movement movement, AttackModel attack) {
        super(startPosition, width, height, movement, attack);
        this.brain = new Brain(sightRange);
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
        private float friendlyLastSeenX = Float.MIN_VALUE;
        private float friendlyLastSeenY = Float.MIN_VALUE;
        private final float sightRange;

        public Brain(float sightRange){
            this.sightRange = sightRange;
        }

        public void think() {
            if(Math.abs(friendlyLastSeenX - getX()) > sightRange &&
                    Math.abs(friendlyLastSeenY - getY()) > sightRange){
                moveRandomly();
            } else if(friendly != null && Math.abs(friendlyLastSeenX - getX()) > attack.getAttackRange() &&
                    Math.abs(friendlyLastSeenY - getY()) > attack.getAttackRange()){
                moveTowardsFriendly();
            }else if(friendly != null)
                attackFriendly();
        }

        private void moveRandomly(){
            System.out.println("MOVE RANDOMLY");
        }

        private void moveTowardsFriendly(){
            System.out.println("MOVE TOWARDS FRIENDLY");
        }

        private void attackFriendly(){
            Rectangle attackRectangle = attack.getAttackRectangle(position, width);
            friendly.getHit(attackRectangle, attack.getAttackDamage());
            System.out.println("ATTACK");
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
