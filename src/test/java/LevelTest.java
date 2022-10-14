import entity.Player;
import model.level.*;
import model.level.room.Door;
import org.junit.Test;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LevelTest {

    @Test
    public void doesRoomHaveEntryOppositeToWherePlayerWalkedIn() {
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(4,  new Player(new Coordinate(0, 0),0,0));
        for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
            level.playerEnterRoom(new Coordinate(0,0), cardinalDirection);
            assertEquals(cardinalDirection.getOppositeDirection(), level.getCurrentRoomEntry());
        }

    }

    @Test
    public void doesLevelCalculateIfCoordinateIsBlocked(){
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(4, new Player(new Coordinate(0, 0),0,0));
        level.playerEnterRoom(new Coordinate(0,0), CardinalDirection.WEST);
        for(int i = 0; i < 30; i++){
            for (int j = 0; j < 18; j++){
                Coordinate playerCoord = new Coordinate(i, j);
                boolean blocked = level.isCoordinateInWallOrObstacle(playerCoord);
                boolean blocked2 = false;
                for (Coordinate obstaclesCoordinates : level.getCurrentRoomObstacles()) {
                    if (obstaclesCoordinates.equals(playerCoord)) {
                        blocked2 = true;
                        break;
                    }
                }
                for (Coordinate wallCoordinates : level.getCurrentRoomWalls()) {
                    if (wallCoordinates.equals(playerCoord)) {
                        blocked2 = true;
                        break;
                    }
                }
                assertEquals(blocked, blocked2);
            }
        }
    }

    @Test
    public void doorTest(){
        //TODO: fix issue with duplicate doors
        int size = 4;
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(size, new Player(new Coordinate(1, 1),0,0));
        level.playerEnterRoom(new Coordinate(1,1), CardinalDirection.WEST);
        ArrayList<Door> doors = level.getCurrentRoomDoors();
    }

    @Test
    public void drawRoom() {
        int size = 4;
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(size, new Player(new Coordinate(0, 0),0,0));

        int[][] nodeMatrix = level.getCurrentRoomAsMatrix();
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < GameConstants.RoomMapSizes.HEIGHT.getSize(); i++) {
            for (int j = 0; j < GameConstants.RoomMapSizes.WIDTH.getSize(); j++) {
                matrixString.append(nodeMatrix[i][j]).append(" ");
            }
            matrixString.append("\n");
        }
        System.out.println(matrixString);
    }

}
