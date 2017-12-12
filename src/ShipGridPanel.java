import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ShipGridPanel extends JPanel {
    final int W;
    final int H;
    final boolean isComp;
    Battleship ship;
    ShipButton[][] buttons;

    ShipGridPanel(int x, int y, Battleship b, boolean comp) {
        this.setLayout(new GridLayout(x,y));
        ship = b;
        W = x;
        H = y;
        isComp = comp;
        buttons = new ShipButton[W][H];
        initButtons();
    }
    private void initButtons() {
        for (int i = 0; i < W; ++i)
            for (int j = 0; j < H; ++j) {
                this.add(new ShipButton(i,j,ship, isComp, new Temp(i,j)));
            }
                
    }
    class Temp extends AbstractAction {
        int x,y;
        Temp(int x, int y) {
            this.x=x;this.y=y;
        }
        public void actionPerformed(ActionEvent e) {
            //this gets called every time the button is pressed
            //modify as you see fit
            System.out.printf("(%d,%d)\n", x,y);
        }
    }
}
