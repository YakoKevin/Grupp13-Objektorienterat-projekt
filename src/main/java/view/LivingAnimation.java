package view;

import entity.Entity;
import entity.Living;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.image.BufferedImage;

public class LivingAnimation {
    private BufferedImage[][] imageGrid;
    private Living living;
    private int animationIndex;
    private int animationTick;

    LivingAnimation(Living living){
        this.living = living;
        setImageGrid(living.getAnimationId());
    }

    private void setImageGrid(ImageServer.AnimationIds id){
        this.imageGrid = ImageServer.getImageGrid(id);
    }

    public void addAnimationTick(){
        this.animationTick++;
    }

    public void addAnimationIndex(){
        this.animationIndex++;
    }

    public void resetAnimationIndex(){
        this.animationIndex = 0;
    }

    public void resetAnimationTick() {
        this.animationTick = 0;
    }

    public int getAnimationIndex(){
        return animationIndex;
    }

    public int getAnimationTick(){
        return animationTick;
    }

    public Entity getEntity(){
        return living;
    }

    public EntityStates getEntityState() {
        return living.getState();
    }

    public int getX(){
        return (int)living.getX();
    }

    public int getY(){
        return (int)living.getY();
    }

    public BufferedImage[][] getImageGrid() {
        return imageGrid;
    }
}
