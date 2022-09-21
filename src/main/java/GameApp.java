import Models.GamePanel;
import Views.GameView;

public class GameApp implements Runnable {

    private GameView gameView;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int MAX_FPS = 120;

    public GameApp(){
        gamePanel = new GamePanel();
        gameView = new GameView(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 /MAX_FPS;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int fps = 0;
        long lastChecked = System.currentTimeMillis();

        while(true){
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = System.nanoTime();
                fps++;
            }

            if (System.currentTimeMillis() - lastChecked >= 1000){
                lastChecked = System.currentTimeMillis();
                System.out.println("FPS: " + fps);

                fps = 0;
            }
        }
    }
}
