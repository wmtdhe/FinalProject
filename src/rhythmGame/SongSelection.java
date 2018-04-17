package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame sf;
    public SongSelection(JFrame frame){
        sf = frame;
        c = new CardLayout();
        setLayout(c);
        JPanel songlist = new JPanel();
        JPanel levelselection = new LevelSelection(frame);
        songlist.setLayout(null);
        JLabel previewImg1 = new JLabel();
        previewImg1.setBounds(0,0,400,300);
        previewImg1.setIcon(ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\bg.jpg",previewImg1));
        JLabel previewImg2 = new JLabel();
        previewImg2.setBounds(0,300,400,300);
        previewImg2.setIcon(ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\p2.jpg",previewImg2));
        JButton song1 = new JButton("Unravel");
        JButton song2 = new JButton("心做し");

        song1.setBounds(400,0,400,300);
        song2.setBounds(400,300,400,300);


        song1.addActionListener(this);
        song2.addActionListener(this);
        songlist.add(song1);
        songlist.add(song2);
        songlist.add(previewImg1);
        songlist.add(previewImg2);
        add(songlist);
        add(levelselection,"level");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        //System.out.println(b.getLabel());
        //System.out.println(b.getText());
        if(b.getText().equals("Unravel")) {
            c.show(this, "level");
            sf.setTitle("Unravel");
        }
        else if(b.getText().equals("心做し")){
            c.show(this, "level");
            sf.setTitle("心做し");
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
