package view;

import entity.Entity;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

import static utilz.EntityStates.IDLE;

public class Animation {
    private BufferedImage[][] animations;
    private static int animationTick = 30;
    private static int animationIndex = 0;
    private static int animationSpeed = 30;

    private static BufferedImage image; // TODO: LISTA AV IMAGES?
    private static HashMap<Entity, BufferedImage[][]> entities = new HashMap<>();
    public boolean moving = false;
    public boolean attacking = false;
/*
    public static void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            /*if (animationIndex >= playerAction.getAnimationSpriteAmount()){
                animationIndex = 0;
            }
        }
    }*/

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

    public static void clearEntities() {
        entities.clear();
    }

    public static void loadImages() {
        image = ImageServer.getImage(ImageServer.Ids.PLAYER);
    }

    private void resetAnimationTick() {
        animationIndex = 0;
        animationTick = 0;
    }

    public static BufferedImage[][] loadAnimations(ImageServer.Ids ids) {
        BufferedImage[][] animations = new BufferedImage[6][3];
        for (int row = 0; row < animations.length; row++) {
            for (int column = 0; column < animations[row].length; column++) {
                animations[row][column] = ImageServer.getImage(ids).getSubimage(row * 40, column * 80, 30, 80);
            }
        }
        return animations;
    }

    public static void render(Graphics g) {


        if (entities != null) {
                for (Entity entity : entities.keySet()) {
                    g.drawImage(entities.get(entity)[entity.getState().ordinal()][0], (int) entity.getX(), (int) entity.getY(), null); //TODO: redo so it works for everything and is static
            }
            for (Entity entity : entities.keySet()){
                g.setColor(new Color(0x00ff0000));
                g.drawRect((int)entity.getX(), (int)entity.getY(), 10, 10);
            }
        }

    }

    public static void addEntity(Entity entity, ImageServer.Ids imageId) {
        entities.put(entity, loadAnimations(imageId));

    }
}
