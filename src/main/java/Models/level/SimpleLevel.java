package Models.level;

import Models.level.room.Room;
import Models.level.room.RoomMapFunction;
import Models.level.room.SimpleRoom;

import java.util.ArrayList;
import java.util.Iterator;

public class SimpleLevel implements Level {
    private Room currentRoom;
    private ArrayList<Room> allRooms;

    private final LevelMap levelMap;
    private final RoomMapFunction[] roomMapFunctions = {new SimpleRoom()};

    public SimpleLevel(LevelMap levelMap) {
        this.levelMap = levelMap;
        createRoom(levelMap.getStartCoordinate());
    }

    //TODO: test right now
    public void createRoom(Coordinate coordinate){
        currentRoom = new SimpleRoom().apply(0);
        currentRoom.setCoordinate(coordinate);
    }

    @Override
    public int[][] getCurrentRoomAsMatrix(){
        return currentRoom.getRoomAsMatrix();
    }

    @Override
    public Iterator<Coordinate> getCurrentRoomWalls(){
        return currentRoom.getWalls();
    }

    @Override
    public Iterator<Coordinate> getCurrentRoomObstacles(){
        return currentRoom.getObstacles();
    }
}
