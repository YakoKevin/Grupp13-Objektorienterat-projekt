package view;

import controller.ActionController;
import general.GameApp;
import model.Movement;
import utilz.GameConstants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GameApp gameApp;
    private KeyView keyView;

    public GamePanel(GameApp gameApp, Movement movement){
        addKeyListener(new ActionController(this, movement));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;

        KeyView keyView = new KeyView(width, -30, width, 30, 0);

    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GameConstants.GameSizes.WIDTH.getSize(), GameConstants.GameSizes.HEIGHT.getSize());
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GameConstants.GameSizes.WIDTH.getSize() + " : " + GameConstants.GameSizes.HEIGHT.getSize());
    }


    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        gameApp.render(g);

        // g.translate(this.keyView.getX(), this.keyView.getY());
        // this.keyView.paint(g);

    }

    public GameApp getGameApp() {
        return gameApp;
    }
}
