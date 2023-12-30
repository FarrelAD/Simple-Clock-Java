import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class coro {
    private static JLabel dateLabel = new JLabel();
    private static JFrame frame = new JFrame("Clock Program");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);

            displayDate();

            frame.setVisible(true);
        });
    }

    public static void displayDate() {
        dateLabel.setForeground(Color.white);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        frame.add(dateLabel, gbcDate);

        updateDateAndSetTimer();
    }

    public static void updateDateAndSetTimer() {
        getActualDate();

        // Calculate the initial delay until midnight
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
        Duration initialDelay = Duration.between(now, midnight);

        // Set the timer with initial delay and repeat every 24 hours
        Timer timer = new Timer((int) initialDelay.toMillis(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getActualDate();
                ((Timer) e.getSource()).setInitialDelay(24 * 60 * 60 * 1000); // Set delay for 24 hours
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
    }
}
