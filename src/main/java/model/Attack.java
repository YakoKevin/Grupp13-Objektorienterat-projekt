package model;

import controller.ActionController;
import entity.Entity;
import entity.Skeleton;
import utilz.Coordinate;
import utilz.CardinalDirection;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Attack {
    private final Animation animation;
    List<IObserver> iObservers;

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

    public void attack(Coordinate coordinate, CardinalDirection dir){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        atkOffSetCoordX = coordinate.getX();
        atkOffSetCoordY = coordinate.getY();
        animation.attacking = true;

        double width = 30; //Players storlek i x och// y
        double height = 100;
        //System.out.println("playerCoords: " + this.x+this.y);
        if(dir == CardinalDirection.WEST){ //left
            setAtkOffSetCoordX(coordinate.getX()-height); //beror på hur stor spelaren är och riktning
            setAtkOffSetCoordY(coordinate.getY());
            // System.out.println("v");
        }
        else if(dir==CardinalDirection.NORTH){
            setAtkOffSetCoordX(coordinate.getX()-width);
            setAtkOffSetCoordY(coordinate.getY()-height);

            //System.out.println("u");
        }
        else if(dir==CardinalDirection.SOUTH){
            setAtkOffSetCoordX(coordinate.getX()-width);
            setAtkOffSetCoordY(coordinate.getY()+height);
            //System.out.println("n");
        }
        else if(dir==CardinalDirection.EAST){
            setAtkOffSetCoordX(coordinate.getX()+width);
            setAtkOffSetCoordY(coordinate.getY());
            //System.out.println("h");
        }
        else{setAtkOffSetCoordX(coordinate.getX());
            setAtkOffSetCoordY(coordinate.getY());}

        if(getAtkOffSetX()<0){setAtkOffSetCoordX(0);}
        if(getAtkOffSetY()<0){setAtkOffSetCoordY(0);}
        //if utanför rutan också, vet inte vad den ska har för storlek
        //sk.checkedIfIsAttacked(coordinate, dir); //få till det med observer bara, byt ut coordinate

        //notifyObservers();
    }

    public void setAtkOffSetCoordX(double atkX){this.atkOffSetCoordX=atkX;}
    public void setAtkOffSetCoordY(double atkY){this.atkOffSetCoordY=atkY;}

    public Rectangle2D getRectangle() {
        return this.rect2D;
    }
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
