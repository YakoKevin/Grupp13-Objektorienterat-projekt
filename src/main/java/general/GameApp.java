package general;

import entity.KeyItem;
import model.Movement;
import model.level.Level;
import model.level.LevelFactory;
import model.level.LevelManager;
import utilz.GameConstants;
import view.GamePanel;
import view.GameView;
import entity.EnemyBrain;
import entity.Player;

import java.awt.*;

public class GameApp implements Runnable {

    private LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;


    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;

    //Inget av dessa borde finnas som en direkt referens här i GameApp - byt till att fråga currentLevel istället
    private EnemyBrain enemyBrain;
    private Player player;
    private KeyItem key;
    private LevelManager levelManager;
    private Movement movement;

    // SKAPA EN KLASS MED DESSA
    /*
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.3f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;*/



    public GameApp(){
        levelManager = new LevelManager(this);
        player = new Player(100, 100, 30, 100);
        movement = new Movement(player);
        key = new KeyItem(450, 350, 40, 40);
        //player.loadLevelData(levelManager.getCurrentLevel());
        enemyBrain = new EnemyBrain();
        enemyBrain.addEnemies();
        gamePanel = new GamePanel(this, movement);
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();


        firstSetup();
        startGameLoop();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        enemyBrain.draw(g);
        player.render(g);
        if(player.getAttack()==true){
            player.drawAttackHitbox(g);
        }
        player.drawHP(g);
        key.draw(g);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        movement.update();
        enemyBrain.update();
        levelManager.update();
        boolean collision = key.isCollision((int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight());
        if(collision)
        {
            key.setNewPosition();
            player.addKey();
        }
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

    public Player getPlayer() {
        return player;
    }

    private void firstSetup(){
        Player player = new Player(100, 100, 30, 100);
        setupLevel(player);
    }

    private void setupLevel(Player player){
        currentLevel = levelFactory.simpleLevel(GameConstants.LevelSizes.MEDIUM.getSize(), player);
    }
}
