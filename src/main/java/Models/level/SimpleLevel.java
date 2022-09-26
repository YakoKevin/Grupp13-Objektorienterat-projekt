package Models.level;

import Models.level.room.Room;
import Models.level.room.RoomMapFunction;
import Models.level.room.SimpleRoom;

import java.util.ArrayList;

public class SimpleLevel implements ILevel{
    private Room currentRoom;
    private ArrayList<Room> roomsVisitedInLevel;

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

    public int[][] getCurrentRoomAsMatrix(){
        return currentRoom.getRoomAsMatrix();
    }
}
