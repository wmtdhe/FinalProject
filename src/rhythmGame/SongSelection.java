package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame sf;
    public JButton back;
    public SongSelection(JFrame frame) {
        sf = frame;
        back = new JButton("Back");
        c = new CardLayout();
        setLayout(c);
        JPanel songlist = new JPanel();
        JPanel levelselection = new LevelSelection(frame);
        songlist.setLayout(null);
        JLabel previewImg1 = new JLabel();
        previewImg1.setBounds(0,0,400,250);
        previewImg1.setIcon(ResizedIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\rhythmGame\\Realistic_virtual_singing.png",previewImg1));
        JLabel previewImg2 = new JLabel();
        previewImg2.setBounds(0,250,400,250);
        previewImg2.setIcon(ResizedIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\rhythmGame\\p2.jpg",previewImg2));
        JButton song1 = new JButton("Freely Tomorrow");
        JButton song2 = new JButton("心做し");

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
        add(levelselection,"level");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Freely Tomorrow")) {
            c.show(this, "level");
            sf.setTitle("Freely Tomorrow");
        }
        else if(b.getText().equals("心做し")){
            c.show(this, "level");
            sf.setTitle("心做し");
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
}