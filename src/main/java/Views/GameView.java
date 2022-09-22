package Views;

import Models.GamePanel;

import javax.swing.*;


public class GameView extends JFrame { // TEMPORÄRT NAMN

    private JFrame jFrame;


    public GameView(GamePanel gamePanel){
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }



}
