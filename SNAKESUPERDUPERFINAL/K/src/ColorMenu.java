import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

    
public class ColorMenu extends JFrame{

    // Jlist
    JList colorList;
    // Color arrays
    
    private String[] colorNameArray = {"PURPLE", "BLUE", "PINK", "YELLOW"};
    private Color[] colorClassArray = {new Color(120, 94, 143), new Color(110,144,186), 
                                        new Color(221, 110, 129), new Color(214, 170, 118) };
    
    ColorMenu(){

        
        JLabel cm= new JLabel();
        ImageIcon icon= new ImageIcon("selectcolor_final.gif");
        cm.setIcon(icon);
        this.add(cm);
        this.setResizable(false);

       

        // Jlist

        colorList = new JList(colorNameArray);
        // Restricts to one selection
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorList.setFont(new Font("Courier New", Font.BOLD, 25));
        colorList.setBackground(Color.WHITE);
       // colorList.setBackground(new Color(110,144,186));
        colorList.setBounds(490, 225, 130, 140);

       

        cm.add(colorList);

        bgColorEventHandler bgColorHandler= new bgColorEventHandler();
        colorList.addListSelectionListener(bgColorHandler);

        this.setLayout(new FlowLayout());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1100,650);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        // set background color of frame
        this.getContentPane().setBackground(new Color(237,241,214));
        // lock frame
        this.setResizable(false);
    }
    private class bgColorEventHandler implements ListSelectionListener{

        public void valueChanged(ListSelectionEvent event) {
            Board gp= new Board();

            gp.gameBGColor= colorClassArray[colorList.getSelectedIndex()];
            
            new PlayerMenu();
        

            dispose();

    }
    
}
}