package entity;

import general.GameMain;
import view.Animation;
import general.IObservable;
import general.IObserver;
import model.Movement;
import utilz.ImageServer;
import controller.ActionController;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Player extends Entity implements IObservable, HostileAttacker {
    private int playerDirection = -1;
    private boolean attacking = false;
    private int[][] levelData;
    private double atkOffSetCoordX = this.getX(), atkOffSetCoordY = this.getY();
    private Rectangle atkRect = new Rectangle((int)getX(),(int)getY(),100,100);
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private Skeleton sk= new Skeleton(50,50);
    static int index=0;
    static String data[][]=new String[50][3];
    static String rank="1";

    private int skelX=0,skelY=0;

    private int keyCount;
    private static int scoreCount;

    Animation animation;
    Movement movement;

    public int getScoreCount(){
        return scoreCount;
    }

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        this.setAttackPoints(20);
        this.healthPoints=100;
        this.keyCount = 0;
        scoreCount=0;
        animation = new Animation(ImageServer.Ids.PLAYER, this);
        movement = new Movement(this, animation);
        //super(100,20,0,5, 10);
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    public Movement getMovement() {
        return movement;
    }

    public void update(){
        skelX=Skeleton.cx;
        skelY=Skeleton.cy;
        updateHitbox();
        /*if(checkIfHitByAttacker(sk)==true){ //kanske inte är härifrån som man kollar detta
            this.setHealthPoints(this.getHealthPoints()-sk.getAttackPoints());
        }*/

        if(attacking){
            setPlayerAttackRectangle();
            sk.checkedIfIsAttacked(this.getPlayerAttackRectangle(),this.getAttackPoints()); //ersätt med lista av enemies, tillfälligt
        }

        movement.updatePosition();

        checkAttack(skelX,skelX);
    }

    private void checkAttack(int xx,int yy) { //borde vara enemy's och player's hitbox-rektanglar som parametrar
        if(this.x==xx && this.x<xx+10) {
            setHealthPoints(getHealthPoints()-5);
        }
        else if(this.y==yy && this.y<yy+10) {
            setHealthPoints(getHealthPoints()-5);
        }
        if(getHealthPoints()<=0) {
            /*
            JOptionPane.showMessageDialog(null, "Do You want to try again??",
                    "Game Over", JOptionPane.YES_NO_CANCEL_OPTION);
            System.exit(0);
            */
            int a=JOptionPane.showConfirmDialog(null,"Game Over!!!\nYour Score is: "+ getScoreCount() +"\nDo You want to try again??");
            data[index][0]=rank;
            data[index][1]=String.valueOf(getScoreCount());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            data[index][2]=dtf.format(now);
            index++;
            String temp1 = "";
            for(int i=0;i<data.length && data[i][0]!=null;i++) {
                for (int j = i+1; j < data.length && data[j][0]!=null; j++) {
                    System.out.println(Integer.parseInt(data[i][1]));
// 						System.out.println(Integer.parseInt(data[1][1]));
                    System.out.println(data.length);
                    if(Integer.parseInt(data[i][1]) > Integer.parseInt(data[j][1])) {
                        // For Score
                        temp1 = data[i][1];
                        data[i][1] = data[j][1];
                        data[j][1] = temp1;
                        // For time
                        temp1 = data[i][2];
                        data[i][2] = data[j][2];
                        data[j][2] = temp1;
                    }
                }
            }
            if(a==JOptionPane.YES_OPTION){
                GameMain.reset();
            }
            if(a==JOptionPane.NO_OPTION){

            }
            if(a==JOptionPane.CANCEL_OPTION){
                System.exit(0);
            }

            System.exit(0);
        }
    }

    public void render(Graphics g){
        drawHitbox(g);
        animation.render(g);
    }


    List<IObserver> iObservers;
    public void drawHP(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String hpStr = Double.toString(getHealthPoints());
        g2.setFont(new Font("Araial", Font.BOLD, 12));
        g2.setColor(new Color(255, 0, 70));
        g2.drawString("HP: " + hpStr,10,10);
        g2.drawString("KEYS: " + this.keyCount, 10,30);
        g2.drawString("SCORE: " + scoreCount, 10,50);
    }


    public boolean checkIfInRange(Enemy enemy) {
        double enX = enemy.getX();
        double enY = enemy.getY();
        /*(x,y) is inside the rectangle with coordinates (x1,y1,x2,y2)

        x <= x2 && x >= x1 && y <= y2 && y >= y1;
*/
        //boolean check = enX<=100 && enX>= getAtkOffSetX() && enY <= 100 && enY>=getAtkOffSetY();
        //if(check==true){
         //   return true;
       // }
        //System.out.println("enemy coord: " +enX + enY);
       // if(getAttackRectangle().contains(enX,enY)){
        ////    return true;
       // }
        return false;
    }

    //Method checks if attacker has walked into player, i.e. their hitboxes allign
    public boolean checkIfHitByAttacker(double lEx, double lEy, double width, double height){ //ska vara en lista med enemies som kommer från level här sen
        double lPx = this.getX(); //think two rectangles, top left and bottom right coordinates for player and enemy
        double lPy = this.getY();
        double rPx = this.getX()+this.getWidth();
        double rEx = lEx + width;
        double rEy = lEy + height; //kan nog städa upp variablerna lite här och (kanske namnge annorlunda)

        if(lPx > rEx||lEx>rPx){ //if any rectangle is on the left side of the other one
            return false;
        }
        if(rEx>lPx||rEy>lEy){
            return false;
        }
        return true;
    }

    public void addObserver(IObserver obs) {
        iObservers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        iObservers.remove(obs);
    }

    public void notifyObservers() {
        for(IObserver IObserver: iObservers){
            IObserver.update();
        }
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void addKey()
    {
        keyCount++;
        scoreCount+=10;
    }


    public Rectangle getPlayerAttackRectangle(){ //dessa två klasser ska nog vara allmänna så att enemy också kan använda
        return this.atkRect;
    }
    public void setPlayerAttackRectangle(){
        int d = ActionController.dir;
        int atkX= (int)this.x;
        int atkY= (int)this.y;

        if(d ==0){ //left
            atkX-=this.height; //beror på hur stor spelaren är och riktning
            // System.out.println("v");
        }
        else if(d==2){
            atkX -= this.getWidth();
            atkY -= this.getHeight();

            //System.out.println("u");
        }
        else if(d==3){
            atkX -=this.getWidth();
            atkY +=this.getHeight();

            //System.out.println("n");
        }
        else if(d==1){
            atkX += getWidth();

            //System.out.println("h");
        }
        Rectangle r = new Rectangle (atkX,atkY, 100,100);
        this.atkRect= r;
    }


    @Override
    public void addHostilesList(ArrayList<Hostile> hostile) {
        this.hostiles = hostile;
    }
}
