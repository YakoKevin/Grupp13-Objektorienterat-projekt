package view;

import controller.ActionController;
import general.GameApp;
import model.level.Level;
import model.level.room.Door;
import utilz.Coordinate;
import utilz.GameConstants;
import utilz.ImageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static general.GameApp.GAME_HEIGHT;
import static general.GameApp.GAME_WIDTH;

class Pause extends SwingWorker<Void,String> {

    @Override
    protected Void doInBackground() throws Exception {
        while(!isCancelled()) {
            publish(String.format(null, (new Random()).nextInt(99999)));
            Thread.sleep(100);
        }
        return null;
    }

}

public class GamePanel extends JPanel {

    public FPSUpdater fpsUpdater;

    protected Animation animation;
    protected Animation animationEnemy;
    private Pause pause;
    private Level level;


    //TODO: Se till att ha LEVEL ist. -> hämta där fienderna och player och loopa genom dem alla för att hämta animation och sådant
    public GamePanel(GameApp gameApp, FPSUpdater fpsUpdater, Level level){
        addKeyListener(new ActionController(this, level.getPlayer()));
        setBackground(Color.ORANGE);
        setPanelSize();
        int width = 50;
        this.level = level;
        //inititateHitbox();
        JButton jb=new JButton("Pause");
        jb.setBackground(Color.BLUE);
        jb.setBounds(515, 300, 80, 30);
        this.add(jb);
        JButton jb1=new JButton("Resume");
        jb1.setBackground(Color.BLUE);
        jb1.setBounds(600, 300, 80, 30);
        this.add(jb1);

        jb.addActionListener(new ActionListener() { // TODO: fixa variabel namnen jb och jb1
            @Override
            public void actionPerformed(ActionEvent e) {
                (pause = new Pause()).execute();
                try {
                    pauseThread();
                    jb.setEnabled(false);
                    jb1.setEnabled(true);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(pause != null) {
                        pause.cancel(true);
                        jb.setEnabled(true);
                        jb1.setEnabled(false);
                        resumeThread();
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        this.fpsUpdater = fpsUpdater;
    }
    public  void pauseThread() throws InterruptedException
    {
        fpsUpdater.pauseGameLoop();
    }

    public  void resumeThread()
    {
        fpsUpdater.continueGameLoop();
    }
    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawUI(g);
        drawDoors(g);
        render(g);
        //drawWalls(g); // behöver nån tråd alternativt göra fyra stora väggar som omsluter rutan.
        //drawObstacles(g);
    }

    private void drawObstacles(Graphics g) {
        int c = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        if(!level.getCurrentRoomObstacles().isEmpty()) {
            for (Coordinate obstacleCoord : level.getCurrentRoomObstacles()) {
                g.drawImage(ImageServer.getImage(ImageServer.Ids.WALL), // temporärt wall, ska byta till nån passande bild
                        obstacleCoord.getX() * c , // x och y är kanske feltransponerade
                        obstacleCoord.getY() * c,
                        c,
                        c, null);
            }
        }
    }

    private void drawWalls(Graphics g) {
        int c = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        if(!level.getCurrentRoomWalls().isEmpty()) {
            for (Coordinate wallCoord : level.getCurrentRoomWalls()) {
                g.drawImage(ImageServer.getImage(ImageServer.Ids.WALL),
                        wallCoord.getY() * c , // x och y är kanske feltransponerade
                        wallCoord.getX() * c,
                        c,
                        c, null);
            }
        }
    }

    private void drawDoors(Graphics g) {
        if(!level.getCurrentRoomDoors().isEmpty()) {
            for (Door door : level.getCurrentRoomDoors()) {
                g.drawImage(ImageServer.getImage(ImageServer.Ids.DOOR),
                        50 +door.getCoordinate().getX()*GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(),
                        door.getCoordinate().getY()*GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(),
                        2 * GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(),
                        2 * GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize(), null);
            }
        }
    }


    private void drawMap(Graphics g) {

    }

    protected void render(Graphics g){
        Animation.render(g);
    }

    public void drawUI(Graphics g) { //kanske kan separera dessa om man vill
        String hpStr = Double.toString(level.getPlayer().getHealthPoints());
        g.setFont(new Font("Araial", Font.BOLD, 12));
        g.setColor(new Color(255, 0, 70));
        g.drawString("HP: " + hpStr,10,10);
        g.setColor(Color.YELLOW);
        g.setColor(Color.WHITE);
    }
}
