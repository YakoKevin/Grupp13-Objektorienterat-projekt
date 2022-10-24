package model.entity;

import model.Coordinate;

import static model.entity.EntitySetup.PLAYER;

/**
 * Factory for creating a {@code Player} object.
 */
public class PlayerFactory {
    /**
     * Creating player
     * @return
     */
    public static  Player createPlayer(){
        Player player = new Player(new Coordinate(PLAYER.getStartX(), PLAYER.getStartY()), PLAYER.getHitBoxWidth(), PLAYER.getHitBoxHeight());
        return player;
    }
}
