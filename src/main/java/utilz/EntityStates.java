package utilz;

public enum EntityStates {
    ;
    public enum PlayerStates{
        IDLE(10),
        RUNNING(10),
        ATTACK(1);
        int animationSpriteAmount;

        PlayerStates(int animationSpriteAmount){
            this.animationSpriteAmount = animationSpriteAmount;
        }

        public int getAnimationSpriteAmount() {
            return animationSpriteAmount;
        }
    }

    public enum EnemyStates{
        IDLE(3),
        RUNNING(1),
        ATTACK(1),
        HIT(1),
        DEAD(1);

        int animationSpriteAmount;

        EnemyStates(int animationSpriteAmount){
            this.animationSpriteAmount = animationSpriteAmount;
        }

        public int getAnimationSpriteAmount() {
            return animationSpriteAmount;
        }
    }
}
