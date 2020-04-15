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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import tools.Images;
        
/**
 *
 * @author Rickolas-PC
 */
public class SetupUIController {
    
    private JTabbedPane setupTabs;
    private JPanel setupPanel;
    private JPanel treblePanel; 
    private JPanel bassPanel;
    private JLabel sheetSetupLabel;
    private JTextField messureNumInput;
    private JTextField messureBeatInput;
    private JTextField tempoInput;
    private JButton scoreSetupNext;
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
    private JButton trebleIntervalConfirm;
    private JComboBox trebleNote;
    private JComboBox trebleOctave;
    private JCheckBox trebleTriplet;
    private JCheckBox trebleInterval;
    private JButton trebleClefNext;
    private JButton bassWholeNote;
    private JButton bassHalfNote; 
    private JButton bassQuarterNote; 
    private JButton bassEighthNote; 
    private JButton bassSixteenthNote;
    private JButton bassThirtysecondNote; 
    private JButton bassWholeRest; 
    private JButton bassHalfRest; 
    private JButton bassQuarterRest; 
    private JButton bassEighthRest; 
    private JButton bassSixteenthRest; 
    private JButton bassThirtysecondRest;
    private JButton bassIntervalConfirm;
    private JComboBox bassNote; 
    private JComboBox bassOctave; 
    private JCheckBox bassTriplet; 
    private JCheckBox bassInterval; 
    private JButton bassClefNext;
    private List soundInfoList;
    private List soundTrebleInfoList;
    private List soundBassInfoList;
    private SetupUI userInterface;

    private int messureCurrentNumber;
    private int messureMaxNumber;
    private int messureBeats;
    private int tempo;
    private int listSpot;
    private int sheetNum;
    private int currentSheet;
    private int intervalCount;
    private double beatCompare;

    private final String treble = "Treble, ";
    private final String bass = "Bass, ";
    private final String messureMessage = "Messure: ";
    private final String pageMessage = "Page: ";
    private final String spacer = "---------";    
    private final String fancySpacer = "|----+---+---+----|";
    private final String triplet = "Triplet";
    private final String interval = "Interval";
    
    private Border setupBorder;
    private Border trebleBorder;
    private Border bassBorder;
    private LinkedList<String> trebleClef;
    private LinkedList<String> trebleNoteValues;
    private LinkedList<String> bassClef;
    private LinkedList<String> bassNoteValues;
    
    private final LinkedList<File> sheetMusic;
    
