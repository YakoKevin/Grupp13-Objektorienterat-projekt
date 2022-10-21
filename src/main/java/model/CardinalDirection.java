package model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An enum for the cardinal directions. Also includes helper functions useful for directionality.
 */
public enum CardinalDirection {
    NORTH(0,-1),
    NORTHEAST(1,-1),
    EAST(1,0),
    SOUTHEAST(1,1),
    SOUTH(0,1),
    SOUTHWEST(-1,1),
    WEST(-1,0),
    NORTHWEST(-1,-1);

    private int xOffset;
    private int yOffset;

    private final double hypothenuseReciprocal;

    CardinalDirection(int xOffset, int yOffset) {
        this.hypothenuseReciprocal = 1.0 / Math.sqrt(xOffset * xOffset + yOffset * yOffset); //räkna ut hypotenusan för olika riktningar
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * @return hypotenuse reciprocal.
     */
    public double getHypothenuseReciprocal() {
        return hypothenuseReciprocal;
    }

    /**
     * @return {@code cardinal direction} opposite this.
     */
    public CardinalDirection getOppositeDirection(){
        int ordinal = this.ordinal();
        int oppositeOrdinal = (ordinal + 4) % 8;
        return CardinalDirection.values()[oppositeOrdinal];
    }

    /**
     * Makes a true {@code cardinal direction} randomly (i.e. only North, East, South and West).
     * @return {@code cardinal direction}.
     */
    public static CardinalDirection getRandomAxisDirection(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 4);
        return CardinalDirection.values()[randomInt*2];
    }

    /**
     * @return x,y-offsets from the direction.
     */
    public Coordinate getOffset(){
        return new Coordinate(xOffset, yOffset);
    }

    /**
     * @return x-offset from the direction.
     */
    public int getXOffset(){
        return xOffset;
    }

    /**
     * @return y-offset from the direction.
     */
    public int getYOffset(){
        return yOffset;
    }

}
