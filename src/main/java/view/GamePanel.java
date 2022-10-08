package view;

import controller.ActionController;
import general.GameApp;
import model.Attack;
import model.Movement;
import utilz.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static general.GameApp.GAME_HEIGHT;
import static general.GameApp.GAME_WIDTH;

class aTask extends SwingWorker<Void,String> {

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

    private GameApp gameApp;
    private KeyView keyView;
    private aTask t;

    public GamePanel(GameApp gameApp, Movement movement, Attack attack){
        addKeyListener(new ActionController(this, movement, attack));
        this.gameApp = gameApp;
        setPanelSize();
        int width = 50;

        JButton jb=new JButton("Pause");
        jb.setBackground(Color.BLUE);
        jb.setBounds(515, 300, 80, 30);
        this.add(jb);
        JButton jb1=new JButton("Resume");
        jb1.setBackground(Color.BLUE);
        jb1.setBounds(600, 300, 80, 30);
        this.add(jb1);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (t=new aTask()).execute();
                try {
                    gameApp.pauseThread();
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
                    if(t!=null) {
                        t.cancel(true);
                        jb.setEnabled(true);
                        jb1.setEnabled(false);
                        gameApp.resumeThread();
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        KeyView keyView = new KeyView(width, -30, width, 30, 0);

    }


    // Sets here, instead of view since it includes the border as well.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }


    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        gameApp.render(g);

        // g.translate(this.keyView.getX(), this.keyView.getY());
        // this.keyView.paint(g);

    }

    public GameApp getGameApp() {
        return gameApp;
    }
}
