package Models;

import utilz.Directions;

import java.util.List;



public class Player extends Character implements IObservable{
    public Player(){
        super(100,20,0,5, 10);
    }
    List<IObserver> observers;
    //List<Enemy> enemies;

    boolean isAttacking=false;

    Player p = new Player ();

    public void setAttackArea(double coordX, double coordY){

    }


    public void attack(double coordX, double coordY){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        if(Directions.LEFT ==1){
            //make hit area/rectangle to the left. Let's say 10x10. Hit area starts from character outwards, depth 10 and length 5 to the right and left. Array is ordered "clockwise" in the rectangle.



        }

        if(Directions.RIGHT==1){}
        if(Directions.UP==1){} //playerDirections?
        if(Directions.DOWN==1){}
        notifyObservers();

    }

    public boolean checkIfInRange(Enemy enemy) { //just nu bara radie för att göra det lätt, men den ska så klart ta hänsyn till direction och en hitbox
        double enXPos = enemy.getX();
        double enYPos = enemy.getY();
        double diffX = (enXPos - this.getX());
        double diffY = (enYPos - this.getY());

        double atkR = p.getAttackRange();
        System.out.println(atkR);

        if (Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) < atkR) {

            return true;
        }
        return false;
    }

    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        observers.remove(obs);


    }


    public void notifyObservers() {
        for(IObserver IObserver: observers){
            IObserver.update();
        }

    }
}
