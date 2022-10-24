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
    /**
     * Setting up cavern with room objects.
     * @param doors
     */
    public Cavern(ArrayList<Door> doors) {
        super(doors, new EnemyFactory());
        createWalls();
        addObstacles();
        addEnemies(enemyFactory);
        addKeys();
        addCoins();
        addHeart();
    }

    /**
     * Creating the room's walls
     */
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

    /**
     * Adding wall on the vertical side of the room.
     * @param doorCoordinates
     * @param x
     */
    private void addVerticalWallIfPossible(ArrayList<Coordinate> doorCoordinates, int x) {
        for (int y = 0; y < HEIGHT; y++){
            addWallIfPossible(doorCoordinates, x, y);
        }
    }

    /**
     * Adding wall on the horizontal side of the room.
     * @param y
     * @param doorCoordinates
     */
    private void addHorizontalWallIfPossible(int y, ArrayList<Coordinate> doorCoordinates) {
        for (int x = 0; x < WIDTH; x++) {
            addWallIfPossible(doorCoordinates, x, y);
        }
    }

    /**
     * Only adding wall if there is not a door at that coordinate.
     * @param doorCoordinates
     * @param x
     * @param y
     */
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

    /**
     * Adding enemies randomly to the room.
     * @param enemyFactory
     */
    private void addEnemies(EnemyFactory enemyFactory) {
        for (int i = 0; i <= Constants.getRandomEnemiesAmount(); i++) {
            int x = ThreadLocalRandom.current().nextInt(1, GameConstants.RoomMapSizes.WIDTH.getSize());
            int y = ThreadLocalRandom.current().nextInt(1, GameConstants.RoomMapSizes.HEIGHT.getSize());
            Enemy enemy = enemyFactory.createSkeleton();
            enemy.setCoordinate(new Coordinate(x, y));
            enemies.add(enemy);
        }
    }

    /**
     * Adding obstacles randomly to the room
     */
    private void addObstacles() {
        for (int i = 0; i < Constants.getRandomObstaclesAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            obstaclesCoordinates.add(randomCoordinate);
        }
    }

    /**
     * Adding keys randomly to the room
     */
    private void addKeys() {
        for (int i = 0; i < Constants.getRandomKeysAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            keysCoordinates.add(randomCoordinate);
        }
    }

    /**
     * Adding coins randomly to the room
     */
    private void addCoins() {
        for (int i = 0; i < Constants.getRandomKeysAmount(); i++) {
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
            coinsCoordinates.add(randomCoordinate);
        }
    }

    /**
     * Adding heart coordinate somewhere random in the room.
     */
    private void addHeart() {
        heartCoordinate = Coordinate.randomCoordinate(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * Constants for minimum and maximum amount of objects in the room.
     */
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

        /**
         * Setting up amount
         * @param amount
         */
        Constants(int amount) {
            this.amount = amount;
        }

        /**
         * Getting random enemies amount
         * @return
         */
        static int getRandomEnemiesAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_ENEMIES.amount, Constants.MAX_NUMBER_OF_ENEMIES.amount + 1);
        }
        /**
         * Getting random obstacles amount
         * @return
         */
        static int getRandomObstaclesAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_OBSTACLES.amount, Constants.MAX_NUMBER_OF_OBSTACLES.amount + 1);
        }
        /**
         * Getting random keys amount
         * @return
         */
        static int getRandomKeysAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_KEYS.amount, Constants.MAX_NUMBER_OF_KEYS.amount + 1);
        }
        /**
         * Getting random keys amount
         * @return
         */
        static int getRandomCoinsAmount() {
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_COINS.amount, Constants.MAX_NUMBER_OF_COINS.amount + 1);
        }
    }
}
