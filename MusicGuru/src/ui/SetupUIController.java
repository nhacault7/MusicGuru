package ui;

import collections.LinkedList;
import java.awt.Color;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
    private JButton trebleWholeNote;
    private JButton trebleHalfNote;
    private JButton trebleQuarterNote;
    private JButton trebleEighthNote;
    private JButton trebleSixteenthNote;
    private JButton trebleThirtysecondNote;
    private JButton trebleWholeRest;
    private JButton trebleHalfRest;
    private JButton trebleQuarterRest;
    private JButton trebleEighthRest;
    private JButton trebleSixteenthRest;
    private JButton trebleThirtysecondRest;
    private JButton scoreSetupNext;
    private JButton trebletrebleIntervalConfirm;
    JComboBox trebleNote;
    JComboBox trebleOctave;
    private JCheckBox trebleTriplet;
    private JCheckBox trebleInterval;
    private JButton trebleClefNext;
    private List soundInfoList;
    private SetupUI userInterface;
    
    private int messureCurrentNumber;
    private int messureMaxNumber;
    private int messureBeats;
    private int tempo;
    private int listSpot;
    private int sheetNum;
    private double beatCompare;
    private int trebleIntervalCount;

    private final String messureMessage = "Messure: ";
    private final String spacer = "---------";
            
    private LinkedList<File> sheetMusic;
    private LinkedList<String> trebleClef;
    private LinkedList<String> trebleNoteValues;
    private LinkedList<String> baseClef;
    private LinkedList<String> baseNoteValues;
    
    SetupUIController(LinkedList displaySheets, JTabbedPane setupTabs, 
            JLabel sheetSetupLabel, JTextField messureNumInput, 
            JTextField messureBeatInput, JTextField tempoInput, 
            JButton scoreSetupNext, JButton trebletrebleIntervalConfirm, 
            JButton trebleWholeNote, JButton trebleHalfNote, JButton trebleQuarterNote, 
            JButton eigthtNote, JButton trebleSixteenthNote, JButton thirtysecondNote, 
            JButton trebleWholeRest, JButton trebleHalfRest, JButton trebleQuarterRest, 
            JButton eigthtRest, JButton trebleSixteenthRest, JButton thirtysecondRest, 
            JComboBox trebleNote, JComboBox trebleOctave, JCheckBox trebleTriplet, 
            JCheckBox trebleInterval, JButton trebleClefNext, List soundInfoList, 
            SetupUI userInterface) {
        
        this.setupTabs = setupTabs;
        this.sheetSetupLabel = sheetSetupLabel;
        this.messureNumInput = messureNumInput;
        this.messureBeatInput = messureBeatInput;
        this.tempoInput = tempoInput;
        this.trebleWholeNote = trebleWholeNote;
        this.trebleHalfNote = trebleHalfNote;
        this.trebleQuarterNote = trebleQuarterNote;
        this.trebleEighthNote = eigthtNote;
        this.trebleSixteenthNote = trebleSixteenthNote;
        this.trebleThirtysecondNote = thirtysecondNote;
        this.trebleWholeRest = trebleWholeRest;
        this.trebleHalfRest = trebleHalfRest;
        this.trebleQuarterRest = trebleQuarterRest;
        this.trebleEighthRest = eigthtRest;
        this.trebleSixteenthRest = trebleSixteenthRest;
        this.trebleThirtysecondRest = thirtysecondRest;
        this.scoreSetupNext = scoreSetupNext;
        this.trebletrebleIntervalConfirm = trebletrebleIntervalConfirm;
        this.trebleNote = trebleNote;
        this.trebleOctave = trebleOctave;
        this.trebleTriplet = trebleTriplet;
        this.trebleInterval = trebleInterval;
        this.trebleClefNext = trebleClefNext;
        this.soundInfoList = soundInfoList;
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
        trebletrebleIntervalConfirm.setEnabled(false);
        trebleClefNext.setEnabled(false);
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
        if (!messureNumInput.getText().equals("") && 
                !messureBeatInput.getText().equals("") && 
                !tempoInput.getText().equals("")) {
            scoreSetupNext.setEnabled(true);
        } else {
            scoreSetupNext.setEnabled(false);
        }
        
    }

    void nextTab() {
        messureCurrentNumber = 1;
        messureMaxNumber = Integer.parseInt(messureNumInput.getText());
        messureBeats = Integer.parseInt(messureBeatInput.getText());
        tempo = Integer.parseInt(tempoInput.getText());
        soundInfoList.removeAll();
        soundInfoList.add(messureMessage + messureCurrentNumber);
        int currentIndex = setupTabs.getSelectedIndex();
        setupTabs.setSelectedIndex(++currentIndex);
    }

    void addTrebleNote(String type, double value) {
        String soundValue = type;
        String note = (String) trebleNote.getSelectedItem();
        String octave = (String) trebleOctave.getSelectedItem();
        
        if (soundValue != null && !soundValue.equals("")) {
            
            if (trebleClef == null || trebleClef.isEmpty()) {
                trebleClef = new LinkedList<>();
            }
            
            if (trebleNoteValues == null || trebleNoteValues.isEmpty()) {
                trebleNoteValues = new LinkedList<>();
            }
            
            if (trebleTriplet.isSelected() == true && trebleInterval.isSelected() == true) {
                ++trebleIntervalCount;
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                if (trebleIntervalCount == 1) {
                    beatCompare += value * 2;
                } 
            }
            else if (trebleTriplet.isSelected() == true) {
                trebleClef.add(soundValue + " " + soundValue + " " + soundValue);
                trebleNoteValues.add(note + " " + octave + " " + note + " " + octave + " " + note + " " + octave);
                soundInfoList.add(soundValue + " " + note + octave + " " + "trebleTriplet");
                beatCompare += value * 2;
            } 
            else if (trebleInterval.isSelected() == true) {
                ++trebleIntervalCount;
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                if (trebleIntervalCount == 1) beatCompare += value; 
            } else {
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                soundInfoList.add(soundValue + " " + note + octave);
                beatCompare += value;
            }
            if(trebleIntervalCount == 0) currentMessureCheck();
        }
    }
    
    void addRest(String type, double value) {
        String soundValue = type;
        
        if (soundValue != null && !soundValue.equals("")) {
            
            if (trebleClef == null || trebleClef.isEmpty()) {
                trebleClef = new LinkedList<>();
            }
            
            trebleClef.add(soundValue);
            soundInfoList.add(soundValue);
            beatCompare += value;
            currentMessureCheck();
        }
    }

    void currentMessureCheck() {
        
        if (messureBeats != 0 && beatCompare == messureBeats) {
            
            if(messureCurrentNumber < messureMaxNumber) {
                soundInfoList.add(spacer);
                soundInfoList.add(messureMessage + ++messureCurrentNumber);
            }
            else {
            trebleClefNext.setEnabled(true);
            trebleWholeNote.setEnabled(false);
            trebleHalfNote.setEnabled(false);
            trebleQuarterNote.setEnabled(false);
            trebleEighthNote.setEnabled(false);
            trebleSixteenthNote.setEnabled(false);
            trebleThirtysecondNote.setEnabled(false);
            trebleWholeRest.setEnabled(false);
            trebleHalfRest.setEnabled(false);
            trebleQuarterRest.setEnabled(false);
            trebleEighthRest.setEnabled(false);
            trebleSixteenthRest.setEnabled(false);
            trebleThirtysecondRest.setEnabled(false);
            trebleTriplet.setEnabled(false);
            trebleInterval.setEnabled(false);
            }
            
            beatCompare = 0;
        } 
    }

    void trebleIntervalMerge() {
        int length = trebleClef.size();
        int merge = length - trebleIntervalCount;
        String trebleIntervalValue = trebleClef.get(merge);
        String trebleIntervalNote = trebleNoteValues.get(merge);
        String trebleIntervalDisplay = trebleClef.get(merge) + " " + trebleNoteValues.get(merge).replace(" ", "");
        
        for (int i = merge + 1; i <= trebleClef.size() - 1; i++) {
            String grabValue = trebleClef.get(i);
            String grabNote = trebleNoteValues.get(i);
            trebleIntervalValue += " " + grabValue;
            trebleIntervalNote += " " + grabNote;
            trebleIntervalDisplay += "," + " " + trebleClef.get(i) + " " + trebleNoteValues.get(i).replace(" ", "");
            trebleClef.remove(i);
            trebleNoteValues.remove(i);
            --i;
        }
        trebleIntervalCount = 0;
        trebleClef.removeBack();
        trebleNoteValues.removeBack();
        
        if (trebleTriplet.isSelected() == true) {
            trebleClef.add(trebleIntervalValue + " " + trebleIntervalValue + " " + trebleIntervalValue);
            trebleNoteValues.add(trebleIntervalNote + " " + trebleIntervalNote + " " + trebleIntervalNote);
            soundInfoList.add(trebleIntervalDisplay + " " + "trebleTriplet");
            currentMessureCheck();
        }
        if (trebleTriplet.isSelected() == false) {
            trebleClef.add(trebleIntervalValue);
            trebleNoteValues.add(trebleIntervalNote);
            soundInfoList.add(trebleIntervalDisplay);
            currentMessureCheck();
        }
    }

    void buttonUpdate() {
        if (trebleInterval.isSelected() == true) {
            trebletrebleIntervalConfirm.setEnabled(true);
        }
        else {
            trebletrebleIntervalConfirm.setEnabled(false);
        }
    }

}
