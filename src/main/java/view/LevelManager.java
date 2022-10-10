package view;

import general.GameApp;
import utilz.ImageServer;

import java.awt.*;
import java.awt.image.BufferedImage;

// TEMPORÄRT !
public class LevelManager {
    private BufferedImage levelSprite;
    private int[][] levelData;

    public LevelManager(){
        this.levelSprite = ImageServer.getImage(ImageServer.Ids.LEVEL);
        //importOutsideSprites();
    }

/* IFALL VI SKA GÖRA TILE BASERD MAPP
    private void importOutsideSprites() {
        BufferedImage image = LoadSave.GetSprite(LoadSave.LEVEL_SPRITE);
        levelSprite = new BufferedImage[48];
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 12; col++){
                int index = row * 12 + col;
                levelSprite[index] = image.getSubimage(col * 32, row * 32, 32, 32);
            }
        }
    }


 */

    public int getSpriteIndex(int x, int y){
        return levelData[y][x];
    }
    public void draw(Graphics g){
        g.drawImage(levelSprite, 0, 0, null);
    }

    public void update(){

    }

    public int[][] getCurrentLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    // public Level getCurrentLevel(){
    //    return levelSprite;
    // }
}