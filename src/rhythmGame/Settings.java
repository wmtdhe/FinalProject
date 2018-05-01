package rhythmGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Settings extends JPanel {
	public Settings(GUI window) {

    setBackground(new Color(0,0,0,0));
    setLayout(null);
    JTextField easy = new JTextField("2",1);
    JTextField normal = new JTextField("3",1);
    JTextField expert = new JTextField("4",1);
    easy.setBounds(500, 50, 30, 30);
    normal.setBounds(500,250,30,30);
    expert.setBounds(500,450,30,30);
    JLabel eText = new JLabel("Speed for Easy Levels");
    JLabel nText = new JLabel("Speed for Hard Levels");
    JLabel exText = new JLabel("Speed for Expert Levels");
    Font font = new Font("Consolas", Font.PLAIN, 30); 
    eText.setForeground(Color.WHITE);
    nText.setForeground(Color.WHITE);
    exText.setForeground(Color.WHITE);
    eText.setFont(font);
    nText.setFont(font);
    exText.setFont(font);
    eText.setBounds(100,50,400,30);
    nText.setBounds(100,250,400,30);
    exText.setBounds(100,450,400,30);

    JButton back = new JButton();
    back.setBounds(0,0,80,50);
    ImageIcon b = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\button_back.png");
    Image before = b.getImage();
    Image resizedImage = before.getScaledInstance(back.getWidth(),back.getHeight(),Image.SCALE_SMOOTH);
    ImageIcon newicon = new ImageIcon(resizedImage);
    back.setIcon(newicon);
    back.setBackground(new Color(0,0,0,0));
    back.setBorder(null);
    back.setMargin(new Insets(0, 0, 0, 0));

    back.setOpaque(false);

    back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
        }
    });
    
    easy.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            window.game.easySpeed = Integer.parseInt(easy.getText());
        }
    });
    
    normal.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            window.game.normalSpeed = Integer.parseInt(normal.getText());
            System.out.println(normal.getText());
        }
    });
    
    expert.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            window.game.exSpeed = Integer.parseInt(expert.getText());
        }
    });
    add(back);
    add(easy);
    add(normal);
    add(expert);
    add(eText);
    add(nText);
    add(exText);
    
    setVisible(true);
   
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw background
		ImageIcon icon = new ImageIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\img_and_audio\\bg_1.jpg",null);
		Image before = icon.getImage();
		Image newImage = before.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		newImage = newIcon.getImage();
		g.drawImage(newImage, 0, 0, null);
	}

	
}
