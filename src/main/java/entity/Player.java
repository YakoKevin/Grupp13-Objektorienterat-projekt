package entity;

import model.AttackModel;
import utilz.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Living implements Friendly{
    private int keys;
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private Coordinate c;
    private boolean attackMode = false;

    public Player(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height);
        this.setHealthPoints(100);
        this.setAttackPoints(20);
        this.setMovementSpeed(3);
    }
    private AttackModel atkM = new AttackModel();

    //Denna är ju den som ActionController ska anropar på.
    public void attack(){
        c = new Coordinate((int)this.getX(),(int)this.getY());
        atkM.getAttackCoordinate(c, this.dir);
        Rectangle r = atkM.getAttackRectangle(c,this.width); //width är samma som attackRange just nu, så att det blir hyfsat symmetriskt åt alla riktningar
        for(Hostile hostile : hostiles){
            hostile.gettingHit(r, this.getAttackPoints());
        }
    }

    @Override
    public void tick() {

    }

    //Denna metod anropar enemy (som har en lista med Friendlies), för att slå Player (Friendly).

    public boolean getAttackMode() { // ska kanske vara state
        return this.attackMode;
    }
    public void setAttackMode(boolean atkM){
        this.attackMode=atkM;
    }


    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }
}