package view;

import model.entity.Living;
import model.entity.LivingStates;

import java.awt.image.BufferedImage;

/***
 * This class handles the animation of all Living.
 */
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

    /***
     * Sets the image of Living when HP reaches 0.
     * @param deadId
     */
    private void setDeathImage(ImageServer.DeathId deadId) {
        this.imageDead = ImageServer.getImage(deadId);
    }

    private void setImageGrid(ImageServer.AnimationIds id){
        this.imageGrid = ImageServer.getImageGrid(id);
    }

    /***
     * Increments the animationTick variable.
     */
    public void addAnimationTick(){
        this.animationTick++;
    }

    /***
     * Increments the animationIndex variable.
     */
    public void addAnimationIndex(){
        this.animationIndex++;
    }

    /***
     * Sets the animationIndex variable to 0.
     */
    public void resetAnimationIndex(){
        this.animationIndex = 0;
    }

    /***
     * Sets the animationTick variable to 0.
     */
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

    public LivingStates getEntityState() {
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
