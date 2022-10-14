package entity;


import general.IObserver;
import model.AttackModel;
import utilz.Coordinate;
import utilz.EntityStates.*;

import java.awt.*;
import java.util.ArrayList;


public abstract class Enemy extends Entity implements Hostile {
    private Friendly friendly;
    //private EnemyStates enemyState = IDLE;

    public Enemy(int v, int x, int y, int width, int height){ //TODO: den som vet: gör bättre variabelnamn!... Vad är int v?
        super(x, y, width, height);
    }

    //TODO: lägg här allt som ska hända när logiken i fienden ska göras. Ska EnemyBrain hantera detta tro?
    public void tick(){
        //TODO: lägg till att gå,
        //TODO: lägg till att slå
        //TODO: lägg till att ...?
        //TODO: ELLER anropa EnemyBrain.tick() kankse, om vi fixar den klassen dvs.?
    }


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

    public void addFrendliesList(Friendly friendly) {
        this.friendly = friendly;
    }


}
