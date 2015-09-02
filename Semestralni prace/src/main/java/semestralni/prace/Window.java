package semestralni.prace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Zdenek
 */
public class Window extends JFrame {

    boolean clicked = false;
    int x;
    int y;
    Game g;
    ButtonField bf;
    Player p1;
    Player p2;
    private PlayerEnum whoseTurn = PlayerEnum.FIRST;
    private int gameStatus = 0;
    Menu m;
    private boolean filledWithAL = false;

    public Window(int a, String firstPlayerName, String secondPlayerName) {
        super("Piškvorky");
        this.setSize(1000, 688);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setBackground(Color.pink);
        g = new Game(a);
        bf = new ButtonField(this);
        this.add(bf);
        p1 = new PCPlayer(firstPlayerName, 1, this);
        p2 = new PCPlayer(secondPlayerName, 2, this);
        m = new Menu(this, 660, 0, 340, 688, firstPlayerName, secondPlayerName);
        this.add(m);
        this.setBackground(Color.pink);
    }

    public void setFilledWithAL(boolean filledWithAL) {
        this.filledWithAL = filledWithAL;
    }

    public boolean isFilledWithAL() {
        return filledWithAL;
    }

    public PlayerEnum getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(PlayerEnum whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void switchPlayer() {
        if (whoseTurn == PlayerEnum.FIRST) {
            setWhoseTurn(PlayerEnum.SECOND);
            m.name1.setForeground(Color.black);
            m.name2.setForeground(Color.blue);
        } else {
            setWhoseTurn(PlayerEnum.FIRST);
            m.name1.setForeground(Color.blue);
            m.name2.setForeground(Color.black);
        }
    }

    public void win() {
        Button[][] helper = bf.getField();
        char[][] helper2 = g.getField();
        Image myImage = null;
        ImageIcon myIcon = null;
        int helper3;

        if (gameStatus == 3) {
            m.doContinue.setVisible(true);
            m.saveGame.setBackground(Color.red);
            m.loadGame.setBackground(Color.red);
            m.restartGame.setBackground(Color.red);
            return;
        }
        if (gameStatus == 1) {
            URL localUrl = Button.class.getResource("/O2.jpg");
            try {
                myImage = ImageIO.read(localUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            helper3 = (int) Double.parseDouble(m.score1.getText());
            helper3++;
            m.score1.setText(Integer.toString(helper3));
        } else {
            URL localUrl = Button.class.getResource("/X2.jpg");
            try {
                myImage = ImageIO.read(localUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            helper3 = (int) Double.parseDouble(m.score2.getText());
            helper3++;
            m.score2.setText(Integer.toString(helper3));

        }
        if (myImage != null) {
            myIcon = new ImageIcon(myImage);
        }

        for (int k = 0; k < g.getA(); k++) {
            for (int l = 0; l < g.getA(); l++) {
                helper[k][l].removeActionListener(helper[k][l]);
            }
        }
        bf.setField(helper);
        setFilledWithAL(false);

        for (int k = 0; k < g.getA(); k++) {
            for (int l = 0; l < g.getA(); l++) {
                if (helper2[k][l] == 'w') {
                    helper[k][l].setIcon(myIcon);
                }
            }
        }
        m.saveGame.setBackground(Color.red);
        m.loadGame.setBackground(Color.red);
        m.restartGame.setBackground(Color.red);
        m.doContinue.setVisible(true);
    }

    public void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (gameStatus == 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (getGameStatus() != 0) {
                        break;
                    }
                    if (whoseTurn == PlayerEnum.FIRST) {
                        p1.makeMove();
                    } else {
                        p2.makeMove();
                    }

                }
                win();
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
}
