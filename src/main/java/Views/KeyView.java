package Views;

import javax.swing.*;
import java.awt.*;

public class KeyView extends JPanel {
    private static KeyView Instance;
    private Color backgroundColor;
    private boolean clearBackground;

    public KeyView(int x, int y, int w, int h, int keys) {
        Instance = this;
        this.setBounds(x - w, y + h, w, h);
        backgroundColor = Color.BLACK;
        clearBackground = true;
        setForeground(Color.MAGENTA);
    }
}
