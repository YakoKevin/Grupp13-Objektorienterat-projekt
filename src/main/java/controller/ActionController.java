package controller;


import model.Attack;
import model.Movement;
import utilz.CardinalDirection;
import utilz.Coordinate;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;
    private Movement movement;
    private Attack attack;
    private Coordinate coordinate = new Coordinate(0,0); // FIXA COORDINATES, TILLFÄLLIGT FÖR ATT FÅ PROGRAMMET ATT KÖRA
    public static CardinalDirection dir;
    //public static int dir; //tillfällig för att veta åt vilket håll spelaren står (0=A,1=D,2=W,3=S)


    public ActionController(GamePanel gamePanel, Movement movement, Attack attack){
        this.gamePanel = gamePanel;
        this.movement = movement;
        this.attack = attack;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            //gamePanel.getGameApp().getPlayer().setUp(true);
            movement.setUp(true);
            dir=CardinalDirection.NORTH;

            //System.out.println("PRESSED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            //gamePanel.getGameApp().getPlayer().setRight(true);
            movement.setRight(true);
            //System.out.println("PRESSED A");
            dir = CardinalDirection.WEST;
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            //gamePanel.getGameApp().getPlayer().setDown(true);
            movement.setDown(true);
            dir=CardinalDirection.SOUTH;
            //System.out.println("PRESSED S");
        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            //gamePanel.getGameApp().getPlayer().setLeft(true);
            movement.setLeft(true);
            //System.out.println("PRESSED D");

            dir=CardinalDirection.EAST;
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            //attack.setAttack(true);
            //gamePanel.getGameApp().getPlayer().setAttack(true);
            //System.out.println("PRESSED SPACE");
            //gamePanel.getGameApp().getPlayer().setAttack(true);
            //gamePanel.getGameApp().getPlayer().attack();
            //player.isAttacking=true;
            gamePanel.getGameApp().getPlayer().setAttackMode(true); //fult

            //Level.getPlayer().setAttackMode(true);
            Coordinate coordinate = new Coordinate((int)movement.getX(), (int)movement.getY()); // är detta tillåtet? Ja hyfsat
            //System.out.println("x och y player ActionController: " + movement.getX()+" och " + movement.getY());

            attack.getAttackCoordinate(coordinate, dir);

            //attack.setAttack(true);
            //gamePanel.getGameApp().getPlayer().setAttack(true);
        }
        gamePanel.getGameApp().getPlayer().setDirection(dir); //tillfälligt, fult
        //Level.getPlayer().setDirection(dir); //behöver ett objekt här
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            //gamePanel.getGameApp().getPlayer().setUp(false);
            movement.setUp(false);
            //System.out.println("RELEASED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            //gamePanel.getGameApp().getPlayer().setRight(false);
            movement.setRight(false);
            //System.out.println("RELEASED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            //gamePanel.getGameApp().getPlayer().setDown(false);
            movement.setDown(false);
            //System.out.println("RELEASED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            //gamePanel.getGameApp().getPlayer().setLeft(false);
            movement.setLeft(false);
            //System.out.println("RELEASED D");
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            //System.out.println("RELEASED SPACE");

            //attack.setAttack(false);
            //gamePanel.getGameApp().getPlayer().setAttack(false); //tillfälligt, ska kanske vara en timer hur länge man attackerar
        }
        //gamePanel.getGameApp().getPlayer().setMoving(false); // inte bra metod anrop, fixafixafixa
    }
}
