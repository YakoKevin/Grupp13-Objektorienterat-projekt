package model.entity;


import model.CardinalDirection;
import model.Coordinate;

import java.awt.*;

public class AttackModel { //

    private int attackDamage;
    private int attackRange;
    private int coolDown;
    private final int COOL_DOWN_TIME = 15;

    public AttackModel(int attackDamage, int attackRange){
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
    }
    public Coordinate getAttackCoordinate(float atkOffSetX, float atkOffSetY, CardinalDirection dir, int width, int height){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad

        //TODO: ska lägga till för fyra andra riktningar
        if(dir == CardinalDirection.WEST) { //left
            atkOffSetX -= height;
        }
        else if(dir==CardinalDirection.NORTH){
            atkOffSetX-=width;
            atkOffSetY-=height;
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

        //Coordinate c = new Coordinate(atkOffSetX,atkOffSetY);
        //notifyObservers();
        return new Coordinate(0,0);
    }
    public Rectangle getAttackRectangle (Rectangle hitbox, CardinalDirection direction) {
        return new Rectangle(hitbox.x - hitbox.width + hitbox.width*direction.getXOffset(), hitbox.y - 15 + 30*direction.getYOffset(),attackRange, attackRange);
    }

    public float getAttackRange() {
        return attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public boolean coolDown() {
        if(coolDown > 0) {
            coolDown--;
            return false;
        }
        return true;
    }

    public void coolDownReset() {
        this.coolDown = COOL_DOWN_TIME;
    }
}
