package model.level;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import model.level.room.Door;
import model.level.room.Room;
import model.level.room.RoomTypeFunction;
import utilz.CardinalDirection;
import utilz.Coordinate;

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

    public ArrayList<Coordinate> getCurrentRoomWalls(){
        return currentRoom.getWalls();
    }

    public ArrayList<Coordinate> getCurrentRoomObstacles(){
        return currentRoom.getObstacles();
    }

    public CardinalDirection getCurrentRoomEntry(){
        return currentRoom.getEntryDirection();
    }

    public ArrayList<Door> getCurrentRoomDoors(){
        return currentRoom.getDoors();
    }

    public boolean isCoordinateInObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInObstacle(coordinate);
    }

    public boolean isCoordinateInDoor(Coordinate coordinate){
        return currentRoom.isCoordinateInDoor(coordinate);
    }

    public boolean isCoordinateInWall(Coordinate coordinate){
        return currentRoom.isCoordinateInWall(coordinate);
    }

    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInWallOrObstacle(coordinate);
    }

    public void playerEnterRoom(Coordinate coordinate, CardinalDirection doorDirection){
        if(currentRoom != null)
            allRooms.add(currentRoom);
        currentRoom = createRoom(coordinate);
        currentRoom.setEntryDirection(doorDirection.getOppositeDirection());
    }

    private void changeRoom() {
        try {
            Door door = currentRoom.getClosestDoor(player.getPosition());
            CardinalDirection doorDirection = door.getDoorDirection();
            Coordinate newRoomCoordinate = new Coordinate(currentRoom.getX() + doorDirection.getXOffset(), currentRoom.getY() + doorDirection.getYOffset());
            currentRoom.removeEnemies();
            currentRoom = createRoom(newRoomCoordinate);
            player.setCoordinate(door.getCoordinate().add(doorDirection.getOppositeDirection().getOffset()));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected Room createRoom(Coordinate coordinate){
        int index = ThreadLocalRandom.current().nextInt(0, roomTypes.length);
        ArrayList<Door> doors = levelMap.getNodeDoors(coordinate);
        Room room = roomTypes[index].apply(doors);
        room.setCoordinate(coordinate);
        room.givePlayerHostiles(player);
        room.giveEnemiesFriendly(player);
        return room;
    }

    public void tick(){
        checkDoorCollision();
        updateEnemies();
        updatePlayer();
    }

    private void updateEnemies(){
        for (Enemy enemy : currentRoom.getEnemies()) {
            enemy.giveFriendlyCoordinates(player.getX(), player.getY());
            enemy.tick();
        }
    }

    private void updatePlayer(){
        player.tick();
    }

    private void checkDoorCollision(){
      if(isCoordinateInDoor(player.getPosition())){
          changeRoom();
          System.out.println("Change room");
      }
    }

    public void doCollisions(){
        if(isCoordinateInWallOrObstacle(player.getPosition())){

        }
    }

    public ArrayList<Entity> getCurrentEntities(){
        ArrayList<Entity> entities = new ArrayList<>();
        entities.addAll(entities);
        entities.add(player);
        return entities;
    }

    public Player getPlayer() {
        return this.player;
    } //behöver ett objekt

}
