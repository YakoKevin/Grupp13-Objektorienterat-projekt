package controller;

import model.Attack;
import model.Movement;
import utilz.Coordinate;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;
    private Movement movement;
    private Attack attack;
    private Coordinate coordinate = new Coordinate(0,0); // FIXA COORDINATES, TILLFÄLLIGT FÖR ATT FÅ PROGRAMMET ATT KÖRA
    public static int dir; //tillfällig för att veta åt vilket håll spelaren står (0=A,1=D,2=W,3=S)

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
            movement.setUp(true);
            dir=2;
            System.out.println("PRESSED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            movement.setRight(true);
            System.out.println("PRESSED A");
            dir=0;
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            movement.setDown(true);
            dir=3;
            System.out.println("PRESSED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            movement.setLeft(true);
            System.out.println("PRESSED D");
            dir=1;
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("PRESSED SPACE");
            Coordinate coordinate = new Coordinate((int)movement.getX(), (int)movement.getY()); // är detta tillåtet?
            attack.attack(coordinate, dir);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            movement.setUp(false);
            System.out.println("RELEASED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            movement.setRight(false);
            System.out.println("RELEASED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            movement.setDown(false);
            System.out.println("RELEASED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            movement.setLeft(false);
            System.out.println("RELEASED D");
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("RELEASED SPACE");
        }
    }
}
