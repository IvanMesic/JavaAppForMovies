/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author 505wL
 */
public class IconUtils {
        public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = bufferedImage.getScaledInstance(width, height,Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }    
}
