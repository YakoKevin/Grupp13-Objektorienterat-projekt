package entity;

import controller.ActionController;
import model.IObservable;
import model.IObserver;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import static utilz.EntityStates.*;
import static utilz.EntityStates.PlayerStates.*;


public class Player extends Entity implements IObservable {
    private int playerDirection = -1;
    private boolean attacking = false;
    private int[][] levelData;
    private double atkOffSetCoordX = this.getX(), atkOffSetCoordY = this.getY();
    private Rectangle2D rect2D = new Rectangle2D.Double(getX(),getY(),100,100);

    private int keyCount;

    public double getAtkOffSetX(){ //kanske inte så fint
        return atkOffSetCoordX;
    }

    public double getAtkOffSetY(){
        return atkOffSetCoordY;
    }


    @Override
    public double getAttackRange() {
        return 20; //tillfälligt så här
    }

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        this.setAttackPoints(20);
        this.healthPoints=100;
        this.keyCount = 0;
        //super(100,20,0,5, 10);
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    public void update(){
        updateHitbox();
    }

    public void render(Graphics g){
        drawHitbox(g);
    }




    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    public boolean getAttack(){
        return attacking;
    }

    public void setAtkOffSetCoordX(double atkX){this.atkOffSetCoordX=atkX;}

    public void setAtkOffSetCoordY(double atkY){this.atkOffSetCoordY=atkY;}

    List<IObserver> iObservers;
    Skeleton sk =new Skeleton(50,50); //tillfälligt


    public void attack(){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        double playerWidth = 30; //Players storlek i x och// y
        double playerHeight = 100;
        //System.out.println("playerCoords: " + this.x+this.y);
        if(ActionController.dir ==0){ //left
            setAtkOffSetCoordX(this.getX()-playerHeight); //beror på hur stor spelaren är och riktning
            setAtkOffSetCoordY(this.getY());
           // System.out.println("v");
        }
        else if(ActionController.dir==2){
            setAtkOffSetCoordX(this.getX()-playerWidth);
            setAtkOffSetCoordY(this.getY()-playerHeight);

            //System.out.println("u");
        }
        else if(ActionController.dir==3){
            setAtkOffSetCoordX(getX()-playerWidth);
            setAtkOffSetCoordY(getY()+playerHeight);
            //System.out.println("n");
        }
        else if(ActionController.dir==1){
            setAtkOffSetCoordX(getX()+playerWidth);
            setAtkOffSetCoordY(getY());
            //System.out.println("h");
        }
        else{setAtkOffSetCoordX(getX());
            setAtkOffSetCoordY(getY());}

        if(getAtkOffSetX()<0){setAtkOffSetCoordX(0);}
        if(getAtkOffSetY()<0){setAtkOffSetCoordY(0);}
        sk.update(this); //få till det med observer bara

        //notifyObservers();
    }

    public void drawAttackHitbox(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        double atkX = getAtkOffSetX();
        double atkY = getAtkOffSetY();
        Rectangle2D rect = new Rectangle2D.Double(atkX,atkY,100,100);
        g2.draw(rect);
        setAttackRectangle(rect);
    }
    public void drawHP(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String hpStr = Double.toString(getHealthPoints());
        g2.drawString("HP: " + hpStr,10,10);
        g2.drawString("KEYS: " + this.keyCount, 10,30);
    }
    public void setAttackRectangle(Rectangle2D r){
        rect2D=r;
    }
    public Rectangle2D getAttackRectangle(){
        return this.rect2D;
    }


    public boolean checkIfInRange(Enemy enemy) {
        double enX = enemy.getX();
        double enY = enemy.getY();
        /*(x,y) is inside the rectangle with coordinates (x1,y1,x2,y2)

        x <= x2 && x >= x1 && y <= y2 && y >= y1;
*/
        boolean check = enX<=100 && enX>= getAtkOffSetX() && enY <= 100 && enY>=getAtkOffSetY();
        if(check==true){
            return true;
        }
        //System.out.println("enemy coord: " +enX + enY);
        if(getAttackRectangle().contains(enX,enY)){
            return true;
        }
        return false;
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

    public void addKey()
    {
        keyCount++;
    }
}
