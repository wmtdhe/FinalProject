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
    private int speed;
    //public AudioStream BGM;
    public Clip clip;
    public Song song;
    public Notes[] notes = new Notes[0];
    public String songName;
    //public JPanel game;
    public class keyList extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_J){
                System.out.println("J");
            }
        }
        public void keyReleased(KeyEvent e){

        }
    }

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

        //load mapping
        this.song = new Song(notes,songName);
        this.song.mapFirstSong();
        notes = this.song.notes;

        add(backBut);

        //adjust speed
        if(level == "Easy")speed = 2;
        if(level == "Normal")speed = 3;
        if(level == "Expert")speed = 4;

        addKeyListener(new keyList());
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
                    while(true){
                        //Bars goes downwards
                        //if((System.nanoTime()-start)>=20000000){
                        //start = System.nanoTime();
                        yOffset = yOffset + speed;
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
        this.window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {


                if(e.getKeyCode() == KeyEvent.VK_J){
                    System.out.println("J");
                }
                if(e.getKeyCode() == KeyEvent.VK_K){
                    System.out.println("K");
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    System.out.println("D");
                }
                if(e.getKeyCode() == KeyEvent.VK_F){
                    System.out.println("F");
                }


            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });




    }



    private static final long serialVersionUID = 1L;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //draw background
        ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\bg.jpg",null);
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

    public void paintBars(Graphics g) {
        //bars
        g.setColor(Color.YELLOW);
        for (int i = 0; i<this.song.notes.length;i++) {
            if(notes[i].y+yOffset>-30)g.fillRect(notes[i].x, notes[i].y+yOffset, 95, 30);
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
        else{

        }
    }


    public void music()
    {
        /*
        AudioPlayer MGP = AudioPlayer.player;
        //AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\rhythmGame\\FREELY_TOMORROW.wav");
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
        MGP.start();
        */
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream;
            //-select song to play
            if(songName.equals("orange")) {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\Orange.wav"));
            }
            else {
                inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\p80_FREELY TOMORROW feat. 初音ミク.wav"));
            }
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }


}