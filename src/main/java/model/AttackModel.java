package model;


import utilz.CardinalDirection;
import utilz.Coordinate;

import java.awt.*;

public class AttackModel { //

    private int attackDamage;
    private float attackRange;
    private int coolDown;
    private final int COOL_DOWN_TIME = 15;

    public AttackModel(int attackDamage, float attackRange){
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
    }
    public AttackModel(){
    }
    public Coordinate getAttackCoordinate(Coordinate coordinate, CardinalDirection dir, int width, int height){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        int atkOffSetX = coordinate.getX();
        int atkOffSetY = coordinate.getY();


        //TODO: ska ta lägga till för fyra andra riktningar
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

        Coordinate c = new Coordinate(atkOffSetX,atkOffSetY);
        //notifyObservers();
        return c;
    }
    public Rectangle getAttackRectangle (float x, float y) {
        return new Rectangle((int)x, (int)y,(int)attackRange, (int)attackRange);
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
