package model.level;

import model.entity.Player;
import model.level.room.Cavern;
import model.level.room.RoomTypeFunction;

 /**
 * A simple {@code Level} instance. It has the {@code Cavern} for its roomtype.
 *
 * @see Level
 * @see Cavern
 * @see RoomTypeFunction
 */
public class SimpleLevel extends Level{

    protected SimpleLevel(LevelMap levelMap, Player player) {
        super(levelMap, player, RoomTypes.cavern);
    }

    private enum RoomTypes{
        ;
        static private final RoomTypeFunction cavern = Cavern::new;
    }
}
