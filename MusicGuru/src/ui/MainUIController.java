package ui;

import collections.LinkedList;
import io.Dialogs;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import tools.Images;

/**
 *
 * @author Rickolas-PC
 */
public class MainUIController {
    
    private JLabel sheetMusicLabel;
    private JButton selectSheets;
    private JButton clearSheets;
    private JLabel speed;
    private JButton speedDown;
    private JButton speedUp;
    private JSlider speedSlider;
    private JToggleButton metronome;
    private JButton instrumentSelect;
    private JButton restartPlayback;
    private JToggleButton togglePlayback;
    private JToggleButton loop;
    private JToggleButton toggleVolume;
    private JSlider volumeSlider;
    private JButton pageForward;
    private JButton pageBack;
    private JLabel pageNum;
    private MainUI userInterface;
    
    private int pageSpot;
    private int listSpot;
    
    private final int width = 890;
    private final int height = 900;
    private final int speedMin = 1;
    private final int speedMax = 200;
    private final int initialSpeed = 100;
    private final int volumeMin = 1;
    private final int volumeMax = 100;
    private final int initialVolume = 50;
    private final String initialPage = " 0/0 ";
    private final String initialMessage = "Sheets Here";
    
    private Dialogs dialog = new Dialogs();
    private File[] files;
    private LinkedList<File> displaySheets;
    
    MainUIController(JLabel sheetMusicLabel, JButton selectSheets, 
            JButton clearSheets, JLabel speed, JButton speedDown, 
            JButton speedUp, JSlider speedSlider, JToggleButton metronome, 
            JButton instrumentSelect, JButton restartPlayback, 
            JToggleButton togglePlayback, JToggleButton loop, 
            JToggleButton toggleVolume, JSlider volumeSlider, 
            JButton pageForward, JButton pageBack, JLabel pageNum, 
            MainUI userInterface) {
        
        this.sheetMusicLabel = sheetMusicLabel;
        this.selectSheets = selectSheets;
        this.clearSheets = clearSheets;
        this.speed = speed;
        this.speedDown = speedDown;
        this.speedUp = speedUp;
        this.speedSlider = speedSlider;
        this.metronome = metronome;
        this.instrumentSelect = instrumentSelect;
        this.restartPlayback = restartPlayback;
        this.togglePlayback = togglePlayback;
        this.loop = loop;
        this.toggleVolume = toggleVolume;
        this.volumeSlider = volumeSlider;
        this.pageForward = pageForward;
        this.pageBack = pageBack;
        this.pageNum = pageNum;
        this.userInterface = userInterface;
        
        userInterface.setSize(width,height);
        userInterface.setResizable(false);
        userInterface.setLocationRelativeTo(null);
        sheetMusicLabel.setText(initialMessage);
        selectSheets.setEnabled(true);
        clearSheets.setEnabled(false);
        speedDown.setEnabled(true);
        speedUp.setEnabled(true);
        speedSlider.setMinimum(speedMin);
        speedSlider.setMaximum(speedMax);
        speedSlider.setValue(initialSpeed);
        metronome.setEnabled(false);
        instrumentSelect.setEnabled(true);
        restartPlayback.setEnabled(false);
        togglePlayback.setEnabled(false);
        loop.setEnabled(false);
        toggleVolume.setEnabled(true);
        volumeSlider.setMinimum(volumeMin);
        volumeSlider.setMaximum(volumeMax);
        volumeSlider.setValue(initialVolume);
        pageForward.setEnabled(false);
        pageBack.setEnabled(false);
        pageNum.setText(initialPage);
        userInterface.setVisible(true);
    }

    void selectSheets() {
        if (displaySheets == null || displaySheets.isEmpty()) {
            displaySheets = new LinkedList<>();
        }
        if (files == null) {
            files = dialog.openFiles(userInterface);
            displaySheets.addAll(files);
        }
                
        if (displaySheets != null) {  
            listSpot = 0;
            String path = displaySheets.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetMusicLabel.setText(null);
            sheetMusicLabel.setIcon(icon);
            Images.resizeToContainer(sheetMusicLabel);                                   
        }
        
        pageSpot = listSpot + 1;
        selectSheets.setEnabled(false);
        clearSheets.setEnabled(true);
        metronome.setEnabled(true);
        restartPlayback.setEnabled(true);
        togglePlayback.setEnabled(true);
        loop.setEnabled(true);
        pageNum.setText(" " + pageSpot + "/" + displaySheets.size() + " ");
        if (displaySheets.size() > 1) {
            pageForward.setEnabled(true);
        }
    }

    void clearSheets() {
        listSpot = 0;
        pageSpot = 0;
        files = null;
        displaySheets.clear();
        sheetMusicLabel.setIcon(null);
        sheetMusicLabel.setText(initialMessage);
        selectSheets.setEnabled(true);
        clearSheets.setEnabled(false);
        metronome.setEnabled(false);
        restartPlayback.setEnabled(false);
        togglePlayback.setEnabled(false);
        loop.setEnabled(false);
        pageForward.setEnabled(false);
        pageBack.setEnabled(false);
        pageNum.setText(initialPage);
    }
    
    void pageForward() {
        if (displaySheets != null) {
            listSpot++;
            String path = displaySheets.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetMusicLabel.setIcon(icon);
            Images.resizeToContainer(sheetMusicLabel);
        }
        
        pageSpot++;
        pageNum.setText(" " + pageSpot + "/" + displaySheets.size() + " ");
        pageBack.setEnabled(true);
        if (displaySheets.size() == listSpot + 1) {
            pageForward.setEnabled(false);
        }
    }

    void pageBack() {
        if (displaySheets != null) {
            listSpot--;
            String path = displaySheets.get(listSpot).getAbsolutePath();
            Icon icon = new ImageIcon(path);
            sheetMusicLabel.setIcon(icon);
            Images.resizeToContainer(sheetMusicLabel);
        }
        
        pageSpot--;
        pageNum.setText(" " + pageSpot + "/" + displaySheets.size() + " ");
        pageForward.setEnabled(true);
        if(listSpot == 0) {
            pageBack.setEnabled(false);
        }
    }

}
