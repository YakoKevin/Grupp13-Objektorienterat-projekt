import Models.level.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LevelTest {

    @Test
    public void doesRoomHaveEntryOppositeToWherePlayerWalkedIn() {
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.createSimpleLevel(4);
        for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
            level.playerEnterRoom(new Coordinate(0,0), cardinalDirection);
            assertEquals(cardinalDirection.getOppositeDirection(), level.getCurrentRoomEntry());
        }

    }

    public void willLevelCalculateIfCoordinateIsInWall(){

    }

    @Test
    public void drawRoom() {
        int size = 4;
        LevelFactory levelFactory = new LevelFactory();
        Level level = levelFactory.createSimpleLevel(size);
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
