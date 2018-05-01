package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import sun.audio.*;

public class SongSelection extends JPanel implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	public CardLayout c;
    public GUI sf;
    public JButton back;
    public AudioStream BGM;
    public SongSelection(GUI frame) {
        sf = frame;
        ImageIcon b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_back.png");
        back = new JButton(b);
        c = new CardLayout();
        setLayout(c);
        
        //main panel
        JPanel songlist = new JPanel() {
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
        songlist.setLayout(null);
        songlist.setBackground(new Color(0,0,0,0));
        
        //add images
        JLabel previewImg1 = new JLabel();
        previewImg1.setBounds(0,0,400,250);
        previewImg1.setIcon(ResizedIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\Realistic_virtual_singing.png",previewImg1));
        JLabel previewImg2 = new JLabel();
        previewImg2.setBounds(0,250,400,250);
        previewImg2.setIcon(ResizedIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\yume.jpg",previewImg2));
        
        //add buttons
        JButton song1 = new JButton("Freely Tomorrow");
        song1.setForeground(Color.white);
        song1.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 30));
        JButton song2 = new JButton("Yumetourou");
        song2.setForeground(Color.white);
        song2.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 30));
        
        
        //add demo listening
        song1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
            	song1.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 40));
                music("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\FREELY_TOMORROW.wav");
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
            	song1.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 30));
            	AudioPlayer.player.stop(BGM);
            }
            
        });
        
        song2.addMouseListener(new MouseAdapter(){
        	@Override
            public void mouseEntered(MouseEvent me) {
        		song2.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 50));
                music("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\ÃÎµÆÁý.wav");
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
            	song2.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 30));
            	AudioPlayer.player.stop(BGM);
            }
        });
        
        
        
        //add hard mode
        ImageIcon h = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_hard-mode.png");
        JButton hardMode = new JButton(h);
        hardMode.setBounds(400,480,400,100);
        hardMode.addActionListener(this);

        song1.setBounds(400,50,400,150);
        song2.setBounds(400,300,400,150);
        back.setBounds(0,480,400,100);
        

        
        sf.setButtonStyle(back);
        sf.setButtonStyle(hardMode);
        sf.setButtonStyle(song1);
        sf.setButtonStyle(song2);
        
        

        song1.addActionListener(this);
        song2.addActionListener(this);
        back.addActionListener(this);
        songlist.add(song1);
        songlist.add(song2);
        songlist.add(previewImg1);
        songlist.add(previewImg2);
        songlist.add(back);
        songlist.add(hardMode);
        add(songlist);
        setVisible(true);
        
        
    }
    
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	JButton b = (JButton)e.getSource();
    	
    	//start new games
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
        //back
        else if(b==back) {
        	this.setVisible(false);
        	
        }
        //hard mode
        else{
        	MainGameScreen mainGame = new MainGameScreen(sf,"Expert","freely tomorrow",true);
       	 	add(mainGame,"game");
       	 	c.show(this,"game");
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
    
    
    /**
     * Plays audio.
     * @param bgm
     */
    public void music(String bgm) 
    {       
        AudioPlayer MGP = AudioPlayer.player;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream(bgm);
            this.BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            
           
        }
        catch(FileNotFoundException e){
        	e.printStackTrace();
        }
        catch(IOException error)
        {
        	error.printStackTrace();
        }
        MGP.start(loop);
    }

}