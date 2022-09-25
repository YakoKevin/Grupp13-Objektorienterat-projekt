package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class WindowManager {

    private JFrame frame;
    private JPanel panel;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;

    public WindowManager() {
        this.frame = new JFrame("Dungeon Crawler");
        this.frame.setBounds(70, 70, 0, 0);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
    }

    public void addPanel(JPanel panel) {
        this.panel = panel;
    }

    public void addKeyListener(KeyListener listener) {
        try {
            this.panel.addKeyListener(listener);
        } catch(NullPointerException e) {
            System.err.println("[WindowManager]: Error! Tried to add KeyListener before JPanel");
            System.exit(-1);
        }
    }

    public void createWindow() {
        this.frame.add(panel);
        this.frame.setVisible(true);
    }
}
