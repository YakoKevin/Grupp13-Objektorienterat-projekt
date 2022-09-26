package General;

import Models.GamePanel;
import Views.GameView;
import entities.Player;

import java.awt.*;

public class GameApp implements Runnable {

    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;

    private Player player;

    public GameApp(){
        player = new Player(200, 200);

        gamePanel = new GamePanel(this);
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();

    }

    public void render(Graphics g){
        player.render(g);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
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

    public Player getPlayer() {
        return player;
    }
}
