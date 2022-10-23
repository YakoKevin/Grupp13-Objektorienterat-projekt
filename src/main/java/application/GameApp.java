package application;

import model.PlayerDeathObserver;
import model.RoomChangeObserver;
import model.entity.*;
import model.entity.Player;
import model.level.Level;
import model.level.LevelFactory;
import model.GameConstants;
import view.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

/***
 * This class acts as the Application in MVC-A.
 * It is the foundation of the program as it sets up everything,
 * and takes care of main features as stopping the game.
 */
public class GameApp implements RoomChangeObserver, PlayerDeathObserver {

    private final LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;
    Timer timer = new Timer();
    public GameView gameView;
    private FPSUpdater fpsUpdater;
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
        timer.schedule(new GameTicker(), 15, 15);
    }

    public static void main(String[] args) {
        new GameApp();
    }

    /**
     * The main logic updater.
     * * @param gameApp
     */
    public void gameTick(){
        currentLevel.tick();
    }

    /***
     * This method will be called when you enter a new room.
     * It will clear all the entities in the previous room,
     * and add the entities in the new room.
     */
    public void roomChangeUpdate() {
        Animation.clearEntities();
        for(Living living : currentLevel.getCurrentLiving())
            Animation.addEntity(living);
    }

    /***
     * This will set up the logic in the game.
     */
    private void firstSetup(){
        Player player = PlayerFactory.createPlayer();
        setupLevel(player);
        setupView();
        currentLevel.addRoomChangeObserver(this);
        currentLevel.addPlayerDeathObserver(this);
        for(Living living : currentLevel.getCurrentLiving())
            Animation.addEntity(living);
    }

    /***
     * This will set up the view in the game.
     */
    private void setupView(){
        GamePanel gamePanel = new GamePanel(fpsUpdater, currentLevel);
        fpsUpdater = new FPSUpdater(gamePanel);
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.fpsUpdater = fpsUpdater;
    }

    /***
     * This will setup the level in the game.
     * @param player The player instance that the user plays as.
     */
    private void setupLevel(Player player){
        currentLevel = levelFactory.simpleLevel(GameConstants.LevelSizes.MEDIUM.getSize(), player);
    }

    /***
     * Stops the game.
     */
    public void stopGame(){
        timer.cancel();
        currentLevel.getCurrentLiving().clear();
        Animation.clearEntities();
        if(currentLevel.getPlayer().getHealthPoints() <= 0) {
            currentLevel.stopEnemies();
            int result = 1;
            JOptionPane.showMessageDialog(null, "Game has finished");
            // No  - 1
            // Yes - 0
            stopGame();
            if(result == 1) {
                gameView.jFrame.dispatchEvent(new WindowEvent(gameView.jFrame, WindowEvent.WINDOW_CLOSING));
            }else {
            }
        }
    }

    /***
     * Called if the player reaches 0 HP.
     */
    @Override
    public void updatePlayerDeathObserver() {
        stopGame();
    }

    /***
     * Starts a GameLoop for logic related code.
     */
    public class GameTicker extends TimerTask {
        public GameTicker() {
        }

        @Override
        public void run() {
            gameTick();
        }
    }
}
