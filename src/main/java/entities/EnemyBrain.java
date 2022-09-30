package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EnemyBrain {

    private BufferedImage[][] skeletonArray;
    public ArrayList<Skeleton> skeletons = new ArrayList<>();

    public EnemyBrain(){
        loadEnemyImages();
        addEnemies();
    }
//temporärt public tills vi har fungerande rum metod
    public void addEnemies() {
        skeletons.add(new Skeleton(500, 500));
    }

    public void update(){
        for(Skeleton skeleton: skeletons)
            skeleton.update();
    }

    public void draw(Graphics g){
        drawSkeletons(g);
    }

    private void drawSkeletons(Graphics g) {
        for(Skeleton skeleton : skeletons)
            g.drawImage(skeletonArray[skeleton.getEnemyState()][skeleton.getAnimationIndex()], 200,200, null);
    }

    private void loadEnemyImages() {
        skeletonArray = new BufferedImage[6][3];

        try (InputStream is = getClass().getResourceAsStream("/skeleton_sprites.png")) {
            BufferedImage image = ImageIO.read(is);

            skeletonArray = new BufferedImage[6][3];
            for (int row = 0; row < skeletonArray.length; row++){
                for (int column = 0; column < skeletonArray[row].length; column++){
                    skeletonArray[row][column] = image.getSubimage(row*100, column*100, 60, 100);
                }
            }
        } catch (IOException ignored) {
        }

    }
}
