package model.level.room;

import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;

public enum Door {
    NORTH(0, GameConstants.TileSizes.LENGTH.getSize()/2, CardinalDirection.NORTH),
    EAST(GameConstants.TileSizes.LENGTH.getSize(), GameConstants.TileSizes.HEIGHT.getSize()/2, CardinalDirection.EAST),
    SOUTH(GameConstants.TileSizes.HEIGHT.getSize(), GameConstants.TileSizes.LENGTH.getSize()/2, CardinalDirection.SOUTH),
    WEST(0, GameConstants.TileSizes.HEIGHT.getSize()/2, CardinalDirection.WEST);

    Coordinate coordinate;
    CardinalDirection direction;

    Door(int x, int y, CardinalDirection direction) {
        this.coordinate = new Coordinate(x, y);
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public CardinalDirection getDoorDirection(){
        return direction;
    }

    //TODO: make better/not have to return null.
    public static Door getDoorFromCardinalDirection(CardinalDirection direction){
        for(Door door : Door.values()){
            if (direction.equals(door.direction)){
                return door;
            }
        }
        return null;
    }
}
