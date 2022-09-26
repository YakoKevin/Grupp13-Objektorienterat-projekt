package Models.level;

import Models.level.room.RoomMapFunction;
import Models.level.room.SimpleRoom;


public class SimpleLevel extends Level{
    private final RoomMapFunction[] roomMapFunctions = {new SimpleRoom()};

    protected SimpleLevel(LevelMap levelMap) {
        super(levelMap);
        createRoom(levelMap.getStartCoordinate());
    }

    //TODO: test right now
    protected void createRoom(Coordinate startingCoordinate){
        currentRoom = new SimpleRoom().apply(0);
        currentRoom.setCoordinate(startingCoordinate);
    }
}
