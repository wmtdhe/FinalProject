package rhythmGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
//import sun.audio.*;

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
	//public AudioStream BGM;
    public Clip clip;
	public Song song;
	public Notes[] notes = new Notes[0];
	public JPanel scoreBar;
	public JLabel scoreContent;
	
	public JPanel finalScore;
	public int perfectCount;
	public int goodCount;
	public int missCount;
	
	public JLabel accuracy;
	public JButton pause;
	public boolean p=false;
	public long pauseloc;
	public int combo=0;
	public JLabel co;
	
	
	
	public MainGameScreen(GUI window, String level, String songName, boolean hardMode) {
		this.setLayout(null);
		this.window = window;
		this.setBounds(0, 0, 800, 600);
		 
		
		//set up back button
		JPanel backBut = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(this);
        backBut.setBounds(0, 0, 115, 30);
        backBut.add(back);
        backBut.setBackground(new Color(0,0,0,0));
        add(backBut);
        
        //set up score display
        scoreBar=new JPanel();
        scoreContent=new JLabel("Score: 0");
        scoreContent.setForeground(Color.WHITE);
        scoreBar.add(scoreContent);
        scoreBar.setBounds(0,100,200,200);
        scoreBar.setBackground(new Color(0,0,0,0));
        add(scoreBar);
        
        //accuracy display
        accuracy=new JLabel();
        accuracy.setBounds(625,200,150,100);
        add(accuracy);
        
        //set up final score display
        finalScore = new JPanel();
        finalScore.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JButton hsbutton = new JButton("High Score");
    	hsbutton.addActionListener(this);
    	JButton next = new JButton("Next Challenge");
    	next.addActionListener(this);
        finalScore.setBounds(200,200,400,200);
        finalScore.setBackground(new Color(0.4f,0.4f,0,0.8f));
        finalScore.setVisible(false);
        add(finalScore);
        
        
        //pause
        JPanel pauseB = new JPanel();
        pauseB.setBackground(new Color(0,0,0,0));
        pause = new JButton("Pause");
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
                    		repaint();
                    		window.setFocusable(true);
                    		window.requestFocusInWindow();
                    	}
                    	else {
                    		window.setFocusable(false);
                    	}
                    	
                        Thread.sleep(20);
     
                   }
                }catch (InterruptedException e) {
                	   e.printStackTrace();
                   }
                try{Thread.sleep(1000);}catch(InterruptedException e) {e.printStackTrace();}
                
                if(song.health <=0) {
                	finalScore.add(new JLabel("Try next time!"),gbc);
                	finalScore.add(hsbutton,gbc);
                	finalScore.setVisible(true);
                }
                else {
                	
                	
                	//display final score
                	finalScore.add(scoreContent,gbc);
                	finalScore.add(new JLabel("Perfect: "+ Integer.toString(perfectCount)),gbc);
                	finalScore.add(new JLabel("Good: "+ Integer.toString(goodCount)),gbc);
                	finalScore.add(new JLabel("Miss: "+ Integer.toString(missCount)),gbc);
                	finalScore.add(hsbutton,gbc);
                	finalScore.setVisible(true);
                	
                	//update player profile
                	window.game.player.setHighScore(song.score);
                	if(song.name=="freely tomorrow")window.game.player.setFirstScores(song.score);
                	else window.game.player.setSecondScores(song.score);
            	
                	//hard mode
                	if(hardMode & song.name == "freely tomorrow" ) {
                		finalScore.add(next,gbc);
                	}
                	
                	//ranked on highscore ranking
                	if(window.game.songEnd() && window.game.ranked) {
                		finalScore.add(new JLabel("Congrats! You ranked!"),gbc);
                		HighScore hs = new HighScore(window.game,song,true);
                		hs.setVisible(true);
                	}
            	
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
            			System.out.println("J");
            			findLabel(402);
            			highlight(getGraphics(),402);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_K){
            			System.out.println("K");
            			findLabel(502);
            			highlight(getGraphics(),502);
            			
            		}
            		if(e.getKeyCode() == KeyEvent.VK_D){
            			System.out.println("D");
            			findLabel(202);
            			highlight(getGraphics(),202);
            			
            		}
            		if(e.getKeyCode() == KeyEvent.VK_F){
            			System.out.println("F");
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
		ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\bg_1.jpg",null);
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
            if(notes[i].y>600)notes[i].pass = 1;
            if(notes[i].pass == 1 & notes[i].counted == false & notes[i].hit == 0) {
            	//song.health--;
            	notes[i].counted = true;
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

        Clip clip_button;

        try {
                        clip_button = AudioSystem.getClip();
                        AudioInputStream inputStream;
                        //-select song to play
                               inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\button-16.wav"));
                        clip_button.open(inputStream);
                        clip_button.start();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
        
		g.setColor(new Color(1,0,1,(float)0.4));
		g.fillRect(x, 450, 95, 50);
	}
	

	
	
	@Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
               if(b.getText().equals("Back")) {
                       //AudioPlayer.player.stop(BGM);
                       clip.stop();
                       this.setVisible(false);
                   }
               else if(b.getText().equals("High Score")){
                           	HighScore hs = new HighScore(window.game,song,false);
                        		hs.setVisible(true);

                                    }
             else if(b.getText().equals("Pause")){
                        b.setText("Resume");
                        p=true;
                        pauseloc = clip.getMicrosecondPosition();
                        clip.stop();

                            }
                else if(b.getText().equals("Resume")){
                        p=false;
                        b.setText("Pause");
                        clip.setMicrosecondPosition(pauseloc);
                       clip.start(); }
         else {
        	 this.window.removeKeyListener(this.window.getKeyListeners()[0]);
        	 this.finalScore.setVisible(false);
        	 MainGameScreen mainGame = new MainGameScreen(this.window,"Expert","yumetourou",true);
                   mainGame.setVisible(true);
        	 add(mainGame);
        	 
         }
    }
	
	
	public void music() 
    {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream;
            //-select song to play
            if(this.song.name.equals("yumetourou")) {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\梦灯笼.wav"));
            }
            else {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\FREELY_TOMORROW.wav"));
            }
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
	
	
	/**
	 * Find if there are blocks inside the target area.
	 * If there is, increase the score.
	 * @param i
	 */
	private void findLabel(int i) {
        for (int j = 0; j<notes.length; j++ ) {
        	//perfect
            if(notes[j].x==i && notes[j].y>450 & notes[j].y<500 & notes[j].hit == 0) {
            	System.out.println("hit");
                song.score+=500;
                perfectCount+=1;
                //cannot calculate one bar twice
                notes[j].hit = 1;
                
                scoreContent.setText("Score: "+Integer.toString(song.score));
                
                
                if(j!=0){
                	if( notes[j-1].hit==1) {
                	 combo += 1;
                	 }
                	 else {combo=0;}
                	}
                else combo=1;

                co.setText(String.valueOf(combo));
                //----
                showAccuracy("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\perfect.png");
                return;
            }
            
          //good
            else if(notes[j].x==i & ((notes[j].y>=430 & notes[j].y<450) || (notes[j].y>500 & notes[j].y<530)) & notes[j].hit==0){
                notes[j].hit=1;
                song.score+=200;
                scoreContent.setText("Score: "+Integer.toString(song.score));
                goodCount+=1;
                if(j!=0){
                    if( notes[j-1].hit==1) {
                        combo += 1;
                    }
                    else {combo=0;}
                }
                else if(j==0){
                    combo=1;
                }
                co.setText(String.valueOf(combo));
                
                showAccuracy("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\Good1.png");
               
                return;
            }
        }

    }
	
	 /**
     * pop out accuracy img
     * @param path
     */
    public void showAccuracy(String path){
        accuracy.setVisible(true);
        ImageIcon icon = new ImageIcon(path, null);
        Image before = icon.getImage();
        Image resizedImage = before.getScaledInstance(accuracy.getWidth(),accuracy.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(resizedImage);
        accuracy.setIcon(newicon);
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
