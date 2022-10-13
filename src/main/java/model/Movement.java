package model;

import entity.Entity;
import view.Animation;
import view.WindowManager;

public class Movement {
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float playerSpeed = 2.0f;
    private Entity entity;
    private float x;
    private float y;
    private Animation animation;
    private Animation animationEnemy;

    public Movement(Animation animation, Entity entity, Animation animationEnemy){
        this.entity = entity;
        this.animation = animation;
        this.animationEnemy = animationEnemy;
    }

    public void updatePosition() { //TODO: retunera en ny Coordinate ist. och kalla på denna metod från entitin ist.
        animation.moving = false;
        //System.out.println(entity.getX());

        if(this.x>477 && this.x<539) { //TODO: vad är dessa för specifika siffror?
            if(this.y>191 && this.y<223) {
//        		moving = false;
//        		return;
            }
        }

        if(entity.getX() < 0) {
            entity.setX(0);
            return;
        }else if(entity.getX() > WindowManager.WIDTH) {
            entity.setX(WindowManager.WIDTH);
            return;
        }else if(entity.getY() < 0) {
            entity.setY(0);
            return;
        }else if(entity.getY() > WindowManager.HEIGHT) {
            entity.setY(WindowManager.HEIGHT);
            return;
        }

        if (left && !right) {
            x = entity.getX() + playerSpeed;
            entity.setX(x);
            animation.moving = true;
        }
        else if (right && !left) {
            x = entity.getX() - playerSpeed;
            entity.setX(x);
            animation.moving = true;
        }

        if (up && !down) {
            y = entity.getY() - playerSpeed;
            entity.setY(y);
            //animation.moving = true;
        }
        else if (down && !up) {
            y = entity.getY() + playerSpeed;
            entity.setY(y);
            animation.moving = true;
        }


    }

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
}