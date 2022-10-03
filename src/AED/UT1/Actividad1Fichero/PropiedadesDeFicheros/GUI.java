package AED.UT1.Actividad1Fichero.PropiedadesDeFicheros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JButton button = new JButton("Choose a File!");

    GUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConfigPanel();

        this.add(panel);
        this.setVisible(true);
        this.pack();
    }

    private void ConfigPanel() {
        panel.setPreferredSize(new Dimension(200,120));

        button.setBounds(0,0,80,50);
        button.setFocusable(false);
        button.addActionListener(this);

        panel.add(button,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(Objects.requireNonNull(this.getClass().getResource("./")).getFile()));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                File[] filesList = file.listFiles();

                for (File f: filesList) {
                    System.out.print(f.getName()+" || ");
                    System.out.print(f.getPath()+" || ");
                    System.out.print(f.getAbsolutePath()+" || ");
                    System.out.print(f.canRead()+" || ");
                    System.out.print(f.canWrite()+" || ");
                    System.out.print(f.length()+" || ");
                    System.out.print(f.isDirectory()+" || ");
                    System.out.print(f.isFile()+"\n");
                }
            }
        }
    }
}
