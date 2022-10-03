package view;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


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
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGameApp().windowFocusLost();

            }
        });
    }



}