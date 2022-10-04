package model;

import entity.Player;

public class Movement {
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float playerSpeed = 2.0f;
    private Player player;
    private float x;
    private float y;

    public Movement(Player player){
        this.player = player;
    }

    private void updatePosition() {
        moving = false;

        if (left && !right) {
            x = player.getX() + playerSpeed;
            player.setX(x);
            moving = true;
        }
        else if (right && !left) {
            x = player.getX() - playerSpeed;
            player.setX(x);
            moving = true;
        }

        if (up && !down) {
            y = player.getY() - playerSpeed;
            player.setY(y);
            moving = true;
        }
        else if (down && !up) {
            y = player.getY() + playerSpeed;
            player.setY(y);
            moving = true;
        }


    }

    public void resetDirectionBooleans(){
        //System.out.println("TEST");
        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void update(){
        updatePosition();
    }

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
}
