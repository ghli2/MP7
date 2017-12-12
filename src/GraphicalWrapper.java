import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
class GraphicalWrapper {
    static final int W = 6, H = 6;
    //maybe needed
    javax.swing.Timer refreshTimer;

    //fram to fit everything in
    JFrame frame;
    
    //main panel, since putting everything onto the fram
    //is faux pas
    JPanel mainPanel;

    //menu panel
    JPanel menu;
    //constraints for a adequate lookin panel
    GridBagConstraints saneMenuConstraints;
    //various buttons
    JButton start;
    JButton instructions;
    JButton credits;
    JButton quit;
    //bring up the menu in a box with keypress
    Action menuAction;

    //container to hold the running game
    JPanel gameContainer;
    ShipGridPanel computerGrid;
    ShipGridPanel playerGrid;
    
    //battleships for easy access
    Battleship computer;
    Battleship player;

    //piped IO streams as a hack to
    //transition from swing gui
    //to pure console application
    PipedOutputStream outp = new PipedOutputStream();
    PipedInputStream in;
   
    //visual debug window, because why not 
    CustomOutputStream out;

    //variable from semi-legacy feature
    //that's still useful
    int vertical = 0;

    GraphicalWrapper() {
        //continuation of the IO hack
        try{
        in = new PipedInputStream(outp); 
        }catch(IOException e){}
        System.setIn(in);
        
        //get the computer out of the way
        computer = new Battleship();

        //frame initialization
        frame = new JFrame();
        frame.setSize(1800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");
        
        //panel initialization
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        
        //button initialization
        start = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)mainPanel.getLayout()).show(mainPanel, "game");
            }
        });
    
        //button initialization
        start.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                vertical = 1-JOptionPane.showOptionDialog(gameContainer,"Please help me all the code is malformed, would you like your ship to be vertical?","",JOptionPane.PLAIN_MESSAGE,0,null,new String[] {"Vertical","Horizontal"},"Vertical");
                System.out.println(vertical);
            }
        });

        //button initialization
        instructions = new JButton(new InfoButtonAction(menu,"In the placement phase, select a square to place you battleship on the left grid.\nIn the battle phase, select a location to attack on the right grid.\nPress \"m\" for menu during the game.\nPLEASE don't press the buttons too fast.","Instructions",JOptionPane.INFORMATION_MESSAGE));

        //button initialization
        credits = new JButton(new InfoButtonAction(menu,
"Graphics: George Li\nBattleship Logic: Michael Rivkin\nLiscenced under the There's No Liscence Liscence 2017","Credits",JOptionPane.INFORMATION_MESSAGE));
        
        //button initialization
        quit = new JButton(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //popup menu initialization
        menuAction = new InfoButtonAction(menu,new Object[] {instructions, credits, quit},"Menu",JOptionPane.PLAIN_MESSAGE);



        //text for the buttons
        start.setText("Start");
        instructions.setText("Instructions");
        credits.setText("Credits");
        quit.setText("Quit");

        //initialize menu
        menu = new JPanel();

        //initialize formatting for menu
        saneMenuConstraints = new GridBagConstraints();
        saneMenuConstraints.gridwidth = GridBagConstraints.REMAINDER;
        saneMenuConstraints.fill = GridBagConstraints.HORIZONTAL;

        //adding buttons to menu
        menu.setLayout(new GridBagLayout());
        menu.add(start, saneMenuConstraints);
        menu.add(instructions, saneMenuConstraints);
        menu.add(credits, saneMenuConstraints);
        menu.add(quit, saneMenuConstraints);
                
        //game container initialization
        gameContainer = new JPanel();
        gameContainer.setLayout(new GridLayout(1,3));

        //grid initialization
        computerGrid = new ShipGridPanel(W, H, computer, true, this);
        playerGrid = new ShipGridPanel(W, H, null, false, this);

        //debug text display initialization
        JTextArea textArea = new JTextArea(50,10);
        textArea.setEditable(false);
        out = new CustomOutputStream(textArea);
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);
        System.setErr(printStream);        
        System.out.println("Debug Only, Do not mind.");


        //added relevant components to
        //game container
        gameContainer.add(playerGrid);
        gameContainer.add(computerGrid);
        gameContainer.add(textArea);
        gameContainer.add(new JScrollPane(textArea));
        //map the m key to the popup menu
        gameContainer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "menuPopup");
        gameContainer.getActionMap().put("menuPopup", menuAction);

        //add menu and game container to
        //main display area
        mainPanel.add(menu, "menu");
        mainPanel.add(gameContainer, "game");
        
        //add main display area to frame
        frame.add(mainPanel);
    }
    int nxConverter(String s) {
        return Integer.parseInt(s.substring(2,3));
    }
    int nyConverter(String s) {
        return Integer.parseInt(s.substring(4,5));
    }
    void say(String s) {
        JOptionPane.showMessageDialog(gameContainer,s,"",JOptionPane.PLAIN_MESSAGE,null);
    }
    private void show() {
        //show the frame to the world
        frame.setVisible(true);
        Thread t = new Thread(new Runnable() {
            public void run() {
//<<<<<<< put game code here
                /*while (true) {
                    try{
                        System.out.println(s.nextLine());
                    }
                    catch(Exception e){}
                }*/
		        int i = 1;
		        int j = 1;
		        int game = 0;
		        int count1 = 0;
		        int count2 = 0;
		        Scanner in = new Scanner(System.in);
		        //Battleship computer = new Battleship(); taken care of 
		        //initial placement and setup
		        //taken care of 
		        //System.out.print("Would you like your ship to be vertical(1 for yes, 0 for no): ");
		        //int vertical = in.nextInt();                                                  //nonstandard accpet command could go here
		        System.out.print("Select the upper left point of your ship: ");
		        String location;
                gameContainer.repaint();
                while((location = in.next()).charAt(0) != 'P');
		        i = nxConverter(location);
		        j = nyConverter(location);
		        Battleship player = new Battleship(vertical, i, j);
                playerGrid.setShip(player);
                //taken care of
		        //System.out.println("Here is your board:");
		        //player.printPlayerBoard();
		        //System.out.println("Here is your opponent's board:");
		        //computer.printGuessBoard();
		        //most of the game
		        while(game == 0) {
		        	//player guess and move
		            while(count2 == 0) {
                        gameContainer.repaint();
		                System.out.print("Select your guess");
                        gameContainer.repaint();
                        while((location = in.next()).charAt(0) != 'C');
		                i = nxConverter(location);
		                j = nyConverter(location);
		                if(computer.playerOptions[i-1][j-1]==0) {
		                    Battleship.playermove(computer, i, j);
		                    count2++;
		                } else {
		                    System.out.println("You already tried that");
		                }
		            }
		            count2 = 0;
		            count1 = 0;
		            //checks to see if player won
		            for(int u=0; u<6; u++) {
		                for(int v=0; v<6; v++) {
		                    if(computer.guessBoard[u][v].equals("X")) {
		                        count1++;
		                    }
		                }
		            }
		            if(count1 == 3) {
		                game = 1;
		            }
		            count1 = 0;
		            //ai below
		            for(int u=0; u<6; u++) {
		                for(int v=0; v<6; v++) {
		                    if(player.playerBoard[u][v].equals("X")) {
		                        count1++;
		                    }
		                }
		            }
		            if(count1 == 0) {
		                Battleship.aimove1(computer, player);
		            }
		            if(count1 == 1) {
		                Battleship.aimove2(computer, player);
		            }
		            if(count1 == 2) {
		                if(Battleship.aimove3(computer, player, game, vertical)) {
		                	game = 2;
		                }
		            }
		        }
		        count1 = 0;
		        if(game == 1) {
		            say("Congrats you are a winner");
		        }
		        if(game == 2) {
		            say("You lost to a computer");
		        }
                System.exit(0);
//>>>>>>>
            }
        });
        t.start();
    }
    public static void main(String[] args) {
        //run the everything
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphicalWrapper().show();
            }
        });
    }
 }


