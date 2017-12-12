import java.io.*;
import java.util.concurrent.*;
class CustomInputStream extends InputStream {
    final LinkedBlockingQueue<Character> sb = new LinkedBlockingQueue<Character>();
    public void put(String s) {
        for (char c : s.toCharArray()) 
            sb.offer(c);
    }
    public int read() throws IOException {
        int c = -1;
        try {
            c = sb.take();
        } catch(InterruptedException ie) {
        }
        return c;
    }
} 
