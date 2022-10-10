package utilz;

public enum EntitySetup {
    PLAYER(100, 100, 30, 100),
    SKELETON(100, 100, 30, 100);
    int hitBoxWidth;
    int hitBoxHeight;
    int width;
    int height;

    EntitySetup(int hitBoxWidth, int hitBoxHeight, int width, int height){
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public int getWidth() {
        return width;
    }
}