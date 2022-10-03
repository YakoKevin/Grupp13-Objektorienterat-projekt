package model.level;

import entity.Player;

public class LevelFactory {

    public Level simpleLevel(int size, Player player){
        LevelMap levelMap = SquareMapGenerator.generate(size);
        return new SimpleLevel(levelMap, player);
    }
}
