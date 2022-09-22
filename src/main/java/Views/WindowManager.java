package Views;

import javax.swing.*;

public class WindowManager {

    private JFrame frame;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;

    public WindowManager() {
        this.frame = new JFrame("Dungeon Crawler");
        this.frame.setBounds(70, 70, 0, 0);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
    }

    public void createWindow() {
        this.frame.setVisible(true);
    }
}
