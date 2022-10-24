package model.entity;

import model.Coordinate;
import model.GameConstants;
import view.ImageServer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Player is the player of the game. It holds all the score-, key- and enemy slain count. Implements Friendly and can therefore
 * attack {@code Hostiles}. Extends Living since Player is a living entity and can thus have various states,
 * including being dead.
 *
 * @see Friendly
 * @see Hostile
 * @see Living
 * @see ImageServer
 * @see GameConstants
 */
public class Player extends Living implements Friendly {
    private int keyCount = 0;
    private int scoreCount = 0;
    private int roomScore = 0;
    private int slainEnemies;
    private int roomSlainEnemies;
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private ArrayList<Coordinate> keysCoordinates = new ArrayList<>();
    private ArrayList<Coordinate> coinCoordinates = new ArrayList<>();
    private Coordinate heartCoordinate;
    private ImageServer.AnimationIds identification = ImageServer.AnimationIds.PLAYER;
    private ImageServer.DeathId deathIdentification = ImageServer.DeathId.PLAYER;
    private final int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
    private boolean takenHeart = false;

    public Player(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height, new Movement(), new AttackModel(25, 100));
        this.setHealthPoints(100);
        this.setMovementSpeed(6);
    }

    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    public void attack() {
        Rectangle attackRectangle = attack.getAttackRectangle(hitbox, dir);
        for (Hostile hostile : hostiles) {
            hostile.getHit(attackRectangle, attack.getAttackDamage());
        }
    }

    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }

    @Override
    public void tickExtra() {
        checkLivingMovement();
        checkKeys();
        checkCoins();
        if(!takenHeart){
            checkHeart();
        }

    }

    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }

    public void giveKeyList(ArrayList<Coordinate> keyCoordinates) {
        this.keysCoordinates = keyCoordinates;
    }
    public void giveCoinList(ArrayList<Coordinate> coinCoordinates){
        this.coinCoordinates = coinCoordinates;
    }
    public void giveHeartObject(Coordinate hpCoord){
        this.heartCoordinate = hpCoord;
    }

    protected void checkKeys() {
        for (Iterator<Coordinate> iterator = keysCoordinates.iterator(); iterator.hasNext();) {
            Coordinate coordinate = (Coordinate) iterator.next();
            Rectangle keyCoordinate = new Rectangle(coordinate.getX() * scaling, coordinate.getY() * scaling, scaling, scaling);
            if (this.hitbox.intersects(keyCoordinate)) {
                iterator.remove();
                keyCount++;
                break;
            }
        }
    }

    protected void checkCoins() {
        for(Iterator<Coordinate> iterator = coinCoordinates.iterator(); iterator.hasNext();) {
            Coordinate coordinate = (Coordinate) iterator.next();
            Rectangle coinCoordinate = new Rectangle(coordinate.getX() * scaling, coordinate.getY() * scaling, scaling, scaling);
            if (this.hitbox.intersects(coinCoordinate)) {
                iterator.remove();
                scoreCount += 10;
                break;
            }
        }
    }
    protected void checkHeart() {
            Rectangle coinCoordinate = new Rectangle(heartCoordinate.getX() * scaling, heartCoordinate.getY() * scaling, scaling, scaling);
            if (this.hitbox.intersects(coinCoordinate)) {
                if(this.healthPoints<=this.getMaximumHealthPoints()-10) {
                    this.healthPoints += 10;
                    takenHeart = true;
                }
            }
    }

    public boolean takenHeart(){
        return this.takenHeart;
    }


    public int getKeyCount(){
        return this.keyCount;
    }
    public int getSlainEnemies(){
        return this.slainEnemies;
    }
    public void setSlainEnemies(int totalSlainEnemies){
        this.slainEnemies = totalSlainEnemies;
    }
    public void setRoomSlainEnemies(int slainEnemies){
        this.roomSlainEnemies = slainEnemies;
    }
    public int getRoomSlainEnemies(){
        return this.roomSlainEnemies;
    }
    public int getScoreCount(){
        return this.scoreCount;
    }
    public void setRoomScoreCount(int score){
        this.roomScore = score;
    }
    public int getRoomScoreCount(){
        return this.roomScore;
    }
    public void setScoreCount(int totalScore){
        this.scoreCount = totalScore;
    }


}