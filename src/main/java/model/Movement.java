package model;

import entity.Entity;
import utilz.CardinalDirection;
import utilz.Coordinate;
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
    private Animation animation;
    private Animation animationEnemy;

    public Movement(Animation animation, Entity entity, Animation animationEnemy){
        //this.entity = entity;
        this.animation = animation;
        this.animationEnemy = animationEnemy;
    }

    public float[] updatePosition(float x, float y, double speed, CardinalDirection dir) {
        animation.moving = false;
        //System.out.println(entity.getX());
        /*
        if(this.x>477 && this.x<539) { //TODO: vad är dessa för specifika siffror?
            if(this.y>191 && this.y<223) {
//        		moving = false;
//        		return;
            }
        }*/
/*
        if(x < 0) {
            x=0;
        }
        else if(x > WindowManager.WIDTH) {
            x = WindowManager.WIDTH;
        }
        else if(y < 0) {
            y = 0;
        }
        else if(y > WindowManager.HEIGHT) {
            y = WindowManager.HEIGHT;

        }*/
        double diagonal = 2/Math.sqrt(2);
        if (dir==CardinalDirection.NORTHWEST) {
            speed /=diagonal;
            x -= speed;
            y -= speed;

            animation.moving = true;
        }
        if (dir==CardinalDirection.NORTHEAST) {
            speed /=diagonal;
            x += speed;
            y -= speed;

            animation.moving = true;
        }
        if (dir==CardinalDirection.SOUTHWEST) {
            speed /=diagonal;
            x -= speed;
            y += speed;

            animation.moving = true;
        }
        if (dir==CardinalDirection.SOUTHEAST) {
            speed /=diagonal;
            x += speed;
            y += speed;

            animation.moving = true;
        }


        if (dir==CardinalDirection.WEST) {
            x -= speed;

            animation.moving = true;
        }
        else if (dir==CardinalDirection.EAST) {
            x += speed;
            animation.moving = true;
        }

        if (dir==CardinalDirection.NORTH) {
            y -= speed;

            animation.moving = true;
        }
        else if (dir==CardinalDirection.SOUTH) {
            y += speed;
            animation.moving = true;
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