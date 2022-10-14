package view;

import entity.Entity;

import java.awt.*;
import java.util.Random;

public class FPSUpdater implements Runnable {
    private final int MAX_FPS = 120;
    private final int MAX_UPS = 200;
    private GamePanel gamePanel;
    private Thread gameThread;

    public FPSUpdater(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pauseGameLoop(){
        gameThread.suspend();
    }

    public void continueGameLoop(){
        gameThread.resume();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS;
        double timePerUpdate = 1000000000.0 / MAX_UPS;
        long previousTime = System.nanoTime();
        int fps = 0;
        int updates = 0;
        long lastChecked = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1){
                this.gamePanel.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                this.gamePanel.repaint();
                fps++;
                deltaF--;
            }

            //if (now - lastFrame >= timePerFrame){
            //    gamePanel.repaint();
            //    lastFrame = System.nanoTime();
            //    fps++;
            //}

            if (System.currentTimeMillis() - lastChecked >= 1000){
                lastChecked = System.currentTimeMillis();
//                System.out.println("FPS: " + fps + " | UPS: " + updates);

                fps = 0;
                updates = 0;
                //TODO: Must move and redo! Can not lay in view-class. Do more dynamically, use objects (our enemies)!
/*
                float enemyX = this.gamePanel.animationEnemy.entity.getX();
                float enemyY = this.gamePanel.animationEnemy.entity.getY();

                float playerX = this.gamePanel.animation.entity.getX();
                float playerY = this.gamePanel.animation.entity.getY();

//                System.out.println("--" + Math.abs(playerX - enemyX));
//                System.out.println("--" + Math.abs(playerY - enemyY));

                int enemyMoveDistance = 55;
                boolean isNear = false;
                if(Math.abs(playerX - enemyX) < 150 && Math.abs(playerY - enemyY) < 150) {
                    isNear = true;
                }

                if(isNear) {
                    if(Math.abs(playerX - enemyX) > Math.abs(playerY - enemyY)) {
                        if(enemyX > playerX) {
                            this.gamePanel.animationEnemy.entity.setX(this.gamePanel.animationEnemy.entity.getX()-enemyMoveDistance);
                        }else {
                            this.gamePanel.animationEnemy.entity.setX(this.gamePanel.animationEnemy.entity.getX()+enemyMoveDistance);
                        }
                    }else {
                        if(enemyY > playerY) {
                            this.gamePanel.animationEnemy.entity.setY(this.gamePanel.animationEnemy.entity.getY()-enemyMoveDistance);
                        }else {
                            this.gamePanel.animationEnemy.entity.setY(this.gamePanel.animationEnemy.entity.getY()+enemyMoveDistance);
                        }
                    }
                }else {
                    // move random
                    int random = new Random().nextInt(4);

                    if(random == 0) {
                        this.gamePanel.animationEnemy.entity.setX(this.gamePanel.animationEnemy.entity.getX()+enemyMoveDistance);
                    }else if(random == 1) {
                        this.gamePanel.animationEnemy.entity.setY(this.gamePanel.animationEnemy.entity.getY()+enemyMoveDistance);
                    }else if(random == 2) {
                        this.gamePanel.animationEnemy.entity.setX(this.gamePanel.animationEnemy.entity.getX()-enemyMoveDistance);
                    }else if(random == 3) {
                        this.gamePanel.animationEnemy.entity.setY(this.gamePanel.animationEnemy.entity.getY()-enemyMoveDistance);
                    }
                }

                if(Math.abs(this.gamePanel.animationEnemy.entity.getX() - this.gamePanel.animation.entity.getX()) < 20 && Math.abs(this.gamePanel.animationEnemy.entity.getY() - this.gamePanel.animation.entity.getY()) < 20) {
                    this.gamePanel.player.healthPoints = this.gamePanel.player.healthPoints - 10;
                }
*/
            }

        }
    }
}
