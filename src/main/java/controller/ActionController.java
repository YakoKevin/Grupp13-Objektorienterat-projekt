package controller;

import entity.Player;
import model.AttackModel;
import model.Movement;
import utilz.CardinalDirection;
import utilz.Coordinate;
import utilz.EntityStates;
import view.GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class ActionController implements KeyListener {

    private GamePanel gamePanel;
    private Movement movement;
    private AttackModel attack;
    private Coordinate coordinate = new Coordinate(0,0); // FIXA COORDINATES, TILLFÄLLIGT FÖR ATT FÅ PROGRAMMET ATT KÖRA
    private CardinalDirection dir = CardinalDirection.EAST;
    private Player player;
    private boolean up,down,left,right,space=false;


    public ActionController(GamePanel gamePanel, Player player){
        this.gamePanel = gamePanel;
        this.movement = player.getMovement();
        this.player = player;
        this.attack = player.getAttack();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(player.getX()<0 || player.getY()<0) {
            return;
        }
        if (event.getKeyCode() == KeyEvent.VK_W) {
            up=true;
            //movement.setUp(true);

            //player.setVelY(-(float)player.getMovementSpeed());


            dir=CardinalDirection.NORTH;

            //System.out.println(player.getX()  + "-" + player.getY());
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            left=true;

            //player.setVelX(-(float)player.getMovementSpeed());

            //movement.setRight(true);
            //System.out.println("PRESSED A");

            dir = CardinalDirection.WEST;

        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            down=true;

            //player.setVelY((float)player.getMovementSpeed());

            //movement.setDown(true);
            dir= CardinalDirection.SOUTH;

            //System.out.println("PRESSED S");
        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            right=true;

            //player.setVelX((float)player.getMovementSpeed());

            //movement.setLeft(true);
            //System.out.println("PRESSED D");

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
        player.setDirection(dir);
        if(up||right||down||left){
            player.setState(EntityStates.RUNNING);
            //player.updateMovement();

            player.setVelX((float)player.getMovementSpeed() * (float) dir.getHypothenuseReciprocal() * dir.getXOffset());
            player.setVelY((float) player.getMovementSpeed() * (float) dir.getHypothenuseReciprocal() * dir.getYOffset());
        }

        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            player.attack();
            player.setAttackMode(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        float tempVelX,tempVelY;
        player.setState(EntityStates.IDLE);
        if (event.getKeyCode() == KeyEvent.VK_W) {
            up=false;


            player.setVelY(0);

            //System.out.println("RELEASED W");
        }
        else if (event.getKeyCode() == KeyEvent.VK_A) {
            left=false;

            player.setVelX(0);

            //System.out.println("RELEASED A");
        }
        else if (event.getKeyCode() == KeyEvent.VK_S) {
            down=false;
            player.setVelY(0);

            //System.out.println("RELEASED S");

        }
        else if (event.getKeyCode() == KeyEvent.VK_D) {
            right=false;
            player.setVelX(0);

            //System.out.println("RELEASED D");
        }



        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setAttackMode(false); //tillfälligt, ska kanske vara en timer hur länge man attackerar
        }
        //gamePanel.getGameApp().getPlayer().setMoving(false); // inte bra metod anrop, fixafixafixa
    }
}