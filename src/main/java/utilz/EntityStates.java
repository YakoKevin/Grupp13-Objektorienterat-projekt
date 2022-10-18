package utilz;

public enum EntityStates {
        IDLE(10),
        RUNNING(10),
        ATTACK(10),
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
