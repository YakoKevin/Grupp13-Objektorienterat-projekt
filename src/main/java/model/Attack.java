package model;

import utilz.Coordinate;
import utilz.CardinalDirection;


public class Attack {
    public static Coordinate getAttackCoordinate(Coordinate coordinate, CardinalDirection dir){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        int atkOffSetX = coordinate.getX();
        int atkOffSetY = coordinate.getY();
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
        else if(dir==CardinalDirection.SOUTH){
            atkOffSetX-=width;
            atkOffSetY+=height;
        }
        else if(dir==CardinalDirection.EAST){
            atkOffSetX+=width;
        }

        if(atkOffSetX<0){
            atkOffSetY=0;
        }
        if(atkOffSetY<0){
            atkOffSetY=0;
        }
        //if utanför rutan också, vet inte vad den ska har för storlek

        Coordinate c = new Coordinate(atkOffSetX,atkOffSetY);
        //notifyObservers();
        return c;
    }
/*
    public void drawAttackHitbox(Graphics g){

        Rectangle rect = new Rectangle(atkOffSetX,atkOffSetY,100,100);

        g.drawRect(atkOffSetX,atkOffSetY,100,100);
        setAttackRectangle(rect);
    }*/
}
