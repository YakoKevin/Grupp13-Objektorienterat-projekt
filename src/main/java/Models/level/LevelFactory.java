package Models.level;

public class LevelFactory {

    public ILevel createSimpleLevel(MapSize size){
        LevelMap levelMap = SquareMap.generate(size);
        return new SimpleLevel(levelMap);
    }
}
