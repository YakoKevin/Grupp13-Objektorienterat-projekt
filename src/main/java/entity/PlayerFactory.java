package entity;

import utilz.Coordinate;

import static utilz.EntitySetup.PLAYER;

public class PlayerFactory {

    public Player createPlayer(){
        return new Player(new Coordinate(PLAYER.getStartX(), PLAYER.getStartY()), PLAYER.getHitBoxWidth(), PLAYER.getHitBoxHeight());
    }
}
