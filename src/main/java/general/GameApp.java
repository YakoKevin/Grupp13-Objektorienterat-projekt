package general;

import entity.KeyItem;
import model.Animation;
import model.Attack;
import model.Movement;
import model.level.Level;
import model.level.LevelFactory;
import model.level.LevelManager;
import utilz.GameConstants;
import utilz.ImageServer;
import view.GamePanel;
import view.GameView;
import entity.EnemyBrain;
import entity.Player;
import entity.Skeleton;

import java.awt.*;

public class GameApp {

    private LevelFactory levelFactory = new LevelFactory();
    private Level currentLevel;


    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;

    //Inget av dessa borde finnas som en direkt referens här i GameApp - byt till att fråga currentLevel istället
    //private EnemyBrain enemyBrain;

    private Skeleton skel;
    private Player player;
    private KeyItem key;
    private LevelManager levelManager;
    private Movement movement;
    private Animation animation;
    private Animation animationEnemy;
    private Attack attack;

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

    private final Object lock = new Object();

    public GameApp(){
        //levelManager = new LevelManager();
        player = new Player(100, 100, 30, 100);
        animation = new Animation(ImageServer.Ids.PLAYER, player);

        // NEW STUFF
        updateFrame = new UpdateFrame(player, animation);


        movement = new Movement(player, animation);
        //attack = new Attack(animation);
        key = new KeyItem(450, 350, 40, 40);
        //player.loadLevelData(levelManager.getCurrentLevel());
        skel=new Skeleton(200,200);
        //skel.loadEnemyImages();
        skel.addEnemies();
        animationEnemy = new Animation(ImageServer.Ids.ENEMY, skel); // TODO: Vi behöver en factory, då kan vi få det att fungera mycket mer abstrakt.
                                                                      // Just nu är detta bara för att testa MVC men detta måste fixas!

        gamePanel = new GamePanel(this, movement, attack, updateFrame, fpsUpdater, player, animation, animationEnemy); // FIXA REFERENCES...
        fpsUpdater = new FPSUpdater(gamePanel);

        gameView = new GameView(gamePanel, updateFrame);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        fpsUpdater.startGameLoop();



        firstSetup();
        startGameLoop();
    }

    public void render(Graphics g){
        AttackView view = new AttackView();
        levelManager.draw(g);
        //enemyBrain.draw(g);
        skel.draw(g);
        player.render(g);
        //animation.render(g);

        view.drawAttackRectangle(g, (int)player.getX(),(int)player.getY(),player.getWidth(),player.getHeight()); //tillfällig hitbox runt spelaren
        if(player.getAttackMode()==true){
            Coordinate c = new Coordinate((int)player.getX(),(int)player.getY());
            c = Attack.getAttackCoordinate(c,player.getDirection());
            view.drawAttackRectangle(g,c.getX(),c.getY(),player.getHeight(),player.getHeight());
            player.setAttackMode(false);
        }

        player.drawHP(g);
        key.draw(g);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
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

    public void update(){
        player.update();
        //movement.updatePosition();
        //animation.update();
        //enemyBrain.update();
        skel.update();
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
        return this.player;
    }

    private void firstSetup(){
        Player player = new Player(100, 100, 30, 100);
        setupLevel(player);
    }

    private void setupLevel(Player player){
        currentLevel = levelFactory.simpleLevel(GameConstants.LevelSizes.MEDIUM.getSize(), player);
    }
}
