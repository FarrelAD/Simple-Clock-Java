import javax.swing.JFrame;
import java.awt.*;
public class clock {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("THE SIMPLE CLOCK"); // The title of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the window exit and end the program  when pressing the 'X' button
        frame.setVisible(true); // Makes the window is visible

        frame.getContentPane().setBackground(new Color(0, 0, 0)); // Default color of the window

        // Get the screenwidth of the device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        int screenWidth = mode.getWidth();
        int screenHeight = mode.getHeight();

        // Set the initial width and length of the window
        int initialWidth = (int) (screenWidth * 0.3);
        int initialHeight = (int) (initialWidth / 16 * 9); // You can set an initial height as desired

        // Set the size of window when first time appear to the screen
        frame.setSize(initialWidth, initialHeight);

        // Set the position of window when first time appear to the screen
        int x = (int) (screenWidth / 2) - initialWidth;
        int y = (int) ((screenHeight / 2) - initialHeight) - (screenHeight / 12);
        frame.setLocation(x, y);

        // Value monitoring
        System.out.println(
            "Values:\n"+
            "- screenWidth: "+screenWidth+"\n"+
            "- screenHeight: "+screenHeight+"\n"+
            "- initialWidth: "+initialWidth+"\n"+
            "- initialHeight: "+initialHeight+"\n"+
            "- x: "+x+"\n"+
            "- y: "+y+"\n"
        );
    }
}