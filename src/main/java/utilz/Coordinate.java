package utilz;

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

    @Override
    public boolean equals(Object coordinate) {
        if (!super.equals(coordinate)) {
            return coordinate instanceof Coordinate && x == ((Coordinate) coordinate).getX() && y == ((Coordinate) coordinate).getY();
        } else
            return true;
    }
}

