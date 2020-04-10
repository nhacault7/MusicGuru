package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Rickolas-PC
 */
public class Images {
    
    public static void resizeToContainer(JLabel label) {
        int width = label.getWidth();
        int height = label.getHeight();
        ImageIcon originalIcon = (ImageIcon)label.getIcon();
        if (originalIcon == null) return;
        Image originalImage = originalIcon.getImage();
        Image newImage = originalImage.getScaledInstance(
                                width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        label.setIcon(icon);
        label.setSize(width, height);
    }
    
}
