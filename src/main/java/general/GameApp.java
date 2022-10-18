package general;

import entity.*;
import entity.Player;
import model.level.Level;
import model.level.LevelFactory;
import utilz.GameConstants;
import view.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameApp implements RoomChangeObserver{

    private final LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;
    Timer timer = new Timer();


    private GameView gameView;
    private FPSUpdater fpsUpdater;

    // SKAPA EN KLASS MED DESSA
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.3f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public GameApp(){
        firstSetup();
        fpsUpdater.startGameLoop();
        timer.schedule(new GameTicker(), 10, 10);
    }

    /**
     * The main logic updater.
     */
    public void gameTick(){
        currentLevel.tick();
    }

    public void roomChangeUpdate() {
        Animation.clearEntities();
        for(Living living : currentLevel.getCurrentLiving())
            Animation.addEntity(living);
    }

    private void firstSetup(){
        Player player = PlayerFactory.createPlayer();
        setupLevel(player);
        setupView();
        currentLevel.addRoomChangeObserver(this);
        for(Living living : currentLevel.getCurrentLiving())
            Animation.addEntity(living);
    }


    private void setupView(){
        GamePanel gamePanel = new GamePanel(this, fpsUpdater, currentLevel);
        fpsUpdater = new FPSUpdater(gamePanel);
        gameView = new GameView(gamePanel);
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
