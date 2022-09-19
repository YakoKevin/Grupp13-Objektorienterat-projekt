package Views;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameView extends GameApplication { // TEMPORÄRT NAMN
    private Entity player;

    public static void start(String[] args){
        launch(args);
    }
    @Override
    protected void initGame() {
        player = entityBuilder()
                .at(400, 400)
                .view(new Rectangle(25, 25, Color.PURPLE))
                .buildAndAttach();
    }
    @Override // DETTA MÅSTE VARA VID CONTROLLER
    protected void initInput() {
        onKey(KeyCode.D, () -> {
            player.translateX(5); // move right 5 pixels
            inc("CurrentXCoordinates", +5);
        });
        onKey(KeyCode.A, () -> {
            player.translateX(-5); // move left 5 pixels
            inc("CurrentXCoordinates", -5);
        });

        onKey(KeyCode.W, () -> {
            player.translateY(-5); // move up 5 pixels
            inc("CurrentYCoordinates", -5);
        });

        onKey(KeyCode.S, () -> {
            player.translateY(5); // move down 5 pixels
            inc("CurrentYCoordinates", +5);
        });
    }

    @Override
    protected void initUI() {
        Text textPixelsX = new Text();
        textPixelsX.setTranslateX(50); // x = 50
        textPixelsX.setTranslateY(100); // y = 100
        textPixelsX.textProperty().bind(getWorldProperties().intProperty("CurrentXCoordinates").asString());
        Text textPixelsY = new Text();
        textPixelsY.setTranslateX(50); // x = 50
        textPixelsY.setTranslateY(120); // y = 100
        textPixelsY.textProperty().bind(getWorldProperties().intProperty("CurrentYCoordinates").asString());
        getGameScene().addUINode(textPixelsX); // add to the scene graph
        getGameScene().addUINode(textPixelsY); // add to the scene graph
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("CurrentXCoordinates", 0);
        vars.put("CurrentYCoordinates", 0);
    }


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("DungeonCrawler");
        gameSettings.setVersion("1.0");
    }
}
