import Models.level.Coordinate;
import Models.level.LevelMap;
import Models.level.SquareMapGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapGenerationTest {



    @Test
    public void doesLevelMapContainAMapWithNodes() {
        for(int k = 1; k < 10; k++){
            for (int i = 0; i < 10; i++) {
                LevelMap levelMap = SquareMapGenerator.generate(k);
                assertEquals(levelMap.getNodesCoordinates().length, 0);
            }
        }
    }

    @Test
    public void doesMapGeneratorNotReturnANullMap() {
        int size = 4;
        LevelMap levelMap = SquareMapGenerator.generate(size);
        assertNotNull(levelMap);
    }

    @Test
    public void drawMap(){
        int size = 4;
        LevelMap levelMap = SquareMapGenerator.generate(size);
        int[][] nodeMatrix = levelMap.getMatrixOfMap(true);
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < size*2-1; i++) {
            for (int j = 0; j < size*2-1; j++) {
                matrixString.append(nodeMatrix[i][j]).append(" ");
            }
            matrixString.append("\n");
        }
        System.out.println(matrixString);
    }
}
