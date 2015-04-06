package findcorrect;

import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Timer extends JTextArea implements Runnable {

    private Screen screen;
    public int remainingTime = 15;
    private Fr frame;

    public Timer(Fr frame) {
        this.frame = frame;
        setSize(55, 20);
        setText("timer : "+Integer.toString(remainingTime));
        setEditable(false);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                remainingTime--;
                setText("timer : "+Integer.toString(remainingTime));
                try {
                    screen = (Screen) this.getParent();
                    screen.repaint();

                    if (remainingTime <= 0) {


                        frame.gameOver();
                        frame.repaint();
                        frame.revalidate();
                        break;
                    }
                } catch (java.lang.NullPointerException nullPointer) {
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
