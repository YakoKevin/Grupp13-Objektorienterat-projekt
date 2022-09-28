package Models.level;

import General.GameApp;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

// TEMPORÄRT !
public class LevelManager {
    private GameApp gameApp;
    private BufferedImage levelSprite;
    private int[][] levelData;

    public LevelManager(GameApp gameApp){
        this.gameApp = gameApp;
        this.levelSprite = LoadSave.GetSprite(LoadSave.LEVEL_SPRITE);
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

   // public Level getCurrentLevel(){
    //    return levelSprite;
   // }
}
