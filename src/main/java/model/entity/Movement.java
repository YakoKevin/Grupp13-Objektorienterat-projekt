package model.entity;

import model.CardinalDirection;

public class Movement {
    public Movement(){

    }

    public float[] updatePosition(float x, float y, double speed, CardinalDirection dir) {
        x+= (float) speed * (float) dir.getHypothenuseReciprocal() * dir.getXOffset();
        y+= (float) speed * (float) dir.getHypothenuseReciprocal() * dir.getYOffset();


        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        if(x < 1) {
            x=200;
        }

        if(y < 0) {
            y = 300;
        }
        return new float[]{x,y};
    }
}