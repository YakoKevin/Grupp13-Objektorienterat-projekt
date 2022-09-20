package Views;

import Controllers.ActionController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int dx = 0;
    private int dy = 0;

    public GamePanel(){
        addKeyListener(new ActionController(this));
    }

    public void movementInXAxis(int value){
        this.dx += value;
        repaint();
    }

    public void movementInYAxis(int value){
        this.dy += value;
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawRect(100 + dx, 100 + dy, 50, 50);
    }
}
