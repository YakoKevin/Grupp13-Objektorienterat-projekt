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
        super(startCoordinate, width, height, new AttackModel(25, 100));
        this.setHealthPoints(100);
        this.setMovementSpeed(6);
    }

    /**
     * Getting animation
     * @return
     */
    @Override
    public ImageServer.AnimationIds getAnimationId() {
        return identification;
    }

    /**
     * Attacking hostiles, if in range
     */
    public void attack() {
        Rectangle attackRectangle = attack.getAttackRectangle(hitbox, dir);
        for (Hostile hostile : hostiles) {
            hostile.getHit(attackRectangle, attack.getAttackDamage());
        }
    }

    /**
     * Adding hostiles to player. Getting from level
     * @param hostile
     */
    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }

    /**
     * Player checking if it can move, if it is close to keys, coins or a heart object.
     */
    @Override
    public void tickExtra() {
        checkLivingMovement();
        checkKeys();
        checkCoins();
        if(!takenHeart){
            checkHeart();
        }

    }

    /**
     * Getting the death image.
     * @return
     */
    @Override
    public ImageServer.DeathId getDeadId() {
        return deathIdentification;
    }

    /**
     * Getting the key coordinates from level.
     * @param keyCoordinates
     */
    public void giveKeyList(ArrayList<Coordinate> keyCoordinates) {
        this.keysCoordinates = keyCoordinates;
    }

    /**
     * Getting coin coordinates from level.
     * @param coinCoordinates
     */

    public void giveCoinList(ArrayList<Coordinate> coinCoordinates){
        this.coinCoordinates = coinCoordinates;
    }

    /**
     * Getting heart object from level, one per room
     * @param hpCoord
     */
    public void giveHeartObject(Coordinate hpCoord){
        this.heartCoordinate = hpCoord;
    }

    /**
     * Checking if close to key, if so, key is added to key count and removed from key list.
     */
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
    /**
     * Checking if close to coin, if so, key is added to key count and removed from coin list.
     */
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

    /**
     * Cheking if heart is close to player.
     */
    protected void checkHeart() {
            Rectangle coinCoordinate = new Rectangle(heartCoordinate.getX() * scaling, heartCoordinate.getY() * scaling, scaling, scaling);
            if (this.hitbox.intersects(coinCoordinate)) {
                if(this.healthPoints<=this.getMaximumHealthPoints()-10) {
                    this.healthPoints += 10;
                    takenHeart = true;
                }
            }
    }

    /**
     * Heart taken
     * @return
     */
    public boolean takenHeart(){
        return this.takenHeart;
    }

    public void setHeartTaken(boolean takenHeart){
        this.takenHeart = takenHeart;
    }
    /**
     * Getting how many keys
     * @return
     */
    public int getKeyCount(){
        return this.keyCount;
    }
    /**
     * Getting how many enemies killed
     * @return
     */
    public int getSlainEnemies(){
        return this.slainEnemies;
    }

    /**
     * Setting total slain enemies
     * @param totalSlainEnemies
     */
    public void setSlainEnemies(int totalSlainEnemies){
        this.slainEnemies = totalSlainEnemies;
    }

    /**
     * Setting slain enemies in this room
     * @param slainEnemies
     */
    public void setRoomSlainEnemies(int slainEnemies){
        this.roomSlainEnemies = slainEnemies;
    }

    /**
     * Get slain enemies in this room
     * @return
     */
    public int getRoomSlainEnemies(){
        return this.roomSlainEnemies;
    }

    /**
     * Get total score count
     * @return
     */
    public int getScoreCount(){
        return this.scoreCount;
    }

    /**
     * Setting score count for this room
     * @param score
     */
    public void setRoomScoreCount(int score){
        this.roomScore = score;
    }

    /**
     * Getting room score count
     * @return
     */
    public int getRoomScoreCount() {
        return this.roomScore;
    }

    /**
     * Set total score
     * @param totalScore
     */
    public void setScoreCount(int totalScore){
        this.scoreCount = totalScore;
    }


}