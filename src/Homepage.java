import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame{
    Homepage() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.setTitle("World Cup Qatar 2022");
        frame.setSize(1200, 857);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 7; i++) {
            panel.add(buttons[i]);
        }

        JLabel title = new JLabel("HOME");
        title.setBounds(290, 30, 136, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        panel.add(title);
    }
}
