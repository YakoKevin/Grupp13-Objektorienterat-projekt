package general;

import model.Engine;
import javax.swing.*;

// TEMPORÄRT KLASS NAMN
public class GameMain {

    static GameApp game;

    public static void main(String[] args) {

        game = new GameApp();
        //GameView gameView = new GameView();

        //ActionController actionController = new ActionController(gameView);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Engine.init();
                Engine.start();
            }
        });

    }

    public static void reset() {
        game=null;
        game= new GameApp();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Engine.init();
                Engine.start();
            }
        });
    }
}
