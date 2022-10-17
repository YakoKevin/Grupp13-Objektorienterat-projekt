package entity;

import java.awt.image.BufferedImage;

import utilz.Coordinate;
import utilz.ImageServer;

public class KeyItem extends Entity {

    private BufferedImage keySprite;

    public KeyItem(Coordinate startCoordinate, int width, int height) {
        super(startCoordinate, width, height);
        keySprite = ImageServer.getImage(ImageServer.Ids.KEY);
    }

    public BufferedImage getKey() {
        return keySprite;
    }

    //TODO: remove/move View from here
/*
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
    }*/
}
