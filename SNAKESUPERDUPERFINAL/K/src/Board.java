import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Board extends JPanel implements ActionListener{
    
    public final int B_WIDTH = 1100;
    public final int B_HEIGHT = 650;
    private final int DOT_SIZE = 30;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 19;

    // Background color property
    static Color gameBGColor;

    // Game difficulty
    static int DELAY;
    static String playerName;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    static int highestScore;
    public int foodsEaten;
    public static int applesEaten;
    static String fruitImage;

    public Board() {
    
       initBoard();
        
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(gameBGColor);
        setFocusable(true);
        applesEaten = 0;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
        
    }
    
    
    private void loadImages() {

        ImageIcon iid = new ImageIcon("round1.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(fruitImage);
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("head1.png");
        head = iih.getImage();


    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 60 - z * 30;
            y[z] = 60;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    public void doDrawing(Graphics g) {
        
        if (inGame) {
             
            g.drawImage(apple, apple_x, apple_y, this);
        
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

            // Score
            g.setColor(new Color(237,241,214));
            g.setFont(new Font("Cambria", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());

            g.drawString("Score: " + applesEaten, (B_WIDTH - metrics.stringWidth("Score: " + B_HEIGHT)) / 2,
                    g.getFont().getSize());

            g.drawString("Player: " + playerName, 0,
                    g.getFont().getSize());

                    
                    

        } else {

            gameOver(g);
        }        
    }

    public void gameOver(Graphics g) {
        //highest score
        if (foodsEaten >= highestScore) {
            highestScore = foodsEaten;
        }

        String hs = "Highest Score: ";
        

        Font hsf = new Font("Arial", Font.PLAIN, 100);
        FontMetrics metr1 = getFontMetrics(hsf);
        

        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());

        g.drawString("Score: " + applesEaten, (B_WIDTH - metrics.stringWidth("Score: " + B_HEIGHT)) / 2,
                g.getFont().getSize());
        g.drawString(hs + highestScore, metr1.stringWidth(hs)+100, 700);

        // String msg = "VOVO KA BEH ULIT SNAKE NALANG YAN";
        // Font small = new Font("Cooper Black", Font.BOLD, 44);
        // FontMetrics metr = getFontMetrics(small);
        

        // g.setColor(Color.pink);
        // g.setFont(small);
        // g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 3);
        

        JFrame parentFrame = (JFrame) this.getTopLevelAncestor();
        g.drawString("Score: " + applesEaten, (B_WIDTH - metrics.stringWidth("Score: " + B_HEIGHT)) / 2,
                g.getFont().getSize());
        g.drawString(hs + highestScore, metr1.stringWidth(hs)+100, 700);
        parentFrame.dispose();
        // Open Try again frame
        new TryAgain();
        
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            applesEaten++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 3) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();

           
        }
    }

    
        private void locateApple() {

            int r = (int)(Math.random() * 30) + 0;
            apple_x = ((r * 30));
    
            r = (int)(Math.random() * 15) + 0;
            apple_y = ((r * 30) + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}