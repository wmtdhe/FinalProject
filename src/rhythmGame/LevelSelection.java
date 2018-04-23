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

public class LevelSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame lf;
    public JPanel mainGame;
    public LevelSelection(JFrame frame){
        lf=frame;
        c = new CardLayout();
        setLayout(c);
        JPanel levels = new JPanel();
        levels.setLayout(null);

        JButton easy = new JButton("Easy");
        JButton normal = new JButton("Normal");
        JButton expert = new JButton("Expert");
        JButton back = new JButton("Back");

        levels.add(easy);
        levels.add(normal);
        levels.add(expert);
        levels.add(back);
        easy.setBounds(300,50,200,100);
        normal.setBounds(300,250,200,100);
        expert.setBounds(300,450,200,100);
        back.setBounds(0,0,80,80);
        easy.addActionListener(this);
        normal.addActionListener(this);
        expert.addActionListener(this);
        back.addActionListener(this);
        //mainGame = new MainGameScreen(lf,null);

        add(levels,"level");
        //add(mainGame,"game");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	 JButton b = (JButton)e.getSource();
         if(b.getText().equals("Back")) {
             this.setVisible(false);
         }
         else{
        	 mainGame = new MainGameScreen(lf,b.getText());
        	 add(mainGame,"game");
        	 c.show(this,"game");
        	 lf.setTitle("Game on");
         }
    }
}