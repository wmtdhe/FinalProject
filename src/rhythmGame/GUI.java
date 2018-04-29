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

/**
 * @author szheng20, lechang3
 */

public class GUI extends JFrame{
	
	//using a cardlayout
    public JPanel cardPanel;
    public CardLayout cardLayout = new CardLayout();
    public String name;
    //cards

    public JPanel songSelection = new SongSelection(this);
    public JPanel credit = new Credit();
    public JPanel profile;
    public  Game game;

    public Boolean gameOn = true;

    
    
    
    public GUI(){
    	
    	name = JOptionPane.showInputDialog("Please tell me your name");
    	//Player player = new Player(name);
    	game = new Game(name);
    	profile = new Profile(game.player);

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
                //profile = new Profile(name);
                cardLayout.show(cardPanel,"Profile");
            }
        });
        
        creditButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	//credit is runnable so we need to reset it everytime
            	credit = new Credit();
            	cardPanel.add(credit,"Credit");
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
        JPanel menu=new JPanel(new GridBagLayout()) {
        	private static final long serialVersionUID = 1L; 
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
       	    
       	    
        		//draw title
        		g.setColor(Color.WHITE);
        		Font font = new Font("Consolas", Font.PLAIN, 50); 
        		g.setFont(font);
        		g.drawString("Rhythm Game!", 250, 150);
       	}
        };
        menu.add(newGame,gbc);
        menu.add(profileButton,gbc);
        menu.add(creditButton,gbc);
        menu.add(Exit,gbc);
        cardPanel.add(menu, "Start Menu");
        cardPanel.add(songSelection, "New Game");
        cardPanel.add(profile,"Profile");
        cardPanel.add(credit,"Credit");
        cardPanel.setBackground(new Color(0,0,0,0));
        
        this.add(cardPanel,BorderLayout.CENTER);
        this.setResizable(false);
        
        
    }

   
   
    public static void main(String []args){
        GUI s=new GUI();
        s.setTitle("Game");
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        s.setVisible(true);
    }

   
}