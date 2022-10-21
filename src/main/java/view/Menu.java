package view;

import java.awt.*;
import java.awt.image.BufferedImage;

// PREPARATION FOR NEW MAIN MENU
public class Menu {
    private int x;
    private int y ;
    private int rowIndex;
    private int index;
    private BufferedImage[] image;
    private boolean mouseOver;
    private boolean mousePressed;
    private Rectangle bounds;

    public Menu(int x, int y, int rowIndex){
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        loadImage();
        initBounds();
    }
    private void initBounds(){
        bounds = new Rectangle(x, y);
    }

    private void loadImage(){
        image = new BufferedImage[3];
        BufferedImage tempImage = ImageServer.getImage(ImageServer.Ids.MAINMENU);
        for(int i = 0; i < image.length; i++){
            image[i] = tempImage.getSubimage(i, rowIndex, i, i);
        }
    }

    public void draw(Graphics g){
        g.drawImage(image[index], x, y, null);
    }

    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGameState(){
    }
}
