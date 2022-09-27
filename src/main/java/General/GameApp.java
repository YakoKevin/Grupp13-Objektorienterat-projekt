package General;

import Models.level.LevelManager;
import Views.GamePanel;
import Views.GameView;
import entities.EnemyBrain;
import entities.Player;

import java.awt.*;

public class GameApp implements Runnable {

    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;
    private EnemyBrain enemyBrain;
    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;





    public GameApp(){
        levelManager = new LevelManager(this);
        player = new Player(200, 200, 30, 100);
        player.loadLevelData(levelManager.getCurrentLevel());
        enemyBrain = new EnemyBrain();
        enemyBrain.addEnemies();
        gamePanel = new GamePanel(this);
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();

    }

    public void render(Graphics g){
        levelManager.draw(g);
        enemyBrain.draw(g);
        player.render(g);

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        enemyBrain.update();
        levelManager.update();
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
        player.resetDirectionBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
