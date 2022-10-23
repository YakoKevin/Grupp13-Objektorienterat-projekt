package model.entity;

/**
 * Interface for all the entities who are on the side of {@code Enemy} and that hits {@code Friendly}. Can only support
 * one {@code Friendly} ({@code Player}) at the time.
 *
 * @see Player
 * @see Friendly
 * @see Enemy
 */
public interface Hostile extends Attackable {

    void addFriendly(Friendly friendly);

}
