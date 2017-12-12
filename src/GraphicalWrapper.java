import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
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
    JButton quit;
    Action menuAction;

    JPanel gameContainer;
    JPanel computerGrid;
    JPanel playerGrid;
    
    Battleship computer;
    Battleship player;

    PipedOutputStream outp = new PipedOutputStream();
    PipedInputStream in;
    
    CustomOutputStream out;
    int vertical = 0;
    GraphicalWrapper() {
        try{
        in = new PipedInputStream(outp); 
        }catch(IOException e){}
        System.setIn(in);

        computer = new Battleship();

        frame = new JFrame();
        frame.setSize(1800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        
        start = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)mainPanel.getLayout()).show(mainPanel, "game");
            }
        });
        start.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                vertical = 1-JOptionPane.showOptionDialog(gameContainer,"Please help me all the code is malformed, would you like your ship to be vertical?","",JOptionPane.PLAIN_MESSAGE,0,null,new String[] {"Vertical","Horizontal"},"Vertical");
                System.out.println(vertical);
            }
        });

        instructions = new JButton(new InfoButtonAction(menu,"In the placement phase, select a square to place you battleship on the left grid.\nIn the battle phase, select a location to attack on the right grid.\nPress \"m\" for menu during the game.\nPLEASE don't press the buttons too fast.","Instructions",JOptionPane.INFORMATION_MESSAGE));

        credits = new JButton(new InfoButtonAction(menu,
"Graphics: George Li\nBattleship Logic: Michael Rivkin\nLiscenced under the GPLv3 2017","Credits",JOptionPane.INFORMATION_MESSAGE));
        
        quit = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuAction = new InfoButtonAction(menu,new Object[] {instructions, credits, quit},"Menu",JOptionPane.PLAIN_MESSAGE);



        start.setText("Start");
        instructions.setText("Instructions");
        credits.setText("Credits");
        quit.setText("Quit");

        menu = new JPanel();

        saneMenuConstraints = new GridBagConstraints();
        saneMenuConstraints.gridwidth = GridBagConstraints.REMAINDER;
        saneMenuConstraints.fill = GridBagConstraints.HORIZONTAL;

        menu.setLayout(new GridBagLayout());
        menu.add(start, saneMenuConstraints);
        menu.add(instructions, saneMenuConstraints);
        menu.add(credits, saneMenuConstraints);
        menu.add(quit, saneMenuConstraints);
                
        gameContainer = new JPanel();
        gameContainer.setLayout(new GridLayout(1,3));

        computerGrid = new ShipGridPanel(W, H, computer, true, this);
        playerGrid = new ShipGridPanel(W, H, null, false, this);

        JTextArea textArea = new JTextArea(50,10);
        textArea.setEditable(false);
        out = new CustomOutputStream(textArea);
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);
        System.setErr(printStream);        
        System.out.println("Debug Only, Do not mind.");


        gameContainer.add(playerGrid);
        gameContainer.add(computerGrid);
        gameContainer.add(textArea);
        gameContainer.add(new JScrollPane(textArea));
        gameContainer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "menuPopup");
        gameContainer.getActionMap().put("menuPopup", menuAction);

        mainPanel.add(menu, "menu");
        mainPanel.add(gameContainer, "game");
        
        frame.add(mainPanel);
    }
    private void show() {
        frame.setVisible(true);
        Thread t = new Thread(new Runnable() {
            public void run() {
//<<<<<<< put game code here
                Scanner s = new Scanner(System.in);
                while (true) {
                    try{
                        System.out.println(s.nextLine());
                    }
                    catch(Exception e){}
                }
//>>>>>>>
            }
        });
        t.start();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphicalWrapper().show();
            }
        });
    }
 }


