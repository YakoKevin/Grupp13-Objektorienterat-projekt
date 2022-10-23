package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Place to store all locations for images used in the game.
 *
 * @see BufferedImage
 */
public enum ImageServer {
    ;
    public enum Ids {
        LEVEL("bg_sprite2.png"),
        KEY("key.png"),
        COIN("coin_coin_blue.png"),
        DOOR("door_sprite.png"),
        WALL("brick_wall.png"),
        FLOOR("floor.png"),
        BACKGROUND("image_background.png"),
        MAINMENU("mainmenu.png"),
        ICON_HP("Heart_heart_full.png"),
        ICON_KEY("key.png"),
        ICON_COIN("coin_coin_blue.png");
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

    public enum AnimationIds{
        PLAYER("player_sprites_Knightver.png"),
        ENEMY("skeleton_sprites_Knightver.png");
        private BufferedImage[][] imageGrid;

        AnimationIds(String path){
            this.imageGrid = loadAnimations(path);
        }
    }

    public enum DeathId{
        PLAYER("death.png"),
        ENEMY("death.png");
        private BufferedImage deathImage;

        DeathId(String path){
            this.deathImage = getSprite(path);
        }
    }

    private static BufferedImage[][] loadAnimations(String path) {
        BufferedImage[][] animations = new BufferedImage[3][10]; //TODO: add into a constant instead to avoid miss-match errors
        for (int row = 0; row < animations.length; row++) {
            for (int column = 0; column < animations[row].length; column++) {
                animations[row][column] = ImageServer.getSprite(path).getSubimage(row * 80, column * 120, 80, 80);
            }
        }
        return animations;
    }

    public static BufferedImage[][] getImageGrid(AnimationIds id) {
        return id.imageGrid;
    }

    public static BufferedImage getImage(Ids ids){
        return getSprite(ids.path);
    }

    public static BufferedImage getImage(DeathId ids){
        return ids.deathImage;
    }
}
