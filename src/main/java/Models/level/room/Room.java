package Models.level.room;

//TODO: Make sure all the game logic has the same referens to height and length so that no errors occur because of mis-
// matched numbers!

import Models.level.Coordinate;

import java.util.ArrayList;

/**
 * A class representing a Rooms main construction: walls and obstacles (and perhaps more). Returns list of the
 * coordinates of those. It has a size of 30x18 tiles.
 */
public abstract class Room implements RoomMapFunction {
    protected final int HEIGHT = 18;
    protected final int LENGTH = 30;

    protected Coordinate coordinate = new Coordinate(0,0);
    protected ArrayList<Coordinate> wallCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> obstaclesCoordinates = new ArrayList<>();

    public boolean isCoordinateInWall(Coordinate coordinate) {
        for (Coordinate coordinateWall : wallCoordinates) {
            if (coordinate.equals(coordinateWall))
                return true;
        }
        return false;
    }

    public boolean isCoordinateInObstacle(Coordinate coordinate) {
        for (Coordinate coordinateObs : obstaclesCoordinates) {
            if (coordinate.equals(coordinateObs))
                return true;
        }
        return false;
    }

    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate) {
        return isCoordinateInObstacle(coordinate) || isCoordinateInWall(coordinate);
    }

    public int[][] getRoomAsMatrix() {
        int[][] matrix = new int[HEIGHT][LENGTH];

        for (Coordinate coordinate : wallCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : obstaclesCoordinates) {
            matrix[coordinate.getY()][coordinate.getX()] = 2;
        }

        return matrix;
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }
}
