package Models.level;

public class LevelFactory {

    public Level createSimpleLevel(int size){
        LevelMap levelMap = SquareMapGenerator.generate(size);
        return new SimpleLevel(levelMap);
    }
}
