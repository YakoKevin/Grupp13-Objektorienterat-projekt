package view;

import entity.Entity;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static utilz.EntityStates.PlayerStates.*;

public class Animation implements Animator {
    private static BufferedImage[][] animations;
    private static int animationTick = 30;
    private static int animationIndex = 0;
    private static int animationSpeed = 30;
    private static EntityStates.PlayerStates playerAction = IDLE;

    private BufferedImage image;
    private static HashMap<Entity, ImageServer.Ids> entities;
    public boolean moving = false;
    public boolean attacking = false;

    public Animation(){// TODO: Logik i klassen? Separera det då!!
        loadAnimations();
    }

    public static void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            /*if (animationIndex >= playerAction.getAnimationSpriteAmount()){
                animationIndex = 0;
            }*/
        }
    }

    //TODO: inte super viktigt, men borde gå att ta bort boolean:en för moving eftersom vi har enums för player state så
    // slipper vi denna if-sats radda.
    public static void setAnimation() {
        /*EntityStates.PlayerStates startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK;

        if (startAnimation != playerAction)
            resetAnimationTick();*/
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

    public static void render(Graphics g){
        if(entities != null) {
            for (Entity entity : entities.keySet()) {
                g.drawImage(animations[playerAction.ordinal()][animationIndex], (int) entity.getX(), (int) entity.getY(), null); //TODO: redo so it works for everything and is static!
            }
        }

    }

    @Override
    public void addEntity(Entity entity, ImageServer.Ids imageId) {
        this.entities.put(entity, imageId);
    }
}
