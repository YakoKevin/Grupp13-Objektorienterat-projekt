package model.level;

public class LevelFactory {

    public Level createCavern(int size){
        LevelMap levelMap = SquareMapGenerator.generate(size);
        return new SimpleLevel(levelMap);
    }
}
