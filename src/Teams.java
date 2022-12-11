import javax.swing.*;
import java.awt.*;

public class Teams extends JFrame {
    Teams() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

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
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("TEAMS");

    }

}
