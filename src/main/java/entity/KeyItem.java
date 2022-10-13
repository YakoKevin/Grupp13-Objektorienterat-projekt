package entity;

import java.awt.image.BufferedImage;

import general.IObserver;
import utilz.ImageServer;
import java.awt.*;

public class KeyItem extends Entity implements IObserver {

    private BufferedImage keySprite;

    public KeyItem(int x, int y, int width, int height) {
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
        double newX = Math.random()*700;
        double newY = Math.random()*450;
        this.setX((float)newX);
        this.setY((float)newY);
    }
}
