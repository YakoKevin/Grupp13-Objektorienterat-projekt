package utilz;

public enum EntityStates {
        IDLE(3),
        RUNNING(1),
        ATTACK(1),
        HIT(1),
        DEAD(1);
        int animationSpriteAmount;

    EntityStates(int animationSpriteAmount){
            this.animationSpriteAmount = animationSpriteAmount;
        }

        public int getAnimationSpriteAmount() {
            return animationSpriteAmount;
        }

}
