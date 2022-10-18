package view;

import entity.Entity;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.EntityStates.PlayerStates.*;

public class Animation {
    private BufferedImage[][] animations;
    private int animationTick = 20;
    private int animationIndex = 0;
    private int animationSpeed = 20;
    private EntityStates.PlayerStates playerAction = IDLE;

    private BufferedImage image;
    private Entity entity;
    public boolean moving = false;
    public boolean attacking = false;

    public Animation(ImageServer.Ids imageServer, Entity entity){// TODO: Logik i klassen? Separera det då!!
        this.image = ImageServer.getImage(imageServer);
        this.entity = entity;
        loadAnimations();
    }

    public void updateAnimationTick() {
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

    public void setAnimation() {
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
        animations = new BufferedImage[2][10];
        for (int row = 0; row < animations.length; row++){
            for (int column = 0; column < animations[row].length; column++){
                animations[row][column] = image.getSubimage(row * 130, column*120, 30, 80);
            }
        }
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction.ordinal()][animationIndex], (int) entity.getX(), (int) entity.getY(), null);

    }
}
