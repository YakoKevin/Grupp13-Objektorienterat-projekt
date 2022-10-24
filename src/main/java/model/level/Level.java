package model.level;

import model.entity.Enemy;
import model.entity.Living;
import model.entity.Player;
import model.PlayerDeathObserver;
import model.RoomChangeObserver;
import model.level.room.Door;
import model.level.room.Room;
import model.level.room.RoomTypeFunction;
import model.CardinalDirection;
import model.Coordinate;
import model.entity.LivingStates;

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
    private final ArrayList<PlayerDeathObserver> deathObservers = new ArrayList<>();

    private boolean canGoThroughDoor = true;
    private int doorTimer;

    protected Level(LevelMap levelMap, Player player, RoomTypeFunction ... roomTypeFunctions) {
        this.levelMap = levelMap;
        this.roomTypes = roomTypeFunctions;
        this.player = player;
        this.currentRoom = createRoom(levelMap.getStartCoordinate());
        updateRoomChangeObservers();
        giveObstructionCoordinatesToLiving();
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
     * @return an {@code ArrayList} of the {@code Coordinates} of all the keys.
     */
    public ArrayList<Coordinate> getCurrentRoomKeys(){
        return currentRoom.getKeys();
    }

    /**
     * @return an {@code ArrayList} of the {@code Coordinates} of all the Coin.
     */
    public ArrayList<Coordinate> getCurrentRoomCoins(){
        return currentRoom.getCoins();
    }

    public Coordinate getCurrentRoomHeart(){
        return currentRoom.getHeart();
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
    public boolean isCoordinateInDoor(float x, float y){
        return currentRoom.isCoordinateInDoor(x, y);
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
    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate) {
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
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void giveObstructionCoordinatesToLiving(){
        ArrayList<Coordinate> list = new ArrayList<>(getCurrentRoomWalls());
//        ArrayList<Coordinate> list = new ArrayList<>(getCurrentRoomObstacles());
        list.addAll(getCurrentRoomObstacles());
        player.giveObstructionList(list);
        player.giveKeyList(getCurrentRoomKeys());
        player.giveCoinList(getCurrentRoomCoins());
        player.giveHeartObject(getCurrentRoomHeart());
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
        room.setPlayerTotalScore(player);
        //player.setScoreCount(player.getRoomScoreCount()+player.getScoreCount());
        player.setRoomScoreCount(0);
        player.setSlainEnemies(player.getRoomSlainEnemies()+player.getSlainEnemies());
        player.setRoomSlainEnemies(0);
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

        if (!player.isAlive() )
            updatePlayerDeathObserver();
    }

    private void doorTimer(){
        this.doorTimer++;
        if(doorTimer >= 8) {
            canGoThroughDoor = true;
            doorTimer = 0;
        }
    }

    /**
     * Stopping enemies, setting their state to "idle".
     */
    public void stopEnemies(){
        for (Enemy enemy : currentRoom.getEnemies()) {
            enemy.setState(LivingStates.IDLE);
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

        if(player.getState()==LivingStates.ATTACK) {
            int score=0; boolean anyEnemyDead=false;
            for (Enemy enemy : currentRoom.getEnemies()) {
                if (!enemy.isAlive()) {
                    score++;
                    anyEnemyDead =true;
                }
            }
            if(anyEnemyDead){
                player.setRoomScoreCount(score*10);
                player.setRoomSlainEnemies(score);
            }
        }
    }

    private void checkDoorCollision(){
        if(isCoordinateInDoor(player.getX(), player.getY())){
            canGoThroughDoor = false;
            changeRoom();
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
     * @return return the {@code player} referens. Note that this is the same reference used in {@code level}, not a new object!
     */
    public Player getPlayer() {
        return this.player;
    } //behöver ett objekt

    /**
     * To add a new observer, listening to whenever a new room is created.
     */
    private void updateRoomChangeObservers(){
        for (RoomChangeObserver observer : observers)
            observer.roomChangeUpdate();
    }

    public void addRoomChangeObserver(RoomChangeObserver observer){
        observers.add(observer);
    }

    public void addPlayerDeathObserver(PlayerDeathObserver observer){
        deathObservers.add(observer);
    }

    private void updatePlayerDeathObserver(){
        for (PlayerDeathObserver observer : deathObservers)
            observer.updatePlayerDeathObserver();
    }

}