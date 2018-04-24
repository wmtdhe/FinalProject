package rhythmGame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Profile extends JPanel implements ActionListener{
    public JLabel label;
    public JPanel trophies;
    public JPanel scores;
    public String[] fakescores = new String[5];
    public String[] fakesongs = new String[5];
    public int[] plays = new int[5];
    public JPanel []tro = new JPanel[4];
    //public String name;
    public Profile() {
        setLayout(null);
        JPanel avatar = new JPanel();
        JPanel uploadbut = new JPanel();
        trophies = new JPanel();
        scores = new JPanel();
        /**************
         * For static testing
         **************/
        fakescores[0]="1250";
        fakescores[1]="1250";
        fakescores[2]="13250";
        fakescores[3]="11250";
        fakescores[4]="90550";
        fakesongs[0]="saad";
        fakesongs[1]="as2";
        fakesongs[2]="sfr";
        fakesongs[3]="sfv3";
        fakesongs[4]="womeitiandouhene";
        plays[0]=1;
        plays[1]=2;
        plays[2]=3;
        plays[3]=4;
        plays[4]=8;

        //---

        avatar.setBounds(5,30,150,150);
        uploadbut.setBounds(20,200,115,30);
        trophies.setBounds(350,30,400,600);
        scores.setBounds(20,250,200,300);
        
        //added back option
        JPanel backBut = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(this);
        backBut.setBounds(150, 200, 115, 30);
        backBut.add(back);
        backBut.setBackground(new Color(0,0,0,0));
        


        label = new JLabel();
        label.setBounds(avatar.getX(),avatar.getY(),avatar.getWidth(),avatar.getHeight());
        //default avatar
        ImageIcon newicon = ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\avatar.png");//new ImageIcon(resizedImage);
        label.setIcon(newicon);
        avatar.add(label);
        avatar.setBackground(new Color(0,0,0,0));
        //upload button
        JButton upload = new JButton("Upload Avatar");
        upload.addActionListener(this);
        uploadbut.add(upload);
        uploadbut.setBackground(new Color(0,0,0,0));
        //trophies
        SetTBoard(trophies);
        //scores
        SetSBoard(scores);

        add(avatar);
        add(uploadbut);
        add(trophies);
        add(scores);
        add(backBut);
        setBackground(Color.orange);
}

    /**
     * Select images in local drive to change avatar
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	JButton b = (JButton)e.getSource();
        if(b.getText().equals("Back")) {
            this.setVisible(false);
        }
        else {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile=file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            label.setIcon(ResizedIcon(path));
        }
        else if (result==JFileChooser.CANCEL_OPTION){
            System.out.println("no file selected");
        }
        }
    }

    /**
     *  resize image to fit jpanel
     * @param path
     * @return resized imageicon
     */
    public ImageIcon ResizedIcon(String path){
        ImageIcon icon = new ImageIcon(path, null);
        Image before = icon.getImage();
        Image resizedImage = before.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(resizedImage);
        return newicon;
    }

    /**
     * load achievements images
     * locked grey images
     * @param trophies
     */
    public void SetTBoard(JPanel trophies){
        trophies.setLayout(new GridLayout(2,2));
        ImageIcon []icon = new ImageIcon[4];//("C:\\Users\\˼ң\\IdeaProjects\\FinalProject\\src\\rhythmGame\\once.jpg");
        icon[0]=ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\one.jpg"); // 1
        icon[1]=ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\10.png");
        icon[2]=ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\10000.jpg");
        icon[3]=ResizedIcon("C:\\Users\\思遥\\IdeaProjects\\FinalProject\\src\\rhythmGame\\10k.jpg");
        Image []normalImage = new Image[4];//icon.getImage();
        JProgressBar []progresses = new JProgressBar[4];
        //JPanel []tro = new JPanel[4];
        JLabel []imgicon = new JLabel[4];
        for(int i=0;i<4;i++){
            normalImage[i]=icon[i].getImage();
            normalImage[i]=GrayFilter.createDisabledImage(normalImage[i]);
            tro[i]=new JPanel();
            imgicon[i]=new JLabel(new ImageIcon(normalImage[i]));
            tro[i].add(imgicon[i]);
            //tro[i].add(new JLabel(""));
            tro[i].setBackground(new Color(0,0,0,0));
            trophies.add(tro[i]);
        }
        tro[0].add(new JLabel("Play the game once"));
        tro[1].add(new JLabel("Play the game 10 times"));
        tro[2].add(new JLabel("Reach 10k"));
        tro[3].add(new JLabel("Reach 100k"));
        for(int j=0;j<4;j++){
            progresses[j]=new JProgressBar();
            progresses[j].setStringPainted(true);
            tro[j].add(progresses[j]);
        }
        progresses[0].setString("0/1");
        progresses[1].setString("0/10");
        progresses[2].setString("0/10000");
        progresses[3].setString("0/100000");
        trophies.setBackground(new Color(0,0,0,0));

        updateAchievement(progresses,icon,imgicon);

    }

    /**
     * List players' highest scores
     * @param scores
     */
    public void SetSBoard(JPanel scores){
        scores.setLayout(new GridLayout(6,1));
        scores.add(new JLabel("Highest Scores"));
        for(int i=0;i<fakesongs.length;i++){
            scores.add(new JLabel(fakesongs[i]+": "+fakescores[i]));
        }
        //scores.setBackground(Color.black);
        scores.setBackground(new Color(0,1,0,0));
    }
    /**
     * update achievements progress
     * color icons if requirements are satisfied
     */
    public void updateAchievement(JProgressBar []progressBars, ImageIcon []icons, JLabel[] imgicon){
        //convert string into int
        int[] intscores = new int[fakescores.length];
        for(int i=0;i<intscores.length;i++){
            intscores[i]=Integer.parseInt(fakescores[i]);
        }
        int played = getMax(plays);
        int highscore = getMax(intscores);

        for(int j=0;j<4;j++){
            // 1
            if(j==0){
                int percent = played/1*100;
                int progressed = percent+progressBars[0].getValue();
                if(percent>progressBars[0].getMaximum()){
                    percent = progressBars[0].getMaximum();
                }
                progressBars[0].setValue(progressed);
                if(played>1){
                    progressBars[0].setString("1/1");
                }
                else{
                    progressBars[0].setString("0/1");
                }
            }
            // 10
            if(j==1){
                float percent = (float)(played/10.0*100.0);
                float progressed = percent+progressBars[1].getValue();
                if(percent>progressBars[1].getMaximum()){
                    percent = progressBars[1].getMaximum();
                }
                progressBars[1].setValue((int)progressed);
                if(played>10){
                    progressBars[1].setString("10/10");
                }
                else{
                    progressBars[1].setString(played+"/10");
                }
            }
            // score 10,000

            if(j==2){
                float percent = (float)(highscore/10000.0*100.0);
                float progressed = percent+progressBars[2].getValue();
                if(percent>progressBars[2].getMaximum()){
                    percent = progressBars[2].getMaximum();
                }
                progressBars[2].setValue((int)(progressed));
                if(highscore>10000){
                    progressBars[2].setString("10000/10000");
                }
                else{
                    progressBars[2].setString(highscore+"/10000");
                }
            }
            // 100,000
            if(j==3){
                float percent = (float)(((float)(highscore/100000.0))*100.0);
                float progressed = percent+progressBars[3].getValue();
                //System.out.println((float)highscore/100000);
                //System.out.println(percent);
                if(percent>progressBars[3].getMaximum()){
                    percent = progressBars[3].getMaximum();
                }
                progressBars[3].setValue((int)(progressed));
                if(highscore>100000){
                    progressBars[3].setString("100000/100000");
                }
                else{
                progressBars[3].setString(highscore+"/100000");
                }
            }
        }
        //----------
        for(int k=0;k<4;k++){
            if(progressBars[k].getValue()==progressBars[k].getMaximum()){
                imgicon[k].setIcon(icons[k]);
            }
        }
    }

    /**
     * get the largest integer
     * @param array
     * @return largest integer
     */
    public int getMax(int[] array){
        int max = array[0];
        for(int i=1;i<array.length;i++){
            if(max<array[i]){
                max=array[i];
            }
        }
        return max;
    }
}