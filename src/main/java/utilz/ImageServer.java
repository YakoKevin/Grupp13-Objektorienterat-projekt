package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Place to store all locations for images used in the game. Perhaps for other storage of animation related stuff.
 */
public enum ImageServer {
    ;
    public enum Ids {
        PLAYER("player_sprites3.png"),
        LEVEL("bg_sprite2.png"),
        KEY("key_sprite.png");
        private String path;

        Ids(String path){
            this.path = path;
        }


    }

    private static BufferedImage getSprite(String filePath) {
        BufferedImage image = null;

        try (InputStream is = ImageServer.class.getResourceAsStream("/" + filePath)) {
            image = ImageIO.read(is);
        }
        catch (IOException ignored) {
        }
        return image;
    }

    public static BufferedImage getImage(Ids ids){
        return getSprite(ids.path);
    }
}
