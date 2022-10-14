package utilz;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An enum for the cardinal directions. Also includes helper functions useful for directionality.
 */
public enum CardinalDirection {
    NORTH(0,-1),
    NORTHWEST(-1,1),
    NORTHEAST(1,1),
    EAST(1,0),
    SOUTH(0,1),
    SOUTHWEST(-1,-1),
    SOUTHEAST(1,-1),
    WEST(0,-1);

    private final int xOffset;
    private final int yOffset;

    CardinalDirection(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public CardinalDirection getOppositeDirection(){
        int ordinal = this.ordinal();
        int oppositeOrdinal = (ordinal + 2) % 4;
        return CardinalDirection.values()[oppositeOrdinal];
    }

    public static CardinalDirection getRandomDirection(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 4);
        return CardinalDirection.values()[randomInt];
    }

    public static CardinalDirection getEastWestDirection(int start, int end) {
        int diff = start - end;

        if(diff < 0) {
            return EAST;
        }else
            return WEST;
    }

    public static CardinalDirection getNorthSouthDirection(int start, int end) {
        int diff = start - end;

        if(diff < 0) {
            return SOUTH;
        } else
            return NORTH;

    }
}
