package Controllers;

import Models.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Directions.*;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;

    public ActionController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            //gamePanel.movementInYAxis(-5);
            gamePanel.setPlayerDirection(UP);
            System.out.println("W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            //gamePanel.movementInXAxis(-5);
            gamePanel.setPlayerDirection(LEFT);
            System.out.println("A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
           // gamePanel.movementInYAxis(5);
            gamePanel.setPlayerDirection(DOWN);
            System.out.println("S");
        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            //gamePanel.movementInXAxis(5);
            gamePanel.setPlayerDirection(RIGHT);
            System.out.println("D");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("RELEASED W");
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("RELEASED A");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("RELEASED S");
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("RELEASED D");
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Player attacking ");
            // player.attack() liksom
        }
        gamePanel.setMoving(false);
    }
}
