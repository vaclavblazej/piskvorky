/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Zdenek
 */
public class Button extends JButton implements ActionListener {

    private int i;
    private int j;
    Window w;

    public Button(int i, int j, String text, Window w) {
        super();
        this.i = i;
        this.j = j;
        this.w = w;
        this.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        w.x = i;
        w.y = j;
        w.clicked = true;
    }

    public void changeField() {
        Button[][] helper2;
        helper2 = w.bf.getField();
        Image myImage = null;
        ImageIcon myIcon;

        if (w.getWhoseTurn() == 1) {
            URL localUrl = Button.class.getResource("/O.jpg");
            try {
                myImage = ImageIO.read(localUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            URL localUrl = Button.class.getResource("/X.jpg");
            try {
                myImage = ImageIO.read(localUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        myIcon = new ImageIcon(myImage);
        this.setIcon(myIcon);
        w.setGameStatus(w.g.winnerCheck(w.getWhoseTurn()));

        for (int k = 0; k < w.g.getA(); k++) {
            for (int l = 0; l < w.g.getA(); l++) {
                helper2[k][l].removeActionListener(helper2[k][l]);
            }
        }
        w.bf.setField(helper2);
        w.switchPlayer();
        w.setFilledWithAL(false);
    }
}
