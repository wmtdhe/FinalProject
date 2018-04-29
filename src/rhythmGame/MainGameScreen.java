package rhythmGame;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.net.URL;

public class MainGameScreen extends JPanel implements ActionListener{
    public JFrame window;
    private int yOffset = 0;
    public int k=0; //keyword hit
    private int speed;
    //public AudioStream BGM;
    public Clip clip;
    public Song song;
    public Notes[] notes = new Notes[0];
    public String songName;
    public JPanel scoreBar;
    public JLabel scoreContent;
    public int perfectCount;
    public int goodCount;
    public int missCount;
    public JLabel accuracy;
    public JButton pause;
    public boolean p=false;
    public long pauseloc;
    public int combo=0;
    public JLabel co;
    ///
    //public JPanel game;

    public MainGameScreen(JFrame window, String level, String songName) {
        this.setLayout(null);
        this.songName=songName;
        this.window=window;
        JPanel backBut = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(this);
        backBut.setBounds(0, 0, 115, 30);
        backBut.add(back);
        backBut.setBackground(new Color(0,0,0,0));
        //ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\perfect.png",null);
        accuracy=new JLabel();
        accuracy.setBounds(625,200,150,100);
        add(accuracy);
        //------
        scoreBar=new JPanel();
        scoreContent=new JLabel("Score: 0");
        scoreBar.add(scoreContent);
        scoreBar.setBounds(0,100,200,200);
        add(scoreBar);
        //----
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

        //load mapping
        this.song = new Song(notes,songName);
        if(songName.equals("freely tomorrow")) {
        	if(level.equals("Normal"))song.mapFirstSongNormal();
        	if(level.equals("Easy"))song.mapFirstSongEasy();
        }
        else {
            if(level.equals("Normal"))song.normalYumetourou();
            if(level.equals("Easy"))song.easyYumetourou();
            if(level.equals("Expert"))song.expertYumetourou();

        }

        notes = this.song.notes;

        add(backBut);

        //adjust speed
        if(level == "Easy")speed = 2;
        if(level == "Normal")speed = 3;
        if(level == "Expert")speed = 4;


      //another thread plays music
        new Thread(new Runnable() {
            @Override
            public void run() {

                    music();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	//float start = System.nanoTime();
                    //if(p){
                    //    wait();
                    //}
                    //System.out.println(p);
                    while(true){
                    	//Bars goes downwards
                    	//if((System.nanoTime()-start)>=20000000){
                    		//start = System.nanoTime();
                        if(!p)
                        {
                            yOffset = yOffset + speed;
                            window.setFocusable(true);
                            window.requestFocusInWindow();
                        }
                        else{
                            window.setFocusable(false);
                        }
                    		//time+=delta;



                    		repaint();

                    	//}
                        Thread.sleep(20);
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();



        setVisible(true);

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {


            		if(e.getKeyCode() == KeyEvent.VK_J){
            		    //k=74;
                        // x in first column
                        System.out.println("J");
                        findLabel(402);
                        highlight(getGraphics(),402);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_K){
            		    //k=75;
                        System.out.println("K");
                        findLabel(502);
                        highlight(getGraphics(),502);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_D){
            		    //k=68;
                        findLabel(202);
                        highlight(getGraphics(),202);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_F){
            		    //k=70;
                        findLabel(302);
                        highlight(getGraphics(),302);
            		}


            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        window.setFocusable(true);
        window.requestFocusInWindow();



	}


    /**
     * set up game frame
     */
	private static final long serialVersionUID = 1L;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		//draw background
		ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\bg.jpg",null);
		Image before = icon.getImage();
		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		newImage = newIcon.getImage();
		g.drawImage(newImage, 0, 0, null);


		Line2D linU = new Line2D.Float(0, 450, 800, 450);
        Line2D linB = new Line2D.Float(0, 500, 800, 500);
        Line2D linD = new Line2D.Float(0, 500, 800, 500);
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


        paintBars(g2);


    }

    /**
     * draw falling blocks
     * @param g
     */
    public void paintBars(Graphics g) {
        //bars

        for (int i = 0; i<this.song.notes.length;i++) {
            notes[i].y = notes[i].original_y + yOffset;
            if(notes[i].original_y+yOffset>-30)
            {   g.setColor(Color.YELLOW);
                //update y
                //notes[i].y=notes[i].y+yOffset;
                //boolean hit = notes[i].hit(k);
                //if (hit==true){
                //    song.score+=1;
                //    System.out.println("hit..");
               // }
                //update UI
                g.fillRect(notes[i].x, notes[i].y, 95, 30);
                g.setColor(new Color(0,0,0, (float) 0.4));
                g.drawRect(notes[i].x, notes[i].y, 95, 30);
            }


        }


    }

    /**
     * Highlight the area of keys when pressed and sound feedback
     * @param g
     * @param x
     */
    public void highlight(Graphics g, int x) {
        Clip clip_button;
        g.setColor(new Color(1,0,1,(float)0.4));
        g.fillRect(x, 450, 95, 50);
        //click sound feedback
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
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Back")) {
            //AudioPlayer.player.stop(BGM);
            clip.stop();
            this.setVisible(false);
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
            clip.start();
        }
    }

    /**
     * render background music
     */
    public void music()
    {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream;
            //-select song to play
            if(songName.equals("yumetourou")) {
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

    /**
     * find if there are blocks inside the target area
     * @param i
     */
    public void findLabel(int i) {
        for (int j = 0; j<notes.length; j++ ) {
           // Rectangle r = bars[j].getBounds();
            //perfect
            if(notes[j].x==i & notes[j].y>430 & notes[j].y<450 & notes[j].guo==0) {
                //bars[j].setVisible(false);
                notes[j].guo=1;
                song.score+=500;
                scoreContent.setText("Score: "+Integer.toString(song.score));
                perfectCount+=1;
                if(j!=0){
                    if( notes[j-1].guo==1) {
                        combo += 1;
                    }
                    else {combo=0;}
                }
                else if(j==0){
                    combo=1;
                }
                co.setText(String.valueOf(combo));
                //----
                showAccuracy("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\perfect.png");
                //System.out.println(k);
                return;
            }
            //normal
            else if(notes[j].x==i & notes[j].y>=450 & notes[j].y<500 & notes[j].guo==0){
                notes[j].guo=1;
                song.score+=200;
                scoreContent.setText("Score: "+Integer.toString(song.score));
                goodCount+=1;
                if(j!=0){
                    if( notes[j-1].guo==1) {
                        combo += 1;
                    }
                    else {combo=0;}
                }
                else if(j==0){
                    combo=1;
                }
                co.setText(String.valueOf(combo));
                //
                showAccuracy("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\Good1.png");
                //System.out.println(k);
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