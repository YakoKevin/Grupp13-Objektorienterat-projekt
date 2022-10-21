package model.level;

import model.entity.Player;

/**
 * Factory class for levels. Contains all the different types of levels available to create. Is a facade tp the level module.
 *
 * @see LevelMap
 * @see Level
 */
public class LevelFactory {

    /**
     * Creates a {@code SimpleLevel}.
     *
     * @param size of the map, where area will be size*size.
     * @param player is the player of the game.
     * @return a {@code SimpleLevel} as a {@code Level}.
     *
     * @see Player
     */
    public Level simpleLevel(int size, Player player){
        LevelMap levelMap = SquareMapGenerator.generate(size);
        return new SimpleLevel(levelMap, player);
    }
}
