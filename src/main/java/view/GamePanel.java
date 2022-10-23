package view;

import controller.ActionController;
import model.Coordinate;
import model.GameConstants;
import model.entity.Living;
import model.entity.LivingStates;
import model.level.Level;
import model.level.room.Door;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static application.GameApp.GAME_HEIGHT;
import static application.GameApp.GAME_WIDTH;

/***
 * This class takes care of all the JPanel related code.
 * Like adding a keylistener and, setting the panel size,
 * and drawing the UI.
 */
public class GamePanel extends JPanel implements IRepaint{

    public FPSUpdater fpsUpdater;
    private final Level level;
    public static BufferedImage wall = ImageServer.getImage(ImageServer.Ids.WALL);
    public static BufferedImage floor = ImageServer.getImage(ImageServer.Ids.FLOOR);
    public static BufferedImage key = ImageServer.getImage(ImageServer.Ids.KEY);
    public static BufferedImage coin = ImageServer.getImage(ImageServer.Ids.COIN);
    public static BufferedImage heart = ImageServer.getImage(ImageServer.Ids.HEART);
    public static BufferedImage iconKey = ImageServer.getImage(ImageServer.Ids.ICON_KEY);
    public static BufferedImage iconHp = ImageServer.getImage(ImageServer.Ids.ICON_HP);
    public static BufferedImage iconCoin = ImageServer.getImage(ImageServer.Ids.ICON_COIN);
    public static BufferedImage iconSlain = ImageServer.getImage(ImageServer.Ids.ICON_SLAIN);


    public GamePanel(FPSUpdater fpsUpdater, Level level){
        addKeyListener(new ActionController(level.getPlayer()));
        setBackground(Color.GREEN);
        setPanelSize();
        this.level = level;
        //inititateHitbox();
        JButton jb=new JButton("Pause");
        jb.setBackground(Color.WHITE);
        jb.setBounds(515, 300, 80, 30);
        this.add(jb);
        JButton jb1=new JButton("Resume");
        jb1.setBackground(Color.WHITE);
        jb1.setBounds(600, 300, 80, 30);
        this.add(jb1);

        jb.addActionListener(new ActionListener() { // TODO: fixa variabel namnen jb och jb1
            @Override
            public void actionPerformed(ActionEvent e) {
                jb.setEnabled(false);
                jb1.setEnabled(true);
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jb.setEnabled(true);
                    jb1.setEnabled(false);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        this.fpsUpdater = fpsUpdater;
    }

    /***
     * Sets the panel size of the game. Does not include the border.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }


    /***
     * Overriden method from JPanel.
     * This takes care of drawing the images of the game.
     * It is called by .repaint().
     * @param g The graphics of the game.
     */
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
        if(!level.getPlayer().takenHeart()){
            drawHeart(g);
        }
        drawHealthBars(g,level.getCurrentLiving());
        render(g);
    }

    private void drawHealthBars(Graphics g, ArrayList<Living> livings){
        double percentageOfHealth;
        Rectangle2D rect;
        Graphics2D g2 =(Graphics2D)g;
        for(Living living: livings){
            if(living.getState()!= LivingStates.DEAD) {
                percentageOfHealth = living.getHealthPoints() / living.getMaximumHealthPoints();
                g.setColor(Color.BLACK);
                g.drawRect((int) living.getX(), (int) living.getY() - 5, living.getWidth(), 10);
                g.setColor(Color.RED);
                rect = new Rectangle2D.Double(living.getX() + 1, living.getY() - 5, living.getWidth() * percentageOfHealth, 10);
                g2.fill(rect);
            }
        }
    }

    /***
     * This draws the hearts in the game.
     * @param g The graphics of the game.
     */
    private void drawHeart(Graphics g){
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        g.drawImage(heart,level.getCurrentRoomHeart().getX() * scaling,
                level.getCurrentRoomHeart().getY() * scaling,
                scaling, scaling, null);
    }

    /***
     * This draws the coins in the game.
     * @param g The graphics of the game.
     */
    private void drawCoins(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();
        if(!level.getCurrentRoomCoins().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomCoins()) {
                g.drawImage(coin,
                        obsCoord.getX() * scaling,
                        obsCoord.getY() * scaling,
                        scaling, scaling, null);
            }
        }
    }

    /***
     * This draws the keys in the game.
     * @param g The graphics of the game.
     */
    private void drawKeys(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomKeys().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomKeys()) {
                g.drawImage(key,
                        obsCoord.getX() * scaling,
                        obsCoord.getY() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    /***
     * This draws the obstacles in the game.
     * @param g The graphics in the game.
     */
    private void drawObstacles(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomObstacles().isEmpty()) {
            for (Coordinate obsCoord : level.getCurrentRoomObstacles()) {
                g.drawImage(wall,
                        obsCoord.getX() * scaling, // x och y är kanske feltransponerade
                        obsCoord.getY() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    /***
     * This draws the walls in the game.
     * @param g The graphics in the game.
     */
    private void drawWalls(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomWalls().isEmpty()) {
            for (Coordinate wallCoord : level.getCurrentRoomWalls()) {
                g.drawImage(wall,
                        wallCoord.getX() * scaling, // x och y är kanske feltransponerade
                        wallCoord.getY() * scaling,
                        scaling, scaling, null);
            }
        }

    }

    /***
     * This draws the doors in the game.
     * @param g The graphics in the game.
     */
    private void drawDoors(Graphics g) {
        int scaling = GameConstants.GameScalingFactors.TILE_SCALE_FACTOR.getSize();

        if(!level.getCurrentRoomDoors().isEmpty()) {
            for (Door door : level.getCurrentRoomDoors()) {

                g.drawImage(ImageServer.getImage(ImageServer.Ids.DOOR),
                        door.getCoordinate().getX() * scaling,
                        door.getCoordinate().getY() * scaling,
                        scaling, scaling, null);

            }
        }
    }

    /***
     * This draws the map in the game.
     * @param g The graphics in the game.
     */
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

    /***
     * This renders the animation in the game.
     * @param g The graphics in the game.
     */
    protected void render(Graphics g){
        Animation.render(g);
    }

    /***
     * This draws the UI in the game. Such as HP, Score, Keys, and how many slain enemies.
     * @param g The graphics in the game.
     */
    public void drawUI(Graphics g) {
        String hpStr = Double.toString(level.getPlayer().getHealthPoints());
        String scoreStr = String.valueOf(level.getPlayer().getScoreCount() + level.getPlayer().getRoomScoreCount());
        String keyStr = String.valueOf(level.getPlayer().getKeyCount());
        String slainStr = String.valueOf(level.getPlayer().getSlainEnemies() +level.getPlayer().getRoomSlainEnemies());

        g.setFont(new Font("Araial", Font.BOLD, 14));
        g.drawImage(iconHp, 10, 8, null);
        g.setColor(Color.RED);
        g.drawString(hpStr, 30, 20);

        g.drawImage(iconCoin, 100, 8, null);
        g.setColor(Color.BLUE);
        g.drawString(scoreStr, 120, 20);

        g.drawImage(iconKey, 190, 8, null);
        g.setColor(Color.YELLOW);
        g.drawString(keyStr, 210, 20);

        g.drawImage(iconSlain, 280, 3, null);
        g.setColor(Color.WHITE);
        g.drawString(slainStr, 320,20);
    }
}
