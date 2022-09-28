package Models.level;

import Models.level.room.Cavern;


public class SimpleLevel extends Level{

    protected SimpleLevel(LevelMap levelMap) {
        super(levelMap);
        this.roomMapFunctions.add(new Cavern());

    }

    //TODO: test right now, not final at all!
    /*@Override/*
    protected Room createRoom(Coordinate coordinate){
        currentRoom = new SimpleRoom(CardinalDirection.WEST).apply(0);
        currentRoom.setCoordinate(coordinate);
        return room;
    }*/
}
