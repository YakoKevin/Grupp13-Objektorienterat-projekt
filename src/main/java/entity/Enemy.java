package entity;


import utilz.Coordinate;

import java.awt.*;

import static utilz.EnemyConstants.GetSpriteAmount;
import static utilz.EntityStates.*;
import static utilz.EntityStates.EnemyStates.*;

public abstract class Enemy extends Entity implements model.IObserver, Hostile {

    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;

    //private EnemyStates enemyState = IDLE;

    public Enemy(float v, float x, float y, int width, int height){
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.setHealthPoints(50);
        //super(50,10,1,3,10);
    }

    protected void updateAnimationTick(){
        animationTick++;

        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)){
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

    public int getEnemyState(){
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

    @Override
    public void checkIfAttacked(Coordinate start, Coordinate end) {
        //TODO: add the stuff here.
    }

    //Function to be added

    public void followPlayer(int x,int y) {

    }

    public void paint(Graphics g) {
        // TODO Auto-generated method stub

    }

}
