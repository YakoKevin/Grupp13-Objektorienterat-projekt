import Models.level.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LevelTest {

    @Test
    public void doesTheLevelHasWalls() {
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
