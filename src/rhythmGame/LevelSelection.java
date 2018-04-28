package rhythmGame;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.ImageIcon;  

public class LevelSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame lf;
    public JPanel mainGame;
    public String songName;

	private static final long serialVersionUID = 1L; 
    public LevelSelection(JFrame frame, String songName){
        lf=frame;
        c = new CardLayout();
        this.songName = songName;
        setLayout(c);
        JPanel levels = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		//draw background
        		ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\bg.jpg",null);
        		Image before = icon.getImage();
        		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        		ImageIcon newIcon = new ImageIcon(newImage);
        		newImage = newIcon.getImage();
        		g.drawImage(newImage, 0, 0, null);
            }
        };
        levels.setLayout(null);

        JButton easy = new JButton("Easy");
        JButton normal = new JButton("Normal");
        JButton expert = new JButton("Expert");
        JButton back = new JButton("Back");

        levels.add(easy);
        levels.add(normal);
        levels.add(expert);
        levels.add(back);
        easy.setBounds(300,50,200,100);
        normal.setBounds(300,250,200,100);
        expert.setBounds(300,450,200,100);
        back.setBounds(0,0,80,80);
        easy.addActionListener(this);
        normal.addActionListener(this);
        expert.addActionListener(this);
        back.addActionListener(this);
        

        add(levels,"level");
        setVisible(true);
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	 JButton b = (JButton)e.getSource();
    	 
         if(b.getText().equals("Back")) {
             this.setVisible(false);
         }
         else{
        	 mainGame = new MainGameScreen(lf,b.getText(),this.songName);
        	 add(mainGame,"game");
        	 c.show(this,"game");
        	 lf.setTitle("Game on");
         }
    }
}