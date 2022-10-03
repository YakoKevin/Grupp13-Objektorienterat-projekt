package model.level.room;

import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.Iterator;
import java.util.function.Function;

/**
 * A simple rectangular room with a size of 30x18. It has no obstacles or doors.
 */
public class Cavern extends Room {

    public Cavern(Iterator<CardinalDirection> doors) {
        super(doors);
        createWalls();
        addDoors(doors);
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

    private void addDoors(Iterator<CardinalDirection> doorsIt) {
        for (Iterator<CardinalDirection> it = doorsIt; it.hasNext(); ) {
            CardinalDirection doorDirection = it.next();
            Door doorToAdd = Door.getDoorFromCardinalDirection(doorDirection);
            if(doorToAdd != null)
                doors.add(doorToAdd);
        }
    }

    private void removeWalls(){

    }
}
