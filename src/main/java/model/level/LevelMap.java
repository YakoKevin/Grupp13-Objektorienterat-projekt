package model.level;

import model.level.room.Door;
import utilz.CardinalDirection;
import utilz.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: Fix so that we have an coordinate object instead of int[]?

/**
 * An object for containing a node matrix, a neighbor matrix and the coordinates for a start and a finish node.
 */
public class LevelMap {
    private final MapGraph mapGraph;
    private final Coordinate[] mainNodePathCoordinates;
    private final Coordinate startCoordinate;
    private final Coordinate finishCoordinate;
    private final int size;

    public LevelMap(Coordinate[] mainNodePathCoordinates, Coordinate startCoordinate, Coordinate finishCoordinate, int size){
        this.mapGraph = new MapGraph();
        this.mainNodePathCoordinates = mainNodePathCoordinates;
        this.startCoordinate = startCoordinate;
        this.finishCoordinate = finishCoordinate;
        this.size = size;
    }


    public int[][] getMatrixOfMap(boolean withDoors) {
        if(withDoors) {
            return mapGraph.asMatrixBig();
        } else
            return mapGraph.asMatrixSmall();
    }

    public int getMapSize(){
        return size;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getFinishCoordinate() {
        return finishCoordinate;
    }

    public Coordinate[] getMainNodePathCoordinates() {
        return mainNodePathCoordinates.clone();
    }

    public Coordinate[] getNodesCoordinates() {
        return mapGraph.getNodesCoordinates().clone();
    }

    public void placeDoorsAtNode(int x, int y, CardinalDirection... directionList){
        mapGraph.placeDoorsAtNode(x, y, directionList);
    }

    public void placeDoorsAtNode(Coordinate coordinate, CardinalDirection... directionList){
        mapGraph.placeDoorsAtNode(coordinate.getX(), coordinate.getY(), directionList);
    }

    public Iterator<Door> getNodeDoors(Coordinate nodeCoordinate) throws NoSuchElementException {
        return mapGraph.getNodeDoors(nodeCoordinate);
    }

    public void addCoordinatePath(Coordinate[] path){
        for (Coordinate coordinate : path) {
            mapGraph.addNode(new MapNode(coordinate.copy()));
        }
    }

    public boolean getIfCoordinateIsNode(int x, int y, int[][] mapMatrix) {
        return mapMatrix[x][y] > 0;
    }


    /**
     * A graph representing the map, holding nodes and useful methods.
     */
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

        private int[][] asMatrixBig(){
            int[][] matrix = new int[size*2-1][size*2-1];

            for (MapNode node : nodes) {
                matrix[node.coordinate.getX()*2][node.coordinate.getY()*2] = 2;
                for(Door door : node.doors) {
                    switch (door) {
                        case EAST -> matrix[node.coordinate.getX()*2 + 1][node.coordinate.getY()*2] = 1;
                        case NORTH -> matrix[node.coordinate.getX()*2][node.coordinate.getY()*2 - 1] = 1;
                        case SOUTH -> matrix[node.coordinate.getX()*2][node.coordinate.getY()*2 + 1] = 1;
                        case WEST -> matrix[node.coordinate.getX()*2 - 1][node.coordinate.getY()*2] = 1;
                    }

                }
            }

            return matrix;
        }

        private int[][] asMatrixSmall(){
            int[][] matrix = new int[size][size];

            for (MapNode node : nodes) {
                matrix[node.coordinate.getX()][node.coordinate.getY()] = 2;
            }

            return matrix;
        }

        public void placeDoorsAtNode(int x, int y, CardinalDirection... directionList){
            for(MapNode node : nodes){
                if(node.coordinate.getX() == x && node.coordinate.getY() == y) {
                    for (CardinalDirection direction : directionList) {
                        node.addDoorDirection(direction);
                    }
                }
            }
        }

        public Coordinate[] getNodesCoordinates() {
            Coordinate[] nodesCoordinates = new Coordinate[nodes.size()];

            for (int i = 0; i < nodes.size(); i++){
                Coordinate coordinate = nodes.get(i).getCoordinate().copy();
                nodesCoordinates[i] = coordinate;
            }
            return nodesCoordinates;
        }

        public Iterator<Door> getNodeDoors(Coordinate nodeCoordinate) throws NoSuchElementException {
            for (MapNode node : nodes){
                if (nodeCoordinate.equals(node.getCoordinate()))
                    return node.getDoors();
            }
            throw new NoSuchElementException();
        }
    }


    /**
     * Node class for use with levelMap. Has coordinates but also direction of connected neighbours (doors).
     */
    private class MapNode {
        private final Coordinate coordinate;

        private final ArrayList<Door> doors = new ArrayList<>();

        private MapNode(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        private Coordinate getCoordinate(){
            return coordinate;
        }

        private void addDoorDirection(CardinalDirection doorDirection){
            doors.add(Door.getDoorFromCardinalDirection(doorDirection));
        }

        private Iterator<Door> getDoors(){
            return doors.iterator();
        }

    }
}

