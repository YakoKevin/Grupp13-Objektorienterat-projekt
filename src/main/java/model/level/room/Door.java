package model.level.room;

import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;

/**
 * Enum with the four different door types. Doors contain coordinate and a direction.
 *
 * @see Coordinate
 * @see CardinalDirection
 */
public enum Door {
    NORTH(GameConstants.RoomMapSizes.WIDTH.getSize()/2, 0, CardinalDirection.NORTH),
    EAST(GameConstants.RoomMapSizes.WIDTH.getSize()-1, GameConstants.RoomMapSizes.HEIGHT.getSize()/2, CardinalDirection.EAST),
    SOUTH(GameConstants.RoomMapSizes.WIDTH.getSize()/2, GameConstants.RoomMapSizes.HEIGHT.getSize()-1, CardinalDirection.SOUTH),
    WEST(0, GameConstants.RoomMapSizes.HEIGHT.getSize()/2, CardinalDirection.WEST);

    Coordinate coordinate;
    CardinalDirection direction;

    Door(int x, int y, CardinalDirection direction) {
        this.coordinate = new Coordinate(x, y);
        this.direction = direction;
    }

    /**
     * @return the {@code coordinate} of the door.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @return the {@code cardinal direction} of the door - where on the map it sits.
     */
    public CardinalDirection getDoorDirection(){
        return direction;
    }

    /**
     * To get a door from a cardinal direction with the same direction.
     *
     * @param direction is the {@code cardinal direction} of which to get a {@code door}.
     * @return a {@code door} for the matching {@code cardinal direction}. Returns {@code null} if argument is {@code null} or a direction outside true cardinals.
     */
    public static Door getDoorFromCardinalDirection(CardinalDirection direction){
        for(Door door : Door.values()){
            if (direction.equals(door.direction)){
                return door;
            }
        }
        return null;
    }
}
