package entity;

import controller.ActionController;
import model.Animation;
import model.IObservable;
import model.IObserver;
import model.Movement;
import utilz.EntityStates;
import utilz.ImageServer;
import model.level.Level;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.Animation;
import entity.Hostile;

import javax.swing.*;

import static utilz.EntityStates.*;
import static utilz.EntityStates.PlayerStates.*;


public class Player extends Entity implements IObservable, HostileAttacker {
    private int playerDirection = -1;
    private boolean attacking = false;
    private int[][] levelData;
    private double atkOffSetCoordX = this.getX(), atkOffSetCoordY = this.getY();
    private Rectangle2D rect2D = new Rectangle2D.Double(getX(),getY(),100,100);
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private Skeleton sk= new Skeleton(50,50);

    private int skelX=0,skelY=0;

    private int keyCount;
    Animation animation;
    Movement movement;

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        this.setAttackPoints(20);
        this.healthPoints=100;
        this.keyCount = 0;
        animation = new Animation(ImageServer.Ids.PLAYER, this);
        movement = new Movement(this, animation);
        //super(100,20,0,5, 10);
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    public Movement getMovement() {
        return movement;
    }

    public void update(){
        skelX=Skeleton.cx;
        skelY=Skeleton.cy;
        updateHitbox();
        if(checkIfHitByAttacker(sk)==true){ //kanske inte är härifrån som man kollar detta
            this.setHealthPoints(this.getHealthPoints()-sk.getAttackPoints());
        }

        movement.updatePosition();
        animation.update();

        animation.updateAnimationTick();
        animation.setAnimation();
        checkAttack(skelX,skelX);
    }

    private void checkAttack(int xx,int yy) {
        if(this.x==xx && this.x<xx+10) {
            setHealthPoints(getHealthPoints()-5);
        }
        else if(this.y==yy && this.y<yy+10) {
            setHealthPoints(getHealthPoints()-5);
        }
    }

    public void render(Graphics g){
        drawHitbox(g);
        animation.render(g);
    }


    List<IObserver> iObservers;
    public void drawHP(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String hpStr = Double.toString(getHealthPoints());
        g2.drawString("HP: " + hpStr,10,10);
        g2.drawString("KEYS: " + this.keyCount, 10,30);
    }


    public boolean checkIfInRange(Enemy enemy) {
        double enX = enemy.getX();
        double enY = enemy.getY();
        /*(x,y) is inside the rectangle with coordinates (x1,y1,x2,y2)

        x <= x2 && x >= x1 && y <= y2 && y >= y1;
*/
        //boolean check = enX<=100 && enX>= getAtkOffSetX() && enY <= 100 && enY>=getAtkOffSetY();
        //if(check==true){
         //   return true;
       // }
        //System.out.println("enemy coord: " +enX + enY);
       // if(getAttackRectangle().contains(enX,enY)){
        ////    return true;
       // }
        return false;
    }

    //Method checks if attacker has walked into player, i.e. their hitboxes allign
    public boolean checkIfHitByAttacker(Enemy en){ //ska vara en lista med enemies som kommer från level här sen
        double lPx = this.getX(); //think two rectangles, top left and bottom right coordinates for player and enemy
        double lPy = this.getY();
        double lEx = en.getX();
        double lEy= en.getY();
        double rPx = this.getX()+this.getWidth();
        double rEx = lEx + en.getWidth();
        double rEy = lEy + en.getHeight(); //kan nog städa upp variablerna lite här och (kanske namnge annorlunda)

        if(lPx > rEx||lEx>rPx){ //if any rectangle is on the left side of the other one
            return false;
        }
        if(rEx>lPx||rEy>lEy){
            return false;
        }
        return true;
    }

    public void addObserver(IObserver obs) {
        iObservers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        iObservers.remove(obs);
    }

    public void notifyObservers() {
        for(IObserver IObserver: iObservers){
            IObserver.update();
        }
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void addKey()
    {
        keyCount++;
    }

    @Override
    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }
}
