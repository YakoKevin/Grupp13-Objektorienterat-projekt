package Models.level;

public enum MapSize {
    SMALL, MEDIUM, LARGE;

    public int toInt(){
        return ordinal() + 4;
    }
}
