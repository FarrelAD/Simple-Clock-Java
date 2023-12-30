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

    public static void main(String[] args) {
        displayFrame();
        displayTime();
        displayDate();
    }

    public static void displayFrame() {
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
        int initialHeight = (int) (initialWidth / 16 * 9);

        // Set the size of window when first time appear to the screen
        frame.setSize(initialWidth, initialHeight);

        // Set the position of window when first time appear to the screen
        int x = (int) (screenWidth / 2) - initialWidth;
        // int x = (int) (screenWidth - initialWidth) / 2;
        int y = (int) ((screenHeight / 2) - initialHeight) - (screenHeight / 12);
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

    public static void displayTime() {
        timeLabel.setForeground(Color.white); // Make the text color white

        // Using GridBagLayout to center timeLabel
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcTime = new GridBagConstraints();
        gbcTime.gridx = 0;
        gbcTime.gridy = 0;
        frame.add(timeLabel, gbcTime);

        getActualTime();

        // Change the actual time every 1 second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getActualTime();
            }
        });
        timer.start();
    }

    public static void getActualTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(timeFormatter);

        timeLabel.setText(formattedTime);

        // Monitoring the output
        // System.out.println(
        //     "Time: " + formattedTime+"\n"+
        //     "---------------------------------------------"
        // );
    }

    public static void displayDate() {
        dateLabel.setForeground(Color.white); // Make the text color white

        // Using GridBagLayout to center timeLabel
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        frame.add(dateLabel, gbcDate);

        getActualDate();

        // Calculate the initial delay until midnight
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
        Duration initialDelay = Duration.between(now, midnight);

        // Change the actual time every 24 hours
        Timer timer = new Timer((int) initialDelay.toMillis(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getActualDate();
                ((Timer) e.getSource()).setInitialDelay(24 * 60 * 60 * 1000); // Update delay for 24 hours
            }
        });
        timer.setRepeats(false); // Only execute once, then repeat every 24 hours
        timer.start();
    }

    public static void getActualDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currentDate.format(dateFormatter);

        dateLabel.setText(formattedDate);

        // Monitoring the output
        // System.out.println(
        //     "Date: " + formattedDate+"\n"+
        //     "---------------------------------------------"
        // );
    }

}