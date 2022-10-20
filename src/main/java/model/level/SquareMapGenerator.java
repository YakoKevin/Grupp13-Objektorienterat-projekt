package model.level;

import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//TODO: Fix so that the rest utilizes Coordinate instead of int[]. Fix why < 4 in size is problematic.

/**
 * Functional class for generating a map with pseudo random amount of nodes in it. Takes a size parameter that represents
 * the border size of the map. Generates a LevelMap with a random amount of nodes and edges connecting those. There is
 * always a path from a starting coordinate to an end coordinate.
 *
 * @see LevelMap
 */
public enum SquareMapGenerator {
    ;

    /**
     *
     * @param size is the size of the square map sides. Is always greater than 4.
     * @return a complete {@code LevelMap} with all the nodes and edges generated.
     */
    public static LevelMap generate(int size) {
        if(size < 4)
            size = 4;

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
        CardinalDirection borderForStartingNode = CardinalDirection.getRandomAxisDirection();
        CardinalDirection borderForEndingNode = borderForStartingNode.getOppositeDirection();

        Coordinate startingCoordinate = addNodeToBorderOn(borderForStartingNode, size);
        Coordinate endingCoordinate = addNodeToBorderOn(borderForEndingNode, size);

        Coordinate[] mainNodePath = placeNodesBetweenStartAndEnd(size*size, startingCoordinate, endingCoordinate);

        LevelMap levelMap = new LevelMap(mainNodePath, startingCoordinate, endingCoordinate, size);
        levelMap.addCoordinatePath(mainNodePath);

        return levelMap;
    }

    private static Coordinate addNodeToBorderOn(CardinalDirection borderDirection, int sideLength){
        int x = 0;
        int y = 0;

        //int[] coordinate = new int[2];

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
        return new Coordinate(x, y);
    }

    private static Coordinate[] placeNodesBetweenStartAndEnd(int maxAmountOfNodes, Coordinate startCoordinate, Coordinate endCoordinate) {
        Coordinate currentCoordinate = startCoordinate.copy();
        Coordinate[] mainPath = new Coordinate[maxAmountOfNodes];
        mainPath[0] = currentCoordinate;

        int i = 1;
        for( ; i < maxAmountOfNodes; i++){
            Coordinate directionality = new Coordinate(currentCoordinate.getX() - endCoordinate.getX(), currentCoordinate.getY() - endCoordinate.getY());

            if(currentCoordinate.equals(endCoordinate)){
                break;
            }

            if (Math.abs(directionality.getX()) > Math.abs(directionality.getY())) {
                if(directionality.getX() > 0) {
                    mainPath[i] = currentCoordinate.left(); //We walk "left"
                } else {
                    mainPath[i] = currentCoordinate.right(); //We walk "right"
                }
            } else {
                if(directionality.getY() > 0) {
                    mainPath[i] = currentCoordinate.up(); //We walk "up".
                } else {
                    mainPath[i] = currentCoordinate.down(); //We walk "down".
                }
            }
            currentCoordinate = mainPath[i];
        }

        return  Arrays.copyOf(mainPath, i); //Trunking of the array to only have length of the path length.
    }
    private static void placeBranchingNodesInLevelMap(LevelMap levelMap) {
        int size = levelMap.getMapSize();

        int index = ThreadLocalRandom.current().nextInt(0, levelMap.getMainNodePathCoordinates().length);
        Coordinate startCoordinate = levelMap.getMainNodePathCoordinates()[index];

        int randomX = ThreadLocalRandom.current().nextInt(0, size);
        int randomY = ThreadLocalRandom.current().nextInt(0, size);
        Coordinate endCoordinate = new Coordinate(randomX, randomY);

        Coordinate[] nodePath = placeNodesBetweenStartAndEnd(size*size, startCoordinate, endCoordinate);

        levelMap.addCoordinatePath(nodePath);
    }

    private static void placeEdgesForSortedPath(LevelMap levelMap) {
        for(int i = 0; i < levelMap.getMainNodePathCoordinates().length - 1; i++) {
            Coordinate[] coordinates = levelMap.getMainNodePathCoordinates();
            if(coordinates[i].getX() + 1 == levelMap.getMainNodePathCoordinates()[i+1].getX()){
                levelMap.placeDoorsAtNode(coordinates[i].getX(), coordinates[i].getY(), CardinalDirection.EAST);
            } else if(coordinates[i].getX() - 1 == levelMap.getMainNodePathCoordinates()[i+1].getX()){
                levelMap.placeDoorsAtNode(coordinates[i].getX(), coordinates[i].getY(), CardinalDirection.WEST);
            } else if(coordinates[i].getY() + 1 == levelMap.getMainNodePathCoordinates()[i+1].getY()){
                levelMap.placeDoorsAtNode(coordinates[i].getX(), coordinates[i].getY(), CardinalDirection.SOUTH);
            } else if(coordinates[i].getY() - 1 == levelMap.getMainNodePathCoordinates()[i+1].getY()){
                levelMap.placeDoorsAtNode(coordinates[i].getX(), coordinates[i].getY(), CardinalDirection.NORTH);
            }
        }
    }

    private static void placeEdgesRandomlyForNodes(LevelMap levelMap){
        for(int i = 0; i < 25; i++){
            Coordinate[] nodeCoordinates = levelMap.getNodesCoordinates();
            int randomIndex = ThreadLocalRandom.current().nextInt(0, nodeCoordinates.length);
            CardinalDirection randomDirection = CardinalDirection.getRandomAxisDirection();
            int[][] mapMatrix = levelMap.getMatrixOfMap(false);

            Coordinate neighbour = nodeCoordinates[randomIndex].add(randomDirection.getOffset());

            switch (randomDirection) {
                case WEST -> {
                    if(nodeCoordinates[randomIndex].getX() != 0 && levelMap.getIfCoordinateIsNode(neighbour.getX(), neighbour.getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                        levelMap.placeDoorsAtNode(neighbour, randomDirection.getOppositeDirection());
                    }
                }
                case SOUTH -> {
                    if(nodeCoordinates[randomIndex].getY() != levelMap.getMapSize() - 1 && levelMap.getIfCoordinateIsNode(neighbour.getX(), neighbour.getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                        levelMap.placeDoorsAtNode(neighbour, randomDirection.getOppositeDirection());
                    }
                }
                case EAST -> {
                    if(nodeCoordinates[randomIndex].getX() != levelMap.getMapSize() - 1 && levelMap.getIfCoordinateIsNode(neighbour.getX(), neighbour.getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                        levelMap.placeDoorsAtNode(neighbour, randomDirection.getOppositeDirection());
                    }
                }
                case NORTH -> {
                    if(nodeCoordinates[randomIndex].getY() != 0 && levelMap.getIfCoordinateIsNode(neighbour.getX(), neighbour.getY(), mapMatrix)){
                        levelMap.placeDoorsAtNode(nodeCoordinates[randomIndex], randomDirection);
                        levelMap.placeDoorsAtNode(neighbour, randomDirection.getOppositeDirection());
                    }
                }
            }
        }
    }
}
