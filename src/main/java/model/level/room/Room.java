package model.level.room;

//TODO: Make sure all the game logic has the same references to height and length so that no errors occur because of mis-
// matched numbers!

import model.entity.*;
import model.Coordinate;
import model.GameConstants.*;

import java.util.ArrayList;

/**
 * A class representing a Rooms main construction: walls and obstacles and doors. Also keeps and creates the enemies.
 *
 * @see Coordinate
 * @see EnemyFactory
 */
public abstract class Room{
    protected final int HEIGHT;
    protected final int WIDTH;
    protected Coordinate coordinate = new Coordinate(0,0);
    protected ArrayList<Coordinate> wallCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> obstaclesCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> keysCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> coinsCoordinates = new ArrayList<>();
    protected Coordinate heartCoordinate;
    protected ArrayList<Door> doors = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected EnemyFactory enemyFactory;
    private final int DOOR_DISTANCE = 2;

    /**
     * Setting up room, with correct size, and doors and enemies
     * @param doors
     * @param enemyFactory
     */
    public Room(ArrayList<Door> doors, EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
        this.HEIGHT = RoomMapSizes.HEIGHT.getSize();
        this.WIDTH = RoomMapSizes.WIDTH.getSize();
        addDoors(doors);
    }

    /**
     * Checking if a coordinate is in a wall.
     * @param coordinate
     * @return
     */
    public boolean isCoordinateInWall(Coordinate coordinate) {
        for (Coordinate coordinateWall : wallCoordinates) {
            if (coordinate.equals(coordinateWall))
                return true;
        }
        return false;
    }

    /**
     * Checking if a coordinate is in an obstacle
     * @param coordinate
     * @return
     */
    public boolean isCoordinateInObstacle(Coordinate coordinate) {
        for (Coordinate coordinateObs : obstaclesCoordinates) {
            if (coordinate.equals(coordinateObs))
                return true;
        }
        return false;
    }

    /**
     * Checking if a coordinate is in either a wall or obstacle
     * @param coordinate
     * @return
     */
    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate) {
        return isCoordinateInObstacle(coordinate) || isCoordinateInWall(coordinate);
    }

    /**
     * Checking if a coordinate is in a door
     * @param x
     * @param y
     * @return
     */
    public boolean isCoordinateInDoor(float x, float y){
        for(Door door : doors){
            float scale = GameScalingFactors.TILE_SCALE_FACTOR.getSize();

            if (Math.abs((float)door.coordinate.getX()*scale - x) <= scale && Math.abs((float)door.coordinate.getY()*scale - y) <= scale)
                return true;
        }
        return false;
    }

    /**
     * Get x of coordinate
     * @return
     */
    public int getX(){
        return coordinate.getX();
    }

    /**
     * Get y of coordinate
     * @return
     */
    public int getY(){
        return coordinate.getY();
    }

    /**
     * Getting the room as a matrix.
     * @return
     */
    public int[][] getRoomAsMatrix() {
        int[][] matrix = new int[WIDTH][HEIGHT];

        for (Coordinate coordinate : wallCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : obstaclesCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Door door : doors) {
            matrix[door.coordinate.getX()][door.coordinate.getY()] = 8;
        }

        for (Coordinate coordinate : keysCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : coinsCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }
        return matrix;
    }

    /**
     * Setting coordinate
     * @param coordinate
     */
    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    /**
     * Getting wall coordinates
     * @return
     */
    public ArrayList<Coordinate> getWalls(){
        return new ArrayList<>(wallCoordinates);
    }

    /**
     * Getting obstacle coordinates
     * @return
     */
    public ArrayList<Coordinate> getObstacles(){
        return new ArrayList<>(obstaclesCoordinates);
    }

    /**
     * Getting key coordinates
     * @return
     */
    public ArrayList<Coordinate> getKeys(){
        return keysCoordinates;
    }

    /**
     * Getting coin coordinates
     * @return
     */
    public ArrayList<Coordinate> getCoins(){
        return coinsCoordinates;
    }

    /**
     * Get coordinate of heart
     * @return
     */
    public Coordinate getHeart(){
        return heartCoordinate;
    }

    /**
     * Get door coordinates
     * @return
     */
    public ArrayList<Door> getDoors(){
        return new ArrayList<>(doors);
    }

    /**
     * Get enemy coordinates
     * @return
     */
    public ArrayList<Enemy> getEnemies(){
        return new ArrayList<>(enemies);
    }

    /**
     * Giving the player the hostiles (enemies).
     * @param player
     */
    public void givePlayerHostiles(Player player){
        ArrayList<Hostile> hostiles = new ArrayList<>(enemies);
        player.addHostilesList(hostiles);
    }

    /**
     * Giving the player its score.
     * @param player
     */
    public void setPlayerTotalScore(Player player){
        player.setScoreCount(player.getRoomScoreCount()+player.getScoreCount());
    }

    /**
     *
     * @param friendly
     */
    public void giveEnemiesFriendly(Friendly friendly){
        for(Enemy enemy : enemies){
            enemy.addFriendly(friendly);
        }
    }

    /**
     * Getting the door located closest to coordinate
     * @param position
     * @return
     * @throws Exception
     */
    public Door getClosestDoor(Coordinate position) throws Exception {
        for(Door door : doors){
            if(Math.abs(position.getX() - door.coordinate.getX()) < DOOR_DISTANCE && Math.abs(position.getY() - door.coordinate.getY()) < DOOR_DISTANCE){
                return door;
            }
        }
        throw new Exception("No door found but request of a door close by was made");
    }

    /**
     * Adding doors
     * @param doors
     */
    private void addDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

    /**
     * Removing the enemies from enemy list.
     */
    public void removeEnemies(){
        enemies.clear();
    }
}
