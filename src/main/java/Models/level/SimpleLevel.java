package Models.level;

import Models.level.room.RoomMapFunction;
import Models.level.room.SimpleRoom;

import java.util.ArrayList;


public class SimpleLevel extends Level{
    private final ArrayList<RoomMapFunction> roomMapFunctions = new ArrayList<>();

    protected SimpleLevel(LevelMap levelMap) {
        super(levelMap);
        createRoom(levelMap.getStartCoordinate());
    }

    //TODO: test right now
    @Override
    protected void createRoom(Coordinate coordinate){
        currentRoom = new SimpleRoom().apply(0);
        currentRoom.setCoordinate(coordinate);
    }
}
