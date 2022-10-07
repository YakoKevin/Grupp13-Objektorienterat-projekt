package general;

import entity.KeyItem;
import model.Animation;
import model.Attack;
import model.Movement;
import model.level.Level;
import model.level.LevelFactory;
import model.level.LevelManager;
import utilz.EntitySetup;
import utilz.GameConstants.*;
import utilz.ImageServer;
import view.GamePanel;
import view.GameView;
import entity.EnemyBrain;
import entity.Player;
import entity.Skeleton;

import java.awt.*;

public class GameApp implements Runnable {

    private LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;


    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = RefreshRate.MAX_FPS.getRate();
    private final int MAX_UPS = RefreshRate.MAX_UPS.getRate();

    //Inget av dessa borde finnas som en direkt referens här i GameApp - byt till att fråga currentLevel istället
    //private EnemyBrain enemyBrain;
    private Skeleton skel;
    private Player player;
    private KeyItem key;
    private LevelManager levelManager;

    //TODO: lägg dessa i Player så att level har lätt tillgång till dem, eller någonstans där de passar. Borde nog inte
    // ligga GameApp.
    private Movement movement;
    private Animation animation;
    private Attack attack;


    public GameApp(){
        firstSetup();

        levelManager = new LevelManager(this);
        //player = new Player(100, 100, 30, 100);
        key = new KeyItem(450, 350, 40, 40);
        player.loadLevelData(levelManager.getCurrentLevel());
        skel=new Skeleton(50,50);
        skel.loadEnemyImages();
        skel.addEnemies();
        gamePanel = new GamePanel(this, movement, attack);
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();


        startGameLoop();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        //enemyBrain.draw(g);
        skel.draw(g);
        player.draw(g);
        animation.render(g);
        /*if(player.isAttacking()){
            attack.drawAttackHitbox(g);
        }*/
        //player.drawHP(g);
        key.draw(g);
        currentLevel.drawEntities(g);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        movement.updatePosition();
        //animation.update();
        //enemyBrain.update();
        skel.update();
        levelManager.update();
        /*boolean collision = key.isCollision((int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight());
        if(collision)
        {
            key.setNewPosition();
            player.addKey();
        }*/
        currentLevel.update();
        animation.update();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS;
        double timePerUpdate = 1000000000.0 / MAX_UPS;
        long previousTime = System.nanoTime();
        int fps = 0;
        int updates = 0;
        long lastChecked = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamePanel.repaint();
                fps++;
                deltaF--;
            }

            //if (now - lastFrame >= timePerFrame){
            //    gamePanel.repaint();
            //    lastFrame = System.nanoTime();
            //    fps++;
            //}

            if (System.currentTimeMillis() - lastChecked >= 1000){
                lastChecked = System.currentTimeMillis();
                System.out.println("FPS: " + fps + " | UPS: " + updates);

                fps = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost(){
        movement.resetDirectionBooleans();
    }

    private void firstSetup(){
        Player player = setupPlayer();

        setupLevel(player);
    }

    public Player setupPlayer(){
        Player player = new Player(EntitySetup.PLAYER.getHitBoxWidth(), EntitySetup.PLAYER.getHitBoxHeight(), EntitySetup.PLAYER.getWidth(), EntitySetup.PLAYER.getHeight());
        this.player = player;
        animation = new Animation(ImageServer.Ids.PLAYER, player);
        movement = new Movement(player, animation);
        attack = new Attack(animation);
        return player;
    }

    private void setupLevel(Player player){
        currentLevel = levelFactory.simpleLevel(LevelSizes.MEDIUM.getSize(), player);
    }
}
