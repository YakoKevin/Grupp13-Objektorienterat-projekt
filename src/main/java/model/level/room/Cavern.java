package model.level.room;

import model.entity.Enemy;
import model.entity.EnemyFactory;
import model.Coordinate;
import model.GameConstants;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple rectangular room with doors and obstacles and enemies.
 *
 * @see Room
 * @see Enemy
 * @see EnemyFactory
 */
public class Cavern extends Room {

    public Cavern(ArrayList<Door> doors) {
        super(doors, new EnemyFactory());
        createWalls();
        addObstacles();
        addEnemies(enemyFactory);
        addKeys();
    }

    private void createWalls(){
        int y = 0;
        ArrayList<Coordinate> doorCoordinates = new ArrayList<>();

        for (Door door : doors){
            doorCoordinates.add(door.coordinate);
        }

        for (int x = 0; x < HEIGHT; x++)
            for (Coordinate coordinate : doorCoordinates) {
                Coordinate wallCoordinate = new Coordinate(x, y);
                if (!coordinate.equals(wallCoordinate))
                    wallCoordinates.add(wallCoordinate);
            }

        y = WIDTH -1;
        for (int x = 0; x < HEIGHT; x++)
            for (Coordinate coordinate : doorCoordinates) {
                Coordinate wallCoordinate = new Coordinate(x, y);
                if (!coordinate.equals(wallCoordinate))
                    wallCoordinates.add(new Coordinate(x, y));
            }

        int x = 0;
        for (y = 0; y < WIDTH; y++)
            for (Coordinate coordinate : doorCoordinates) {
                Coordinate wallCoordinate = new Coordinate(x, y);
                if (!coordinate.equals(wallCoordinate))
                    wallCoordinates.add(new Coordinate(x, y));
            }

        x = HEIGHT -1;
        for (y = 0; y < WIDTH; y++)
            for (Coordinate coordinate : doorCoordinates) {
                Coordinate wallCoordinate = new Coordinate(x, y);
                if (!coordinate.equals(wallCoordinate))
                    wallCoordinates.add(new Coordinate(x, y));
            }
    }

    private void addEnemies(EnemyFactory enemyFactory) {
        for (int i = 0; i <= Constants.getRandomEnemiesAmount(); i++) {
            int x = ThreadLocalRandom.current().nextInt(1, GameConstants.RoomMapSizes.WIDTH.getSize());
            int y = ThreadLocalRandom.current().nextInt(1, GameConstants.RoomMapSizes.HEIGHT.getSize());
            Enemy enemy = enemyFactory.createSkeleton();
            enemy.setCoordinate(new Coordinate(x, y));
            enemies.add(enemy);
        }
    }

    private void addObstacles() {
        for (int i = 0; i < Constants.getRandomObstaclesAmount(); i++){
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1,1, WIDTH-1, HEIGHT-1);
            obstaclesCoordinates.add(randomCoordinate);
        }
    }

    private void addKeys() {
        for (int i = 0; i < Constants.getRandomKeysAmount(); i++){
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1,1, WIDTH-1, HEIGHT-1);
            keysCoordinates.add(randomCoordinate);
        }
    }

    private enum Constants{
        MIN_NUMBER_OF_ENEMIES(0),
        MAX_NUMBER_OF_ENEMIES(4),
        MIN_NUMBER_OF_OBSTACLES(5),
        MAX_NUMBER_OF_OBSTACLES(10),
        MIN_NUMBER_OF_KEYS(2),
        MAX_NUMBER_OF_KEYS(5);
        int amount;

        Constants(int amount){
            this.amount = amount;
        }

        static int getRandomEnemiesAmount(){
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_ENEMIES.amount,Constants.MAX_NUMBER_OF_ENEMIES.amount+1);
        }

        static int getRandomObstaclesAmount(){
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_OBSTACLES.amount,Constants.MAX_NUMBER_OF_OBSTACLES.amount+1);
        }

        static int getRandomKeysAmount(){
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_KEYS.amount,Constants.MAX_NUMBER_OF_KEYS.amount+1);
        }
    }
}
