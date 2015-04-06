/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findcorrect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class Screen extends JPanel {

    private Image bachgroundImage;

    public Screen() {

        //   bachgroundImage = getToolkit().createImage("background/office.png");
        setRandomBackground();

    }

    public void setRandomBackground() {
        File file = new File("background");
        Random random = new Random();
        int backgroundNumber = random.nextInt(file.list().length);
        bachgroundImage = getToolkit().createImage("background/" + file.list()[backgroundNumber]);
        // or // bachgroundImage = getToolkit().createImage(file.listFiles()[backgroundNumber].toString());
    }

    public Image getBachgroundImage() {
        return bachgroundImage;
    }

    public void setBachgroundImage(Image bachgroundImage) {
        this.bachgroundImage = bachgroundImage;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //   g.setColor(Color.yellow);
        //g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bachgroundImage, null, this);
        g2d.drawString("score : " + Integer.toString(Item.score), 100, 10);
    }
}
