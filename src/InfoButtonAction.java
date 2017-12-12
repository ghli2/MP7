import javax.swing.*;
import java.awt.event.*;
class InfoButtonAction extends AbstractAction{
    Object msg;
    JComponent parent;
    String title;
    int type;
    InfoButtonAction(JComponent c, Object m, String t, int ty){
        parent = c;
        msg = m;
        title = t;
        type = ty;
    }
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(parent,msg,title,type,null);
    }
} 
