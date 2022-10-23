package model.entity;

import java.awt.*;

/**
 * Interface for entities that should be able to be hit when something attacks.
 */
public interface Attackable {
    void getHit(Rectangle r, double atkD);
}
