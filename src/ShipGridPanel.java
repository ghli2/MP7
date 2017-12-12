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
    GraphicalWrapper parent;
    
    ShipGridPanel(int x, int y, Battleship b, boolean comp, GraphicalWrapper parent) {
        this.setLayout(new GridLayout(x,y));
        ship = b;
        W = x;
        H = y;
        isComp = comp;
        buttons = new ShipButton[W][H];
        this.parent = parent;
        initButtons();
    }
    void setShip(Battleship ship) {
        //sets the ship for this and
        //all child components
        this.ship = ship;
        for (ShipButton[] row : buttons)
            for (ShipButton b: row)
                b.ship = ship;
        repaint();
    }
    private void initButtons() {
        //makes the buttons
        for (int i = 1; i <= H; ++i)
            for (int j = 1; j <= W; ++j) {
                ShipButton temp = new ShipButton(i,j,ship, isComp, new BattleshipAction(i,j,isComp,parent));
                buttons[i-1][j-1] = temp;
                this.add(temp);
            }
                
    }
 }
