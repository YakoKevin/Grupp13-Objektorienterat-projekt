package model.level;

import entity.Player;
import model.level.room.Cavern;
import model.level.room.RoomTypeFunction;


public class SimpleLevel extends Level{

    protected SimpleLevel(LevelMap levelMap, Player player) {
        super(levelMap, player, RoomTypes.cavern);
    }

    private enum RoomTypes{
        ;
        static private final RoomTypeFunction cavern = Cavern::new;
    }
}
