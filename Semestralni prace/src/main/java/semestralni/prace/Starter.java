package semestralni.prace;

import javax.swing.*;
import java.awt.*;

/**
 * @author Zdenek
 */
public class Starter extends JFrame {

    JTextField firstPlayerName;
    JTextField secondPlayerName;
    JButton startButton;
    Window window;

    public Starter() {
        super("Piškvorky");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 200));
        this.getContentPane().setBackground(Color.PINK);

        firstPlayerName = new JTextField();
        firstPlayerName.setText("Player 1");
        firstPlayerName.setBounds(35, 20, 100, 50);
        this.add(firstPlayerName);

        secondPlayerName = new JTextField();
        secondPlayerName.setText("Player 2");
        secondPlayerName.setBounds(155, 20, 100, 50);
        this.add(secondPlayerName);

        startButton = new JButton();
        startButton.setText("START");
        startButton.setBounds(90, 90, 100, 50);
        startButton.addActionListener(ae -> start());
        this.add(startButton);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void start() {
        window = new Window(30, firstPlayerName.getText(), secondPlayerName.getText());
        window.setVisible(true);
        this.dispose();
        window.run();
    }
}
