package Models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    static Enemy enemy;
    static Player player;

    @BeforeAll
    public static void setup (){
        enemy = new Enemy();
        player = new Player();
    }
}
