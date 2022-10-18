package view;

import entity.Entity;
import entity.Living;
import utilz.EntityStates;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LivingAnimation {
    private BufferedImage[][] imageGrid;
    private BufferedImage imageDead;
    private Living living;
    private int animationIndex;
    private int animationTick;
    private int flip;

    LivingAnimation(Living living){
        this.living = living;
        setImageGrid(living.getAnimationId());
        setDeathImage(living.getDeadId());
    }

    private void setDeathImage(ImageServer.DeathId deadId) {
        this.imageDead = ImageServer.getImage(deadId);
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

    public Living getEntity(){
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

    public int getFlip() {
        if(living.getDirection().getXOffset() == 0)
            return flip;
        return this.flip = living.getDirection().getXOffset();
    }

    public int getFlipAddition() {
        if(getFlip() < 0)
            return 53;
        return -12;
    }

    public BufferedImage getDeadImage() {
        return imageDead;
    }
}
