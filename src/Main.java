import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new GamePanel());
        frame.setTitle("Snake-Game");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
        frame.setLocationRelativeTo(null);  
    } 
}     
        
