package Views;

import Controllers.gamestates.GameState;
import Controllers.gamestates.GameStateManager;

import java.awt.*;

public class MainMenu extends GameState {

    private String[] optionsMenu;
    private int selected;

    public MainMenu(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[] {"START_GAME"};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        graphics.setColor(new Color(30, 30, 70));
        graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);

        for(int i=0;i<this.optionsMenu.length;i++) {
            if(i==this.selected) graphics.setColor(Color.GREEN);
            else graphics.setColor(Color.WHITE);
            graphics.drawString(this.optionsMenu[i], 20, 50 + i * 40);
        }
    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
