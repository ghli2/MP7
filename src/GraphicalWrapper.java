import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Battleship graphical wrapper
 *
 * @author George Li
 * @version 0.0.0
 */
public class GraphicalWrapper extends JPanel implements Runnable {
    int w =8; h =8;
    
    /** timer as tool for rudimentery graphical refreshing */
    javax.swing.Timer t;

    /** Panel to contain game. */
    private JPanel gamePanel;
    
    /** Panel to contain options. */
    private JPanel optionsPanel;

    /** Battleships. */
    private Battleship player, computer;
    
    /** Buttons. */
    ShipButton[][][] buttons = new ShipButton[2][w][h];

    ImageIcon Blank;
    Image Blnk;
    Image Token;

    public GraphicalWrapper(Battleship player, Battleship computer) {

        this.player = new Battleship();
        this.computer = new Battleship();

        try {
            Blnk = ImageIo.read(getClass().getResource("img//blank.png"));
            Blank = new ImageIcon(Blnk);
            HitToken = ImageIO.read(getClass().getResource("img//hitToken.png"));
            MissToken = ImageIO.read(getClass().getResource("img//missToken.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridLayout(8,8));
        for(int s = 0; s < 2; ++s)
        for (int i = 0; i < w; ++i)
            for (int j = 0; j < h; ++j) {
                acts[i][j] = new SpotAction(i,j, s == 0 player);
                ShipButton temp = new ShipButton(s == 0 ? acts[i,j] : null);
                buttons[s][i][j] = temp;
                this.add(temp);
        } 
        
        
    }
    public void run() {
        ActionListener refreshAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        };
        
        t = new javax.swing.Timer(5, refreshAction);
    }
    
    public void refresh() {
        for (int s = 0; s < 2; ++s)
        for (int i = 0; i < w; ++i)
        for (int j = 0; j < h; ++j) {
            buttons[s][i][j].setIcon(Blank);
            if (button[s][i][j].getStatus() !=
 
    public static void main(string[] args) {
        JFrame f = new JFrame();
        f.add(new GraphicalWrapper);
        f.setSize(700,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Battleship");
        f.setVisible(true);
    }
    
    class ShipButton extends JButton {
        Image img;
        
        public void setImg(Image i) {
            img = i;
        }
        public void removeImage() {
            img = Blnk;
        }
        public ShipButton(Action a) {
            super(a);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img,getWidth()/2-30,getHeight()/2-30,null);
        }
    } 
    
    class SpotAction extends AbstractAction {
        int x,y;
        
}
