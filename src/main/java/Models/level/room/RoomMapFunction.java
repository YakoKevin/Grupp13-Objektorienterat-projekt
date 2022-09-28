package Models.level.room;

import Models.level.CardinalDirection;

import java.util.Iterator;
import java.util.function.Function;

//TODO: Maybe change to javas own Function<T, R> if we have no own code in this class?

public interface RoomMapFunction extends Function<Iterator<CardinalDirection>, Room> {
}
