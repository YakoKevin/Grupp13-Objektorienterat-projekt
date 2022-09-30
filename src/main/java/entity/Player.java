package entity;

import controller.ActionController;
import model.IObservable;
import model.IObserver;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
    private double atkOffSetCoordX = this.getX(), atkOffSetCoordY = this.getY();
    private Rectangle2D rect2D = new Rectangle2D.Double(getX(),getY(),100,100);

    public double getAtkOffSetX(){ //kanske inte så fint
        return atkOffSetCoordX;
    }

    public double getAtkOffSetY(){
        return atkOffSetCoordY;
    }


    @Override
    public double getAttackRange() {
        return 20; //tillfälligt så här
    }

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimations();
        setHealthPoints(100);
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
    public boolean getAttack(){
        return attacking;
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

    @Override
    public void setHealthPoints(double hp) {
        this.healthPoints=hp;
    }
    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    List<IObserver> iObservers;
    Skeleton sk =new Skeleton(50,50); //tillfälligt


    public void attack(){ // man borde veta varifrån och åt vilken riktning man attackerar så att Enemy kan avgöra om den blir träffad
        double playerWidth = 30; //Players storlek i x och// y
        double playerHeight = 100;

        if(ActionController.dir ==0){ //left
            setAtkOffSetCoordX(this.getX()-playerHeight); //beror på hur stor spelaren är och riktning
            setAtkOffSetCoordY(this.getY());
            System.out.println("v");
        }
        else if(ActionController.dir==2){
            setAtkOffSetCoordX(this.getX()-playerWidth);
            setAtkOffSetCoordY(this.getY()-playerHeight);

            System.out.println("u");
        }
        else if(ActionController.dir==3){
            setAtkOffSetCoordX(getX()-playerWidth);
            setAtkOffSetCoordY(getY()+playerHeight);
            System.out.println("n");
        }
        else if(ActionController.dir==1){
            setAtkOffSetCoordX(getX()+playerWidth);
            setAtkOffSetCoordY(getY());
            System.out.println("h");
        }
        else{setAtkOffSetCoordX(getX());
            setAtkOffSetCoordY(getY());}

        if(getAtkOffSetX()<0){setAtkOffSetCoordX(0);}
        if(getAtkOffSetY()<0){setAtkOffSetCoordY(0);}
        //drawRect(int coordX, coordY, atkR, atkR);

        sk.update(this); //få till det med observer bara

        //notifyObservers();
    }

    public void drawAttackHitbox(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        double atkX = getAtkOffSetX();
        double atkY = getAtkOffSetY();
        Rectangle2D rect = new Rectangle2D.Double(atkX,atkY,100,100);
        g2.draw(rect);
        setAttackRectangle(rect);
    }
    public void drawHP(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        String hpStr = Double.toString(getHealthPoints());
        g2.drawString("HP: " + hpStr,10,10);
    }
    public void setAttackRectangle(Rectangle2D r){
        rect2D=r;
    }
    public Rectangle2D getAttackRectangle(){
        return this.rect2D;
    }


    public boolean checkIfInRange(Enemy enemy) { //just nu bara radie för att göra det lätt, men den ska så klart ta hänsyn till direction och en hitbox
        double enXPos = enemy.getX();
        double enYPos = enemy.getY();
        double diffX = (enXPos - this.getX());
        double diffY = (enYPos - this.getY());

        if(getAttackRectangle().contains(enXPos,enYPos)){
            return true;
        }
        return false;
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
}
