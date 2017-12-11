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
    GridBagConstraints saneMenuConstraints;
    JButton start;
    JButton instructions;
    JButton credits;
    Object[] optionStack;
    Action menuAction;

    JPanel gameContainer;
    JPanel computerGrid;
    JPanel playerGrid;
    
    Battleship computer;

    GraphicalWrapper() {
        computer = new Battleship();

        frame = new JFrame();
        frame.setSize(1400,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        
        start = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)mainPanel.getLayout()).show(mainPanel, "game");
            }
        });
        
        instructions = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menu,"In the placement phase, select a square and a direction to place you battleships on the left side.\nIn the battle phase, select a location to attack on the right side.\nPress \"m\" for menu during the game.","Instructions",JOptionPane.INFORMATION_MESSAGE,null);
            }
        });
        credits = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menu,"Graphics: George Li\nBattleship Logic: Michael Rivkin\nLiscenced under the GPLv3 2017","Credits",JOptionPane.INFORMATION_MESSAGE,null);
            }
        });

        optionStack = new Object[] {instructions, credits}; 
        menuAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menu,optionStack,"Menu",JOptionPane.PLAIN_MESSAGE,null);
            }
        };



        start.setText("Start");
        instructions.setText("Instructions");
        credits.setText("Credits");

        menu = new JPanel();

        saneMenuConstraints = new GridBagConstraints();
        saneMenuConstraints.gridwidth = GridBagConstraints.REMAINDER;
        saneMenuConstraints.fill = GridBagConstraints.HORIZONTAL;

        menu.setLayout(new GridBagLayout());
        menu.add(start, saneMenuConstraints);
        menu.add(instructions, saneMenuConstraints);
        menu.add(credits, saneMenuConstraints);
                
        gameContainer = new JPanel();
        gameContainer.setLayout(new GridLayout(1,2));

        computerGrid = new ShipGridPanel(W, H, computer);
        playerGrid = new ShipGridPanel(W, H, computer);

        gameContainer.add(playerGrid);
        gameContainer.add(computerGrid);
        gameContainer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "menuPopup");
        gameContainer.getActionMap().put("menuPopup", menuAction);

        mainPanel.add(menu, "menu");
        mainPanel.add(gameContainer, "game");
        
        frame.add(mainPanel);
    }
    private void gameStart() {
        show();
        //game code here 
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
