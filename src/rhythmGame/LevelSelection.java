package rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelection extends JPanel implements ActionListener{
    public CardLayout c;
    public JFrame lf;
    public LevelSelection(JFrame frame){
        lf=frame;
        c = new CardLayout();
        setLayout(c);
        JPanel levels = new JPanel();
        levels.setLayout(null);

        JButton easy = new JButton("Easy");
        JButton normal = new JButton("Normal");
        JButton expert = new JButton("Expert");

        levels.add(easy);
        levels.add(normal);
        levels.add(expert);
        easy.setBounds(300,50,200,100);
        normal.setBounds(300,250,200,100);
        expert.setBounds(300,450,200,100);
        easy.addActionListener(this);
        normal.addActionListener(this);
        expert.addActionListener(this);
        add(levels,"game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       c.show(this,"game");
       lf.setTitle("Game on");
    }
}
