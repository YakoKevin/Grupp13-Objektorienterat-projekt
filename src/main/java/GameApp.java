import Views.GamePanel;
import Views.GameView;

public class GameApp {

    private GameView gameView;
    private GamePanel gamePanel;

    public GameApp(){
        gamePanel = new GamePanel();
        gameView = new GameView(gamePanel);
        gamePanel.requestFocus();
    }
}
