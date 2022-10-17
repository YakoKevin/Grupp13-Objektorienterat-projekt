package utilz;

import java.util.concurrent.ThreadLocalRandom;

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

    public Coordinate copy(){
        return new Coordinate(this.x, this.y);
    }

    public Coordinate left(){
        return new Coordinate(x - 1, y);
    }
    public Coordinate right(){
        return new Coordinate(x + 1, y);
    }
    public Coordinate up(){
        return new Coordinate(x, y - 1);
    }
    public Coordinate down(){
        return new Coordinate(x, y + 1);
    }

    public static Coordinate randomCoordinate(int xBoundLower, int yBoundLower, int xBoundUpper, int yBoundUpper){
        return new Coordinate(ThreadLocalRandom.current().nextInt(xBoundLower,xBoundUpper+1), ThreadLocalRandom.current().nextInt(yBoundLower,yBoundUpper+1));
    }

    public Coordinate add(Coordinate offset) {
        return new Coordinate(this.x + offset.getX(), this.y  + offset.getY());
    }

    public Coordinate add(Coordinate offset, int multiplier) {
        return new Coordinate(this.x + offset.getX()* multiplier, this.y  + offset.getY()* multiplier);
    }
}

