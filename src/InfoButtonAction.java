import javax.swing.*;
import java.awt.event.*;
//culls the number of the wierd class files you
//get when compiling code with anonymous classes
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
