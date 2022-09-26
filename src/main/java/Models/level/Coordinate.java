package Models.level;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getTuple() {
        return new int[]{x,y};
    }

    public boolean equals(Coordinate coordinate){
        return x == coordinate.getX() && y == coordinate.getY();
    }
}

