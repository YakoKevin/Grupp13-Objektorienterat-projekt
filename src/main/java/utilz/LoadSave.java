package utilz;

import General.GameApp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_SPRITE = "player_sprites3.png";
    public static final String LEVEL_SPRITE = "bg_sprite2.png";



    public static BufferedImage GetSprite(String fileName) {
        BufferedImage image = null;

        try (InputStream is = LoadSave.class.getResourceAsStream("/" + fileName)) {
            image = ImageIO.read(is);
            }
        catch (IOException ignored) {
        }
        return image;
    }


}
