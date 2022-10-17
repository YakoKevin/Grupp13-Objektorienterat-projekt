package view;

import entity.Entity;
import utilz.EntityStates;

public class EntityAnimation {
    private Entity entity;
    private int animationIndex;
    private int animationTick;

    EntityAnimation(Entity entity){
        this.entity = entity;
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
        return entity;
    }

    public EntityStates getEntityState() {
        return entity.getState();
    }

    public int getX(){
        return (int)entity.getX();
    }

    public int getY(){
        return (int)entity.getY();
    }
}
