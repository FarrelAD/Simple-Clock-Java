import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clock {

    static JFrame frame = new JFrame();
    static JLabel timeLabel = new JLabel();
    static JLabel dateLabel = new JLabel();
    static JButton darkModeButton = new JButton();
    static LineBorder outerBorder;
    static Color myColor[] = {Color.WHITE, Color.BLACK};
    static int myFirstColorIndex = 0, mySecondColorIndex = 1;
    static String[] textColorChangeButton = {"DARK MODE", "LIGHT MODE"};
    static int screenWidth, screenHeight, 
        initialWidth, initialHeight, x, y;

    public static void main(String[] args) {
        displayFrame();
        displayTimeAndDate();
        actionDarkModeButton();
    }

    public static void displayFrame() {
        frame.setTitle("THE SIMPLE CLOCK"); // The title of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the window exit and end the program  when pressing the 'X' button
        frame.setVisible(true); // Makes the window is visible

        frame.getContentPane().setBackground(myColor[myFirstColorIndex]); // Default color of the window

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

        // Monitoring the output
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
        // Using GridBagLayout to setting position of label
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcTime = new GridBagConstraints(); 
        gbcTime.gridx = 0;
        gbcTime.gridy = 0;
        gbcTime.weightx = 1;
        gbcTime.weighty = 1;
        gbcTime.anchor = GridBagConstraints.CENTER;
        frame.add(timeLabel, gbcTime);

        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        frame.add(dateLabel, gbcDate);

        getActualTime();
        getActualDate();
        displayDarkModeButton();

        // Change the display output  every 1 milisecond
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getActualTime();
                getActualDate();
                displayDarkModeButton();
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
        timeLabel.setForeground(myColor[mySecondColorIndex]);
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
        dateLabel.setForeground(myColor[mySecondColorIndex]); 
        dateLabel.setFont(new Font("Arial", Font.PLAIN, ((int) (0.05 * frame.getWidth()))));

        // Monitoring the output
        // System.out.println(
        //     "Date: " + formattedDate+"\n"+
        //     "---------------------------------------------"
        // );
    }

    public static void displayDarkModeButton() {
        darkModeButton.setSize(200, 200);
        darkModeButton.setBackground(myColor[myFirstColorIndex]); // Make the button background black
        
        // Set the border and padding of button
        outerBorder = new LineBorder(myColor[mySecondColorIndex], 2);
        darkModeButton.setBorder(new CompoundBorder(outerBorder, (new EmptyBorder(2, 5, 2, 5))));

        // Setting the text that will appear inside of the button
        darkModeButton.setText(textColorChangeButton[myFirstColorIndex]);
        darkModeButton.setForeground(myColor[mySecondColorIndex]);
        darkModeButton.setFont(new Font("Arial", Font.BOLD, 15)); 
        darkModeButton.setFocusable(false);

        // Set the position of the button
        GridBagConstraints gbcDarkModeButton = new GridBagConstraints();
        gbcDarkModeButton.gridx = GridBagConstraints.RELATIVE; // Make the button position on the next right of other component
        gbcDarkModeButton.gridy = 2;
        gbcDarkModeButton.anchor = GridBagConstraints.SOUTHEAST; // Align to the bottom-right corner

        gbcDarkModeButton.weightx = 1;
        gbcDarkModeButton.weighty = 1;

        // Set insets to create some spacing from the edges
        gbcDarkModeButton.insets = new java.awt.Insets(0, 0, 20, 20);

        // Add button to the frame
        frame.add(darkModeButton, gbcDarkModeButton);

        // Monitoring the output
        // System.out.println(
        //     "frame height : "+frame.getHeight()+"\n"+
        //     "---------------------------------------------"
        // );
    }

    public static void actionDarkModeButton() {
        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFirstColorIndex = (myFirstColorIndex + 1) % myColor.length;
                if (myFirstColorIndex == 1) {
                    mySecondColorIndex = 0;
                } else if (myFirstColorIndex == 0) {
                    mySecondColorIndex = 1;
                }

                frame.getContentPane().setBackground(myColor[myFirstColorIndex]);;
                timeLabel.setForeground(myColor[mySecondColorIndex]);
                dateLabel.setForeground(myColor[mySecondColorIndex]);

                // Monitoring the output
                // System.out.println(
                //     "myFirstColorIndex: "+myFirstColorIndex+"\n"+
                //     "mySecondColorIndex: "+mySecondColorIndex+"\n"+
                //     "---------------------------------------------"
                // );
            }
        });
    }
}