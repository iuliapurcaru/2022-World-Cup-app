import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Homepage extends JFrame{
    Homepage() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.getHSBColor(0, 0, 1f));

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
        buttons[7].setText("HOME");

        JButton news = new JButton("News 1");
        news.setBounds(400, 400, 237, 90);
        news.addActionListener(
                e -> {
                    this.dispose();
                    News news2 = new News();
                }

        );
        panel.add(news);


    }

}
