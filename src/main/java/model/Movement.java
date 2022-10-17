package model;

import entity.Entity;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;
import view.Animation;
import view.WindowManager;

public class Movement {
   /* private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float playerSpeed = 2.0f;
    private Entity entity;
    private float x;
    private float y;*/

    public Movement(){

    }

    public float[] updatePosition(float x, float y, double speed, CardinalDirection dir) {
        x+= (float) speed * (float) dir.getHypothenuseReciprocal() * dir.getXOffset();
        y+= (float) speed * (float) dir.getHypothenuseReciprocal() * dir.getYOffset();

        /*
        if(this.x>477 && this.x<539) { //TODO: vad är dessa för specifika siffror?
            if(this.y>191 && this.y<223) {
//        		moving = false;
//        		return;
            }
        }
        */

        // om x eller y hamnar utanför skärmen.

        if(x < 0) {
            x=0;
        }
        else if(y < 0) {
            y = 0;
        }
        else if(x > 30 + GameConstants.GameSizes.WIDTH.getSize()) { // +30 eftersom entity har 30 i width, kanske att man ska ta in player.width hit också. Dock verkar skärmen inte gå så långt som den borde.
            x = 30 + GameConstants.GameSizes.WIDTH.getSize();
        }
        else if(y > GameConstants.GameSizes.HEIGHT.getSize()) {
            y = GameConstants.GameSizes.HEIGHT.getSize();
        }
        return new float[]{x,y};
    }
/*
    public void resetDirectionBooleans(){
        //System.out.println("TEST");
        left = false;
        up = false;
        right = false;
        down = false;
    }

    //public void update(){
    //     updatePosition();
    //}

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    */

}