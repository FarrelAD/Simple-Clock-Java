import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class coco {

    static JFrame frame = new JFrame();
    static JLabel timeLabel = new JLabel();
    static JLabel dateLabel = new JLabel();
    public static void main(String[] args) {
        // displayDate();
        basicDisplayDate();
    }

    private static void basicDisplayDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currentDate.format(dateFormatter);

        dateLabel.setText(formattedDate);

        // Monitoring the output
        System.out.println(
            "Date: " + formattedDate+"\n"+
            "---------------------------------------------"
        );
    }

    public static void displayDate() {
        dateLabel.setForeground(Color.white); // Make the text color white

        // Using GridBagLayout to center timeLabel
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        frame.add(dateLabel, gbcDate);

        // Change the actual time every 24 hours
        Timer timer = new Timer(86400000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM dd, yyyy");
                String formattedDate = currentDate.format(dateFormatter);

                dateLabel.setText(formattedDate);

                // Monitoring the output
                System.out.println(
                    "Date: " + formattedDate+"\n"+
                    "---------------------------------------------"
                );
            }
        });
        timer.start();
    }
}
