import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame{
    Homepage(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("2022_FIFA_World_Cup.png");
        this.setIconImage(img.getImage());
        this.setVisible(true);

        JLabel title = new JLabel("Welcome!");
        title.setBounds(290,50,140,20);
        title.setFont(new Font("SansSerif Bold", Font.BOLD, 21));
        title.setForeground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        panel.add(title);

        JButton logoutButton;
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(10,10,93,28);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(
                e -> {
                    this.dispose();
                    Login login = new Login();
                }

        );
        panel.add(logoutButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
    }
}