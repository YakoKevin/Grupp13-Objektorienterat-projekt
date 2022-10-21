package model.entity;

/**
 * Contains the different states that a Living can be in and its amount of sprites for the animation.
 */
public enum LivingStates {
        IDLE(10),
        RUNNING(10),
        ATTACK(10),
        HIT(1),
        DEAD(1);
        int animationSpriteAmount;

    LivingStates(int animationSpriteAmount){
            this.animationSpriteAmount = animationSpriteAmount;
        }

    /**
     * @return the amount of sprites for the states' animation.
     */
    public int getAnimationSpriteAmount() {
            return animationSpriteAmount;
        }

}
