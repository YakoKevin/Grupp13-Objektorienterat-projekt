package model.level;

import entity.Enemy;
import entity.Living;
import entity.Player;
import general.RoomChangeObserver;
import model.level.room.Door;
import model.level.room.Room;
import model.level.room.RoomTypeFunction;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A facade to the level module. For interacting with the {@code Level} and its {@code room}. It holds all relevant information about
 * the games map and {@code room}, also all the {@code entities} within them. {@code Level} updates from the {@link #tick() tick}
 * function and will call the {@link #tick() tick} function for all its {@code entities}. {@code Level} also manages the detection of
 * the player being inside a {@code door} and will change the {@code room} thereafter.
 *
 * @see Room
 * @see LevelMap
 * @see Player
 * @see Enemy
 */
public abstract class Level{
    protected Room currentRoom;
    protected ArrayList<Room> allRooms = new ArrayList<>();
    private Player player;

    protected final LevelMap levelMap;
    protected final RoomTypeFunction[] roomTypes;
    private final ArrayList<RoomChangeObserver> observers = new ArrayList<>();
    private boolean canGoThroughDoor = true;
    private int doorTimer;

    protected Level(LevelMap levelMap, Player player, RoomTypeFunction ... roomTypeFunctions) {
        this.levelMap = levelMap;
        this.roomTypes = roomTypeFunctions;
        this.player = player;
        this.currentRoom = createRoom(levelMap.getStartCoordinate());
    }


    /**
     * @return the room represented as an int[][] matrix. Ones are walls, twos obstacles and eights are doors.
     */
    public int[][] getCurrentRoomAsMatrix(){
        return currentRoom.getRoomAsMatrix();
    }

    /**
     * @return an {@code ArrayList} of the {@code Coordinates} of all the wall pieces.
     */
    public ArrayList<Coordinate> getCurrentRoomWalls(){
        return currentRoom.getWalls();
    }

    /**
     * @return an {@code ArrayList} of the {@code Coordinates} of all the obstacle pieces.
     */
    public ArrayList<Coordinate> getCurrentRoomObstacles(){
        return currentRoom.getObstacles();
    }

    /**
     * @return an {@code ArrayList} of the {@code Coordinates} of all the {@code door} pieces.
     */
    public ArrayList<Door> getCurrentRoomDoors(){
        return currentRoom.getDoors();
    }

    /**
     * @return a boolean if the argument {@code coordinate} is equal to any obstacle {@code coordinate}.
     */
    public boolean isCoordinateInObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInObstacle(coordinate);
    }

    /**
     * @return a boolean if the argument {@code coordinate} is equal to any {@code door} {@code coordinate}.
     */
    public boolean isCoordinateInDoor(Coordinate coordinate){
        return currentRoom.isCoordinateInDoor(coordinate);
    }

    /**
     * @return a boolean if the argument {@code coordinate} is equal to any wall {@code coordinate}.
     */
    public boolean isCoordinateInWall(Coordinate coordinate){
        return currentRoom.isCoordinateInWall(coordinate);
    }

    /**
     * @return a boolean if the argument {@code coordinate} is equal to any wall or obstacle {@code coordinate}.
     */
    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate){
        return currentRoom.isCoordinateInWallOrObstacle(coordinate);
    }

    private void changeRoom() {
        try {
            if(currentRoom != null)
                allRooms.add(currentRoom);
            Door door = currentRoom.getClosestDoor(player.getPosition());
            CardinalDirection doorDirection = door.getDoorDirection();
            Door doorOpposite = Door.getDoorFromCardinalDirection(doorDirection.getOppositeDirection());
            player.setCoordinate(doorOpposite.getCoordinate().add(doorDirection.getOffset(), 2));
            Coordinate newRoomCoordinate = new Coordinate(currentRoom.getX() + doorDirection.getXOffset(), currentRoom.getY() + doorDirection.getYOffset());
            currentRoom.removeEnemies();
            currentRoom = createRoom(newRoomCoordinate);
            updateRoomChangeObservers();
            giveObstructionCoordinatesToLiving();
            System.out.println(newRoomCoordinate.getX() + ", " + newRoomCoordinate.getY());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void giveObstructionCoordinatesToLiving(){
        ArrayList<Coordinate> list = new ArrayList<>(getCurrentRoomWalls());
        list.addAll(getCurrentRoomObstacles());
        player.giveObstructionList(list);
        for(Enemy enemy : currentRoom.getEnemies()){
            enemy.giveObstructionList(list);
        }
    }

    private Room createRoom(Coordinate coordinate){
        int index = ThreadLocalRandom.current().nextInt(0, roomTypes.length);
        ArrayList<Door> doors = levelMap.getNodeDoors(coordinate);
        Room room = roomTypes[index].apply(doors);
        room.setCoordinate(coordinate);
        room.givePlayerHostiles(player);
        room.giveEnemiesFriendly(player);
        return room;
    }

    /**
     * A method that updates all the logic in the {@code level}. When called {@code level} will tick any entities in it.
     * Should not be called too often since all logic in level depends on its frequency.
     */
    public void tick(){
        if(canGoThroughDoor)
            checkDoorCollision();
        else
            doorTimer();

        updateEnemies();
        updatePlayer();
    }

    private void doorTimer(){
        this.doorTimer++;
        if(doorTimer >= 8) {
            canGoThroughDoor = true;
            doorTimer = 0;
        }
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
          canGoThroughDoor = false;
          changeRoom();
      }
    }

    private void doCollisions(){
        if(isCoordinateInWallOrObstacle(player.getPosition())){

        }
    }

    /**
     * @return all {@code living} in {@code level} in the form of an {@code ArrayList<Living>}. Defencive copying is used.
     */
    public ArrayList<Living> getCurrentLiving(){
        ArrayList<Living> livings = new ArrayList<>(currentRoom.getEnemies());
        livings.add(player);
        return livings;
    }

    /**
     * @return return the {@code player} referens. Note that this is the same referens used in {@code level}, not a new objekt!
     */
    public Player getPlayer() {
        return this.player;
    } //behöver ett objekt

    /**
     * To add a new observer, listening to whenever a new room is created.
     */
    public void addRoomChangeObserver(RoomChangeObserver observer){
        observers.add(observer);
    }

    private void updateRoomChangeObservers(){
        for (RoomChangeObserver observer : observers)
            observer.roomChangeUpdate();
    }

}
