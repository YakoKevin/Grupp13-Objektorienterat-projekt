package Models.level;

public class LevelGenerator implements MapGenerator{

    @Override
    public LevelMap generate(int sizeX, int sizeY) {
        return SquareMap.generate(sizeX);
    }
}
