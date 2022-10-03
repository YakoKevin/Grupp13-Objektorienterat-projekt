package model.level;

import model.level.room.Cavern;
import model.level.room.RoomTypeFunction;


public class SimpleLevel extends Level{

    protected SimpleLevel(LevelMap levelMap) {
        super(levelMap);
        this.roomTypes.add(RoomTypes.cavern);
    }

    private enum RoomTypes{
        ;
        static private final RoomTypeFunction cavern = Cavern::new;
    }
}
