package view;

import java.awt.*;

public class AttackView {
    public void drawAttackRectangle(Graphics g, int x, int y, int w, int h) {
        g.setColor(Color.red);
        g.drawRect(x,y,w,h);
    }
     //vet inte om den här klassen behövs, kanske ska slås ihop med en annan klass också
}
