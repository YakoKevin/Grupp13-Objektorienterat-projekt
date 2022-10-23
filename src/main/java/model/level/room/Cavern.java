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
        addCoins();
        addHeart();
    }

    private void createWalls() {
        int y = 0;
        ArrayList<Coordinate> doorCoordinates = new ArrayList<>();

        for (Door door : doors) {
            doorCoordinates.add(door.coordinate);
        }

        addHorizontalWallIfPossible(y, doorCoordinates);

        y = HEIGHT - 1;
        addHorizontalWallIfPossible(y, doorCoordinates);

        int x = 0;
        addVerticalWallIfPossible(doorCoordinates, x);

        x = WIDTH - 1;
        addVerticalWallIfPossible(doorCoordinates, x);
    }

    private void addVerticalWallIfPossible(ArrayList<Coordinate> doorCoordinates, int x) {
        for (int y = 0; y < HEIGHT; y++){
            addWallIfPossible(doorCoordinates, x, y);
        }
    }

    private void addHorizontalWallIfPossible(int y, ArrayList<Coordinate> doorCoordinates) {
        for (int x = 0; x < WIDTH; x++) {
            addWallIfPossible(doorCoordinates, x, y);
        }
    }

    private void addWallIfPossible(ArrayList<Coordinate> doorCoordinates, int x, int y) {
        boolean intersectingDoor = false;
        Coordinate wallCoordinate = new Coordinate(x, y);
        for (Coordinate coordinate : doorCoordinates) {
            if (coordinate.equals(wallCoordinate)) {
                intersectingDoor = true;
                break;
            }
        }
        if (!intersectingDoor) {
            wallCoordinates.add(wallCoordinate);
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
        for (int i = 0; i < Constants.getRandomObstaclesAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            obstaclesCoordinates.add(randomCoordinate);
        }
    }

    private void addKeys() {
        for (int i = 0; i < Constants.getRandomKeysAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            keysCoordinates.add(randomCoordinate);
        }
    }

    private void addCoins() {
        for (int i = 0; i < Constants.getRandomKeysAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            coinsCoordinates.add(randomCoordinate);
        }
    }

    private void addHeart() {
        heartCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
    }


    private enum Constants {
        MIN_NUMBER_OF_ENEMIES(0),
        MAX_NUMBER_OF_ENEMIES(4),
        MIN_NUMBER_OF_OBSTACLES(5),
        MAX_NUMBER_OF_OBSTACLES(10),
        MIN_NUMBER_OF_KEYS(2),
        MAX_NUMBER_OF_KEYS(5),
        MIN_NUMBER_OF_COINS(3),
        MAX_NUMBER_OF_COINS(6);
        int amount;

        Constants(int amount) {
            this.amount = amount;
        }

        static int getRandomEnemiesAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_ENEMIES.amount, Constants.MAX_NUMBER_OF_ENEMIES.amount + 1);
        }

        static int getRandomObstaclesAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_OBSTACLES.amount, Constants.MAX_NUMBER_OF_OBSTACLES.amount + 1);
        }

        static int getRandomKeysAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_KEYS.amount, Constants.MAX_NUMBER_OF_KEYS.amount + 1);
        }

        static int getRandomCoinsAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_COINS.amount, Constants.MAX_NUMBER_OF_COINS.amount + 1);
        }
    }
}
