package Views;

import Controllers.gamestates.GameState;
import Controllers.gamestates.GameStateManager;

import java.awt.*;

public class MainMenu extends GameState {

    public MainMenu(GameStateManager manager) {
        super(manager);
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);

    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
