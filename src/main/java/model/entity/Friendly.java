package model.entity;

import java.util.ArrayList;

/**
 * Interface for all the entities who are on the side of {@code Player} and that hits {@code Hostiles}.
 *
 * @see Player
 * @see Hostile
 */
public interface Friendly extends Attackable {

    void addHostilesList(ArrayList<Hostile> hostile);
}
