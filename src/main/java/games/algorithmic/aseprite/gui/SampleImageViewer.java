package games.algorithmic.aseprite.gui;

import games.algorithmic.aseprite.AsepriteFile;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A simple image viewer - I assure you.
 * 
 * @author Christian Lins <christian@lins.me>
 */
public class SampleImageViewer extends JFrame {
    public SampleImageViewer() {
        final SampleImageViewer viewer = this;
        final ImagePanel panel = new ImagePanel();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        
        JButton btnOpen = new JButton("Open...");
        btnOpen.addActionListener((ActionEvent e) -> {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(viewer);
            
            if (fc.getSelectedFile() != null) {
                panel.loadImage(fc.getSelectedFile());
            }
        });
        
       getContentPane().add(btnOpen, BorderLayout.PAGE_START);
    }
    
    public static void main(String[] args) {
        new SampleImageViewer().setVisible(true);
    }
}

class ImagePanel extends JPanel {
    
    private Image img;
    
    public void loadImage(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            
            AsepriteFile ase = new AsepriteFile(in);
            ase.getImage();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
    }
}
