package entity;


import static  utilz.EnemyConstants.*;

public abstract class Enemy extends Entity implements model.IObserver {

    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;

    public Enemy(float x, float y, int width, int height, int enemyType){
        super(x, y, width, height);
        this.enemyType = enemyType;
        //super(50,10,1,3,10);
    }

    private void updateAnimationTick(){
        animationTick++;

        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)){
                animationIndex = 0;
            }
        }
    }

    public void update(){
        updateAnimationTick();
    }

    public int getAnimationIndex(){
        return animationIndex;
    }

    public int getEnemyState(){
        return enemyState;
    }

    public void update(Player p){
        System.out.print(this.getHealthPoints());
        if(p.checkIfInRange(this)==true){ // funktionen ska nog inte kallas så här
            this.setHealthPoints(this.getHealthPoints()-p.getAttackPoints());
            System.out.println("Ouch");
        }
        System.out.println(" EnemyHp" + this.getHealthPoints());
    }

}
