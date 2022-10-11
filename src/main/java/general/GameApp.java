package general;

import entity.*;
import entity.Player;
import model.AttackModel;
import model.Movement;
import model.level.Level;
import model.level.LevelFactory;
import utilz.GameConstants;
import utilz.ImageServer;
import view.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameApp {

    private LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;
    Timer timer;


    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;

    //TODO: Inget av dessa borde finnas som en direkt referens här i GameApp - byt till att fråga currentLevel istället
    //private EnemyBrain enemyBrain;
    private Skeleton skel;
    private Player player;
    private KeyItem key;
    private LevelManager levelManager;
    private Movement movement;
    private Animation animation;
    private Animation animationEnemy;
    private AttackModel attack;

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
        player = new Player(100, 100, 30, 100);

        animation = new Animation(ImageServer.Ids.PLAYER, player);
        updateFrame = new UpdateFrame(player, animation);
        movement = new Movement();
        //attack = new Attack(animation);
        key = new KeyItem(450, 350, 40, 40);
        //player.loadLevelData(levelManager.getCurrentLevel());
        skel=new Skeleton(200,200);

        //EnemyFactory enemyFactory = new EnemyFactory();
        //enemyFactory.createSkeleton();
        //skel.loadEnemyImages();
        skel.addEnemies();
        animationEnemy = new Animation(ImageServer.Ids.ENEMY, skel);
        //**********Allt ovan borde bort.***************


        firstSetup();
        fpsUpdater.startGameLoop(); //TODO: fixa med namn så att det är tydligt att det är bara View den uppdaterar
        timer.schedule(new GameTicker(), 100);
    }

    /**
     * The main logic updater.
     */
    public void gameTick(){
        currentLevel.tick();
        //TODO: lägga till mer?
    }

    public void windowFocusLost(){
        movement.resetDirectionBooleans();
    }

    private void firstSetup(){
        setupView();
        setupLevel(player);
    }

    //TODO: REMOVE ALL NEED FOR MOVEMENT, ATTACK osv. Borde kunna hämta all från level ist.
    private void setupView(){
        gamePanel = new GamePanel(this, movement, attack, updateFrame, fpsUpdater, player, animation, animationEnemy); // FIXA REFERENCES...
        fpsUpdater = new FPSUpdater(gamePanel);
        gameView = new GameView(gamePanel, updateFrame);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
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
