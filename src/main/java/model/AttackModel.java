package model;

import controller.ActionController;
import entity.Entity;
import entity.Skeleton;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class AttackModel { //

    private int attackDamage;
    private float attackRange;

    public AttackModel(int attackDamage, float attackRange){
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
    }
    public Coordinate getAttackCoordinate(Coordinate coordinate, CardinalDirection dir){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        int atkOffSetX = (int)coordinate.getX();
        int atkOffSetY = (int)coordinate.getY();
        //animation.attacking = true;

        int width = 30; //Players storlek i x och// y
        int height = 100; // Kan lägga till detta som parameter ifall vi har olika storlek på entities
        //System.out.println("playerCoords: " + this.x+this.y);
        if(dir == CardinalDirection.WEST) { //left
            atkOffSetX -= height;
        }
        else if(dir==CardinalDirection.NORTH){
            atkOffSetX-=width;
            atkOffSetY-=height;

            //System.out.println("u");
        }
        else if(dir== CardinalDirection.SOUTH){
            atkOffSetX-=width;
            atkOffSetY+=height;
        }
        else if(dir==CardinalDirection.EAST){
            atkOffSetX+=width;
        }

        if(atkOffSetX<0){
            atkOffSetX=0;
        }
        if(atkOffSetY<0){
            atkOffSetY=0;
        }
        //if utanför rutan också, vet inte vad den ska har för storlek

        Coordinate c = new Coordinate(atkOffSetX,atkOffSetY);
        //notifyObservers();
        return c;
    }
    public Rectangle getAttackRectangle (Coordinate c, int atkR) {
        return new Rectangle((int)c.getX(),(int)c.getY(),atkR,atkR);
    }

    public float getAttackRange() {
        return attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}
