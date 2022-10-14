package entity;


import utilz.Coordinate;

public interface Hostile extends Attackable {

    void addFriendly(Friendly friendly);
}
