package view;

import entity.Entity;
import utilz.ImageServer;

public interface Animator {
    void addEntity(Entity entity, ImageServer.Ids imageId);
}
