package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

/**
 * @author szheng20
 */

public class GUI extends JFrame{
    //public JFrame window;
    //public JPanel myPanel;
    public JPanel cardPanel;
    public CardLayout cardLayout = new CardLayout();
    public JPanel profile = new Profile();
    public JPanel songSelection = new SongSelection();
    public GUI(){
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);

        getContentPane().add(cardPanel,BorderLayout.SOUTH);
        setSize(800,600);

        JButton newGame =  new JButton("New Game");
        JButton profileButton =new JButton("Profile");
        
        
        
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
        
        
        
        JPanel buttonPanel=new JPanel();
        buttonPanel.add(newGame);
        buttonPanel.add(profileButton);
        
        cardPanel.add(buttonPanel);
        cardPanel.add(songSelection, "New Game");
        cardPanel.add(profile,"Profile");
        
        
    }

    /**
     * set up game frame
     * @param g
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
      
        Line2D linU = new Line2D.Float(0, 450, 800, 450);
        Line2D linB = new Line2D.Float(0, 500, 800, 500);
        Line2D linD = new Line2D.Float(0, 500, 800, 500);
        Line2D lin1 = new Line2D.Float(200, 0, 200, 500);
        Line2D lin2 = new Line2D.Float(300, 0, 300, 500);
        Line2D lin3 = new Line2D.Float(400, 0, 400, 500);
        Line2D lin4 = new Line2D.Float(500, 0, 500, 500);
        Line2D lin5 = new Line2D.Float(600, 0, 600, 500);
        //thickness and color
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.BLACK);
        g2.draw(linU);
        g2.draw(linB);
        g2.draw(lin1);
        g2.draw(lin2);
        g2.draw(lin3);
        g2.draw(lin4);
        g2.draw(lin5);
        //
        g2.drawString("D", 250, 475);
        g2.drawString("F", 350, 475);
        g2.drawString("J", 450, 475);
        g2.drawString("K", 550, 475);
        //g2.drawImage(image, 0, 0, this);
        //System.out.println(image);
        //this.panel.add(g2);
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

    //@Override
    //public void actionPerformed(ActionEvent e) {
        //cardLayout.show(panel,"Profile");
        //JOptionPane.showMessageDialog(null, "This is the simple message dialog box.", "Roseindia.net", 1);
    //}
}