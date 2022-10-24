package controller;

import model.entity.Player;
import model.CardinalDirection;
import model.entity.LivingStates;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***
 * This class handles Key Listening.
 */
public class ActionController implements KeyListener {
    private CardinalDirection dir = CardinalDirection.EAST;
    private Player player;
    private boolean up,down,left,right=false;


    public ActionController(Player player){
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /***
     * This is called when a key is pressed.
     * @param event The pressed key.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
        player.setState(LivingStates.ATTACK);
        player.attack();
        return;
    }
        if (event.getKeyCode() == KeyEvent.VK_W) {
            up=true;
            dir=CardinalDirection.NORTH;
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            left=true;
            dir = CardinalDirection.WEST;

        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            down=true;
            dir= CardinalDirection.SOUTH;
        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            right=true;

            dir=CardinalDirection.EAST;
        }
        if(up&&right){
            dir = CardinalDirection.NORTHEAST;
        }
        else if(up&&left){
            dir = CardinalDirection.NORTHWEST;
        }
        else if(down&&left){
            dir = CardinalDirection.SOUTHWEST;
        }
        else if(down&&right){
            dir = CardinalDirection.SOUTHEAST;
        }
        if(up||right||down||left){
            player.setDirection(dir);
            player.setState(LivingStates.RUNNING);
            player.setVelX((float)player.getMovementSpeed() * (float) dir.getHypothenuseReciprocal() * dir.getXOffset());
            player.setVelY((float) player.getMovementSpeed() * (float) dir.getHypothenuseReciprocal() * dir.getYOffset());
        }
    }


    /***
     * This is called when a key is released.
     * @param event The released key.
     */
    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            up=false;
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            left=false;
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            down=false;
        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            right=false;
        }
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setState(LivingStates.IDLE);
        }
        if(!(up||down)){
            player.setVelY(0);
        }
        if(!(left||right)){
            player.setVelX(0);
        }
        if(up||right||down||left){
            if(up&&!down){
                player.setVelY(-(float)player.getMovementSpeed());
                //dir = CardinalDirection.NORTH;
            }
            if(!up&&down){
                player.setVelY((float)player.getMovementSpeed());
                //dir = CardinalDirection.SOUTH;
            }
            if(!right&&left){
                player.setVelX(-(float)player.getMovementSpeed());
            }
            if(right&&!left){
                player.setVelX((float)player.getMovementSpeed());
            }
            player.setState(LivingStates.RUNNING);
        }
        else{
            player.setState(LivingStates.IDLE);
        }
    }
}