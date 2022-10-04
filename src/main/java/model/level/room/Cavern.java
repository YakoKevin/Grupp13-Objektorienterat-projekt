package model.level.room;

import entity.Enemy;
import entity.EnemyFactory;
import entity.Hostile;
import entity.Player;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple rectangular room with a size of 30x18. It has no obstacles or doors.
 */
public class Cavern extends Room {

    private int MIN_NUMBER_OF_ENEMIES;
    private int MAX_NUMBER_OF_ENEMIES;

    public Cavern(Iterator<CardinalDirection> doors) {
        super(doors, new EnemyFactory());
        createWalls();
        addDoors(doors);
        addEnemies(enemyFactory);
    }



    private void createWalls(){
        int y = 0;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        y = LENGTH -1;
        for (int x = 0; x < HEIGHT; x++)
            wallCoordinates.add(new Coordinate(x,y));

        int x = 0;
        for (y = 0; y < LENGTH; y++)
            wallCoordinates.add(new Coordinate(x,y));

        x = HEIGHT -1;
        for (y = 0; y < LENGTH; y++)
            wallCoordinates.add(new Coordinate(x,y));
    }

    private void addDoors(Iterator<CardinalDirection> doorsIt) {
        while (doorsIt.hasNext()) {
            CardinalDirection doorDirection = doorsIt.next();
            Door doorToAdd = Door.getDoorFromCardinalDirection(doorDirection);
            if(doorToAdd != null)
                doors.add(doorToAdd);
        }
    }

    private void removeWalls(){

    }

    private void addEnemies(EnemyFactory enemyFactory) {
        for (int i = 0; i <= ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_ENEMIES,MAX_NUMBER_OF_ENEMIES+1); i++) {
            enemies.add(enemyFactory.createSkeleton());
        }
    }

    private void givePlayerHostiles(Player player){
        ArrayList<Hostile> hostiles = new ArrayList<>(enemies);
    }
}
