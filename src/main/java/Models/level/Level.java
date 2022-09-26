package Models.level;

import Models.level.room.Room;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Level{
    protected Room currentRoom;
    protected ArrayList<Room> allRooms;

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
}
