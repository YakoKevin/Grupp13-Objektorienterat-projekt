package model.entity;

import model.Coordinate;

import static model.entity.EntitySetup.PLAYER;

public class PlayerFactory {

    public static  Player createPlayer(){
        Player player = new Player(new Coordinate(PLAYER.getStartX(), PLAYER.getStartY()), PLAYER.getHitBoxWidth(), PLAYER.getHitBoxHeight());
        return player;
    }
}
