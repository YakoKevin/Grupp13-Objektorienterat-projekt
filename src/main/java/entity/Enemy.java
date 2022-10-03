package entity;


import utilz.EntityStates;

import static utilz.EntityStates.*;
import static utilz.EntityStates.EnemyStates.*;

public abstract class Enemy extends Entity implements model.IObserver {

    private int animationIndex;
    private int animationTick = 25;
    private int animationSpeed = 25;

    private EnemyStates enemyState = IDLE;

    public Enemy(float x, float y, int width, int height){
        super(x, y, width, height);
        this.setHealthPoints(50);
        //super(50,10,1,3,10);
    }

    private void updateAnimationTick(){
        animationTick++;

        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= enemyState.getAnimationSpriteAmount()){
                animationIndex = 0;
            }
        }
    }

    public void update(){
        updateAnimationTick();
    }

    public int getAnimationIndex(){
        return animationIndex;
    }

    public EnemyStates getEnemyState(){
        return enemyState;
    }

    public void update(Player p){
        //System.out.println(" EnemyHp" + this.getHealthPoints());
        if(p.checkIfInRange(this)==true){ // funktionen ska nog inte kallas så här
            this.setHealthPoints(this.getHealthPoints()-p.getAttackPoints());
            //System.out.println("Ouch" + p.getAttackPoints());
        }
        //System.out.println(" EnemyHp" + this.getHealthPoints());
    }

}
