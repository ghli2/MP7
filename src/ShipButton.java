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
    boolean isComp;
    Image currentImg;
    final int x;
    final int y;

    ShipButton(int i,int j,Battleship b, boolean comp, Action a) {
        super(a);
        x=i;y=j;
        ship = b;
        isComp = comp;
    }
    private void setImg(Image img) {
        currentImg = img;
    }
    private void updateImg() {
        int x = this.x-1;
        int y = this.y-1;
        String status;
        if (ship == null) {
            this.setImg(Blnk);
            this.setIcon(Blank);
            return;
        }
        if (isComp) {
            status = ship.guessBoard[x][y];
            if (status.equals("O")) {
                this.setIcon(Blank);
                this.setImg(MissToken);
            } else if (status.equals("X")) {
                this.setIcon(ShipToken);
                this.setImg(HitToken);
            } else {
                this.setIcon(Blank);
                this.setImg(Blnk);
            }
        } else {
            status = ship.playerBoard[x][y];
            if (status.equals("*") 
             || status.equals("X")) {
                this.setIcon(ShipToken);
                if (status.equals("X"))
                    this.setImg(HitToken);
            } else {
                this.setIcon(Blank);
                if (status.equals("O"))
                    this.setImg(MissToken);
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateImg();
        g.drawImage(currentImg,getWidth()/2-30,getHeight()/2-30,null);
    }
}
