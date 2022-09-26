package Models;

import entities.Player;

import java.util.Observable;
import java.util.Observer;
public class Enemy extends Character implements Observer {
    public Enemy(){
        super(50,10,1,3,10);
    }
    Enemy e1 = new Enemy();




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
