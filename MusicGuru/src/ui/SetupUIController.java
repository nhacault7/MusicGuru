package ui;

import collections.LinkedList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tools.Images;

/**
 *
 * @author Rickolas-PC
 */
public class SetupUIController {
    
    private JTabbedPane setupTabs;
    private JLabel sheetSetupLabel;
    private JTextField messureNumInput;
    private JTextField messureBeatInput;
    private JTextField tempoInput;
    private JButton scoreSetupNext;
    private SetupUI userInterface;
    
    int listSpot;
    int sheetNum;
    private LinkedList<File> sheetMusic;
    
    SetupUIController(LinkedList displaySheets, JTabbedPane setupTabs, 
            JLabel sheetSetupLabel, JTextField messureNumInput, 
            JTextField messureBeatInput, JTextField tempoInput, 
            JButton scoreSetupNext, SetupUI userInterface) {
        
        this.setupTabs = setupTabs;
        this.sheetSetupLabel = sheetSetupLabel;
        this.messureNumInput = messureNumInput;
        this.messureBeatInput = messureBeatInput;
        this.tempoInput = tempoInput;
        this.scoreSetupNext = scoreSetupNext;
        this.userInterface = userInterface;
        this.sheetMusic = displaySheets;
        this.sheetNum = sheetMusic.size();
        
        userInterface.setResizable(false);
        userInterface.setLocationRelativeTo(null);
        userInterface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userInterface.getRootPane().setBorder(
                BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        setupTabs.setEnabled(false);
        sheetSetupLabel.setText(null);
        setSheetLabel();
        scoreSetupNext.setEnabled(false);
        userInterface.setVisible(true);
    }

    void setSheetLabel() {
        if (sheetMusic != null) {  
            listSpot = 0;
            String path = sheetMusic.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetSetupLabel.setText(null);
            sheetSetupLabel.setIcon(icon);
            Images.resizeToContainer(sheetSetupLabel);                                   
        }
    }

    void integerTest(KeyEvent evt) {
        char input = evt.getKeyChar();
        if (!(Character.isDigit(input) || 
                input == KeyEvent.VK_BACK_SPACE ||input == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }

    void fieldCheck() {
        if (messureNumInput.getText().length() != 0 && 
                messureBeatInput.getText().length() != 0 &&
                tempoInput.getText().length() != 0) {
            scoreSetupNext.setEnabled(true);
        }
    }

    void setupNext() {
        int currentIndex = setupTabs.getSelectedIndex();
        setupTabs.setSelectedIndex(++currentIndex);
    }
    
}
