package entity;


import general.IObserver;
import utilz.EntityStates.*;

import java.awt.*;
import java.util.ArrayList;


public abstract class Enemy extends Entity implements IObserver, Hostile {
    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;


    //private EnemyStates enemyState = IDLE;

    public Enemy(int v, int x, int y, int width, int height){ //TODO: den som vet: gör bättre variabelnamn!
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.setHealthPoints(50);
        //super(50,10,1,3,10);
    }


    protected void updateAnimationTick(){ //TODO: ta bort
        animationTick++;

        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= EnemyStates.IDLE.getAnimationSpriteAmount()){
                animationIndex = 0;
            }
        }
    }

    //TODO: lägg här allt som ska hända när logiken i fienden ska göras. Ska EnemyBrain hantera detta tro?
    public void tick(){
        //TODO: lägg till att gå,
        //TODO: lägg till att slå
        //TODO: lägg till att ...?
        //TODO: ELLER anropa EnemyBrain.tick() kankse, om vi fixar den klassen dvs.?
    }

    public void update(){
        updateAnimationTick();
    }

    public int getAnimationIndex(){
        return animationIndex;
    } //TODO: TA bort

    public int getEnemyState(){
        return enemyState;
    }
/*
    public void update(Player p){
        //System.out.println(" EnemyHp" + this.getHealthPoints());
        if(p.checkIfInRange(this)==true){ // funktionen ska nog inte kallas så här
            this.setHealthPoints(this.getHealthPoints()-p.getAttackPoints());
            if(this.healthPoints<=0){
                System.out.println("Monster slain");
                this.setAlive(false);
            }
            //System.out.println("Ouch" + p.getAttackPoints());
        }
        //System.out.println(" EnemyHp" + this.getHealthPoints());
    }*/

    public void hit(Rectangle playerAtkRect, double atkP) {
        if(playerAtkRect.contains(this.hitbox)){
            this.setHealthPoints(this.getHealthPoints()-atkP);
        }
        if(this.getHealthPoints()<=0){
            this.setAlive(false);
            System.out.println("Enemy dead");
        }
        // stuff added
        //TODO: add the stuff here.

    }

    //Function to be added

    public void followPlayer(int x,int y) {
        //simpel följ-efter-metod
        if(x<this.x){
            this.x--;
        }
        else x++;
        if(y<this.y){
            this.y--;
        }
        else this.y++;
    }

    public void paint(Graphics g) { //TODO: TA BORT
        // TODO Auto-generated method stub

    }

    @Override
    public void addFrendliesList(ArrayList<Hostile> hostile) {

    }

}
