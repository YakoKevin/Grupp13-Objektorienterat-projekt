import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

// TEMPORÄRT KLASS NAMN
public class GameApp extends GameApplication{

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("DungeonCrawler");
        gameSettings.setVersion("1.0");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
