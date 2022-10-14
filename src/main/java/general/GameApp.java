package general;

import entity.*;
import entity.Player;
import model.AttackModel;
import model.Movement;
import model.level.Level;
import model.level.LevelFactory;
import utilz.Coordinate;
import utilz.GameConstants;
import utilz.ImageServer;
import view.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameApp {

    private LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;
    Timer timer = new Timer();


    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;

    //TODO: Inget av dessa borde finnas som en direkt referens här i GameApp - byt till att fråga currentLevel istället
    private Player player;
    private UpdateFrame updateFrame;
    private FPSUpdater fpsUpdater;

    // SKAPA EN KLASS MED DESSA
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.3f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    //private final Object lock = new Object();

    public GameApp(){
        //levelManager = new LevelManager();

        //animation = new Animation(ImageServer.Ids.PLAYER, player);

        //attack = new Attack(animation);
        //key = new KeyItem(new Coordinate(450, 350),  40, 40);
        //player.loadLevelData(levelManager.getCurrentLevel());
        //skel=new Skeleton(200,200);

        //EnemyFactory enemyFactory = new EnemyFactory();
        //enemyFactory.createSkeleton();
        //skel.loadEnemyImages();
        //animationEnemy = new Animation(ImageServer.Ids.ENEMY, skel);
        //**********Allt ovan borde bort.***************

       // movement = new Movement(animation, player, animationEnemy);

        firstSetup();
        fpsUpdater.startGameLoop(); //TODO: fixa med namn så att det är tydligt att det är bara View den uppdaterar
        timer.schedule(new GameTicker(), 100, 100);
    }

    /**
     * The main logic updater.
     */
    public void gameTick(){
        currentLevel.tick();
        //TODO: lägga till mer?
    }

    public void windowFocusLost(){
        //movement.resetDirectionBooleans();
    }

    private void firstSetup(){
        player = PlayerFactory.createPlayer();
        setupLevel(player);
        setupView();
    }


    private void setupView(){
        updateFrame = new UpdateFrame(player);
        gamePanel = new GamePanel(this, fpsUpdater, player);
        fpsUpdater = new FPSUpdater(gamePanel);
        gameView = new GameView(gamePanel, updateFrame);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.fpsUpdater = fpsUpdater;
    }

    private void setupLevel(Player player){
        currentLevel = levelFactory.simpleLevel(GameConstants.LevelSizes.MEDIUM.getSize(), player);
    }

    public class GameTicker extends TimerTask {
        @Override
        public void run() {
            gameTick();
        }
    }
}
