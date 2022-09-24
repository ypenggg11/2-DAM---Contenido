package Tests.Threads_UTIL;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    private final JPanel panel;
    private JLabel helloMsg;
    private final Thread thread;

    public GUI() {
        thread = initializeThread();

        helloMsg = new JLabel();
        panel = new JPanel(new BorderLayout());

        ExtraStyles();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(panel);

        this.pack();
        this.setVisible(true);

        thread.start();
    }

    @NotNull
    private Thread initializeThread() {
        final Thread thread;

        //Uses an anonymous inner class to override the run() method
        thread  = new Thread(() -> {

            char[] stringToDisplay = "Jimmy es gay :3".toCharArray();

            for (char c: stringToDisplay) {
                helloMsg.setText(helloMsg.getText()+c);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return thread;
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
