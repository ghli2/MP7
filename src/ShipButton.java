import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ShipButton extends JButton {
    static ImageIcon Blank;
    static Image Blnk;
    static ImageIcon ShipToken;
    static Image Ship;
    static Image HitToken;
    static Image MissToken;
    static HashMap<String, Image> map;
    
    static {
        try {
            Blnk = ImageIO.read(ShipButton.class.getResource("img//blank.png"));
            Ship = ImageIO.read(ShipButton.class.getResource("img//ship.png"));
            Blank = new ImageIcon(Blnk);
            ShipToken = new ImageIcon(Ship);
            HitToken = ImageIO.read(ShipButton.class.getResource("img//stoken.png"));
            MissToken = ImageIO.read(ShipButton.class.getResource("img//mtoken.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Battleship ship;
    Image currentImg;
    final int x;
    final int y;

    ShipButton(int i,int j,Battleship b, Action a) {
        super(a);
        x=i;y=j;
        ship = b;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImg,getWidth()/2-30,getHeight()/2-30,null);
    }
}
