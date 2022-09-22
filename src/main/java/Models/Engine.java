package Models;

import Views.WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Engine {

    private static WindowManager windowManager;
    private static Timer timer;

    public static void init() {
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
        }

    }
}
