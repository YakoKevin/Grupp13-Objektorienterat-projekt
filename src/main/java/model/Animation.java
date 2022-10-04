package model;

import entity.Entity;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.EntityStates.PlayerStates.*;

public class Animation {
    private BufferedImage[][] animations;
    private int animationTick = 30;
    private int animationIndex = 0;
    private int animationSpeed = 30;
    private EntityStates.PlayerStates playerAction = IDLE;
    private BufferedImage image;
    private Entity entity;
    public boolean moving = false;
    public boolean attacking = false;

    public Animation(ImageServer.Ids imageServer, Entity entity){
        this.image = ImageServer.getImage(imageServer);
        this.entity = entity;
        loadAnimations();
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.getAnimationSpriteAmount()){
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        EntityStates.PlayerStates startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK;

        if (startAnimation != playerAction)
            resetAnimationTick();
    }

    private void resetAnimationTick() {
        animationIndex = 0;
        animationTick = 0;
    }

    private void loadAnimations() {
        animations = new BufferedImage[6][3];
        for (int row = 0; row < animations.length; row++){
            for (int column = 0; column < animations[row].length; column++){
                animations[row][column] = image.getSubimage(row*40, column*80, 30, 80);
            }
        }
    }

    public void update(){
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction.ordinal()][animationIndex], (int) entity.getX(), (int) entity.getY(), null);
    }
}
