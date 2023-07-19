/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author xhu
 */
public class NoiseRemoving extends JFrame{

    private String fileName;
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private JButton load;
    private JButton save;
    private JButton clean;
    private ImageProcess ip;
    ImageIcon image;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NoiseRemoving noiseRemove = new NoiseRemoving();
        
        noiseRemove.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        noiseRemove.setSize(700,400);
        noiseRemove.setTitle("Noise Remover");
        noiseRemove.setVisible(true);
        noiseRemove.setResizable(false);
    }
    
    //NoiseRemoving GUI Constructor
    public NoiseRemoving ()
    {
        panel();
    }
    
    //Generates a GUI panel
    private void panel ()
    {
        //Creates an upload image button
        load = new JButton("Upload image");
        load.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser imageFileChooser = new JFileChooser(new File("."));
                int stateImageFileChooser = imageFileChooser.showOpenDialog(null);
                
                if(stateImageFileChooser == JFileChooser.APPROVE_OPTION)
                {
                    if(imageLabel != null)
                    {
                        imageLabel.setIcon(null);
                    }
                    fileName = imageFileChooser.getSelectedFile().getPath();
                    System.out.println(fileName);
                    image = new ImageIcon(fileName);
                    imageLabel = new JLabel((Icon) image);
                    ip = new ImageProcess(fileName);
                    showImage();
                    clean.setEnabled(true);
                    save.setEnabled(true);
                }
            }
        });
        
        //Creates a clean image button
        clean = new JButton("Clean image");
        clean.setEnabled(false);
        clean.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(null);
                ip.removeNoise();
                ImageIcon imageIcon = new ImageIcon(ip.buffered_image);
                image = imageIcon;
                showImage();
                save.setEnabled(true);
            }
        });
        
        //Creates a save image button
        save = new JButton("Save image");
        save.setEnabled(false);
        save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                ip.save("noise_removed.jpg");
                save.setEnabled(false);
            }
        });
        
        buttonPanel = new JPanel();
        buttonPanel.add(load);
        buttonPanel.add(clean);
        buttonPanel.add(save);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    //Shows the uploaded image.
    private void showImage()
    {
        ImageIcon resizedImageIcon = new ImageIcon(image.getImage().getScaledInstance(600, 350, java.awt.Image.SCALE_SMOOTH));
        imageLabel.setIcon(resizedImageIcon);
        this.getContentPane().add(imageLabel, BorderLayout.NORTH);
        this.setVisible(true);
    }
}
