package Models.level;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//TODO: Fix so that the rest utilizes Coordinate instead of int[]. Fix why < 4 in size is problematic.

/**
 * Functional class for generating a map with pseudo random amount of nodes in it. Takes a size parameter that represents
 * the border size of the map. Generates a LevelMap with a random amount of nodes and edges connecting those. There is
 * always a path from a starting coordinate to an end coordinate.
 */
public class SquareMap {

    public static LevelMap generate(MapSize mapSize) {
        int size = mapSize.toInt();
        if(size < 4)
        {
            size = 4;
        }
        LevelMap levelMapWithPath = placePathNodesFromStartToEnd(size);
        placeBranchingNodesInLevelMap(levelMapWithPath);
        placeBranchingNodesInLevelMap(levelMapWithPath);
        placeBranchingNodesInLevelMap(levelMapWithPath);
        placeBranchingNodesInLevelMap(levelMapWithPath);
        placeEdgesForSortedPath(levelMapWithPath);
        placeEdgesRandomlyForNodes(levelMapWithPath);

        return levelMapWithPath;
    }

    private static LevelMap placePathNodesFromStartToEnd(int size) {
        CardinalDirection borderForStartingNode = CardinalDirection.getRandomDirection();
        CardinalDirection borderForEndingNode = borderForStartingNode.getOpposite();

        int[] startingCoordinate = addNodeToBorderOn(borderForStartingNode, size);
        int[] endingCoordinate = addNodeToBorderOn(borderForEndingNode, size);

        int[][] mainNodePath = placeNodesBetweenStartAndEnd(size*size, startingCoordinate, endingCoordinate);

        LevelMap levelMap = new LevelMap(mainNodePath, startingCoordinate, endingCoordinate, size);
        levelMap.addCoordinatePath(mainNodePath);

        return levelMap;
    }

    private static int[] addNodeToBorderOn(CardinalDirection borderDirection, int sideLength){
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

    private static int[][] placeNodesBetweenStartAndEnd(int maxAmountOfNodes, int[] startCoordinate, int[] endCoordinate) {
        int[] directionalVector = new int[2];
        int[][] mainPath = new int[maxAmountOfNodes][2];

        int[] startingCoordinate = startCoordinate.clone();
        int[] endingCoordinate = endCoordinate.clone();

        mainPath[0][0] = startingCoordinate[0];
        mainPath[0][1] = startingCoordinate[1];
        int i = 1;
        for( ; i <= maxAmountOfNodes; i++){
            directionalVector[0] = startingCoordinate[0] - endingCoordinate[0];
            directionalVector[1] = startingCoordinate[1] - endingCoordinate[1];

            if(startingCoordinate[0] == endingCoordinate[0] && startingCoordinate[1] == endingCoordinate[1]){
                break;
            }

            if (Math.abs(directionalVector[0]) > Math.abs(directionalVector[1])) {
                if(directionalVector[0] > 0) {
                    mainPath[i][0] = startingCoordinate[0] - 1; //We walk "left".
                    mainPath[i][1] = startingCoordinate[1];
                    startingCoordinate[0]--;
                } else {
                    mainPath[i][0] = startingCoordinate[0] + 1; //We walk "right".
                    mainPath[i][1] = startingCoordinate[1];
                    startingCoordinate[0]++;
                }
            } else {
                if(directionalVector[1] > 0) {
                    mainPath[i][0] = startingCoordinate[0]; //We walk "up".
                    mainPath[i][1] = startingCoordinate[1] - 1;
                    startingCoordinate[1]--;
                } else {
                    mainPath[i][0] = startingCoordinate[0]; //We walk "down".
                    mainPath[i][1] = startingCoordinate[1] + 1;
                    startingCoordinate[1]++;
                }
            }
        }

        return  Arrays.copyOf(mainPath, i+1); //Trunking of the array to only have length of the path length.
    }

    /*
     * Takes a random node in the main path. Takes another random end node. Makes a path between them.
     */
    private static void placeBranchingNodesInLevelMap(LevelMap levelMap) {
        int index = ThreadLocalRandom.current().nextInt(0, levelMap.getMainNodePathCoordinates().length);
        int[] startCoordinate = levelMap.getMainNodePathCoordinates()[index];

        int size = levelMap.getMapSize();
        int[] endCoordinate = new int[2];

        int indexY = ThreadLocalRandom.current().nextInt(0, size);
        int indexX = ThreadLocalRandom.current().nextInt(0, size);

        endCoordinate[0] = indexX;
        endCoordinate[1] = indexY;

        int[][] nodePath = placeNodesBetweenStartAndEnd(size*size, startCoordinate, endCoordinate);

        levelMap.addCoordinatePath(nodePath);
    }

    private static void placeEdgesForSortedPath(LevelMap levelMap){
        for(int i = 0; i < levelMap.getMainNodePathCoordinates().length - 1; i++) {
            int[][] coord = levelMap.getMainNodePathCoordinates();
            if(coord[i][0] + 1 == levelMap.getMainNodePathCoordinates()[i+1][0]){
                levelMap.placeDoorsAtNode(coord[i][0], coord[i][1], CardinalDirection.EAST);
            } else if(coord[i][0] - 1 == levelMap.getMainNodePathCoordinates()[i+1][0]){
                levelMap.placeDoorsAtNode(coord[i][0], coord[i][1], CardinalDirection.WEST);
            } else if(coord[i][1] + 1 == levelMap.getMainNodePathCoordinates()[i+1][1]){
                levelMap.placeDoorsAtNode(coord[i][0], coord[i][1], CardinalDirection.SOUTH);
            } else if(coord[i][1] - 1 == levelMap.getMainNodePathCoordinates()[i+1][1]){
                levelMap.placeDoorsAtNode(coord[i][0], coord[i][1], CardinalDirection.NORTH);
            }
        }
    }

    public static void placeEdgesRandomlyForNodes(LevelMap levelMap){
        for(int i = 0; i < 25; i++){
            Coordinate[] nodeCoordinates = levelMap.getNodesCoordinates();
            int randomIndex = ThreadLocalRandom.current().nextInt(0, nodeCoordinates.length);
            CardinalDirection randomDirection = CardinalDirection.getRandomDirection();
            int[][] mapMatrix = levelMap.getMatrixOfMap(false);

            switch (randomDirection) {
                case WEST -> {
                    if(nodeCoordinates[randomIndex].getX() != 0 && levelMap.getIfCoordinateIsNode(nodeCoordinates[randomIndex].getX() - 1, nodeCoordinates[randomIndex].getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                    }
                }
                case SOUTH -> {
                    if(nodeCoordinates[randomIndex].getY() != levelMap.getMapSize() - 1 && levelMap.getIfCoordinateIsNode(nodeCoordinates[randomIndex].getX(), nodeCoordinates[randomIndex].getY() + 1, mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                    }
                }
                case EAST -> {
                    if(nodeCoordinates[randomIndex].getX() != levelMap.getMapSize() - 1 && levelMap.getIfCoordinateIsNode(nodeCoordinates[randomIndex].getX() + 1, nodeCoordinates[randomIndex].getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                    }
                }
                case NORTH -> {
                    if(nodeCoordinates[randomIndex].getY() != 0 && levelMap.getIfCoordinateIsNode(nodeCoordinates[randomIndex].getX(), nodeCoordinates[randomIndex].getY() - 1, mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                    }
                }
            }
        }
    }
}
