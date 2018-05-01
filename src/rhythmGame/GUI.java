package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;  

/**
 * @author szheng20, lechang3
 */

public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//using a cardlayout
    public JPanel cardPanel;
    public CardLayout cardLayout = new CardLayout();
    
    //cards
    public JPanel profile;
    public JPanel songSelection = new SongSelection(this);
    public JPanel credit = new Credit(); 
    public JPanel settings = new Settings(this);
    
    public String name;
    public Game game;
    
    
    public GUI(){
    	
    	name = JOptionPane.showInputDialog("Please tell me your name");
    	game = new Game(name);
    	profile = new Profile(game.player);
    	
    	cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);

        getContentPane().add(cardPanel,BorderLayout.SOUTH);
        setSize(800,600);
        
        
        //set icons for buttons
        ImageIcon newgame_b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_new-game.png",null);
        ImageIcon profile_b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_profile.png",null);
        ImageIcon credit_b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_credit.png",null);
        ImageIcon settings_b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_settings.png",null);
        ImageIcon exit_b = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\button_exit.png",null);

        //make start menu buttons
        JButton newGame =  new JButton(newgame_b);
        JButton profileButton =new JButton(profile_b);
        JButton creditButton = new JButton(credit_b);
        JButton settingsButton = new JButton(settings_b);
        JButton Exit = new JButton(exit_b);
        
        setButtonStyle(newGame);
        setButtonStyle(profileButton);
        setButtonStyle(creditButton);
        setButtonStyle(settingsButton);
        setButtonStyle(Exit);
        
        
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
            	//credit is runnable so we need to reset it everytime
            	credit = new Credit();
            	cardPanel.add(credit,"Credit");
                cardLayout.show(cardPanel,"Credit");
                
            }
        });
        
        settingsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Settings");
            }
        });
        
        //exit game
        Exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(name!=null && name!="")game.storePlayerData();
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
        		ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\img_and_audio\\bg_1.jpg",null);
        		Image before = icon.getImage();
        		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        		ImageIcon newIcon = new ImageIcon(newImage);
        		newImage = newIcon.getImage();
        		g.drawImage(newImage, 0, 0, null);
       	    
       	    
        		//draw title
        		g.setColor(Color.WHITE);
        		Font font = new Font("Monospaced", Font.ITALIC + Font.BOLD, 50);
        		g.setFont(font);
        		g.drawString("Rhythm Game!", 250, 150);
       	}
        };
        menu.add(newGame,gbc);
        menu.add(profileButton,gbc);
        menu.add(creditButton,gbc);
        menu.add(settingsButton,gbc);
        menu.add(Exit,gbc);
        cardPanel.add(menu, "Start Menu");
        cardPanel.add(songSelection, "New Game");
        cardPanel.add(profile,"Profile");
        cardPanel.add(credit,"Credit");
        cardPanel.add(settings,"Settings");
        cardPanel.setBackground(new Color(0,0,0,0));
        
        this.add(cardPanel,BorderLayout.CENTER);
        this.setResizable(false);
        
        
    }



    //customize buttons
	public void setButtonStyle(JButton button) {
		button.setBackground(new Color(0,0,0,0));
        button.setBorder(null);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setOpaque(false);
	}

   
   
    public static void main(String []args){
        GUI s=new GUI();
        s.setTitle("Game");
        s.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        s.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(s.name!=null && s.name!="")s.game.storePlayerData();
                    System.exit(0);
                
            }
        });
        
        s.setVisible(true);
    }

   
}