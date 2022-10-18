package entity;

import model.AttackModel;
import model.Movement;
import utilz.Coordinate;
import utilz.GameConstants;
import utilz.ImageServer;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Living implements Friendly{
    private int keys;
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private boolean attackMode = false;
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.PLAYER;

    public Player(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height, new Movement(), new AttackModel(20, 100));
        this.setHealthPoints(100);
        this.setAttackPoints(20);
        this.setMovementSpeed(8);
    }

    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    //Denna är ju den som ActionController ska anropar på.
    public void attack(){
        attack.getAttackCoordinate(getPosition(), this.dir, this.width ,this.height);
        Rectangle r = attack.getAttackRectangle(finePositionX, finePositionY); //width är samma som attackRange just nu, så att det blir hyfsat symmetriskt åt alla riktningar
        for(Hostile hostile : hostiles){
            hostile.getHit(r, this.getAttackPoints());
        }
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

    @Override
    public void tick(){
        updateHitbox();
        finePositionX+=velX;
        finePositionY+=velY;
        if(finePositionX> GameConstants.GameSizes.WIDTH.getSize()){
            finePositionX = GameConstants.GameSizes.WIDTH.getSize();
        }
        if(finePositionY>GameConstants.GameSizes.HEIGHT.getSize()-50){
            finePositionY =GameConstants.GameSizes.HEIGHT.getSize()-50;
        }
        if(finePositionX<0){
            finePositionX =0;
        }
        if(finePositionY<0){
            finePositionY = 0;
        }

        //System.out.println("x, y och velx och vely " + finePositionX + finePositionY + velX + velY);
    }



}