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

    BufferedImage playerImage;
    Player player;
    Animation animation;
    LevelManager levelManager = new LevelManager(); // TODO: FLYTTA!

    // make more generic
    public UpdateFrame(Player player, Animation animation){
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.green);

        playerImage = ImageServer.getImage(ImageServer.Ids.PLAYER);
        this.player = player;
        this.animation = animation;
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
        animation.updateAnimationTick();
        animation.setAnimation();

        updateHitbox();

    }
    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        levelManager.draw(g);
        drawUI(g);
        System.out.println("t h");
        //drawHitbox(g);
        g.drawImage(playerImage, (int)player.getX(), (int)player.getY(), null);
        if(player.getAttackMode()==true){
            //System.out.println("defgrtxz ");
            AttackView atkV = new AttackView();
            AttackModel atkM = new AttackModel();
            Coordinate c = new Coordinate((int)player.getX(), (int)player.getY());
            c = atkM.getAttackCoordinate(c,this.player.getDirection());
            atkV.drawAttackRectangle(g,c.getX(),c.getY(),100,100);
        }
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
        animation.render(g);
    }

}
