package Models;

import Controllers.gamestates.GameStateManager;
import Views.WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Engine {

    private static GameStateManager gameStateManager;

    private static WindowManager windowManager;
    private static Timer timer;

    public static void init() {
        gameStateManager = new GameStateManager();
        windowManager = new WindowManager();
        timer = new Timer(20, new MainGameLoop());
    }

    public static void start() {
        windowManager.createWindow();
        timer.start();
    }

    private static class MainGameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            gameStateManager.loop();
        }

    }
}
