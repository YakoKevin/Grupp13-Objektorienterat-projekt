package Models;

import Controllers.ActionController;
import General.GameApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.PlayerConstants.*;
import static utilz.Directions.*;

public class GamePanel extends JPanel {

    private GameApp gameApp;
    public GamePanel(GameApp gameApp){
        addKeyListener(new ActionController(this));
        this.gameApp = gameApp;
        setPanelSize();
    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        gameApp.render(g);

    }

    public GameApp getGameApp() {
        return gameApp;
    }
}
