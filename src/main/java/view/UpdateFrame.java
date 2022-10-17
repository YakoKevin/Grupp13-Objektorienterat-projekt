package view;

import entity.Player;
import model.AttackModel;
import utilz.Coordinate;
import utilz.ImageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UpdateFrame extends JPanel {
    protected Rectangle hitbox; // Debugging purposes

    Player player;
    LevelManager levelManager = new LevelManager(); // TODO: FLYTTA!

    // make more generic
    public UpdateFrame(Player player){
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.green);


        this.player = player;
        inititateHitbox();
    }
    // Debugging purposes
    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    // Debugging purposes
    private void inititateHitbox() {
        hitbox = new Rectangle((int)player.getX(), (int)player.getY(), 200, 200);
    }

    // Debugging purposes
    protected void updateHitbox(){
        hitbox.x = (int)player.getX();
        hitbox.y = (int)player.getY();
    }

    public void update(){
        //Animation.updateAnimationTick();
        //Animation.setAnimation();
        updateHitbox();

    }
    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        levelManager.draw(g);
        drawUI(g);

        if(player.getAttackMode()==true){
            System.out.println("t h");
            AttackView atkV = new AttackView();
            AttackModel atkM = new AttackModel(); //TODO: detta borde inte vara här -- hämta från spelaren ist.
            Coordinate c = new Coordinate((int)player.getX(), (int)player.getY());
            c = atkM.getAttackCoordinate(c,this.player.getDirection(),player.getWidth(),player.getHeight());
            atkV.drawAttackRectangle(g,c.getX(),c.getY(),(int)player.getAttackRange(),(int)player.getAttackRange());
        }
        player.setAttackMode(false);
        render(g);

    }

    public void drawUI(Graphics g) { //kanske kan separera dessa om man vill
        String hpStr = Double.toString(player.getHealthPoints());
        g.setFont(new Font("Araial", Font.BOLD, 12));
        g.setColor(new Color(255, 0, 70));
        g.drawString("HP: " + hpStr,10,10);
        g.drawString("Keys: " + "player.getKeyCount()", 10,30);
        g.drawString("Score: " + "player.getScoreCount()", 10,50);
    }

    protected void render(Graphics g){
        Animation.render(g);
    }

}
