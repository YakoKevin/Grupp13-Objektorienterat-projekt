package Views;

import javax.swing.*;


public class GameView extends JFrame { // TEMPORÄRT NAMN

    private JFrame jFrame;


    public GameView(GamePanel gamePanel){
        jFrame = new JFrame();
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }



}
