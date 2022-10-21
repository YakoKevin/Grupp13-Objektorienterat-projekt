package view;

/***
 * This class handles the amount of times the game will update per second.
 * If set to N times per second, the game will repaint itself N times.
 */
public class FPSUpdater implements Runnable {
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;
    private GamePanel gamePanel;
    private Thread gameThread;

    public FPSUpdater(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pauseGameLoop(){
        gameThread.suspend();
    } //TODO: ska den vara kvar? Är deprecated.

    public void continueGameLoop(){
        gameThread.resume();
    } //TODO: ska den vara kvar? Är deprecated.

    /***
     * This is the gameloop for the game, run on a separate thread.
     * It will repaint the game N times per second.
     */
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
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                this.gamePanel.repaint();
                fps++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastChecked >= 1000){
                lastChecked = System.currentTimeMillis();
                fps = 0;
                updates = 0;
            }

        }
    }
}
