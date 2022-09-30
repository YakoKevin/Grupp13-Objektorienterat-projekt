package model.level;

public class LevelFactory {

    public Level simpleLevel(int size){
        LevelMap levelMap = SquareMapGenerator.generate(size);
        return new SimpleLevel(levelMap);
    }
}
