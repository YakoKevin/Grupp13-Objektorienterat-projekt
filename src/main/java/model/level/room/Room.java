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
    protected ArrayList<Door> doors = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected EnemyFactory enemyFactory;
    private final int DOOR_DISTANCE = 2; //TODO: lägg i något annat ställe?


    public Room(ArrayList<Door> doors, EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
        this.HEIGHT = RoomMapSizes.HEIGHT.getSize();
        this.WIDTH = RoomMapSizes.WIDTH.getSize();
        addDoors(doors);
    }

    public boolean isCoordinateInWall(Coordinate coordinate) {
        for (Coordinate coordinateWall : wallCoordinates) {
            if (coordinate.equals(coordinateWall))
                return true;
        }
        return false;
    }

    public boolean isCoordinateInObstacle(Coordinate coordinate) {
        for (Coordinate coordinateObs : obstaclesCoordinates) {
            if (coordinate.equals(coordinateObs))
                return true;
        }
        return false;
    }

    public boolean isCoordinateInWallOrObstacle(Coordinate coordinate) {
        return isCoordinateInObstacle(coordinate) || isCoordinateInWall(coordinate);
    }

    public boolean isCoordinateInDoor(Coordinate coordinate){
        for(Door door : doors){
            if (coordinate.equals(door.coordinate))
                return true;
        }
        return false;
    }

    public int getX(){
        return coordinate.getX();
    }

    public int getY(){
        return coordinate.getY();
    }

    public int[][] getRoomAsMatrix() {
        int[][] matrix = new int[HEIGHT][WIDTH];

        for (Coordinate coordinate : wallCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : obstaclesCoordinates) {
            matrix[coordinate.getY()][coordinate.getX()] = 2;
        }

        for (Door door : doors) {
            matrix[door.coordinate.getY()][door.coordinate.getX()] = 8;
        }

        for (Coordinate coordinate : keysCoordinates) {
            matrix[coordinate.getY()][coordinate.getX()] = 2;
        }

        return matrix;
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public ArrayList<Coordinate> getWalls(){
        return new ArrayList<>(wallCoordinates);
    }

    public ArrayList<Coordinate> getObstacles(){
        return new ArrayList<>(obstaclesCoordinates);
    }

    public ArrayList<Coordinate> getKeys(){
        return keysCoordinates;
    }

    public ArrayList<Door> getDoors(){
        return new ArrayList<>(doors);
    }

    public ArrayList<Enemy> getEnemies(){
        return new ArrayList<>(enemies);
    }

    public void givePlayerHostiles(Player player){
        ArrayList<Hostile> hostiles = new ArrayList<>(enemies);
        player.addHostilesList(hostiles);
    }

    public void giveEnemiesFriendly(Friendly friendly){
        for(Enemy enemy : enemies){
            enemy.addFriendly(friendly);
        }
    }

    public Door getClosestDoor(Coordinate position) throws Exception {
        for(Door door : doors){
            if(Math.abs(position.getX() - door.coordinate.getX()) < DOOR_DISTANCE && Math.abs(position.getY() - door.coordinate.getY()) < DOOR_DISTANCE){
                return door;
            }
        }
        throw new Exception("No door found but request of a door close by was made");
    }

    private void addDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

    public void removeEnemies(){
        enemies.clear();
    }
}
