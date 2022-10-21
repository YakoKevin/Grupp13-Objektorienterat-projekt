package view;

import controller.gamestates.GameState;
import controller.gamestates.GameStateManager;
import general.GameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends GameState {

    public static BufferedImage background = ImageServer.getImage(ImageServer.Ids.BACKGROUND);
    private String[] optionsMenu;
    private static final String START_GAME = "Start Game!";
    private static final String SCORE = "Score";
    private static final String QUIT_GAME = "Quit game";
    private int selected;

    public MainMenu(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[] {START_GAME,SCORE, QUIT_GAME};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        //graphics.setColor(new Color(30, 30, 70));
        //graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);

        graphics.drawImage(background,
                0,
                0,
                512, 512, null);

        graphics.setFont(new Font("Araial", Font.PLAIN, 25));
        for(int i=0;i<this.optionsMenu.length;i++) {
            if(i==this.selected) graphics.setColor(Color.GREEN);
            else graphics.setColor(Color.WHITE);
            graphics.drawString(this.optionsMenu[i], 20, 50 + i * 40);
        }
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_UP:
                if(this.selected > 0) this.selected--;
                break;
            case KeyEvent.VK_DOWN:
                if(this.selected < this.optionsMenu.length-1) this.selected++;
                break;
            case KeyEvent.VK_ENTER:
                switch(this.optionsMenu[selected]) {
                    case START_GAME:
                        GameApp game = new GameApp();
                        break;
                        
                    case SCORE:
                        JFrame jf=new JFrame("Score-Board");
                        jf.setVisible(true);
                        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        jf.setSize(300,400);
                        String data[][]={ {"","",""},
                                {"","",""},
                                {"","",""}};
                        String column[]={"Rank #","Score","Time"};
                        JTable jt=new JTable(data,column);
                        jt.setBounds(30,40,200,300);
                        JScrollPane sp=new JScrollPane(jt);
                        jf.add(sp);
                        break;

                    case QUIT_GAME:
                        System.exit(0);
                        break;
                }
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {}
}
