package entities;

import Models.Character;

import java.util.Observable;
import java.util.Observer;

import static  utilz.EnemyConstants.*;

public abstract class Enemy extends Entity implements Observer {

    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;

    public Enemy(float x, float y, int width, int height, int enemyType){
        super(x, y, width, height);
        this.enemyType = enemyType;
        //super(50,10,1,3,10);
    }

    private void updateAnimationTick(){
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

    public static void update(Enemy ex, Enemy ey) {
        //Player p = new Player(); //tillfälligt
        //if(p.checkIfInRange(ex)==true){ // funktionen ska nog inte kallas så här
        //    ex.setHealthPoints(ex.getHealthPoints()-p.getAttackPoints());
        //}

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
