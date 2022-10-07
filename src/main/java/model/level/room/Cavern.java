package model.level.room;

import entity.EnemyFactory;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

//TODO: add method to remove obstacles in the way of doors

/**
 * A simple rectangular room with doors and obstacles and enemies.
 */
public class Cavern extends Room {

    public Cavern(Iterator<Door> doors) {
        super(doors, new EnemyFactory());
        createWalls();
        addDoors(doors);
        addObstacles();
        addEnemies(enemyFactory);
    }

    private void createWalls(){
        int y = 0;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        y = WIDTH -1;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        int x = 0;
        for (y = 0; y < WIDTH; y++)
            wallCoordinates.add(new Coordinate(x,y));

        x = HEIGHT -1;
        for (y = 0; y < WIDTH; y++)
            wallCoordinates.add(new Coordinate(x,y));
    }

    private void addDoors(Iterator<Door> doorsIt) {
        while (doorsIt.hasNext()) {
            doors.add(doorsIt.next());
        }
    }

    private void removeWalls(){

    }

    private void addEnemies(EnemyFactory enemyFactory) {
        for (int i = 0; i <= Constants.getRandomEnemiesAmount(); i++) {
            enemies.add(enemyFactory.createSkeleton());
        }
    }

    private void addObstacles() {
        for (int i = 0; i < Constants.getRandomObstaclesAmount(); i++){
            Coordinate randomCoordinate = Coordinate.randomCoordinate(1,1, WIDTH-1, HEIGHT-1);
            obstaclesCoordinates.add(randomCoordinate);
        }
    }

    private enum Constants{
        MIN_NUMBER_OF_ENEMIES(0),
        MAX_NUMBER_OF_ENEMIES(4),
        MIN_NUMBER_OF_OBSTACLES(5),
        MAX_NUMBER_OF_OBSTACLES(10);
        int amount;

        private Constants(int amount){
            this.amount = amount;
        }

        static int getRandomEnemiesAmount(){
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_ENEMIES.amount,Constants.MAX_NUMBER_OF_ENEMIES.amount+1);
        }

        static int getRandomObstaclesAmount(){
            return ThreadLocalRandom.current().nextInt(Constants.MIN_NUMBER_OF_OBSTACLES.amount,Constants.MAX_NUMBER_OF_OBSTACLES.amount+1);
        }
    }
}
