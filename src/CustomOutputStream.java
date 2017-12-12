import java.io.*;
import javax.swing.*;
class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
    public void write(int b) throws IOException {
        textArea.setText(textArea.getText() + String.valueOf((char)b)); 
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.update(textArea.getGraphics());
    }
}
