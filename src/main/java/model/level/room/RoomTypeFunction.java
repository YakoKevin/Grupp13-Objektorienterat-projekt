package model.level.room;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * A function interface for the rooms in the level module. Every room needs an apply method.
 */
public interface RoomTypeFunction extends Function<ArrayList<Door>, Room> {
}
