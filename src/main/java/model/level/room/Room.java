package model.level.room;

//TODO: Make sure all the game logic has the same references to height and length so that no errors occur because of mis-
// matched numbers!

import entity.*;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class representing a Rooms main construction: walls and obstacles (and perhaps more). Returns list of the
 * coordinates of those. It has a size of 30x18 tiles.
 */
public abstract class Room{
    protected final int HEIGHT = RoomMapSizes.HEIGHT.getSize();
    protected final int WIDTH = RoomMapSizes.WIDTH.getSize();
    protected CardinalDirection entryDirection = CardinalDirection.WEST;
    protected Coordinate coordinate = new Coordinate(0,0);
    protected ArrayList<Coordinate> wallCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> obstaclesCoordinates = new ArrayList<>();
    protected ArrayList<Door> doors = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected EnemyFactory enemyFactory;


    public Room(ArrayList<Door> doors, EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
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
            matrix[(int)coordinate.getX()][(int)coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : obstaclesCoordinates) {
            matrix[(int)coordinate.getY()][(int)coordinate.getX()] = 2;
        }

        for (Door door : doors) {
            matrix[(int)door.coordinate.getY()][(int)door.coordinate.getX()] = 8;
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

    public ArrayList<Door> getDoors(){
        return new ArrayList<>(doors);
    }

    public void setEntryDirection(CardinalDirection entryDirection){
        this.entryDirection = entryDirection;
    }

    public CardinalDirection getEntryDirection(){
        return entryDirection;
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
            System.out.println("Adding to "+enemy);
            enemy.addFriendly(friendly);
        }
    }

    public Door getClosestDoor(Coordinate position) throws Exception {
        Door closestDoor = doors.get(0);
        for(Door door : doors){
            if(Math.abs(coordinate.getX()) - Math.abs(door.coordinate.getX()) < 3 && Math.abs(coordinate.getY()) - Math.abs(door.coordinate.getY()) < 3){
                return door;
            }
        }
        throw new Exception("No door found but request of a door close by was made");
    }

    private void addDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }
}
