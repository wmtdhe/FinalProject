import javax.swing.*;
import java.awt.*;

public class GUI {
    public JFrame window;
    public JPanel myPanel;
    public GUI(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Rhythm Game");
        window.setSize(800, 600);
        //myPanel = initializePanel();
        //window.setContentPane(myPanel);
        window.getContentPane().setBackground( Color.black );
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(800,600));
        myPanel.setLayout(new BorderLayout());
        //myPanel.setLayout(new GridLayout(9, 8));
        return myPanel;
    }
    public static void main(String[] args) {
        new GUI();
    }
}
