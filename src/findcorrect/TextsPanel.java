/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findcorrect;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class TextsPanel extends JPanel{
    public static int ELEMENTS_TO_FIND=1;
    private JTextArea[] texts ;
    public TextsPanel(){
        setLayout(new GridLayout(3,2));
        
      
    }
    
    public void manualValidate(){
    texts = new JTextArea[ELEMENTS_TO_FIND];
    this.removeAll();
    for (int i = 0; i < texts.length; i++) {
            texts[i]= new JTextArea();
            add(texts[i]);
            texts[i].setEditable(false);
            texts[i].setBackground(Color.WHITE);
            texts[i].setText("empty");
        }
    repaint();
    } 
    

    public JTextArea[] getTexts() {
        return texts;
    }
    
    
}
