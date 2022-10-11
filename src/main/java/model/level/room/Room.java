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


    public Room(Iterator<Door> doors, EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
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

        return matrix;
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Iterator<Coordinate> getWalls(){
        return wallCoordinates.iterator();
    }

    public Iterator<Coordinate> getObstacles(){
        return obstaclesCoordinates.iterator();
    }

    public Iterator<Door> getDoors(){
        return doors.iterator();
    }

    public void setEntryDirection(CardinalDirection entryDirection){
        this.entryDirection = entryDirection;
    }

    public CardinalDirection getEntryDirection(){
        return entryDirection;
    }

    public Iterator<Enemy> getEnemies(){
        return enemies.iterator();
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
}
