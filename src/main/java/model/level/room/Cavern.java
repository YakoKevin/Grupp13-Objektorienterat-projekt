package model.level.room;

import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.Iterator;

/**
 * A simple rectangular room with a size of 30x18. It has no obstacles or doors.
 */
public class Cavern extends Room {

    @Override
    public Room apply(Iterator<CardinalDirection> doors) {
        createWalls();
        addDoors(doors);
        return this;
    }

    private void createWalls(){
        int y = 0;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        y = LENGTH -1;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        int x = 0;
        for (y = 0; y < LENGTH; y++)
            wallCoordinates.add(new Coordinate(x,y));

        x = HEIGHT -1;
        for (y = 0; y < LENGTH; y++)
            wallCoordinates.add(new Coordinate(x,y));
    }

    private void removeWalls(){

    }

    private void addDoors(Iterator<CardinalDirection> doors) {

    }
}
