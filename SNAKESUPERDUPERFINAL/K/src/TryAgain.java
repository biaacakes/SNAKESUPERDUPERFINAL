import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TryAgain extends JFrame {
    JButton soundButton;
    String clickSound;
    ButtonHandler bHandler = new ButtonHandler();
    soundEffect se = new soundEffect();

    TryAgain(){

        super("Snake Game");    
        this.setLayout(new FlowLayout());
        
        GameOverSe("wavgameover.wav");
        JLabel imageLabel = new JLabel();
        ImageIcon gameOverIcon = new ImageIcon("gameover.gif");
        imageLabel.setIcon(gameOverIcon);
        this.add(imageLabel);

         if (Board.applesEaten >= Board.highestScore) {
            Board.highestScore = Board.applesEaten;
        }

        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("" + Board.applesEaten);
        scoreLabel.setBounds(540, 340, 260, 57);
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        scoreLabel.setForeground(Color.WHITE);
        imageLabel.add(scoreLabel);

        JLabel highestScoreLabel = new JLabel();
        highestScoreLabel.setText(""+ Board.highestScore);
        highestScoreLabel.setBounds(538, 420, 260, 57);
        highestScoreLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        highestScoreLabel.setForeground(Color.WHITE);
        imageLabel.add(highestScoreLabel);

        JLabel Player = new JLabel();
        Player.setText("" + Board.playerName);
        Player.setBounds(520, 262, 260, 57);
        Player.setFont(new Font("Courier New", Font.BOLD, 30));
        Player.setForeground(Color.WHITE);
        imageLabel.add(Player);

        JButton tryAgainButton = new JButton();
        ImageIcon tryAgainB = new ImageIcon("tryagain.png");
        tryAgainButton.setIcon(tryAgainB);
        tryAgainButton.setBounds(290, 480, 260, 57 );
        //1- higher = right position, lower = left
        //2- higher = south position
        EventHandler handler= new EventHandler();
        tryAgainButton.addActionListener(handler);
        tryAgainButton.addActionListener(bHandler);
        
        JButton exitButton = new JButton();
        ImageIcon exitB = new ImageIcon("exitblue.png");
        exitButton.setIcon(exitB);
        exitButton.setBounds(560, 480, 260, 57 );
        EventHandler1 handler1= new EventHandler1();
        exitButton.addActionListener(handler1);
        exitButton.addActionListener(bHandler);
       // Add button to imageIcon
        imageLabel.add(tryAgainButton);
        imageLabel.add(exitButton);

        this.setLayout(new FlowLayout());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1100,650);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(237,241,214));
        this.setResizable(false);

        clickSound = ".//res//wavbutton.wav";
    
}
    private class EventHandler implements ActionListener{
        
        public void actionPerformed(ActionEvent event) {
            
            // open game frame
            new DifficultyMenu();
            // Close main menu frame
            dispose();
        }
    }
        private class EventHandler1 implements ActionListener{
            
            public void actionPerformed(ActionEvent event) {
                
            
                dispose();
    }
}

public static void GameOverSe(String path){
    try {
        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File("wavgameover.wav"));
        Clip clip= AudioSystem.getClip();
        clip.open(audioInputStream);
        
        clip.start();
        //clip.stop();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public class soundEffect{
    Clip clip;
    
    public void setFile(String soundFileName){
        try{
            File file = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            
        }
        catch(Exception e){

        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
}

public class ButtonHandler implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        se.setFile(clickSound);
        se.play();

    }
}

public static void RunStartSe(String path){
    try {
        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File("wavbutton.wav"));
        Clip clip= AudioSystem.getClip();
        clip.open(audioInputStream);
        
        clip.start();
        // clip.stop();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    }
