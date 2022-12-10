import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame{
    Homepage() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        JButton[] buttons = Buttons.getButtons(this);
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
