package Controllers;

import Views.GamePanel;

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
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            gamePanel.getGameApp().getPlayer().setUp(true);
            System.out.println("PRESSED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            gamePanel.getGameApp().getPlayer().setRight(true);
            System.out.println("PRESSED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGameApp().getPlayer().setDown(true);
            System.out.println("PRESSED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGameApp().getPlayer().setLeft(true);
            System.out.println("PRESSED D");
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            gamePanel.getGameApp().getPlayer().setAttack(true);
            System.out.println("PRESSED SPACE");
            // player.attack() liksom
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            gamePanel.getGameApp().getPlayer().setUp(false);
            System.out.println("RELEASED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            gamePanel.getGameApp().getPlayer().setRight(false);
            System.out.println("RELEASED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGameApp().getPlayer().setDown(false);
            System.out.println("RELEASED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGameApp().getPlayer().setLeft(false);
            System.out.println("RELEASED D");
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("RELEASED SPACE");
            // player.attack() liksom
        }
        //gamePanel.getGameApp().getPlayer().setMoving(false); // inte bra metod anrop, fixafixafixa
    }
}
