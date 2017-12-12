import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class BattleshipAction extends AbstractAction {
    int x, y;
    String msg;
    boolean isComp;
    GraphicalWrapper ancestor;
    BattleshipAction(int x, int y, boolean comp,GraphicalWrapper ancestor) {
        this.ancestor = ancestor;
        this.x=x;this.y=y;
        isComp=comp;
        msg= (isComp? "C" : "P") + String.format("(%d,%d)\n",x,y);
    }
        
    public void actionPerformed(ActionEvent e) {
        synchronized(ancestor.outp) {
            try {
            ancestor.outp.write(msg.getBytes(),0,msg.getBytes().length);
            }
            catch (Exception ex) {}
        }
    }
            
}

