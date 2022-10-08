package view;

import entity.Entity;
import general.IObserver;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameView extends JFrame { // TEMPORÄRT NAMN

    private JFrame jFrame;

    UpdateFrame updateFrame;

    // Entity player ska vara mer generic så att vi kan använda för vad som helst.
// kanske lista av entities?
    public GameView(GamePanel gamePanel, UpdateFrame updateFrame){
        this.updateFrame = updateFrame;
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        this.add(updateFrame);

        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGameApp().windowFocusLost(); // TODO: FIX THIS

            }
        });
    }


}
