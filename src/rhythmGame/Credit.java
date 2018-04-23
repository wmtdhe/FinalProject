package rhythmGame;
/**
 * A credit screen indicating the source we used
 * @author lechang3
 */
import javax.swing.*;
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.Font;    
import java.awt.Graphics;  
import java.awt.FontMetrics;  
import java.awt.*;
public class Credit extends JPanel {
	//strings to display
	public static String str1 = "This is the Credit Page. The resources we used";
	public static String str2 = "includes:";
	public static String str3 = "Images:";
	public static String str4 = "https://pixabay.com/en/milky-way-starry-sky-ni";
	public static String str5 = "ght-sky-star-2695569/";
	private static final long serialVersionUID = 1L; 
	
	//bottom line
	private int posY = 600;
	
	public Credit() {   
	        setPreferredSize(new Dimension(800, 600)); 
	        posY = 600;
	        new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	                try {  
	                    while(true){ 
	                    	//Text goes upwards
	                        posY = posY - 4;  
	                        repaint();  
	                        Thread.sleep(50);
	                        if(posY<=-400) {
	                        	posY = 600;
	                        	setVisible(false);
	                    		//c.show(control, "Start Menu");
	                    		break;
	                    	}
	                    }  
	                } catch (InterruptedException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }).start();  
	        
	        setVisible(true);  
	    }  
	   
	   @Override  
       public void paint(Graphics g) {
		   //set fonts
           g.setColor(Color.BLACK);  
           g.fillRect(0, 0, getWidth(), getHeight());  
           g.setColor(Color.CYAN);  
           Font font = new Font("Consolas", Font.PLAIN, 30); 
           FontMetrics size = g.getFontMetrics();  
           int strHeight = size.getHeight(); 
           g.setFont(font);  
           
           //paint strings
           g.drawString(str1, 0, posY);
           g.drawString(str2, 0, posY+strHeight+10);
           g.drawString(str3, 0, posY+strHeight*2+100);
           g.drawString(str4, 0, posY+strHeight*3+200);
           g.drawString(str5, 0, posY+strHeight*4+200);
           
       }  
       //@Override  
       public void update(Graphics g) {  
           paint(g);  
       }  
	
	   public static void main(String []args){
		   JFrame test = new JFrame();
		   Credit credit = new Credit();
		   test.add(credit);
		   test.setVisible(true);
		   test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   test.setSize(800,600);
		   
		}
	   
	   public int getPosY() {
		   return posY;
	   }
	 

}
