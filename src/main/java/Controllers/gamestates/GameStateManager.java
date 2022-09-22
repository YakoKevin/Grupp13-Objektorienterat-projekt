package Controllers.gamestates;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> states;

    public GameStateManager() {
        this.states = new Stack<>();
    }

    public void stackState(GameState state) {
        this.states.add(state);
    }

    public void backToPreviousState() {
        this.states.pop();
    }

    public void clearStack() {
        this.states.clear();
    }

    public void init() {
            this.states.peek().init();
    }

    public void loop() {
            this.states.peek().loop();
    }

    public void render(Graphics graphics) {
            this.states.peek().render(graphics);
    }

    public void keyPressed(int keyCode) {
            this.states.peek().keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
            this.states.peek().keyReleased(keyCode);
    }
}
