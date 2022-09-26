package Models.level.room;

import Models.level.Coordinate;

/**
 * A simple rectangular room with a size of 30x18. It has no obstacles or doors.
 */
public class SimpleRoom extends Room {

    @Override
    public Room apply(Integer doors) {
        createWalls();
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
}
