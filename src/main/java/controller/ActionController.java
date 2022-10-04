package controller;

import model.Movement;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;
    private Movement movement;
    public static double dir; //tillfällig för att veta åt vilket håll spelaren står (0=A,1=D,2=W,3=S)

    public ActionController(GamePanel gamePanel, Movement movement){
        this.gamePanel = gamePanel;
        this.movement = movement;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            //gamePanel.getGameApp().getPlayer().setUp(true);
            movement.setUp(true);
            dir=2;
            System.out.println("PRESSED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            //gamePanel.getGameApp().getPlayer().setRight(true);
            movement.setRight(true);
            System.out.println("PRESSED A");
            dir=0;
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            //gamePanel.getGameApp().getPlayer().setDown(true);
            movement.setDown(true);
            dir=3;
            System.out.println("PRESSED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            //gamePanel.getGameApp().getPlayer().setLeft(true);
            movement.setLeft(true);
            System.out.println("PRESSED D");
            dir=1;
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            gamePanel.getGameApp().getPlayer().setAttack(true);
            System.out.println("PRESSED SPACE");
            //gamePanel.getGameApp().getPlayer().setAttack(true);
            gamePanel.getGameApp().getPlayer().attack();
            gamePanel.getGameApp().getPlayer().setAttack(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            //gamePanel.getGameApp().getPlayer().setUp(false);
            movement.setUp(false);
            System.out.println("RELEASED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            //gamePanel.getGameApp().getPlayer().setRight(false);
            movement.setRight(false);
            System.out.println("RELEASED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            //gamePanel.getGameApp().getPlayer().setDown(false);
            movement.setDown(false);
            System.out.println("RELEASED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            //gamePanel.getGameApp().getPlayer().setLeft(false);
            movement.setLeft(false);
            System.out.println("RELEASED D");
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("RELEASED SPACE");
            gamePanel.getGameApp().getPlayer().setAttack(false); //tillfälligt, ska kanske vara en timer hur länge man attackerar
        }
        //gamePanel.getGameApp().getPlayer().setMoving(false); // inte bra metod anrop, fixafixafixa
    }
}
