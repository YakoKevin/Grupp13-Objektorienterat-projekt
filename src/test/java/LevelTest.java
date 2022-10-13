import entity.Player;
import model.level.*;
import model.level.room.Door;
import org.junit.Test;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.GameConstants;

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
    public void doorTest(){
        //TODO: fix issue with duplicate doors
        int size = 4;
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.simpleLevel(size, new Player(new Coordinate(1, 1),0,0));
        level.playerEnterRoom(new Coordinate(1,1), CardinalDirection.WEST);
        Iterator<Door> doors = level.getCurrentRoomDoors();

        while (doors.hasNext()) {
            System.out.println("x: " + level.getCurrentRoomDoors().next().getCoordinate().getX() + " and y: " + level.getCurrentRoomDoors().next().getCoordinate().getY());
            doors.next();
        }
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
