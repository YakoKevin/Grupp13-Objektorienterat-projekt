package view;

/***
 * This class handles the amount of times the game will update per second.
 * If set to N times per second, the game will repaint itself N times.
 */
public class FPSUpdater implements Runnable {
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;
    private IRepaint repaint;
    private Thread gameThread;

    public FPSUpdater(IRepaint repaint){
        this.repaint = repaint;
    }

    /***
     * This method creates a thread, and then calls the run method by .start().
     */
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /***
     * This is the gameloop for the game, run on a separate thread.
     * It will repaint the game N times per second.
     *
     * This method contains both UPS and FPS handling.
     * FPS takes care of render/frames, in example, Draws the player, enemies, room, etc...
     * UPS takes care of update/tick, in example takes care of logic as player movement and events.
     * This is just to take care of no matter how good computer you have, the UPS will be the same
     * for all computers. The game will not run faster just because you want to have higher framerate.
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
                this.repaint.repaint();
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
