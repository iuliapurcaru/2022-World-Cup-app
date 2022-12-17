package awt;

import javax.swing.*;

public class BuildFrame extends JFrame {
    
    public static JFrame getFrame() {

        JFrame frame = new JFrame();

        frame.setTitle("World Cup Qatar 2022");
        frame.setSize(1200, 857);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return frame;
    }
}
