package entity;

import utilz.Coordinate;
import utilz.ImageServer;
import view.Animation;

import static utilz.EntitySetup.PLAYER;

public class PlayerFactory {

    public static  Player createPlayer(){
        Player player = new Player(new Coordinate(PLAYER.getStartX(), PLAYER.getStartY()), PLAYER.getHitBoxWidth(), PLAYER.getHitBoxHeight());
        Animation.addEntity(player, ImageServer.Ids.PLAYER);
        return player;
    }
}
