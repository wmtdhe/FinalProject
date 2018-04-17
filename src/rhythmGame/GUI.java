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
import java.awt.Font;    
import java.awt.FontMetrics;  

/**
 * @author szheng20, lechang3
 */

public class GUI extends JFrame{
	
	//using a cardlayout
    public JPanel cardPanel;
    public CardLayout cardLayout = new CardLayout();
    
    //cards
    public JPanel profile = new Profile();
    public JPanel songSelection = new SongSelection(this);
    public Credit credit = new Credit(this.cardLayout,this.cardPanel);
    
    
    public GUI(){
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);

        getContentPane().add(cardPanel,BorderLayout.SOUTH);
        setSize(800,600);

        //make start menu buttons
        JButton newGame =  new JButton("New Game");
        JButton profileButton =new JButton("Profile");
        JButton creditButton = new JButton("Credit");
        JButton Exit = new JButton("Exit");
        
        
        //display other cards when clicked
        newGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"New Game");
            }
        });
        
        
        profileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Profile");
            }
        });
        
        creditButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Credit");
                
            }
        });
        
        //exit game
        Exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonPanel=new JPanel(new GridBagLayout());
        buttonPanel.add(newGame,gbc);
        buttonPanel.add(profileButton,gbc);
        buttonPanel.add(creditButton,gbc);
        buttonPanel.add(Exit,gbc);
        cardPanel.add(buttonPanel, "Start Menu");
        cardPanel.add(songSelection, "New Game");
        cardPanel.add(profile,"Profile");
        cardPanel.add(credit,"Credit");
        cardPanel.setBackground(new Color(0,0,0,0));
        
        this.add(cardPanel,BorderLayout.CENTER);
        this.setResizable(false);
        
        
    }

    /**
     * set up game frame
     * @param g
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //Resize background image
        ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\rhythmGame\\bg.jpg",null);
        Image before = icon.getImage();
        Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);
        newImage = newIcon.getImage();
        
        //For rhythm game playing screen
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
        
        g2.drawString("D", 250, 475);
        g2.drawString("F", 350, 475);
        g2.drawString("J", 450, 475);
        g2.drawString("K", 550, 475);
        
        //draw background
        g2.drawImage(newImage, 0, 0, this);
        
        //draw title
        g.setColor(Color.WHITE);
        Font font = new Font("Consolas", Font.PLAIN, 50); 
        g.setFont(font);
        g.drawString("Rhythm Game!", 250, 150);
        
    }


   
    public static void main(String []args){
        GUI s=new GUI();
        s.setTitle("Game");
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.addKeyListener(new KeyListener() {
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
        s.setVisible(true);
    }

   
}