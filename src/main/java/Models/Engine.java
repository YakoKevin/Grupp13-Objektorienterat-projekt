package Models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Engine {

    private static Timer timer;

    public static void init() {
        timer = new Timer(20, new MainGameLoop());
    }

    public static void start() {
        timer.start();
    }

    private static class MainGameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
        }

    }
}
