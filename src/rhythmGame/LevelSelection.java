package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;  

public class LevelSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public GUI lf;
    public JPanel mainGame;
    public String songName;
    public JButton back,easy,normal,expert;

	private static final long serialVersionUID = 1L; 
    public LevelSelection(GUI frame, String songName){
        lf=frame;
        c = new CardLayout();
        this.songName = songName;
        setLayout(c);
        
        
        JPanel levels = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		//draw background
        		ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\bg_1.jpg",null);
        		Image before = icon.getImage();
        		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        		ImageIcon newIcon = new ImageIcon(newImage);
        		newImage = newIcon.getImage();
        		g.drawImage(newImage, 0, 0, null);
            }
        };
        levels.setLayout(null);

        
        easy = new JButton(new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_easy.png"));
        normal = new JButton(new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_normal.png"));
        expert = new JButton(new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_expert.png"));
        back = new JButton(new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_back.png"));
        lf.setButtonStyle(easy);
        lf.setButtonStyle(normal);
        lf.setButtonStyle(expert);
        lf.setButtonStyle(back);
        
        
        
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
    	 
         if(b==back) {
             this.setVisible(false);
         }
         else{
        	 mainGame = new JPanel();
        	 if(b==easy)mainGame = new MainGameScreen(lf,"Easy",this.songName,false);
        	 if(b==normal)mainGame = new MainGameScreen(lf,"Normal",this.songName,false);
        	 if(b==expert)mainGame = new MainGameScreen(lf,"Expert",this.songName,false);
        	 add(mainGame,"game");
        	 c.show(this,"game");
         }
    }
}