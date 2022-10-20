package utilz;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A coordinate class. Has several useful methods.
 */
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-axis value.
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y-axis value.
     */
    public int getY() {
        return y;
    }

    /**
     * @return boolean if two coordinates share the same x and y value.
     */
    @Override
    public boolean equals(Object coordinate) {
        if (!super.equals(coordinate)) {
            return coordinate instanceof Coordinate && x == ((Coordinate) coordinate).getX() && y == ((Coordinate) coordinate).getY();
        } else
            return true;
    }

    /**
     * @return a {@code coordinate} copy with the same values as this {@code coordinate}.
     */
    public Coordinate copy(){
        return new Coordinate(this.x, this.y);
    }

    /**
     * @return a {@code coordinate} copy with the same values as this {@code coordinate} but -1 x-value.
     */
    public Coordinate left(){
        return new Coordinate(x - 1, y);
    }

    /**
     * @return a {@code coordinate} copy with the same values as this {@code coordinate} but +1 x-value.
     */
    public Coordinate right(){
        return new Coordinate(x + 1, y);
    }

    /**
     * @return a {@code coordinate} copy with the same values as this {@code coordinate} but -1 y-value.
     */
    public Coordinate up(){
        return new Coordinate(x, y - 1);
    }

    /**
     * @return a {@code coordinate} copy with the same values as this {@code coordinate} but +1 y-value.
     */
    public Coordinate down(){
        return new Coordinate(x, y + 1);
    }

    /**
     * Makes a random {@code coordinate}.
     * @param xBoundLower the lower x-value.
     * @param yBoundLower the lower y-value.
     * @param xBoundUpper the upper x-value.
     * @param yBoundUpper the upper y-value.
     * @return a {@code coordinate} with ranom x,y-values depending on the parameters.
     */
    public static Coordinate randomCoordinate(int xBoundLower, int yBoundLower, int xBoundUpper, int yBoundUpper){
        return new Coordinate(ThreadLocalRandom.current().nextInt(xBoundLower,xBoundUpper+1), ThreadLocalRandom.current().nextInt(yBoundLower,yBoundUpper+1));
    }

    /**
     * @param offset the offset to be added to the {@code coordinate}.
     * @return a new {@code coordinate} copy with the same values as this {@code coordinate} but with the added offset.
     */
    public Coordinate add(Coordinate offset) {
        return new Coordinate(this.x + offset.getX(), this.y  + offset.getY());
    }

    /**
     * @param offset the offset to be added to the {@code coordinate}.
     * @param multiplier is a multiplier for the offset.
     * @return a new {@code coordinate} copy with the same values as this {@code coordinate} but with the added offset.
     */
    public Coordinate add(Coordinate offset, int multiplier) {
        return new Coordinate(this.x + offset.getX()* multiplier, this.y  + offset.getY()* multiplier);
    }

    @Override
    public String toString() {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        return "X:"+ this.x*scaling + " -- Y:"+ this.y*scaling;
    }
}

