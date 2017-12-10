import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ShipGridPanel extends JPanel {
    final int W;
    final int H;
    Battleship ship;
    ShipButton[][] buttons;
    //0 = computer
    //1 = human
    int mode;

    ShipGridPanel(int x, int y, Battleship b) {
        this.setLayout(new GridLayout(x,y));
        ship = b;
        W = x;
        H = y;
        buttons = new ShipButton[W][H];
        System.out.println("cuck");
        initButtons();
    }
    private void initButtons() {
        for (int i = 0; i < W; ++i)
            for (int j = 0; j < H; ++j) {
                this.add(new ShipButton(i,j,ship, new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("cuck");
                    }
                }));
            }
                
    }
    
}
