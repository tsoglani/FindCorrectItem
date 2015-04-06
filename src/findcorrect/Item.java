/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findcorrect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class Item extends JPanel {

    private String imageName;
    private Image image;
    private Point position = new Point();
    private Fr frame;
    private Random random = new Random();
    public static int score = 0;

    public Item(Fr frame, String imageName) {
        this.imageName = imageName;
        this.frame = frame;

        image = getToolkit().createImage("images/"+imageName);
        ImageIcon imIcon = new ImageIcon(image);
        int imageWidth = imIcon.getIconWidth();
        int imageHeight = imIcon.getIconHeight();
        setSize(imageWidth, imageHeight);
        //setSize(300,300);
        position.x = random.nextInt(frame.getWidth() - imageWidth - 100);

        position.y = random.nextInt(frame.getHeight() - imageHeight - 100);


        setLocation(position);
        this.addMouseListener(mouseListener);
        this.setBackground(Color.yellow);
    }
    MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (frame.getItemToFind().contains(Item.this)) {
                frame.getItemToFind().remove(Item.this);
                frame.getScreen().remove(Item.this);
                System.out.println("you found " + Item.this.toString());
                score += 50;
                frame.getScreen().repaint();
                for (JTextArea txt : frame.getTextsPanel().getTexts()) {
                    if (txt.getText().equalsIgnoreCase(Item.this.toString())) {
                       // txt.setText("empty");
                        txt.setBackground(Color.green);
                        break;
                    }
                }

                if (frame.getScreen().getComponentCount() == 0 || frame.getItemToFind().isEmpty()) {
                    frame.nextLevel();
                }

            } else {
                getToolkit().beep();
                score -= 10;
                if (score <= 0) {
                    score = 0;
                }
                frame.getScreen().repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, null, this);
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return imageName.substring(0, imageName.length() - 4);
    }
}
