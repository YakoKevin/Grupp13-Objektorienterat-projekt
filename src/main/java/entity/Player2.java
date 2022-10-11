package entity;

import utilz.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class Player2 extends Living implements Friendly{
    private int keys;
    private ArrayList<Hostile> hostiles = new ArrayList<>();

    public Player2(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height);
    }

    //Denna är ju den som ActionController ska anropar på.
    public void attack(){
        for(Hostile hostile : hostiles){
            hostile.hit(new Rectangle(), 0); //TODO: stoppa in faktiska värden som argument (är temp)
        }
    }

    //Denna metod anropar enemy (som har en lista med Friendlies), för att slå Player (Friendly).
    @Override
    public void hit(Rectangle r, double atkD) {
        //TODO: bli slagen här, om träffad
    }

    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }
}
