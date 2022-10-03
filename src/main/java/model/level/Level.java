package model.level;

import entity.Player;
import model.level.room.Room;
import model.level.room.RoomTypeFunction;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

//TODO: problem with accessing room! It might not have been instantiated since it is not a must from the childrens
// constructors!

/**
 * A facade to the level module. For interacting with the level and its rooms.
 */
public abstract class Level{
    protected Room currentRoom;
    protected ArrayList<Room> allRooms;
    private int [][] levelData;
    private Player player;

    protected final LevelMap levelMap;
    protected final RoomTypeFunction[] roomTypes;

    protected Level(LevelMap levelMap, Player player, RoomTypeFunction ... roomTypeFunctions) {
        this.levelMap = levelMap;
        this.roomTypes = roomTypeFunctions;
        this.player = player;

        this.currentRoom = createRoom(levelMap.getStartCoordinate());
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

    public CardinalDirection getCurrentRoomEntry(){
        return currentRoom.getEntryDirection();
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

    public void playerEnterRoom(Coordinate coordinate, CardinalDirection doorDirection){
        currentRoom = createRoom(coordinate);
        currentRoom.setEntryDirection(doorDirection.getOppositeDirection());
    }

    protected Room createRoom(Coordinate coordinate){
        int index = ThreadLocalRandom.current().nextInt(0, roomTypes.length);
        Iterator<CardinalDirection> doors = levelMap.getNodeDoors(coordinate);
        return roomTypes[index].apply(doors);
    }
}
