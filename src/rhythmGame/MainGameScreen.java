package rhythmGame;

import javax.swing.*;
import sun.audio.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MainGameScreen extends JPanel implements ActionListener{
	public GUI window;
	private int yOffset = 0;//control the speed of falling notes
	private int speed;
	public AudioStream BGM;
	public Song song;
	public Notes[] notes = new Notes[0];
	public JPanel scoreBar;//ingame score display
	public JLabel scoreContent;
	
	public JPanel finalScore;//final score display
	public int perfectCount;
	public int goodCount;
	public int missCount;
	
	public JLabel accuracy;//accuracy labels
	public boolean p=false;//paused?
	public int combo=0;
	public int maxcombo=0;
	public JLabel co;//combo display
	
	public ImageIcon perfect;
	public ImageIcon good;
	public JButton back;
	public Boolean hardMode;
	public int searchIndex;
	
	
	
	public MainGameScreen(GUI window, String level, String songName, boolean hardMode) {
		this.setLayout(null);
		this.window = window;
		this.hardMode = hardMode;

		//set up back button
		back = new JButton(new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_back.png"));
        back.addActionListener(this);
        back.setBounds(0, 0, 115, 100);
        window.setButtonStyle(back);
        add(back);
        
        //map the song
        this.song = new Song(notes,songName);
        if(songName.equals("freely tomorrow")) {
        	if(level.equals("Normal"))song.mapFirstSongNormal(window.game.normalSpeed);
        	if(level.equals("Easy"))song.mapFirstSongEasy(window.game.easySpeed);
        	if(level.equals("Expert"))song.mapFirstSongEx(window.game.exSpeed);
        	
        }
        else {
            if(level.equals("Normal"))song.normalYumetourou(window.game.easySpeed);
            if(level.equals("Easy"))song.easyYumetourou(window.game.normalSpeed);
            if(level.equals("Expert"))song.expertYumetourou(window.game.exSpeed);

        }
        notes = this.song.notes;
        window.game.currentSong = this.song;
        
        
        
        //adjust speed
        if(level == "Easy")this.speed = window.game.easySpeed;
        if(level == "Normal")this.speed = window.game.normalSpeed;
        if(level == "Expert")this.speed = window.game.exSpeed;
        
        //set up in game score display
        scoreBar=new JPanel();
        scoreContent=new JLabel("Score: 0");
        scoreContent.setForeground(Color.WHITE);
        scoreContent.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 26));
        scoreBar.add(scoreContent);
        scoreBar.setBounds(0,100,200,200);
        scoreBar.setBackground(new Color(0,0,0,0));
        add(scoreBar);
        
        
        
        //set up final score display
        finalScore = new JPanel();
        finalScore.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JButton hsbutton = new JButton("High Score");
        window.setButtonStyle(hsbutton);
    	hsbutton.addActionListener(this);
    	JButton next = new JButton("Next Challenge");
    	next.addActionListener(this);
        finalScore.setBounds(200,200,400,200);
        finalScore.setBackground(new Color(0.4f,0.4f,0,1));
        finalScore.setVisible(false);
        add(finalScore);
        
        
        //pause
        JPanel pauseB = new JPanel();
        pauseB.setBackground(new Color(0,0,0,0));
        JButton pause = new JButton("Pause");
        pause.setBackground(Color.orange);
        pauseB.setBounds(650,0,115,30);
        pause.addActionListener(this);
        pauseB.add(pause);
        add(pauseB);
        
        //---combo
        co = new JLabel(String.valueOf(combo));
        co.setBounds(400,0,400,100);
        co.setFont(new Font("Consolas", Font.PLAIN, 40));
        co.setForeground(Color.white);
        add(co);
       

        
        
        
      //accuracy display
        accuracy=new JLabel();
        accuracy.setBounds(625,200,150,100);
        add(accuracy);
        
      //set up accuracy icons
      	ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\perfect.png", null);
        Image before = icon.getImage();;
        Image resizedImage = before.getScaledInstance(150,100,Image.SCALE_SMOOTH);
        perfect = new ImageIcon(resizedImage);
              
              
        icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\Good1.png", null);
        before = icon.getImage();
        resizedImage = before.getScaledInstance(150,100,Image.SCALE_SMOOTH);
        good = new ImageIcon(resizedImage);
        
        
        
      //one thread plays music
       new Thread(new Runnable() {
        	@Override
        	public void run() {
        		
        		music();
        	}
        }).start();
       
       //another thread makes the bars fall
        new Thread(new Runnable() {  
            @Override  
            public void run() {
                try {
                	
                    while(notes[notes.length-1].pass != 1 && song.health>0){ 
                    	//Bars goes downwards
                    	
                    	if(!p) {
                    		yOffset = yOffset + speed;
                    		
                    	}
                    	
                    	
                    	repaint();
                        Thread.sleep(20);
     
                   }
                }catch (InterruptedException e) {
                	   e.printStackTrace();
                   }
                try{Thread.sleep(1000);}catch(InterruptedException e) {e.printStackTrace();}
                
                //when the song ends
                if(song.health <=0) {
                	finalScore.add(new JLabel("Try next time!"),gbc);
                	finalScore.add(hsbutton,gbc);
                	finalScore.setVisible(true);
                }
                else {
                	
                	maxcombo = maxcombo>combo?maxcombo:combo;
                	
                	//display final score
                	finalScore.add(scoreContent,gbc);
                	finalScore.add(new JLabel("Perfect: "+ Integer.toString(perfectCount)),gbc);
                	finalScore.add(new JLabel("Good: "+ Integer.toString(goodCount)),gbc);
                	finalScore.add(new JLabel("Miss: "+ Integer.toString(missCount)),gbc);
                	finalScore.add(new JLabel("Combo: "+ Integer.toString(maxcombo)),gbc);
                	finalScore.add(hsbutton,gbc);
                	
                	//ranked on highscore ranking
                	if(window.game.songEnd() && window.game.ranked) {
                		finalScore.add(new JLabel("Congrats! You ranked!"),gbc);
                		HighScore hs = new HighScore(window.game,song,true);
                		hs.setVisible(true);
                	}
                	
                	
                	//hard mode
                	if(hardMode && song.name == "freely tomorrow" ) {
                		if(maxcombo==song.notes.length-1)finalScore.add(next,gbc);
                		else finalScore.add(new JLabel("Try next time!"),gbc);
                	}
                	
                	if(hardMode && song.name =="yumetourou") {
                		if(maxcombo==song.notes.length-1)finalScore.add(new JLabel("Congrats! You beat the hard mode!"),gbc);
                		else finalScore.add(new JLabel("Try next time!"),gbc);
                		AudioPlayer.player.stop(BGM);
                	}
                	finalScore.setVisible(true);
                	
                	//update player profile
                	window.game.player.setCount();
                	window.game.player.setHighScore(song.score);
                	if(level == "Expert")window.game.player.expert = true;
                	if(song.name=="freely tomorrow")window.game.player.setFirstScores(song.score);
                	else window.game.player.setSecondScores(song.score);

                	
            	
                }
            }
                
            
        }).start();
        setVisible(true);  
        
        //when key pressed, calculate score
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
            	
            		
            		if(e.getKeyCode() == KeyEvent.VK_J){
            			findLabel(402);//calculate score
            			highlight(getGraphics(),402);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_K){
            			findLabel(502);
            			highlight(getGraphics(),502);
            			
            		}
            		if(e.getKeyCode() == KeyEvent.VK_D){
            			findLabel(202);
            			highlight(getGraphics(),202);
            			
            		}
            		if(e.getKeyCode() == KeyEvent.VK_F){
            			findLabel(302);
            			highlight(getGraphics(),302);
            		}
            	

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        
       
        //start listening to the keys
        window.setFocusable(true);
        window.requestFocusInWindow();
      
        
        
        
	}
	
	
	/**
	 * Modify the built-in function to draw background and bars
	 */
	private static final long serialVersionUID = 1L; 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		//draw background
		ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\bg.jpg",null);
		Image before = icon.getImage();
		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		newImage = newIcon.getImage();
		g.drawImage(newImage, 0, 0, null);
	    
	    
		Line2D linU = new Line2D.Float(0, 450, 800, 450);
        Line2D linB = new Line2D.Float(0, 500, 800, 500);
        Line2D lin1 = new Line2D.Float(200, 0, 200, 500);
        Line2D lin2 = new Line2D.Float(300, 0, 300, 500);
        Line2D lin3 = new Line2D.Float(400, 0, 400, 500);
        Line2D lin4 = new Line2D.Float(500, 0, 500, 500);
        Line2D lin5 = new Line2D.Float(600, 0, 600, 500);
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.BLACK);
        g2.draw(linU);
        g2.draw(linB);
        g2.draw(lin1);
        g2.draw(lin2);
        g2.draw(lin3);
        g2.draw(lin4);
        g2.draw(lin5);
        
        
        g.setColor(Color.WHITE);
		Font font = new Font("Consolas", Font.PLAIN, 20); 
		g.setFont(font);
        g.drawString("D", 250, 475);
        g.drawString("F", 350, 475);
        g.drawString("J", 450, 475);
        g.drawString("K", 550, 475);
       
        
        //draw health bar
        g.drawString("HP",30,370);
        if(this.song.health>5) {
        	g.setColor(Color.GREEN);
        }
        else if(this.song.health>3) {
        	g.setColor(Color.YELLOW);
        }
        else g.setColor(Color.RED);
        g.fillRect(30, 400, this.song.health*10, 30);	
        
        
       paintBars(g);
       
       
        
	}
	
	/**
	 * update the position of bars
	 * @param g
	 */
	public void paintBars(Graphics g) {
		 //bars
		for (int i = 0; i<this.song.notes.length;i++) {
            notes[i].y = notes[i].original_y + yOffset;
            
            //missed
            if(notes[i].y>500 && notes[i].hit == 0 && !notes[i].counted){
            	missCount++;
            	maxcombo = maxcombo>combo?maxcombo:combo;
            	combo = 0;
            	song.health--;
            	notes[i].counted = true;
            	co.setText("0");
            }
            
            if(notes[i].y>-30 && notes[i].y<600)//only draw bars that are inside the frame
            {   g.setColor(new Color(0,1,1,(float)0.4));

                //update bar position
                g.fillRect(notes[i].x, notes[i].y, 95, 30);
                
                //draw a border surrounding the bar
                g.setColor(new Color(0,0,0, (float) 0.4));
                g.drawRect(notes[i].x, notes[i].y, 95, 30);
            }


        }
        
		
	}
	
	/**
	 * Highlight the area of keys when pressed
	 * @param g
	 * @param x
	 */
	public void highlight(Graphics g, int x) {
		
		//added sound effect
		AudioStream sfx;
        InputStream test = null;
        try
        {
        	
        	test = new FileInputStream("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button-16.wav");
        	sfx = new AudioStream(test);
            AudioPlayer.player.start(sfx);
            
           
        }
        catch(FileNotFoundException e){
        	e.printStackTrace();
        }
        catch(IOException error)
        {
        	error.printStackTrace();
        }
        
        
        //highlight
		g.setColor(new Color(1,0,1,(float)0.4));
		g.fillRect(x, 450, 95, 50);
	}
	

	
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	 JButton b = (JButton)e.getSource();
    	 
    	 //back
         if(b==back) {
        	 AudioPlayer.player.stop(BGM);
             this.setVisible(false);
             if(song.name == "yumetourou" & hardMode == true) {
            	 window.cardLayout.show(window.cardPanel, "New Game");
             }
             else this.window.removeKeyListener(this.window.getKeyListeners()[0]);
         }
         //show high score
         else if(b.getText().equals("High Score")){
        	HighScore hs = new HighScore(window.game,song,false);
     		hs.setVisible(true);
       
         }
         //pause
        else if(b.getText().equals("Pause")){
        	 b.setText("Resume");
        	 p=true;
        	 window.setFocusable(false);
        	 AudioPlayer.player.stop(BGM);}		
         //resume
        else if(b.getText().equals("Resume")){
        	 p=false;
        	 b.setText("Pause");
        	 window.setFocusable(true);
     		 window.requestFocusInWindow();
        	 AudioPlayer.player.start(BGM);}
         //enter next song of hardmode
         else {
        	 this.window.removeKeyListener(this.window.getKeyListeners()[0]);
        	 this.finalScore.setVisible(false);
        	 AudioPlayer.player.stop(BGM);
        	 MainGameScreen mainGame = new MainGameScreen(this.window,"Expert","yumetourou",true);
        	 window.cardPanel.add("next",mainGame);
        	 window.cardLayout.show(window.cardPanel, "next");
        	 
         }
    }
	
	/**
	 * Plays music
	 */
	
	private void music() 
    {       
        AudioPlayer MGP = AudioPlayer.player;


        ContinuousAudioDataStream loop = null;
        InputStream test = null;
        
        try
        {
        	if(this.song.name.equals("freely tomorrow")) {
        		test = new FileInputStream("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\FREELY_TOMORROW.wav");
        	}
        	else {
        		test = new FileInputStream("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\ÃÎµÆÁý.wav");
        		
        	}
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
	
	
	/**
	 * Find if there are blocks inside the target area.
	 * If there is, increase the score.
	 * @param i
	 */
	private void findLabel(int i) {
        for (int j = searchIndex; j<notes.length; j++ ) {
        	int Y = notes[j].y;
        	
            if(notes[j].x==i && notes[j].hit == 0 && Y>430 && Y<530 ) {
            	//perfect
            	if(Y>=450 && Y<=500) {
            	
            		song.score+=500;
            		perfectCount++;
            		showAccuracy(perfect);
            	}
            	else {
            	//good
            		song.score+=200;
            		goodCount++;
            		showAccuracy(good);
            	}
            	
            	
                //cannot calculate one bar twice
                notes[j].hit = 1;
                notes[j].pass = 1;
                combo++;
                scoreContent.setText("Score: "+Integer.toString(song.score));
                
               

                co.setText(String.valueOf(combo));
                //----
                searchIndex = j;   
                return;
            }
            
          
        }

    }
	
	 /**
     * pop out accuracy img
     * @param path
     */
    public void showAccuracy(ImageIcon image){
        accuracy.setVisible(true);
        accuracy.setIcon(image);
        // icon lasts for 2 seconds and then disappears
        
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        Thread.sleep(2000);
                        accuracy.setVisible(false);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
        
    }

	
}
