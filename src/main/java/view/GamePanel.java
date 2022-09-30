package view;

import controller.ActionController;
import general.GameApp;

import javax.swing.*;
import java.awt.*;

import static general.GameApp.GAME_HEIGHT;
import static general.GameApp.GAME_WIDTH;

public class GamePanel extends JPanel {

    private GameApp gameApp;
    private KeyView keyView;

    public GamePanel(GameApp gameApp){
        addKeyListener(new ActionController(this));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;

        KeyView keyView = new KeyView(width, -30, width, 30, 0);

    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
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
