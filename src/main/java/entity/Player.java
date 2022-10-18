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
    private ImageServer.DeathId deathIdentification = ImageServer.DeathId.PLAYER;

    public Player(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height, new Movement(), new AttackModel(100, 100));
        this.setHealthPoints(100);
        this.setMovementSpeed(8);
    }

    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    public void attack(){
        Rectangle attackRectangle = attack.getAttackRectangle(hitbox, dir);
        for(Hostile hostile : hostiles){
            hostile.getHit(attackRectangle, attack.getAttackDamage());
        }
    }

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
    }

    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }

}