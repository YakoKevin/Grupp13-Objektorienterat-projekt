package model.level.room;

import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;

import java.util.ArrayList;

public enum Door {
    NORTH(0, GameConstants.Sizes.LENGTH.getSize()/2, CardinalDirection.NORTH),
    EAST(GameConstants.Sizes.LENGTH.getSize(), GameConstants.Sizes.HEIGHT.getSize()/2, CardinalDirection.EAST),
    SOUTH(GameConstants.Sizes.HEIGHT.getSize(), GameConstants.Sizes.LENGTH.getSize()/2, CardinalDirection.SOUTH),
    WEST(0, GameConstants.Sizes.HEIGHT.getSize()/2, CardinalDirection.WEST);

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
