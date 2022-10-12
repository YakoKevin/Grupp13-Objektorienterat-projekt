package entity;

import java.util.ArrayList;

public interface Friendly extends Attackable {

    void addHostilesList(ArrayList<Hostile> hostile);
}
