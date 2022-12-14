package view;

import model.entity.Living;

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
     * @param entityAnimation The model.entity to get the next frame of.
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
                for (LivingAnimation livingAnimation : livingEntites) {
                    if(livingAnimation.getEntity().isAlive()) {
                        updateAnimationTick(livingAnimation);
                        g.drawImage(livingAnimation.getImageGrid()[livingAnimation.getEntityState().ordinal()][livingAnimation.getAnimationIndex()],
                                livingAnimation.getX() + livingAnimation.getFlipAddition(), livingAnimation.getY() - 30, livingAnimation.getFlip() * 140, 150, null);
                    } else
                        g.drawImage(livingAnimation.getDeadImage(), livingAnimation.getX() - 10, livingAnimation.getY() + 20, 50, 50, null);
            }
        }

    }

    /***
     * This method clears all the entities by using the
     * method defined in LivingAnimation.
     */
    public static void clearEntities() {
        livingEntites.clear();
    }

    /***
     * This method adds an entity by using the
     * method defind in LivingAnimation.
     * @param living The entity to spawn, has to be a Living.
     */
    public static void addEntity(Living living) {
        livingEntites.add(new LivingAnimation(living));
    }
}
