package entity;


import general.IObserver;
import model.AttackModel;
import utilz.Coordinate;
import utilz.EntityStates.*;

import java.awt.*;
import java.util.ArrayList;


public abstract class Enemy extends Entity implements IObserver, Hostile {
    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;
    private AttackModel atkM = new AttackModel();
    private Coordinate c;

    private ArrayList<Friendly> friendlies = new ArrayList<>();

    //private EnemyStates enemyState = IDLE;

    public Enemy(int v, int x, int y, int width, int height){ //TODO: den som vet: gör bättre variabelnamn!... Vad är int v?
        super(x, y, width, height);
    }

    public void attack (){ // attack ska
        c = new Coordinate((int)this.getX(),(int)this.getY());
        atkM.getAttackCoordinate(c, this.dir);
        Rectangle atkR = atkM.getAttackRectangle(c,60); // Har gjort den lite mindre bara så att enemy inte ska bli omöjlig att träffa
        for(Friendly friendly : friendlies){
            //friendlies.gettingHit(atkR, 0);
            // den identifierar ej friendlies som player, så går ej att komma åt den metoden.
        }
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

    //public int getAnimationIndex(){
     //   return animationIndex;
    //} //TODO: TA bort

    public int getEnemyState(){
        return enemyState;
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

    public void addFrendliesList(ArrayList<Friendly> friendlies) {
        this.friendlies = friendlies;
    }


}
