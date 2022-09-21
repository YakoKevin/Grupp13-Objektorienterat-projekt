import Models.level.LevelMap;
import Models.level.LevelMapGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapGenerationTest {

    @Test
    public void doesLevelMapFunctionReturnALevelMap() {
        byte size = 4;
        LevelMap levelMap = LevelMapGenerator.generate(size);
        int[][] nodeMatrix = levelMap.getMatrixOfMap();
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < size*2-1; i++) {
            for (int j = 0; j < size*2-1; j++) {
                matrixString.append(nodeMatrix[i][j]).append(" ");
            }
            matrixString.append("\n");
        }
        System.out.println(matrixString);
        assertNotNull(levelMap);
    }

    @Test
    public void doesLevelMapContainAGoodAmountOfEdges() {

    }

    @Test
    public void doesLevelMapContainAMapWithNodes() {

    }

    @Test
    public void doesLevelMapHaveCorrectDimensions() {

    }
}
