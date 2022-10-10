package view;

import controller.ActionController;
import entity.Enemy;
import entity.Player;
import general.GameApp;
import model.AttackModel;
import model.Movement;
import utilz.Coordinate;
import utilz.ImageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    Player player;
    //Entity player;
    LevelManager levelManager = new LevelManager(); // TODO: FLYTTA!
    private final Object lock = new Object(); // TODO: Ska det verkligen vara object?

    public GamePanel(GameApp gameApp, Movement movement, AttackModel attack, UpdateFrame updateFrame, FPSUpdater fpsUpdater, Player player, Animation animation, Animation animationEnemy){
        addKeyListener(new ActionController(this, movement, attack, player));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;
        playerImage = ImageServer.getImage(ImageServer.Ids.PLAYER);
        this.player = player; //TODO: ALLT MED ENTITIES MÅSTE FLYTTAS
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

        jb.addActionListener(new ActionListener() { // TODO: fixa variabel namnen jb och jb1
            @Override
            public void actionPerformed(ActionEvent e) {
                (pause = new Pause()).execute();
                try {
                    pauseThread();
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
                        resumeThread();
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
    public  void pauseThread() throws InterruptedException
    {
        synchronized(lock){
            lock.wait();
        }
    }

    public  void resumeThread()
    {
        synchronized(lock)
        {
//        	gameThread.notify();
            lock.notify();
//            lock.
        }
    }
    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    // Debugging purposes, will be removed
    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    // TODO: Move to seperate Hitbox class
    private void inititateHitbox() {
        hitbox = new Rectangle((int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight());
    }

    // TODO: Move to seperate Hitbox class
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
        if(player.getAttackMode()==true){
           player.getHostilesList();
           /* for(int i =0; i<hostiles.size();i++) {
                hostiles.get(i).checkedIfIsAttacked(this.getPlayerAttackRectangle(),this.getAttackPoints()); //hostiles är bara object, skulle behöva vara enemy
                //sk.checkedIfIsAttacked(this.getPlayerAttackRectangle(), this.getAttackPoints()); //ersätt med lista av enemies, tillfälligt
            }*/
        }

        updateHitbox();
        player.setAttackMode(false); //ska vara timer
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        levelManager.draw(g);
        drawHitbox(g);
        drawUI(g);
        if(player.getAttackMode()==true){
            AttackView atkV = new AttackView();
            AttackModel atkM = new AttackModel();
            Coordinate c = new Coordinate((int)player.getX(), (int)player.getY());
            c = atkM.getAttackCoordinate(c,this.player.getDirection());
            atkV.drawAttackRectangle(g,c.getX(),c.getY(),100,100);
        }
        render(g);
    }

    protected void render(Graphics g){
        animation.render(g);
        animationEnemy.render(g);
    }

    public void drawUI(Graphics g) { //kanske kan separera dessa om man vill
        String hpStr = Double.toString(player.getHealthPoints());
        g.setFont(new Font("Araial", Font.BOLD, 12));
        g.setColor(new Color(255, 0, 70));
        g.drawString("HP: " + hpStr,10,10);
        g.setColor(Color.YELLOW);
        g.drawString("Keys: " + player.getKeyCount(), 10,30);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + player.getScoreCount(), 10,50);
    }
}
