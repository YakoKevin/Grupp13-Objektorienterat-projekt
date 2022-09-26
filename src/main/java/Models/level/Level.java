package Models.level;

import java.util.Iterator;

public interface Level {

    int[][] getCurrentRoomAsMatrix();

    Iterator<Coordinate> getCurrentRoomWalls();

    Iterator<Coordinate> getCurrentRoomObstacles();

}
