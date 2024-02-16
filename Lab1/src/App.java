import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

public class App {
    static final int interval = 1;

    static JLabel label;
    static int minutesElapsed = 0;
    static Timer timer;
    static boolean isRunning = false;

    public static void main(String[] args) {
        createWindow();
    }

    private static void initializeTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                minutesElapsed++;

                if (minutesElapsed >= 5) {
                    minutesElapsed = 0;
                }
                updateLabelText();
            }
        }, 0, interval * 1000);
    }

    private static void updateLabelText() {
        String mins = String.valueOf(minutesElapsed);
        String hours = "00:";
        String minutes = minutesElapsed < 10 ? "0" + mins : mins;
        String time = hours + minutes;
        label.setText(time);
    }

    private static void createWindow() {
        JFrame frame = new JFrame();
        JButton startButton = new JButton("Start");
        JButton startAtButton = new JButton("Start At 30 minutes"); // New button added
        label = new JLabel();

        startButton.setBounds(10, 10, 80, 35);
        startAtButton.setBounds(100, 10, 180, 35); // New button position
        label.setBounds(10, 60, 190, 35);
        label.setText("");
        frame.add(startButton);
        frame.add(startAtButton); // Add the new button to the frame
        frame.add(label);
        frame.setLayout(null);
        frame.setSize(300, 150); // Increase the frame size to accommodate the new button
        frame.setVisible(true);

        setupActionListener(startButton);
        setupStartAtActionListener(startAtButton); // Add action listener for the new button
    }

    private static void setupActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (timer == null) {
                    minutesElapsed = -1;
                    updateLabelText();
                    button.setText("Stop");
                    initializeTimer();
                } else {
                    button.setText("Start");
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }
            }
        });
    }

    private static void setupStartAtActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Schedule the new timer to start after 30 minutes
                Timer startAtTimer = new Timer();
                startAtTimer.schedule(new TimerTask() {
                    public void run() {
                        // This code will be executed after 30 minutes
                        JOptionPane.showMessageDialog(null, "30 minutes have passed!");
                    }
                }, 30 * 60 * 1000); // 30 minutes in milliseconds
            }
        });
    }
}