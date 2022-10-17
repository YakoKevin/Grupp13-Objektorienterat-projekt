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
    private static int animationSpeed = 30;

    private static HashMap<EntityAnimation, BufferedImage[][]> entities = new HashMap<>();

    public static void updateAnimationTick(EntityAnimation entityAnimation) {
        if (entityAnimation.getAnimationTick() >= animationSpeed) {
            entityAnimation.resetAnimationTick();

            if (entityAnimation.getAnimationIndex() >= entityAnimation.getEntityState().getAnimationSpriteAmount() - 1){
                entityAnimation.resetAnimationIndex();
            }else
                entityAnimation.addAnimationIndex();
        }
        entityAnimation.addAnimationTick();
    }

    public static void clearEntities() {
        entities.clear();
    }

    public static BufferedImage[][] loadAnimations(ImageServer.Ids ids) {
        BufferedImage[][] animations = new BufferedImage[6][3]; //TODO: add into a constant instead to avoid miss-match errors
        for (int row = 0; row < animations.length; row++) {
            for (int column = 0; column < animations[row].length; column++) {
                animations[row][column] = ImageServer.getImage(ids).getSubimage(row * 40, column * 80, 30, 80);
            }
        }
        return animations;
    }

    public static void render(Graphics g) {
        if (entities != null) {
                for (EntityAnimation entityAnimation : entities.keySet()) {
                    updateAnimationTick(entityAnimation);
                    g.drawImage(entities.get(entityAnimation)[entityAnimation.getEntityState().ordinal()][entityAnimation.getAnimationIndex()], entityAnimation.getX(), entityAnimation.getY(), null); //TODO: redo so it works for everything and is static
            }
        }

    }

    public static void addEntity(Entity entity, ImageServer.Ids imageId) {
        entities.put(new EntityAnimation(entity), loadAnimations(imageId));

    }
}
