package Models.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An object for containing a node matrix, a neighbor matrix and the coordinates for a start and a finish node.
 */
public class LevelMap {
    private final MapGraph mapGraph;
    private final int[][] mainNodePathCoordinates;
    private final int[] startCoordinate;
    private final int[] finishCoordinate;
    private final int size;

    public LevelMap(int[][] mainNodePathCoordinates, int[] startCoordinate, int[] finishCoordinate, int size){
        this.mapGraph = new MapGraph();
        this.mainNodePathCoordinates = mainNodePathCoordinates;
        this.startCoordinate = startCoordinate;
        this.finishCoordinate = finishCoordinate;
        this.size = size;
    }

    public int[][] getMatrixOfMap() {
        return mapGraph.asMatrix();
    }

    public int getMapSize(){
        return size;
    }

    public int[] getStartCoordinate() {
        return startCoordinate.clone();
    }

    public int[] getFinishCoordinate() {
        return finishCoordinate.clone();
    }

    public int[][] getMainNodePathCoordinates() {
        return mainNodePathCoordinates.clone();
    }

    public void placeDoorsAtNode(int x, int y, CardinalDirection... directionList){
        mapGraph.placeDoorsAtNode(x, y, directionList);
    }

    public void addNode(int x, int y){
        mapGraph.addNode(new MapNode(x, y));
    }

    public void addCoordinatePath(int[][] path){
        for (int[] i : path) {
            mapGraph.addNode(new MapNode(i[0], i[1]));
        }
    }

    private class MapGraph {
        private final ArrayList<MapNode> nodes;

        private MapGraph() {
            this.nodes = new ArrayList<>();
        }

        private void addNode(MapNode mapNode){
            nodes.add(mapNode);
        }

        private int getLength(){
            return nodes.size();
        }

        private int[][] asMatrix(){
            int[][] matrix = new int[size*2-1][size*2-1];

            for (MapNode node : nodes) {
                matrix[node.coordinateX*2][node.coordinateY*2] = 2;
                for(CardinalDirection door : node.doors) {
                    switch (door) {
                        case EAST -> matrix[node.coordinateX*2 + 1][node.coordinateY*2] = 1;
                        case NORTH -> matrix[node.coordinateX*2][node.coordinateY*2 - 1] = 1;
                        case SOUTH -> matrix[node.coordinateX*2][node.coordinateY*2 + 1] = 1;
                        case WEST -> matrix[node.coordinateX*2 - 1][node.coordinateY*2] = 1;
                    }

                }
            }

            return matrix;
        }

        public void placeDoorsAtNode(int x, int y, CardinalDirection... directionList){
            for(MapNode node : nodes){
                if(node.coordinateX == x && node.coordinateY == y) {
                    for (CardinalDirection direction : directionList) {
                        node.addDoorDirection(direction);
                    }
                }
            }
        }
    }

    /**
     * Node class for use with levelMap. Has coordinates but also direction of connected neighbours (doors).
     */
    private class MapNode {
        private final int coordinateX;
        private final int coordinateY;

        private final HashSet<CardinalDirection> doors;

        private MapNode(int coordinateX, int coordinateY) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
            this.doors = new HashSet<>();
        }

        private int getCoordinateX() {
            return coordinateX;
        }

        private int getCoordinateY() {
            return coordinateY;
        }

        private void addDoorDirection(CardinalDirection doorDirection){
            doors.add(doorDirection);
        }

        private Iterator<CardinalDirection> getDoorDirections(){
            return doors.iterator();
        }

    }
}

