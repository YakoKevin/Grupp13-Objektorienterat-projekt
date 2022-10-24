package model.entity;

import model.Coordinate;
import model.GameConstants;
import view.ImageServer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Entity class for all "living" entities.
 */
public abstract class Living extends Entity implements Attackable{
    protected boolean isAlive = true;
    protected LivingStates state = LivingStates.IDLE;
    protected AttackModel attack;
    protected double movementSpeed;
    private double maximumHealthPoints;
    private ArrayList<Coordinate> obstructionCoordinates = new ArrayList<>();
    private ArrayList<Coordinate> keysCoordinates = new ArrayList<>();
    protected double healthPoints;

    /**
     * Setting living object's initial values
     * @param startCoordinate
     * @param width
     * @param height
     * @param attackModel
     */
    public Living(Coordinate startCoordinate, int width, int height, AttackModel attackModel) {
        super(startCoordinate, width, height);
        this.attack = attackModel;
        this.healthPoints = 100;
        this.setMaximumHealthPoints(100);
    }

    /**
     * Checking if colliding with walls and obstacles
     * @return
     */
    public boolean checkCollision(){
        for (Coordinate obsCoord : obstructionCoordinates){
            Rectangle r = new Rectangle(obsCoord.getX(),obsCoord.getY(),42,42);
            if(this.hitbox.contains(r)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checking if inside the playable area
     * @return
     */
    public boolean checkIfInScreen(){
        if(finePositionX> GameConstants.GameSizes.WIDTH.getSize()-this.width){
            return true;
        }
        if(finePositionY>GameConstants.GameSizes.HEIGHT.getSize()-this.height){
            return true;
        }
        if(finePositionX<0){
            return true;
        }
        if(finePositionY<0){
            return true;
        }
        return false;
    }

    /**
     * Getting attacked of another entity, based on the attack area and attack points.
     * @param atkRect
     * @param atkP
     */
    public void getHit(Rectangle atkRect, double atkP) {
        if(this.state==LivingStates.DEAD){
            return;
        }
        if(isAlive && atkRect.intersects(this.hitbox)){
            this.setHealthPoints(this.healthPoints-atkP);
        }
        if(this.healthPoints<=0){
            this.setAlive(false);
            this.state = LivingStates.DEAD;
        }
    }

    /**
     * Setting movement speed of living.
     * @param speed
     */
    public void setMovementSpeed(double speed){
        this.movementSpeed=speed;
    }

    /**
     * Getting movement speed of living.
     * @return
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }


    /**
     * Setting health points
     * @param hp
     */
    public void setHealthPoints(double hp){
        this.healthPoints=hp;
    }

    /**
     * Getting health points
     * @return
     */
    public double getHealthPoints() {
        return healthPoints;
    }

    /**
     * Getting maximum health points
     * @return
     */
    public double getMaximumHealthPoints(){
        return this.maximumHealthPoints;
    }

    /**
     * Setting maximum health points
     * @param maximumHealthPoints
     */
    public void setMaximumHealthPoints(double maximumHealthPoints) {
        this.maximumHealthPoints = maximumHealthPoints;
    }

    /**
     * Setting alive status true or false
     * @param aliveStatus
     */
    protected void setAlive(boolean aliveStatus){
        this.isAlive=aliveStatus;
    }

    /**
     * Getting obstruction coordinates from level, objects hindering movement.
     * @param obstructionCoordinates
     */
    public void giveObstructionList(ArrayList<Coordinate> obstructionCoordinates){
        this.obstructionCoordinates = obstructionCoordinates;
    }

    /**
     * Getting key coordinates from level.
     * @param keyCoordinates
     */
    public void giveKeyList(ArrayList<Coordinate> keyCoordinates){
        this.keysCoordinates = keyCoordinates;
    }

    /**
     * Get attack object.
     * @return
     */
    public AttackModel getAttack(){
        return attack;
    }

    /**
     * Get attack range
     * @return
     */
    public double getAttackRange(){
        return attack.getAttackRange();
    }

    /**
     * Get animation image ids
     * @return
     */
    public abstract ImageServer.AnimationIds getAnimationId();

    /**
     * Get the death image
     * @return
     */
    public abstract ImageServer.DeathId getDeadId();

    /**
     * Get attack area, as a rectangle
     * @return
     */
    public Rectangle getAttackRect(){
        return attack.getAttackRectangle(hitbox,dir);
    }

    /**
     * Checking if living is alive
     * @return
     */
    public boolean isAlive(){
        return isAlive;
    }

    /**
     * Checking if living can move.
     */
    protected void checkLivingMovement() {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        float tmpX = finePositionX + velX;
        float tmpY = finePositionY + velY;
        boolean intersect = false;
        Rectangle playerPosition = new Rectangle((int)tmpX, (int)tmpY, width, height);
        for (Coordinate coordinate : obstructionCoordinates) {
            Rectangle obsCoordinate = new Rectangle(coordinate.getX()*scaling, coordinate.getY()*scaling, scaling, scaling);
            if(playerPosition.intersects(obsCoordinate)) {
                intersect = true;
                break;
            }
        }

        if(tmpX> GameConstants.GameSizes.WIDTH.getSize()){
            finePositionX = GameConstants.GameSizes.WIDTH.getSize();
        }else if(tmpY>GameConstants.GameSizes.HEIGHT.getSize()-50){
            finePositionY =GameConstants.GameSizes.HEIGHT.getSize()-50;
        }else if(tmpX<0){
            finePositionX =0;
        }else if(tmpY<0){
            finePositionY = 0;
        }else if(intersect){

        }else{
            finePositionX = tmpX;
            finePositionY = tmpY;
        }

    }

    /**
     * Setting living state of living.
     * @param state
     */
    public void setState(LivingStates state){
        this.state = state;
    }

    /**
     * Getting living state of living.
     * @return
     */
    public LivingStates getState() {
        return state;
    }

}