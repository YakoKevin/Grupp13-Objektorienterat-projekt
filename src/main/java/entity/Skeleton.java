package entity;

import utilz.Coordinate;
import static utilz.EnemyConstants.*;

import static utilz.EnemyConstants.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Skeleton extends Enemy implements ActionListener{

    int n=10;
    public BufferedImage[][] skeletonArray;
    public ArrayList<Skeleton> skeletons = new ArrayList<>();
    int cx=20,cy=20;
    int velX=2,velY=2;
    Timer t=new Timer(5,this);
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        // THis is the position of skeleton image which is to be draw
        for(Skeleton skeleton : skeletons) {
            g.drawImage(skeletonArray[skeleton.getEnemyState()][skeleton.getAnimationIndex()], cx,cy, null);
        }
        t.start();
    }
    public void actionPerformed(ActionEvent e) {
        if(cx<20 || cx>700) {
            velX= -velX;
        }
        if(cy<20 || cy>400) {
            velY= -velY;
        }

        cx+= velX;
        cy+= velY;
        JPanel p=new JPanel();
        p.repaint();
    }
    public Skeleton() {
        super(0, 0, 0, 0, SKELETON);
//        loadEnemyImages();
//        addEnemies();
    }

    public Skeleton(float x, float y){
        super(x, y, 0, 0, SKELETON);
    }
    //temporärt public tills vi har fungerande rum metod
    public void addEnemies() {
        skeletons.add(new Skeleton(400,400));
    }

    public void draw(Graphics g){
        paint(g);
    }
    public void loadEnemyImages() {
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

    public void update() {
//    	System.out.println(x + " === "+ y);
        updateAnimationTick();

//    	float x=(float) getX();
//    	float y=(float) getY();
//    	System.out.println(x+"                          "+y);
//    	EnemyBrain.loadEnemyImages();
//    	while(true) {
//    		setX(x+450000);
//    		setY(y+540000);
//    	}
    }

    /*public Skeleton(float x, float y) {
        super(x, y, 0, 0);
        setHealthPoints(50);
    }*/
}
