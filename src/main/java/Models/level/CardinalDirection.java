package Models.level;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An enum for the cardinal directions. Also includes helper functions useful for directionality.
 */
public enum CardinalDirection {
    NORTH, EAST, SOUTH, WEST;

    public static CardinalDirection getOpposite(CardinalDirection direction){
        int ordinal = direction.ordinal();
        int oppositeOrdinal = (ordinal + 2) % 4;
        return CardinalDirection.values()[oppositeOrdinal];
    }

    public static CardinalDirection getRandomDirection(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 5);
        switch (randomInt) {
            case 1 -> {
                return SOUTH;
            }
            case 2 -> {
                return WEST;
            }
            case 3 -> {
                return NORTH;
            }
            case 4 -> {
                return EAST;
            }
        }
        return EAST;
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

    public static int getAdditionToCoordinate(CardinalDirection cardinalDirection) {
        switch (cardinalDirection) {
            case SOUTH, EAST -> {
                return 1;
            }
        }
        return -1;
    }
}