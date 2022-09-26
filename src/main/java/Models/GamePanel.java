package Models;

import Controllers.ActionController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.PlayerConstants.*;
import static utilz.Directions.*;

public class GamePanel extends JPanel {

    private int dx = 0;
    private int dy = 0;
    private BufferedImage image;
    private BufferedImage subImage;
    private BufferedImage[][] animations;
    private int animationTick = 30;
    private int animationIndex = 30;
    private int animationSpeed = 30;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel(){
        addKeyListener(new ActionController(this));
        importImage();
        loadAnimations();

        setPanelSize();
    }
    public void setPlayerDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void loadAnimations() {
        animations = new BufferedImage[6][3];
        for (int row = 0; row < animations.length; row++){
            for (int column = 0; column < animations[row].length; column++){
                animations[row][column] = image.getSubimage(row*40, column*80, 30, 80);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites3.png");

        try{
            image = ImageIO.read(is);
        }
        catch(IOException ignored){
        }
        finally {
            try{
                is.close();
            }
            catch(IOException ignored){
            }
        }
    }
    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction))
                animationIndex = 0;
        }
    }
    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void setAnimation() {
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePosition() {
        if (moving){
            if (playerDirection == LEFT)
                dx -= 5;
            else if (playerDirection == UP)
                dy -= 5;
            else if (playerDirection == RIGHT)
                dx += 5;
            else if (playerDirection == DOWN)
                dy += 5;
        }
    }

    public void updateGame(){
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //g.drawRect(100 + dx, 100 + dy, 50, 50);
        //subImage = image.getSubimage(0,0, 30, 80);
        g.drawImage(animations[playerAction][animationIndex], dx, dy, null);

    }




}
