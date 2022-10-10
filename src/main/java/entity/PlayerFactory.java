package entity;

import static utilz.EntitySetup.PLAYER;

public class PlayerFactory {

    public Player createPlayer(){
        return new Player(PLAYER.getStartX(), PLAYER.getStartY(), PLAYER.getHitBoxWidth(), PLAYER.getHitBoxHeight());
    }
}
