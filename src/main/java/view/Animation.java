package view;

import entity.Entity;
import entity.Living;
import utilz.ImageServer;

import java.awt.*;
import java.util.ArrayList;

public class Animation {
    private static int animationSpeed = 30;

    private static ArrayList<LivingAnimation> livingEntites = new ArrayList<>();

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


    public static void render(Graphics g) {
        if (livingEntites != null) {
                for (LivingAnimation entityAnimation : livingEntites) {
                    updateAnimationTick(entityAnimation);
                    g.drawImage(entityAnimation.getImageGrid()[entityAnimation.getEntityState().ordinal()][entityAnimation.getAnimationIndex()], entityAnimation.getX(), entityAnimation.getY(), null); //TODO: redo so it works for everything and is static
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
