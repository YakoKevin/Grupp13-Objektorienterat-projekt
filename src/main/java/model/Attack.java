package model;

import controller.ActionController;
import entity.Entity;
import entity.Skeleton;
import utilz.Coordinate;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Attack {
    private final Animation animation;
    List<IObserver> iObservers;
    Skeleton sk =new Skeleton(50,50); //tillfälligt
    //private Rectangle2D rect2D = new Rectangle2D.Double(getX(),getY(),100,100);
    private Rectangle2D rect2D = new Rectangle2D.Double(0, 0,100,100);

    private double atkOffSetCoordX;
    private double atkOffSetCoordY;

    public Attack(Animation animation){
        this.animation = animation;
    }

    public double getAtkOffSetX(){ //kanske inte så fint
        return atkOffSetCoordX;
    }

    public double getAtkOffSetY(){
        return atkOffSetCoordY;
    }

    public void attack(Coordinate coordinate){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        atkOffSetCoordX = coordinate.getX();
        atkOffSetCoordY = coordinate.getY();
        animation.attacking = true;

        double playerWidth = 30; //Players storlek i x och// y
        double playerHeight = 100;
        //System.out.println("playerCoords: " + this.x+this.y);
        if(ActionController.dir ==0){ //left
            setAtkOffSetCoordX(coordinate.getX()-playerHeight); //beror på hur stor spelaren är och riktning
            setAtkOffSetCoordY(coordinate.getY());
            // System.out.println("v");
        }
        else if(ActionController.dir==2){
            setAtkOffSetCoordX(coordinate.getX()-playerWidth);
            setAtkOffSetCoordY(coordinate.getY()-playerHeight);

            //System.out.println("u");
        }
        else if(ActionController.dir==3){
            setAtkOffSetCoordX(coordinate.getX()-playerWidth);
            setAtkOffSetCoordY(coordinate.getY()+playerHeight);
            //System.out.println("n");
        }
        else if(ActionController.dir==1){
            setAtkOffSetCoordX(coordinate.getX()+playerWidth);
            setAtkOffSetCoordY(coordinate.getY());
            //System.out.println("h");
        }
        else{setAtkOffSetCoordX(coordinate.getX());
            setAtkOffSetCoordY(coordinate.getY());}

        if(getAtkOffSetX()<0){setAtkOffSetCoordX(0);}
        if(getAtkOffSetY()<0){setAtkOffSetCoordY(0);}
        sk.checkedIfIsAttacked(coordinate, coordinate); //få till det med observer bara, byt ut coordinate

        //notifyObservers();
    }

    public double getAttackRange() {
        return 20; //tillfälligt så här
    }

    public void setAtkOffSetCoordX(double atkX){this.atkOffSetCoordX=atkX;}

    public void setAtkOffSetCoordY(double atkY){this.atkOffSetCoordY=atkY;}

    public void setAttackRectangle(Rectangle2D r){
        rect2D=r;
    }
    public Rectangle2D getAttackRectangle(){
        return this.rect2D;
    }
    public void drawAttackHitbox(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        double atkX = getAtkOffSetX();
        double atkY = getAtkOffSetY();
        Rectangle2D rect = new Rectangle2D.Double(atkX,atkY,100,100);
        g2.draw(rect);
        setAttackRectangle(rect);
    }
}
