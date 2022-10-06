package model.level.room;

//TODO: Make sure all the game logic has the same referens to height and length so that no errors occur because of mis-
// matched numbers!

import entity.Enemy;
import entity.EnemyFactory;
import entity.Hostile;
import entity.Player;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class representing a Rooms main construction: walls and obstacles (and perhaps more). Returns list of the
 * coordinates of those. It has a size of 30x18 tiles.
 */
public abstract class Room{
    protected final int HEIGHT = 18;
    protected final int LENGTH = 30;
    protected CardinalDirection entryDirection = CardinalDirection.WEST;

    protected Coordinate coordinate = new Coordinate(0,0);
    protected ArrayList<Coordinate> wallCoordinates = new ArrayList<>();
    protected ArrayList<Coordinate> obstaclesCoordinates = new ArrayList<>();
    protected ArrayList<Door> doors = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected EnemyFactory enemyFactory;


    public Room(Iterator<CardinalDirection> doors, EnemyFactory enemyFactory) {
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
        int[][] matrix = new int[HEIGHT][LENGTH];

        for (Coordinate coordinate : wallCoordinates) {
            matrix[coordinate.getX()][coordinate.getY()] = 2;
        }

        for (Coordinate coordinate : obstaclesCoordinates) {
            matrix[coordinate.getY()][coordinate.getX()] = 2;
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
}
