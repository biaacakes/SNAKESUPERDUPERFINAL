import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;


public class DifficultyMenu extends JFrame {
    JButton soundButton;
    String clickSound;
    ButtonHandler bHandler = new ButtonHandler();
    soundEffect se = new soundEffect();

    DifficultyMenu() {
        super("Snake Game");
        JLabel imageLabel= new JLabel();
        ImageIcon snakeImage= new ImageIcon("selectdiff_final.gif");
        imageLabel.setIcon(snakeImage);
        this.add(imageLabel);
        RunStartSe("wavbutton.wav");


        JButton easyButton = new JButton();
        ImageIcon easyB = new ImageIcon("easy.png");
        easyButton.setIcon(easyB);
        easyButton.setBounds(430, 170, 230, 49 );
        //1-left right 2-north south 3-width 4-height
        EventHandler easyHandler = new EventHandler(200, "easyfruit.png");
        easyButton.addActionListener(easyHandler);
        easyButton.addActionListener(bHandler);

        JButton normalButton = new JButton();
        ImageIcon medB = new ImageIcon ("normal.png");
        normalButton.setIcon(medB);
        normalButton.setBounds(430, 250, 230, 49 );
        EventHandler normalHandler = new EventHandler(100, "normalfruit.");
        normalButton.addActionListener(normalHandler);
        normalButton.addActionListener(bHandler);

        JButton hardButton = new JButton();
        ImageIcon hardB = new ImageIcon("hard.png");
        hardButton.setIcon(hardB);
        hardButton.setBounds(430, 330, 230, 49);
        EventHandler hardHandler = new EventHandler(50, "hardfruit.png");
        hardButton.addActionListener(hardHandler);
        hardButton.addActionListener(bHandler);

        JButton expertButton= new JButton();
        ImageIcon expertB = new ImageIcon("expert.png");
        expertButton.setIcon(expertB);
        expertButton.setBounds(430, 410, 230, 49);
        EventHandler handler3 = new EventHandler(40, "expert.png");
        expertButton.addActionListener(handler3);
        expertButton.addActionListener(bHandler);
        

        JButton godmodeButton= new JButton();
        ImageIcon godB = new ImageIcon("god.png");
        godmodeButton.setIcon(godB);
        godmodeButton.setBounds(430, 490, 230, 49);
        EventHandler handler4= new EventHandler(10, "godfruit.png");
        godmodeButton.addActionListener(handler4);
        godmodeButton.addActionListener(bHandler);

        imageLabel.add(easyButton);
        imageLabel.add(normalButton);
        imageLabel.add(hardButton);
        imageLabel.add(expertButton);
        imageLabel.add(godmodeButton);


        
        this.setLayout(new FlowLayout());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1100,650);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(237,241,214));

        clickSound = ".//res//wavbutton.wav";

    }

    private class EventHandler implements ActionListener {

        int snakeSpeed;
        String fruitName;

        EventHandler (int speed, String fruit) {
            this.snakeSpeed = speed;
            this.fruitName = fruit;
        }

        public void actionPerformed(ActionEvent event) {

            Board.fruitImage = this.fruitName;
            Board.DELAY = this.snakeSpeed;
        
            new ColorMenu();
            dispose();


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