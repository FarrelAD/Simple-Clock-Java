import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clock {

    static JFrame frame = new JFrame();
    static JLabel timeLabel = new JLabel();
    static JLabel dateLabel = new JLabel();

    static int screenWidth, screenHeight, 
        initialWidth, initialHeight, x, y;

    public static void main(String[] args) {
        displayFrame();
        displayTimeAndDate();
    }

    public static void displayFrame() {
        frame.setTitle("THE SIMPLE CLOCK"); // The title of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the window exit and end the program  when pressing the 'X' button
        frame.setVisible(true); // Makes the window is visible

        frame.getContentPane().setBackground(new Color(0, 0, 0)); // Default color of the window

        getScreenHeightAndWidth();

        // Set the initial width and length of the window
        initialWidth = (int) (screenWidth * 0.3);
        initialHeight = (int) (initialWidth / 16 * 9);

        // Set the size of window when first time appear to the screen
        frame.setSize(initialWidth, initialHeight);

        // Set the position of window when first time appear to the screen
        x = (int) (screenWidth / 2) - initialWidth;
        // int x = (int) (screenWidth - initialWidth) / 2;
        y = (int) ((screenHeight / 2) - initialHeight) - (screenHeight / 12);
        frame.setLocation(x, y);

        // Value monitoring
        // System.out.println(
        //     "Values:\n"+
        //     "- screenWidth: "+screenWidth+"\n"+
        //     "- screenHeight: "+screenHeight+"\n"+
        //     "- initialWidth: "+initialWidth+"\n"+
        //     "- initialHeight: "+initialHeight+"\n"+
        //     "- x: "+x+"\n"+
        //     "- y: "+y+"\n"+
        //     "---------------------------------------------"
        // );
    }

    public static void getScreenHeightAndWidth() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        screenWidth = mode.getWidth();
        screenHeight = mode.getHeight();
    }

    public static void displayTimeAndDate() {
        // Make the text color change to white
        timeLabel.setForeground(Color.white);
        dateLabel.setForeground(Color.white); 

        // Using GridBagLayout to setting position of label
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcTime = new GridBagConstraints();
        gbcTime.gridx = 0;
        gbcTime.gridy = 0;
        frame.add(timeLabel, gbcTime);

        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        frame.add(dateLabel, gbcDate);

        getActualTime();
        getActualDate();

        // Change the actual time every 1 second
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getActualTime();
                getActualDate();
            }
        });
        timer.start();
    }

    public static void getActualTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(timeFormatter);

        timeLabel.setText(formattedTime);

        // Modifiy the time output
        timeLabel.setFont(new Font("Arial", Font.BOLD, ((int) (0.2 * frame.getWidth()))));

        // Monitoring the output
        // System.out.println(
        //     "Time: " + formattedTime+"\n"+
        //     "---------------------------------------------"
        // );
    }

    public static void getActualDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currentDate.format(dateFormatter);

        dateLabel.setText(formattedDate);

        // Modifiy the date output
        dateLabel.setFont(new Font("Arial", Font.PLAIN, ((int) (0.05 * frame.getWidth()))));

        // Monitoring the output
        // System.out.println(
        //     "Date: " + formattedDate+"\n"+
        //     "---------------------------------------------"
        // );
    }

}