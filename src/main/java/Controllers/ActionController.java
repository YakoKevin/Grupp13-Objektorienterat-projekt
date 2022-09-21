package Controllers;

import Models.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;

    public ActionController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            gamePanel.movementInYAxis(-5);
            System.out.println("W");
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            gamePanel.movementInXAxis(-5);
            System.out.println("A");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.movementInYAxis(5);
            System.out.println("S");
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.movementInXAxis(5);
            System.out.println("D");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
