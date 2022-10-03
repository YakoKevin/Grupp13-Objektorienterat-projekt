import model.level.*;
import org.junit.Test;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/*
public class LevelTest {

    @Test
    public void doesRoomHaveEntryOppositeToWherePlayerWalkedIn() {
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(4);
        for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
            level.playerEnterRoom(new Coordinate(0,0), cardinalDirection);
            assertEquals(cardinalDirection.getOppositeDirection(), level.getCurrentRoomEntry());
        }

    }

    @Test
    public void doesLevelCalculateIfCoordinateIsBlocked(){
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(4);
        level.playerEnterRoom(new Coordinate(0,0), CardinalDirection.WEST);
        for(int i = 0; i < 30; i++){
            for (int j = 0; j < 18; j++){
                Coordinate playerCoord = new Coordinate(i, j);
                boolean blocked = level.isCoordinateInWallOrObstacle(playerCoord);
                boolean blocked2 = false;
                for (Iterator<Coordinate> obstaclesCoordinates = level.getCurrentRoomObstacles(); obstaclesCoordinates.hasNext(); ) {
                    Coordinate coordinate = obstaclesCoordinates.next();
                    if(coordinate.equals(playerCoord))
                        blocked2 = true;
                }
                for (Iterator<Coordinate> wallCoordinates = level.getCurrentRoomWalls(); wallCoordinates.hasNext(); ) {
                    Coordinate coordinate = wallCoordinates.next();
                    if(coordinate.equals(playerCoord))
                        blocked2 = true;
                }
                assertEquals(blocked, blocked2);
            }
        }
    }

    @Test
    public void doesRoomHaveCorrectNumberOfDoors(){

    }

    @Test
    public void drawRoom() {
        int size = 4;
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(size);
        assertNotNull(level);

        int[][] nodeMatrix = level.getCurrentRoomAsMatrix();
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 30; j++) {
                matrixString.append(nodeMatrix[i][j]).append(" ");
            }
            matrixString.append("\n");
        }
        System.out.println(matrixString);
    }

}
*/