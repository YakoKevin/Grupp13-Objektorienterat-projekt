package view;

import controller.ActionController;
import entity.Entity;
import entity.Player;
import general.GameApp;
import model.Attack;
import model.Movement;
import utilz.GameConstants;
import utilz.ImageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static general.GameApp.GAME_HEIGHT;
import static general.GameApp.GAME_WIDTH;

class Pause extends SwingWorker<Void,String> {

    @Override
    protected Void doInBackground() throws Exception {
        while(!isCancelled()) {
            publish(String.format(null, (new Random()).nextInt(99999)));
            Thread.sleep(100);
        }
        return null;
    }

}

public class GamePanel extends JPanel {


    private GameApp gameApp;
    private KeyView keyView;
    private FPSUpdater fpsUpdater;

    private Movement movement;
    private Animation animation;
    private Animation animationEnemy;
    protected Rectangle hitbox; // Debugging purposes
    private Pause pause;

    BufferedImage playerImage;
    Entity player;
    LevelManager levelManager = new LevelManager(); // TODO: FLYTTA!

    public GamePanel(GameApp gameApp, Movement movement, Attack attack, UpdateFrame updateFrame, FPSUpdater fpsUpdater, Entity player, Animation animation, Animation animationEnemy){
        addKeyListener(new ActionController(this, movement, attack));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;
        playerImage = ImageServer.getImage(ImageServer.Ids.PLAYER);
        this.player = player;
        this.animation = animation;
        this.animationEnemy = animationEnemy;
        this.movement = movement;
        inititateHitbox();

        JButton jb=new JButton("Pause");
        jb.setBackground(Color.BLUE);
        jb.setBounds(515, 300, 80, 30);
        this.add(jb);
        JButton jb1=new JButton("Resume");
        jb1.setBackground(Color.BLUE);
        jb1.setBounds(600, 300, 80, 30);
        this.add(jb1);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (pause = new Pause()).execute();
                try {
                    gameApp.pauseThread();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(pause != null) {
                        pause.cancel(true);
                        jb.setEnabled(true);
                        jb1.setEnabled(false);
                        gameApp.resumeThread();
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        KeyView keyView = new KeyView(width, -30, width, 30, 0);

        this.fpsUpdater = fpsUpdater;
        //startGameLoop();
    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
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

        animationEnemy.updateAnimationTick();
        animationEnemy.setAnimation();

        movement.updatePosition();

        updateHitbox();

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        levelManager.draw(g);
        drawHitbox(g);
        //g.drawImage(playerImage, (int)player.getX(), (int)player.getY(), null);

        render(g);

    }

    protected void render(Graphics g){
        animation.render(g);
        animationEnemy.render(g);
    }
}
