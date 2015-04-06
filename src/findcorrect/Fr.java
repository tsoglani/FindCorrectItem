/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findcorrect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class Fr extends JFrame {

    private ArrayList<Item> itemToFind = new ArrayList<Item>();// items i want to find
    private ArrayList<Item> allUslessItems = new ArrayList<Item>();// all items i don't care to find
    private Screen screen;
    private TextsPanel textsPanel;
    private ArrayList<String> allImageNames = new ArrayList<String>();// ola ta name twn image mou
    private Timer timer;
    private JPanel gameOverPanel;

    public Fr() {
        this.setResizable(false);
        screen = new Screen();
        textsPanel = new TextsPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(textsPanel, BorderLayout.SOUTH);
        textsPanel.manualValidate();
        ImageIcon imageIcon = new ImageIcon(screen.getBachgroundImage());
        setSize(imageIcon.getIconWidth() + 10, imageIcon.getIconHeight() + 90);

        setLocation(300, 300);
        add(screen);
        timer = new Timer(this);
        new Thread(timer).start();
        screen.setLayout(null);
        //screen.setSize(getSize());
        initImageNames();
        createImages();
        addImages();

        setVisible(true);
    }

    public void gameOver() {
        screen.removeAll();
        remove(textsPanel);
        Item.score = 0;
        remove(screen);
        gameOverPanel = new JPanel();
        JButton gameOverButton = new JButton("new game");
        gameOverButton.addActionListener(gameOverListener);
        gameOverPanel.add(gameOverButton);
        add(gameOverPanel);
    }
    ActionListener gameOverListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            gameOverPanel.removeAll();
            remove(gameOverPanel);
            newGame();
        }
    };

    private void newGame() {

        itemToFind.removeAll(itemToFind);
        allUslessItems.removeAll(allUslessItems);
        textsPanel = new TextsPanel();
        screen = new Screen();
        add(screen);
        timer = new Timer(this);
        new Thread(timer).start();
        textsPanel.ELEMENTS_TO_FIND = 1;
        add(textsPanel, BorderLayout.SOUTH);
        textsPanel.manualValidate();
        screen.setLayout(null);
        initImageNames();
        createImages();
        addImages();


    }

    private void initImageNames() {
        allImageNames.removeAll(allImageNames);
        allImageNames.add("scissor.png");
        allImageNames.add("cutter.png");
        allImageNames.add("Stapler.png");
        allImageNames.add("Desktop.png");
        allImageNames.add("TV.png");
        allImageNames.add("telephone.png");
        allImageNames.add("facebook.png");
        allImageNames.add("fox.png");
        allImageNames.add("trash.png");
        allImageNames.add("airoplane.png");
        allImageNames.add("book.png");
        allImageNames.add("Rubik cube.png");
        allImageNames.add("no smoking.png");
        allImageNames.add("tape.png");
        allImageNames.add("Notepad.png");
        allImageNames.add("gift.png");
        allImageNames.add("guitar.png");
        allImageNames.add("ball.png");
        allImageNames.add("banana.png");
        allImageNames.add("gameboy.png");
        allImageNames.add("magazins.png");
        allImageNames.add("mouse.png");
        allImageNames.add("gun.png");
        //allImageNames.add("Notepad.png");
        //allImageNames.add("Notepad.png");

    }

    private void createImages() {

        for (int i = 0; i < 2 * TextsPanel.ELEMENTS_TO_FIND; i++) {// create all image i don't need to find
            int randomNumber = (int) (Math.random() * allImageNames.size());
            Item item = new Item(this, allImageNames.get(randomNumber));
            allImageNames.remove(allImageNames.get(randomNumber));
            // i alliws 
            //allImageNames.remove(randomNumber);
            allUslessItems.add(item);
        }



        for (int i = 0; i < TextsPanel.ELEMENTS_TO_FIND; i++) {// create all image i need to find
            int randomNumber = (int) (Math.random() * allImageNames.size());
            Item item = new Item(this, allImageNames.get(randomNumber));
            allImageNames.remove(allImageNames.get(randomNumber));
            // i alliws 
            //allImageNames.remove(randomNumber);
            itemToFind.add(item);
        }
        //   Item item = new Item(this, "duck.png");
        //Item item2 = new Item(this, "duck.png");Item item3 = new Item(this, "duck.png");

        //itemToFind.add(item3);itemToFind.add(item2);

    }

    private void addImages() {
        // for (Item item : itemToFind) {



        for (int i = 0; i < itemToFind.size(); i++) { // add all image i need to find
            screen.add(itemToFind.get(i));
            textsPanel.getTexts()[i].setText(itemToFind.get(i).toString());
            System.out.println(itemToFind.get(i).toString());
        }
        screen.add(timer);
        for (int i = 0; i < allUslessItems.size(); i++) { // add all image i do not need to find
            screen.add(allUslessItems.get(i));

        }
        //  screen.add(item);
        // }

    }

    public void nextLevel() {
        System.out.println("nextLevel ?");
        Item.score += 100;
        timer.remainingTime = 15;
        if (TextsPanel.ELEMENTS_TO_FIND < 6) {
            TextsPanel.ELEMENTS_TO_FIND++;
        }
        allUslessItems.removeAll(allUslessItems);
        screen.removeAll();
        initImageNames();
        // System.out.println("allImageNames.size()=" + allImageNames.size());
        textsPanel.manualValidate();
        createImages();
        addImages();
        screen.setRandomBackground();
        ImageIcon imageIcon = new ImageIcon(screen.getBachgroundImage());
        setSize(imageIcon.getIconWidth() + 10, imageIcon.getIconHeight() + 90);
        repaint();
        revalidate();
    }

    public static int GetScreenWorkingWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public static int GetScreenWorkingHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }

    public ArrayList<Item> getItemToFind() {
        return itemToFind;
    }

    public Screen getScreen() {
        return screen;
    }

    public TextsPanel getTextsPanel() {
        return textsPanel;
    }
}
