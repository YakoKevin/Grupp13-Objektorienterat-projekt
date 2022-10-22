package model.entity;

import model.Coordinate;
import model.GameConstants;
import view.ImageServer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Living implements Friendly{
    private int keyCount =0;
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private ArrayList<Coordinate> keysCoordinates = new ArrayList<>();
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.PLAYER;
    private ImageServer.DeathId deathIdentification = ImageServer.DeathId.PLAYER;

    public Player(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height, new Movement(), new AttackModel(50, 100));
        this.setHealthPoints(100);
        this.setMovementSpeed(7);
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

    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }

    @Override
    public void tick(){
        updateHitbox();
        checkEntityMovement();
        checkKeys();
    }

    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }
    public void giveKeyList(ArrayList<Coordinate> keyCoordinates){
        this.keysCoordinates = keyCoordinates;
    }
    protected void checkKeys() {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        for (
                Iterator<Coordinate> iterator = keysCoordinates.iterator(); iterator.hasNext();) {
            Coordinate coordinate = (Coordinate) iterator.next();
            Rectangle keyCoordinate = new Rectangle(coordinate.getY()*scaling, coordinate.getX()*scaling, scaling, scaling);
            if(this.hitbox.intersects(keyCoordinate)) {
                iterator.remove();
                keyCount++;
                break;
            }
        }
    }
    public int getKeyCount(){
        return this.keyCount;
    }

}