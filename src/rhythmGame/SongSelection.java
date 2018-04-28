package rhythmGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
//import sun.audio.*;

public class SongSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame sf;
    public JButton back;
   // public AudioStream BGM;
    public Clip clip;
    public SongSelection(JFrame frame) {
        sf = frame;
        back = new JButton("Back");
        c = new CardLayout();
        setLayout(c);
        JPanel songlist = new JPanel();
        songlist.setLayout(null);
        JLabel previewImg1 = new JLabel();
        previewImg1.setBounds(0,0,400,250);
        previewImg1.setIcon(ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\Realistic_virtual_singing.png",previewImg1));
        JLabel previewImg2 = new JLabel();
        previewImg2.setBounds(0,250,400,250);
        previewImg2.setIcon(ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\yume.jpg",previewImg2));
        JButton song1 = new JButton("Freely Tomorrow");
        JButton song2 = new JButton("Yumetourou");
        
        song1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                music("freely");
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
            	clip.stop();
            }
            
        });
        
        song2.addMouseListener(new MouseAdapter(){
        	@Override
            public void mouseEntered(MouseEvent me) {
                music("yumetourou");
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
            	clip.stop();
            }
        });

        song1.setBounds(400,0,400,250);
        song2.setBounds(400,250,400,250);
        back.setBounds(0,500,800,100);


        song1.addActionListener(this);
        song2.addActionListener(this);
        back.addActionListener(this);
        songlist.add(song1);
        songlist.add(song2);
        songlist.add(previewImg1);
        songlist.add(previewImg2);
        songlist.add(back);
        add(songlist);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JButton b = (JButton)e.getSource();
        if(b.getText().equals("Freely Tomorrow")) {
            JPanel levelselection = new LevelSelection(sf, "freely tomorrow");
            add(levelselection,"level");
            c.show(this, "level");

            sf.setTitle("Freely Tomorrow");
        }
        else if(b.getText().equals("Yumetourou")){
            JPanel levelselection = new LevelSelection(sf,"yumetourou");
            add(levelselection,"level");
            c.show(this, "level");
            sf.setTitle("Yumetourou");
        }
        else if(b.getText().equals("Back")) {
        	this.setVisible(false);
        	
        }
    }

    /**
     *  resize image to fit jpanel
     * @param path
     * @return resized imageicon
     */
    public ImageIcon ResizedIcon(String path,JLabel label){
        ImageIcon icon = new ImageIcon(path, null);
        Image before = icon.getImage();
        Image resizedImage = before.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(resizedImage);
        return newicon;
    }
    
    
    public void music(String bgm) 
    {

        try {
            //-select song to play
            clip = AudioSystem.getClip();
            AudioInputStream inputStream;
            if(bgm.equals("yumetourou")) {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\梦灯笼.wav"));
            }
            else {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\p80_FREELY TOMORROW feat. 初音ミク.wav"));
            }
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}