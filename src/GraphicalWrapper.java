import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class GraphicalWrapper {
    static final int W = 6, H = 6;
    javax.swing.Timer refreshTimer;

    JFrame frame;

    JPanel mainPanel;
    JPanel menu;
    JPanel instructions;
    JPanel gameContainer;
    JPanel computerGrid;
    JPanel playerGrid;
    
    Battleship player;
    Battleship computer;


    GraphicalWrapper() {
        player = new Battleship();
        computer = new Battleship();

        frame = new JFrame();
        frame.setSize(1400,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        
        menu = new JPanel();
        menu.setLayout(new GridLayout(1,5));
        
        gameContainer = new JPanel();
        gameContainer.setLayout(new GridLayout(1,2));

        computerGrid = new ShipGridPanel(W, H, computer);
        playerGrid = new ShipGridPanel(W, H, player);

        gameContainer.add(playerGrid);
        gameContainer.add(computerGrid);
        //mainPanel.add(menu);
        mainPanel.add(gameContainer);
        
        frame.add(mainPanel);
    }
    private void show() {
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphicalWrapper().show();
            }
        });
    }
}
