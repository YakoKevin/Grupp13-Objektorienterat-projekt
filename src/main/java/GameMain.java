import Models.Engine;
import javax.swing.*;

// TEMPORÄRT KLASS NAMN
public class GameMain {

    public static void main(String[] args) {

        //GameApp game = new GameApp();
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
}
