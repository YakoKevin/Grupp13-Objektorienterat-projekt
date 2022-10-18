package view;

import entity.Living;

import java.awt.*;
import java.util.ArrayList;

/***
 * This class handles the Animation part of the game.
 */
public class Animation {
    private static int animationSpeed = 7;
    private static ArrayList<LivingAnimation> livingEntites = new ArrayList<>();

    /***
     * This method updates what frame to use next of a Living.
     * @param entityAnimation The entity to get the next frame of.
     */
    public static void updateAnimationTick(LivingAnimation entityAnimation) {
        if (entityAnimation.getAnimationTick() >= animationSpeed) {
            entityAnimation.resetAnimationTick();

            if (entityAnimation.getAnimationIndex() >= entityAnimation.getEntityState().getAnimationSpriteAmount() - 1){
                entityAnimation.resetAnimationIndex();
            }else
                entityAnimation.addAnimationIndex();
        }
        entityAnimation.addAnimationTick();
    }


    /***
     * This method renders the new frame to use of a Living.
     * @param g The graphics for the game.
     */
    public static void render(Graphics g) {
        if (livingEntites != null) {
                for (LivingAnimation entityAnimation : livingEntites) {
                    updateAnimationTick(entityAnimation);
                    g.drawImage(entityAnimation.getImageGrid()[entityAnimation.getEntityState().ordinal()][entityAnimation.getAnimationIndex()],
                            entityAnimation.getX(), entityAnimation.getY(), 140, 150, null);
            }
        }

    }

    public static void clearEntities() {
        livingEntites.clear();
    }

    public static void addEntity(Living living) {
        livingEntites.add(new LivingAnimation(living));
    }
}
