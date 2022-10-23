package view;

import controller.ActionController;
import application.GameApp;
import model.level.Level;
import model.level.room.Door;
import model.Coordinate;
import model.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import static application.GameApp.GAME_HEIGHT;
import static application.GameApp.GAME_WIDTH;

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

public class GamePanel extends JPanel implements IRepaint{

    public FPSUpdater fpsUpdater;

    protected Animation animation;
    protected Animation animationEnemy;
    private Pause pause;
    private Level level;


    //TODO: Se till att ha LEVEL ist. -> hämta där fienderna och player och loopa genom dem alla för att hämta animation och sådant
    public GamePanel(GameApp gameApp, FPSUpdater fpsUpdater, Level level){
        addKeyListener(new ActionController(level.getPlayer()));
        setBackground(Color.GREEN);
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
        drawMap(g);
        drawWalls(g);
        drawDoors(g);
        drawUI(g);
        drawObstacles(g);
        drawKeys(g);
        drawCoins(g);
        render(g);
    }

    public static BufferedImage wall = ImageServer.getImage(ImageServer.Ids.WALL);
    public static BufferedImage floor = ImageServer.getImage(ImageServer.Ids.FLOOR);
    public static BufferedImage key = ImageServer.getImage(ImageServer.Ids.KEY);
    public static BufferedImage coin = ImageServer.getImage(ImageServer.Ids.COIN);
    public static BufferedImage iconKey = ImageServer.getImage(ImageServer.Ids.ICON_KEY);
    public static BufferedImage iconHp = ImageServer.getImage(ImageServer.Ids.ICON_HP);
    public static BufferedImage iconCoin = ImageServer.getImage(ImageServer.Ids.ICON_COIN);


    private void drawCoins(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomCoins().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomCoins()) {
                g.drawImage(coin,
                        obsCoord.getY() * scaling, // x och y är kanske feltransponerade
                        obsCoord.getX() * scaling,
                        scaling, scaling, null);
            }
        }
    }

    private void drawKeys(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomKeys().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomKeys()) {
                g.drawImage(key,
                        obsCoord.getY() * scaling,
                        obsCoord.getX() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    private void drawObstacles(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomObstacles().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomObstacles()) {
                g.drawImage(wall,
                        obsCoord.getY() * scaling, // x och y är kanske feltransponerade
                        obsCoord.getX() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    private void drawWalls(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomWalls().isEmpty()) {
            for (Coordinate wallCoord : level.getCurrentRoomWalls()) {
                g.drawImage(wall,
                        wallCoord.getY() * scaling, // x och y är kanske feltransponerade
                        wallCoord.getX() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    private void drawDoors(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomDoors().isEmpty()) {
            for (Door door : level.getCurrentRoomDoors()) {

                g.drawImage(ImageServer.getImage(ImageServer.Ids.DOOR),
                        50 + door.getCoordinate().getX() * scaling,
                        + door.getCoordinate().getY() * scaling,
                        2 * scaling, 2 * scaling, null);

            }
        }
    }


    private void drawMap(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        int h = GameConstants.RoomMapSizes.HEIGHT.getSize();
        int w = GameConstants.RoomMapSizes.WIDTH.getSize();

        for(int i = 0; i< h; i++) {
            for(int j = 0; j < w; j++) {
                g.drawImage(floor ,
                        j * scaling,i * scaling,
                        scaling, scaling, null);
            }
        }
    }

    protected void render(Graphics g){
        Animation.render(g);
    }

    public void drawUI(Graphics g) { //kanske kan separera dessa om man vill
        String hpStr = Double.toString(level.getPlayer().getHealthPoints());
        String score = Double.toString(level.getPlayer().getScoreCount());
        String keyScore = Double.toString(level.getPlayer().getKeyCount());
        g.setFont(new Font("Araial", Font.BOLD, 12));
        g.setColor(new Color(255, 5, 50));
        g.setColor(Color.YELLOW);

        g.drawImage(iconHp, 10, 0, null);
        g.drawString(hpStr, 28, 12);

        g.drawImage(iconCoin, 10, 13, null);
        g.drawString(score, 28, 25);

        g.drawImage(iconKey, 10, 26, null);
        g.drawString(keyScore, 28, 38);

        g.setColor(Color.WHITE);
    }
}
