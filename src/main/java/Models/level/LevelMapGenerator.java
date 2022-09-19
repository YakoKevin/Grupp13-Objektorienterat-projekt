package Models.level;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Functional class for generating a map with pseudo random amount of nodes in it. Takes a size parameter that represents
 * the border size of the map.
 */
public class LevelMapGenerator {

    private static final int NODE_TO_FLOOR_DECREMENT = 10; //TODO: fix for dynamic size
    private static final int NODE_TO_CEILING_DECREMENT = 4;

    public static LevelMap generate(int size) {
        int[][] nodeMatrix = placePathNodesFromStartToEnd(size);
        LevelMap levelMap = new LevelMap();
        levelMap.setNodeMatrix(nodeMatrix);



        return levelMap;
    }

    private static int generateMaxAmountOfNodes(int maxPossibleAmountOfNodes) {
        return ThreadLocalRandom.current().nextInt(maxPossibleAmountOfNodes - NODE_TO_FLOOR_DECREMENT, maxPossibleAmountOfNodes - NODE_TO_CEILING_DECREMENT);
    }

    private static int[][] placePathNodesFromStartToEnd(int size) {
        Direction2D borderForStartingNode = Direction2D.getRandomDirection();
        int[] startingCoordinate = addNodeToBorderOnMatrix(borderForStartingNode, size);

        Direction2D borderForEndingNode = Direction2D.getOpposite(borderForStartingNode);
        int[] endingCoordinate = addNodeToBorderOnMatrix(borderForEndingNode, size);

        int[][] nodeMatrix = placeNodesBetweenStartAndEndInMatrix(size, startingCoordinate, endingCoordinate);

        return nodeMatrix;
    }

    private static int[] addNodeToBorderOnMatrix(Direction2D borderDirection, int sideLength){
        int x = 0;
        int y = 0;

        int[] coordinate = new int[2];

        switch (borderDirection) {
            case NORTH -> {
                x = ThreadLocalRandom.current().nextInt(0, sideLength);
            }
            case EAST -> {
                x = sideLength - 1;
                y = ThreadLocalRandom.current().nextInt(0, sideLength);
            }
            case SOUTH -> {
                y = sideLength - 1;
                x = ThreadLocalRandom.current().nextInt(0, sideLength);
            }
            case WEST -> {
                y = ThreadLocalRandom.current().nextInt(0, sideLength);
            }
        }
        coordinate[0] = x;
        coordinate[1] = y;

        return coordinate;
    }

    private static int[][] placeNodesBetweenStartAndEndInMatrix(int size, int[] startingCoordinate, int[] endingCoordinate) {
        boolean hasFoundExit = false;
        int[] directionalVector = new int[2];
        int[][] nodeMatrix = new int[size][size];

        nodeMatrix[startingCoordinate[0]][startingCoordinate[1]] = 1;

        for(int i = 0; i <= generateMaxAmountOfNodes(size*size); i++){
            directionalVector[0] = startingCoordinate[0] - endingCoordinate[0];
            directionalVector[1] = startingCoordinate[1] - endingCoordinate[1];

            if (Math.abs(directionalVector[0]) > Math.abs(directionalVector[1])) {
                if(directionalVector[0] > 0) {
                    nodeMatrix[startingCoordinate[0] - 1][startingCoordinate[1]] = 1; //We walk "left".
                    startingCoordinate[0]--;
                } else {
                    nodeMatrix[startingCoordinate[0] + 1][startingCoordinate[1]] = 1; //We walk "right".
                    startingCoordinate[0]++;
                }
            } else {
                if(directionalVector[1] > 0) {
                    nodeMatrix[startingCoordinate[0]][startingCoordinate[1] - 1] = 1; //We walk "up".
                    startingCoordinate[1]--;
                } else {
                    nodeMatrix[startingCoordinate[0]][startingCoordinate[1] + 1] = 1; //We walk "down".
                    startingCoordinate[1]++;
                }
            }

            if(startingCoordinate[0] == endingCoordinate[0] && startingCoordinate[1] == endingCoordinate[1]){
                hasFoundExit = true;
                break;
            }
        }

        if (hasFoundExit){
            return nodeMatrix;
        } else
            return new int[size][size];
    }

    private static void placeNodesInBranchesInMatrix() {
    }
}
