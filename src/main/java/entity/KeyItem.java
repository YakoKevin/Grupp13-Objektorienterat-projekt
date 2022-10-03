package entity;

import java.awt.image.BufferedImage;

import utilz.ImageServer;
import java.awt.*;

public class KeyItem extends Entity implements model.IObserver {

    private BufferedImage keySprite;

    public KeyItem(float x, float y, int width, int height) {
        super(x, y, width, height);
        keySprite = ImageServer.getImage(ImageServer.Ids.KEY);
    }

    public BufferedImage getKey() {
        return keySprite;
    }

    @Override
    public void update() {

    }

    public boolean isCollision(int xPlayer, int yPlayer, int widthPlayer, int heightPlayer)
    {
        if(this.x + this.width >= xPlayer &&
                this.x <= xPlayer + widthPlayer &&
                this.y + this.height >= yPlayer &&
                this.y <= yPlayer + heightPlayer)
            return true;
        return false;
    }

    public void draw(Graphics g)
    {
        g.drawImage(keySprite, (int)x, (int)y, null);
    }

    public void setNewPosition()
    {
        double newX = Math.random()*1000;
        double newY = Math.random()*500;
        this.setX((float)newX);
        this.setY((float)newY);
    }
}