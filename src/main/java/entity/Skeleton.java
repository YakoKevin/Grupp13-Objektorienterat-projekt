package entity;

import utilz.Coordinate;

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

    //TODO: nästan alla dessa variabler är oförståeliga (gör bättre namn) eller förbjudna att ha här (varför finns det en lista med skelett i varje skelett?)
    int n=10;
    public BufferedImage[][] skeletonArray;
    public ArrayList<Skeleton> skeletons = new ArrayList<>();
    static int cx=420,cy=120;
    int velX=2,velY=2;
    Timer t=new Timer(5,this);
    //@Override
   // public void paint(Graphics g) { // TODO: DETTA MÅSTE BORT från Skelly!  Kommenterar bort, förstör jag koden så får vi fixa!
        //super.paint(g);
        //Graphics2D g2=(Graphics2D) g;
        // THis is the position of skeleton image which is to be draw
        //for(Skeleton skeleton : skeletons) {
        //    g.drawImage(skeletonArray[skeleton.getEnemyState()][skeleton.getAnimationIndex()], cx,cy, null);
        //    g.drawRect(478, 192, 60, 30);
        //}
       // t.start();

    //}
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

    public Skeleton(int x, int y){
        super(x, y, 0, 30, 100);
        this.setHealthPoints(50);
    }
    //temporärt public tills vi har fungerande rum metod o factory
    public void addEnemies() {
        skeletons.add(new Skeleton(400,400));
    } //TODO: vad gör denna och varför är den här? Ta bort.

    @Override
    public void gettingHit(Rectangle r, double atkD) {

    }

    @Override
    public void addFriendly(Friendly friendly) {

    }
}