    SetupUIController(LinkedList displaySheets, JTabbedPane setupTabs, 
            JPanel setupPanel, JPanel treblePanel, JPanel bassPanel,
            JLabel sheetSetupLabel, JTextField messureNumInput, 
            JTextField messureBeatInput, JTextField tempoInput, 
            JButton scoreSetupNext, JButton trebleIntervalConfirm, 
            JButton trebleWholeNote, JButton trebleHalfNote, 
            JButton trebleQuarterNote, JButton eigthtNote, 
            JButton trebleSixteenthNote, JButton thirtysecondNote, 
            JButton trebleWholeRest, JButton trebleHalfRest, 
            JButton trebleQuarterRest, JButton eigthtRest, 
            JButton trebleSixteenthRest, JButton thirtysecondRest, 
            JComboBox trebleNote, JComboBox trebleOctave, 
            JCheckBox trebleTriplet, JCheckBox trebleInterval, 
            JButton trebleClefNext, JButton bassIntervalConfirm, 
            JButton bassWholeNote, JButton bassHalfNote, 
            JButton bassQuarterNote, JButton bassEighthNote, 
            JButton bassSixteenthNote, JButton bassThirtysecondNote, 
            JButton bassWholeRest, JButton bassHalfRest, 
            JButton bassQuarterRest, JButton bassEighthRest, 
            JButton bassSixteenthRest, JButton bassThirtysecondRest, 
            JComboBox bassNote, JComboBox bassOctave, JCheckBox bassTriplet, 
            JCheckBox bassInterval, JButton bassClefNext, List soundInfoList, 
            List soundTrebleInfoList, List soundBassInfoList, 
            SetupUI userInterface) {
        
        this.setupTabs = setupTabs;
        this.setupPanel = setupPanel;
        this.treblePanel = treblePanel; 
        this.bassPanel = bassPanel;
        this.sheetSetupLabel = sheetSetupLabel;
        this.messureNumInput = messureNumInput;
        this.messureBeatInput = messureBeatInput;
        this.tempoInput = tempoInput;
        this.scoreSetupNext = scoreSetupNext;
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
        this.trebleIntervalConfirm = trebleIntervalConfirm;
        this.trebleNote = trebleNote;
        this.trebleOctave = trebleOctave;
        this.trebleTriplet = trebleTriplet;
        this.trebleInterval = trebleInterval;
        this.trebleClefNext = trebleClefNext;
        this.bassWholeNote = bassWholeNote;
        this.bassHalfNote = bassHalfNote;
        this.bassQuarterNote = bassQuarterNote; 
        this.bassEighthNote = bassEighthNote; 
        this.bassSixteenthNote = bassSixteenthNote;
        this.bassThirtysecondNote = bassThirtysecondNote; 
        this.bassWholeRest = bassWholeRest; 
        this.bassHalfRest = bassHalfRest; 
        this.bassQuarterRest = bassQuarterRest; 
        this.bassEighthRest = bassEighthRest; 
        this.bassSixteenthRest = bassSixteenthRest; 
        this.bassThirtysecondRest = bassThirtysecondRest;
        this.bassIntervalConfirm = bassIntervalConfirm;
        this.bassNote = bassNote; 
        this.bassOctave = bassOctave; 
        this.bassTriplet = bassTriplet; 
        this.bassInterval = bassInterval; 
        this.bassClefNext = bassClefNext;
        this.soundInfoList = soundInfoList;
        this.soundTrebleInfoList = soundTrebleInfoList;
        this.soundBassInfoList = soundBassInfoList;
        this.userInterface = userInterface;
        this.sheetMusic = displaySheets;
        this.sheetNum = sheetMusic.size();
        
        userInterface.setResizable(false);
        userInterface.setLocationRelativeTo(null);
        userInterface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userInterface.getRootPane().setBorder(
                BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        setupTabs.setEnabled(false);
        setupBorder = setupPanel.getBorder();
        trebleBorder = treblePanel.getBorder();
        bassBorder = bassPanel.getBorder();
        BorderFactory.createTitledBorder(setupBorder, pageMessage + currentSheet);
        BorderFactory.createTitledBorder(trebleBorder, pageMessage + currentSheet);
        BorderFactory.createTitledBorder(bassBorder, pageMessage + currentSheet);
        sheetSetupLabel.setText(null);
        setSheetLabel();
        scoreSetupNext.setEnabled(false);
        trebleIntervalConfirm.setEnabled(false);
        trebleClefNext.setEnabled(false);
        bassIntervalConfirm.setEnabled(false);
        bassClefNext.setEnabled(false);
        userInterface.setVisible(true);
    }

    void setSheetLabel() {
        if (listSpot > 0) {
            String path = sheetMusic.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetSetupLabel.setText(null);
            sheetSetupLabel.setIcon(icon);
            Images.resizeToContainer(sheetSetupLabel);
            currentSheet = listSpot + 1;
            trebleClef.add(fancySpacer);
            trebleNoteValues.add(fancySpacer);
            bassClef.add(fancySpacer);
            bassNoteValues.add(fancySpacer);
        }
        else if (sheetMusic != null) {  
            listSpot = 0;
            String path = sheetMusic.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetSetupLabel.setText(null);
            sheetSetupLabel.setIcon(icon);
            Images.resizeToContainer(sheetSetupLabel); 
            currentSheet = listSpot + 1;
            
            if (trebleClef == null || trebleClef.isEmpty()) {
                trebleClef = new LinkedList<>();
                trebleClef.add(fancySpacer);
            }
            
            if (trebleNoteValues == null || trebleNoteValues.isEmpty()) {
                trebleNoteValues = new LinkedList<>();
                trebleNoteValues.add(fancySpacer);
            }
            
            if (bassClef == null || bassClef.isEmpty()) {
                bassClef = new LinkedList<>();
                bassClef.add(fancySpacer);
            }
            
            if (bassNoteValues == null || bassNoteValues.isEmpty()) {
                bassNoteValues = new LinkedList<>();
                bassNoteValues.add(fancySpacer);
            }
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

    void nextTab(boolean pageFinish) {
        
        if (pageFinish == true) {
            
            if (listSpot == sheetMusic.size() - 1) {
                userInterface.setVisible(false);
            }
            else {
                resetTabs();
            }
            
        }
        else {
            messureCurrentNumber = 1;
            messureMaxNumber = Integer.parseInt(messureNumInput.getText());
            messureBeats = Integer.parseInt(messureBeatInput.getText());
            tempo = Integer.parseInt(tempoInput.getText());
            messureBeatInput.setEditable(false);
            tempoInput.setEditable(false);
            soundInfoList.removeAll();
            soundInfoList.add(messureMessage + messureCurrentNumber);
            setTrebleList();
            setBassList();
            int currentIndex = setupTabs.getSelectedIndex();
            setupTabs.setSelectedIndex(++currentIndex);
        }
        
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
                ++intervalCount;
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                if (intervalCount == 1) {
                    beatCompare += value * 2;
                } 
            }
            else if (trebleTriplet.isSelected() == true) {
                ++intervalCount;
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                if (intervalCount == 1) beatCompare += value * 2; 
            } 
            else if (trebleInterval.isSelected() == true) {
                ++intervalCount;
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                if (intervalCount == 1) beatCompare += value; 
            } else {
                trebleClef.add(soundValue);
                trebleNoteValues.add(note + " " + octave);
                soundInfoList.add(soundValue + " " + note + octave);
                beatCompare += value;
            }
            if(intervalCount == 0) currentMessureCheck();
        }
    }
    
    void addBassNote(String type, double value) {
        String soundValue = type;
        String note = (String) bassNote.getSelectedItem();
        String octave = (String) bassOctave.getSelectedItem();
        
        if (soundValue != null && !soundValue.equals("")) {
            
            if (bassClef == null || bassClef.isEmpty()) {
                bassClef = new LinkedList<>();
            }
            
            if (bassNoteValues == null || bassNoteValues.isEmpty()) {
                bassNoteValues = new LinkedList<>();
            }
            
            if (bassTriplet.isSelected() == true && bassInterval.isSelected() == true) {
                ++intervalCount;
                bassClef.add(soundValue);
                bassNoteValues.add(note + " " + octave);
                if (intervalCount == 1) {
                    beatCompare += value * 2;
                } 
            }
            else if (bassTriplet.isSelected() == true) {
                ++intervalCount;
                bassClef.add(soundValue);
                bassNoteValues.add(note + " " + octave);
                if (intervalCount == 1) beatCompare += value * 2; 
            } 
            else if (bassInterval.isSelected() == true) {
                ++intervalCount;
                bassClef.add(soundValue);
                bassNoteValues.add(note + " " + octave);
                if (intervalCount == 1) beatCompare += value; 
            } else {
                bassClef.add(soundValue);
                bassNoteValues.add(note + " " + octave);
                soundInfoList.add(soundValue + " " + note + octave);
                beatCompare += value;
            }
            if(intervalCount == 0) currentMessureCheck();
        }
    }
    
    void addTrebleRest(String type, double value) {
        String soundValue = type;
        String pause = "pause";
        
        if (soundValue != null && !soundValue.equals("")) {
            
            if (trebleClef == null || trebleClef.isEmpty()) {
                trebleClef = new LinkedList<>();
            }
            
            if (trebleNoteValues == null || trebleClef.isEmpty()) {
                trebleNoteValues = new LinkedList<>();
            }
            
            trebleClef.add(soundValue);
            trebleNoteValues.add(pause);
            soundInfoList.add(soundValue);
            beatCompare += value;
            currentMessureCheck();
        }
    }
    
    void addBassRest(String type, double value) {
        String soundValue = type;
        String pause = "pause";
        
        if (soundValue != null && !soundValue.equals("")) {
            
            if (bassClef == null || bassClef.isEmpty()) {
                bassClef = new LinkedList<>();
            }
            
            if (bassNoteValues == null || bassNoteValues == null) {
                bassNoteValues = new LinkedList<>();
            }
            
            bassClef.add(soundValue);
            bassNoteValues.add(pause);
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
                int currentTabIndex = setupTabs.getSelectedIndex();
                
                if (currentTabIndex == 1) {
                    
                    setTrebleList();
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
                else if (currentTabIndex == 2) {
                    
                    setBassList();
                    bassClefNext.setEnabled(true);
                    bassWholeNote.setEnabled(false);
                    bassHalfNote.setEnabled(false);
                    bassQuarterNote.setEnabled(false);
                    bassEighthNote.setEnabled(false);
                    bassSixteenthNote.setEnabled(false);
                    bassThirtysecondNote.setEnabled(false);
                    bassWholeRest.setEnabled(false);
                    bassHalfRest.setEnabled(false);
                    bassQuarterRest.setEnabled(false);
                    bassEighthRest.setEnabled(false);
                    bassSixteenthRest.setEnabled(false);
                    bassThirtysecondRest.setEnabled(false);
                    bassTriplet.setEnabled(false);
                    bassInterval.setEnabled(false);
                }
            
            }
            beatCompare = 0;
        } 
    }

    void trebleMerge() {
        int length = trebleClef.size();
        int merge = length - intervalCount;
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
        intervalCount = 0;
        trebleClef.removeBack();
        trebleNoteValues.removeBack();
        
        if (trebleInterval.isSelected() == true && trebleTriplet.isSelected() == true) {
            trebleClef.add(trebleIntervalValue + " " + interval + "/" + triplet);
            trebleNoteValues.add(trebleIntervalNote + " " + interval + "/" + triplet);
            soundInfoList.add(trebleIntervalDisplay + " " + interval + "/" + triplet);
            currentMessureCheck();
        }
        else if (trebleTriplet.isSelected() == true) {
            trebleClef.add(trebleIntervalValue + " " + triplet);
            trebleNoteValues.add(trebleIntervalNote + " " + triplet);
            soundInfoList.add(trebleIntervalDisplay + " " + triplet);
            currentMessureCheck();
        } 
        else if (trebleInterval.isSelected() == true) {
            trebleClef.add(trebleIntervalValue + " " + interval);
            trebleNoteValues.add(trebleIntervalNote + " " + interval);
            soundInfoList.add(trebleIntervalDisplay + " " + interval);
            currentMessureCheck();
        }
    }
    
    void bassMerge() {
        int length = bassClef.size();
        int merge = length - intervalCount;
        String bassIntervalValue = bassClef.get(merge);
        String bassIntervalNote = bassNoteValues.get(merge);
        String bassIntervalDisplay = bassClef.get(merge) + " " + bassNoteValues.get(merge).replace(" ", "");
        
        for (int i = merge + 1; i <= bassClef.size() - 1; i++) {
            String grabValue = bassClef.get(i);
            String grabNote = bassNoteValues.get(i);
            bassIntervalValue += " " + grabValue;
            bassIntervalNote += " " + grabNote;
            bassIntervalDisplay += "," + " " + bassClef.get(i) + " " + bassNoteValues.get(i).replace(" ", "");
            bassClef.remove(i);
            bassNoteValues.remove(i);
            --i;
        }
        intervalCount = 0;
        bassClef.removeBack();
        bassNoteValues.removeBack();
        
        if (bassInterval.isSelected() == true && bassTriplet.isSelected() == true) {
            bassClef.add(bassIntervalValue + " " + interval + "/" + triplet);
            bassNoteValues.add(bassIntervalNote + " " + interval + "/" + triplet);
            soundInfoList.add(bassIntervalDisplay + " " + interval + "/" + triplet);
            currentMessureCheck();
        }
        else if (bassTriplet.isSelected() == true) {
            bassClef.add(bassIntervalValue + " " + triplet);
            bassNoteValues.add(bassIntervalNote + " " + triplet);
            soundInfoList.add(bassIntervalDisplay + " " + triplet);
            currentMessureCheck();
        } 
        else if (bassInterval.isSelected() == true) {
            bassClef.add(bassIntervalValue + " " + interval);
            bassNoteValues.add(bassIntervalNote + " " + interval);
            soundInfoList.add(bassIntervalDisplay + " " + interval);
            currentMessureCheck();
        }
    }

    void trebleButtonUpdate() {
        if (trebleInterval.isSelected() == true || trebleTriplet.isSelected() == true) {
            trebleIntervalConfirm.setEnabled(true);
        }
        else {
            trebleIntervalConfirm.setEnabled(false);
        }
    }
    
    void bassButtonUpdate() {
        if (bassInterval.isSelected() == true || bassTriplet.isSelected() == true) {
            bassIntervalConfirm.setEnabled(true);
        }
        else {
            bassIntervalConfirm.setEnabled(false);
        }
    }

    private void resetTabs() {
        ++listSpot;
        setSheetLabel();
        BorderFactory.createTitledBorder(setupBorder, pageMessage + currentSheet);
        BorderFactory.createTitledBorder(trebleBorder, pageMessage + currentSheet);
        BorderFactory.createTitledBorder(bassBorder, pageMessage + currentSheet);
        messureCurrentNumber = 1;
        soundInfoList.removeAll();
        messureNumInput.setText(null);
        fieldCheck();
        trebleClefNext.setEnabled(false);
        trebleWholeNote.setEnabled(true);
        trebleHalfNote.setEnabled(true);
        trebleQuarterNote.setEnabled(true);
        trebleEighthNote.setEnabled(true);
        trebleSixteenthNote.setEnabled(true);
        trebleThirtysecondNote.setEnabled(true);
        trebleWholeRest.setEnabled(true);
        trebleHalfRest.setEnabled(true);
        trebleQuarterRest.setEnabled(true);
        trebleEighthRest.setEnabled(true);
        trebleSixteenthRest.setEnabled(true);
        trebleThirtysecondRest.setEnabled(true);
        trebleTriplet.setEnabled(true);
        trebleInterval.setEnabled(true);
        bassClefNext.setEnabled(false);
        bassWholeNote.setEnabled(true);
        bassHalfNote.setEnabled(true);
        bassQuarterNote.setEnabled(true);
        bassEighthNote.setEnabled(true);
        bassSixteenthNote.setEnabled(true);
        bassThirtysecondNote.setEnabled(true);
        bassWholeRest.setEnabled(true);
        bassHalfRest.setEnabled(true);
        bassQuarterRest.setEnabled(true);
        bassEighthRest.setEnabled(true);
        bassSixteenthRest.setEnabled(true);
        bassThirtysecondRest.setEnabled(true);
        bassTriplet.setEnabled(true);
        bassInterval.setEnabled(true);
        setupTabs.setSelectedIndex(0);
    }

    private void setTrebleList() {
        soundTrebleInfoList.removeAll();
        
        for (int i = 0; i < trebleClef.size(); i++) {
            soundTrebleInfoList.add(trebleClef.get(i) + " " + trebleNoteValues.get(i));
        }
    }

    private void setBassList() {
        soundBassInfoList.removeAll();
        
        for (int i = 0; i < bassClef.size(); i++) {
            soundBassInfoList.add(bassClef.get(i) + " " + bassNoteValues.get(i));
        }
    }

    private String toString(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
