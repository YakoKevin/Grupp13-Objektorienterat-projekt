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

public class GamePanel extends JPanel {


    private GameApp gameApp;
    private KeyView keyView;
    private FPSUpdater fpsUpdater;

    private Movement movement;
    private Animation animation;
    protected Rectangle hitbox; // Debugging purposes

    BufferedImage playerImage;
    Entity player;
    LevelManager levelManager = new LevelManager(); // TODO: FLYTTA!

    public GamePanel(GameApp gameApp, Movement movement, Attack attack, UpdateFrame updateFrame, FPSUpdater fpsUpdater, Entity player, Animation animation){
        addKeyListener(new ActionController(this, movement, attack));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;
        playerImage = ImageServer.getImage(ImageServer.Ids.PLAYER);
        this.player = player;
        this.animation = animation;
        this.movement = movement;
        inititateHitbox();

        KeyView keyView = new KeyView(width, -30, width, 30, 0);

        this.fpsUpdater = fpsUpdater;
        //startGameLoop();
    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GameConstants.GameSizes.WIDTH.getSize(), GameConstants.GameSizes.HEIGHT.getSize());
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GameConstants.GameSizes.WIDTH.getSize() + " : " + GameConstants.GameSizes.HEIGHT.getSize());
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
    }

    public GameApp getGameApp() {
        return gameApp;
    }




}
