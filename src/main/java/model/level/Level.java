package model.level;

import entity.Enemy;
import entity.Player;
import model.level.room.Door;
import model.level.room.Room;
import model.level.room.RoomTypeFunction;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A facade to the level module. For interacting with the level and its rooms.
 */
public abstract class Level{
    protected Room currentRoom;
    protected ArrayList<Room> allRooms = new ArrayList<>();
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

    public Iterator<Door> getCurrentRoomDoors(){
        return currentRoom.getDoors();
    }

    public boolean isCoordinateInObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInObstacle(coordinate);
    }

    public boolean isCoordinateInWall(Coordinate coordinate){
        return currentRoom.isCoordinateInWall(coordinate);
    }

    public void update(){
        player.update();
        for (Iterator<Enemy> it = currentRoom.getEnemies(); it.hasNext(); ) {
            it.next().update();
        }
    }

    public void drawEntities(Graphics g){
        player.draw(g);
        for (Iterator<Enemy> enemyIterator = currentRoom.getEnemies(); enemyIterator.hasNext(); ) {
            Enemy enemy = enemyIterator.next();
            enemy.draw(g);
        }
    }

    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInWallOrObstacle(coordinate);
    }

    public void playerEnterRoom(Coordinate coordinate, CardinalDirection doorDirection){
        if(currentRoom != null){
            allRooms.add(currentRoom);
        }
        currentRoom = createRoom(coordinate);
        currentRoom.setEntryDirection(doorDirection.getOppositeDirection());
    }

    protected Room createRoom(Coordinate coordinate){
        int index = ThreadLocalRandom.current().nextInt(0, roomTypes.length);
        Iterator<Door> doors = levelMap.getNodeDoors(coordinate);
        Room room = roomTypes[index].apply(doors);
        room.givePlayerHostiles(player);
        return room;
    }

}
