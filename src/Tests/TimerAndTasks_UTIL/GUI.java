package Tests.TimerAndTasks_UTIL;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame {

    private final JPanel panel;
    private JLabel helloMsg;
    private Timer timer;
    private TimerTask timerTask;

    public GUI() {
        helloMsg = new JLabel();
        panel = new JPanel(new BorderLayout());

        ExtraStyles();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(panel);

        this.pack();
        this.setVisible(true);

        timerMethod();
    }

    private void timerMethod() {
        timer = new Timer();

        timerTask = new TimerTask() {

            char[] stringToDisplay = "Jimmy es gay :3".toCharArray();
            int i = 0;

            @Override
            public void run() {
                if(i<stringToDisplay.length) {
                    helloMsg.setText(helloMsg.getText()+stringToDisplay[i]);
                    i++;
                }
                else {
                    timer.cancel();
                }
            }
        };

        timer.schedule(timerTask,5000,500);
    }

    private void ExtraStyles() {
        helloMsg.setVerticalAlignment(JLabel.CENTER);
        helloMsg.setHorizontalAlignment(JLabel.CENTER);
        helloMsg.setFont(new Font("Candara Light",Font.BOLD,70));
        helloMsg.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
        helloMsg.setForeground(Color.GREEN);

        panel.setPreferredSize(new Dimension(600,300));
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        panel.add(helloMsg,BorderLayout.CENTER);
    }
}
