import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Homepage extends JFrame {
    Homepage(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("img/logo.png");
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        JLabel title = new JLabel("Welcome!");
        title.setBounds(290,50,136,20);
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

        JButton homeButton;
        homeButton = new JButton("Home");
        homeButton.setBounds(0,400,137,53);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setBorderPainted(false);
        addButtonMouseAdapter(homeButton);
//        homeButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(homeButton);

        JButton teamsButton;
        teamsButton = new JButton("Teams");
        teamsButton.setBounds(137,400,136,53);
        teamsButton.setForeground(Color.WHITE);
        teamsButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        teamsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teamsButton.setBorderPainted(false);
        addButtonMouseAdapter(teamsButton);
//        homeButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(teamsButton);

        JButton matchesButton;
        matchesButton = new JButton("Matches");
        matchesButton.setBounds(273,400,136,53);
        matchesButton.setForeground(Color.WHITE);
        matchesButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        matchesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        matchesButton.setBorderPainted(false);
        addButtonMouseAdapter(matchesButton);
//        matchesButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(matchesButton);

        JButton groupsButton;
        groupsButton = new JButton("Groups");
        groupsButton.setBounds(409,400,137,53);
        groupsButton.setForeground(Color.WHITE);
        groupsButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        groupsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        groupsButton.setBorderPainted(false);
        addButtonMouseAdapter(groupsButton);
//        groupsButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(groupsButton);

        JButton knockoutButton;
        knockoutButton = new JButton("Knockout");
        knockoutButton.setBounds(546,400,136,53);
        knockoutButton.setForeground(Color.WHITE);
        knockoutButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        knockoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        knockoutButton.setBorderPainted(false);
        addButtonMouseAdapter(knockoutButton);
//        knockoutButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(knockoutButton);

        JButton accountButton;
        accountButton = new JButton("My Account");
        accountButton.setBounds(573,0,109,53);
        accountButton.setForeground(Color.WHITE);
        accountButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        accountButton.addActionListener(
//                e -> {
//                    this.dispose();
//                    Login login = new Login();
//                }
//
//        );
        panel.add(accountButton);

    }

    public static void addButtonMouseAdapter(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.getHSBColor(290,1f,0.396f));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
            }
        });
    }

}
