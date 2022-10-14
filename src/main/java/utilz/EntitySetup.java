package utilz;

public enum EntitySetup {
    PLAYER(0, 0, 30, 100),
    SKELETON(100, 100, 30, 100);
    Coordinate startingCoordinate;
    int hitBoxWidth;
    int hitBoxHeight;

    EntitySetup(int startX, int startY, int hitBoxWidth, int hitBoxHeight){
        this.startingCoordinate = new Coordinate(startX, startY);
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
    }

    public Coordinate getStartCoordinate() {
        return startingCoordinate;
    }

    public int getStartX(){
        return startingCoordinate.getX();
    };

    public int getStartY(){
        return startingCoordinate.getY();
    };

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }
}