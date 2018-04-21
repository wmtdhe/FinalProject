package rhythmGame;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainGameScreen extends JPanel implements ActionListener{
	public JFrame window;
	private int posY = 0;
	private int speed;
	
	public MainGameScreen(JFrame window, String level) {
		this.setLayout(null);
		JPanel backBut = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(this);
        backBut.setBounds(0, 0, 115, 30);
        backBut.add(back);
        backBut.setBackground(new Color(0,0,0,0));
        
        add(backBut);
        
        //adjust speed
        if(level == "Easy")speed = 4;
        if(level == "Normal")speed = 8;
        if(level == "Expert")speed = 12;
        
        
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                try {  
                    while(true){ 
                    	//Bars goes downwards
                        posY = posY + speed;  
                        repaint();  
                        Thread.sleep(50);
                        
                    }  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }).start();  
        
        setVisible(true);  
      
        
        
        
	}
	
	
	
	private static final long serialVersionUID = 1L; 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;
		//draw background
		ImageIcon icon = new ImageIcon("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\FinalProject-master\\src\\rhythmGame\\bg.jpg",null);
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
        
        
        
        
        
        //bars
        g3.setColor(Color.YELLOW);
        g3.fillRect(202, posY, 95, 30);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	 JButton b = (JButton)e.getSource();
         if(b.getText().equals("Back")) {
             this.setVisible(false);
         }
         else{
       
         }
    }

	
}
