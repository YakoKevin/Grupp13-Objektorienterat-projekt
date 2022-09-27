package Models.level;

import Models.level.room.Room;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A facade to the level module. For interacting with the level. Has rooms, the level map.
 */
public abstract class Level{
    protected Room currentRoom;
    protected ArrayList<Room> allRooms;
    private int [][] levelData;

    protected final LevelMap levelMap;

    protected Level(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    public int[][] getCurrentRoomAsMatrix(){
        return currentRoom.getRoomAsMatrix();
    }

    public Iterator<Coordinate> getCurrentRoomWalls(){
        return currentRoom.getWalls();
    }

    public Iterator<Coordinate> getCurrentRoomObstacles(){
        return currentRoom.getObstacles();
    }

    public boolean isCoordinateInObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInObstacle(coordinate);
    }

    public boolean isCoordinateInWall(Coordinate coordinate){
        return currentRoom.isCoordinateInWall(coordinate);
    }

    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInWallOrObstacle(coordinate);
    }

    protected abstract void createRoom(Coordinate coordinate);
}
