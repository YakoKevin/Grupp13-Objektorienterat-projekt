package entities;

import Models.IObservable;
import Models.IObserver;
import utilz.Directions;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static utilz.PlayerConstants.*;


public class Player extends Entity implements IObservable {

    private BufferedImage[][] animations;
    private int animationTick = 30;
    private int animationIndex = 0;
    private int animationSpeed = 30;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private double atkOffSetCoordX =0, atkOffSetCoordY =0;


    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimations();
        //super(100,20,0,5, 10);
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)){
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK;

        if (startAnimation != playerAction)
            resetAnimationTick();
    }

    private void resetAnimationTick() {
        animationIndex = 0;
        animationTick = 0;
    }

    private void updatePosition() {
        moving = false;

        if (left && !right) {
            x += playerSpeed;
            moving = true;
        }
        else if (right && !left) {
            x -= playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        }
        else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }


    }

    private void loadAnimations() {
        BufferedImage image = LoadSave.GetSprite(LoadSave.PLAYER_SPRITE);

        animations = new BufferedImage[6][3];
        for (int row = 0; row < animations.length; row++){
            for (int column = 0; column < animations[row].length; column++){
                animations[row][column] = image.getSubimage(row*40, column*80, 30, 80);
            }
        }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    public void update(){
        updatePosition();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int)y, null);
        drawHitbox(g);
    }

    public void resetDirectionBooleans(){
        System.out.println("TEST");
        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAtkOffSetCoordX(double atkX){this.atkOffSetCoordX=atkX;}
    public void setAtkOffSetCoordY(double atkY){this.atkOffSetCoordY=atkY;}
    public



    List<IObserver> observers;

    public void attack(){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        double coordX = this.getX();
        double coordY = this.getY();
        int hitBoxOffSet = 5; //ändra avstånd
        if(isLeft() ==true){
            coordX -= hitBoxOffSet; //beror på hur stor spelaren är
            coordY -= hitBoxOffSet;

        }
        else if(isRight()==true){
            coordY -= hitBoxOffSet;
        }
        else if(isUp()==true){
            coordX -= hitBoxOffSet;
            coordY -= hitBoxOffSet;

        }
        else if(isDown()==true){
            coordX -= hitBoxOffSet;
        }


        //drawRect(int coordX, coordY, atkR, atkR);

        notifyObservers();

    }
    public setAttackHitBox(double coordX,double coordY,double atkR, double atkR);
    {

    }


    public boolean checkIfInRange(Enemy enemy) { //just nu bara radie för att göra det lätt, men den ska så klart ta hänsyn till direction och en hitbox
        double enXPos = enemy.getX();
        double enYPos = enemy.getY();
        double diffX = (enXPos - this.getX());
        double diffY = (enYPos - this.getY());

        boolean insideAttackRectangle=false;

        double atkR = this.getAttackRange();
        System.out.println(atkR);

        if (Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) < atkR) {

            return true;
        }
        if()


        return false;
    }

    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        observers.remove(obs);


    }


    public void notifyObservers() {
        for(IObserver IObserver: observers){
            IObserver.update();
        }

    }
}
