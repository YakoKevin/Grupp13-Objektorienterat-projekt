package model.level.room;

import utilz.CardinalDirection;

import java.util.Iterator;
import java.util.function.Function;

public interface RoomTypeFunction extends Function<Iterator<CardinalDirection>, Room> {
}
