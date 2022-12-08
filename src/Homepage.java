import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Homepage extends JFrame {
    Homepage(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
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
        logoutButton = new JButton("LOGOUT");
        logoutButton.setBounds(0,0,119,90);
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
        homeButton = new JButton("HOME");
        homeButton.setBounds(0,720,242,90);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setBorderPainted(false);
        addButtonMouseAdapter(homeButton);
        homeButton.addActionListener(
                e -> {
                    this.dispose();
                    Homepage homepage = new Homepage();
                }

        );
        panel.add(homeButton);

        JButton teamsButton;
        teamsButton = new JButton("TEAMS");
        teamsButton.setBounds(242,720,243,90);
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
        matchesButton = new JButton("MATCHES");
        matchesButton.setBounds(485,720,242,90);
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
        groupsButton = new JButton("GROUPS");
        groupsButton.setBounds(727,720,243,90);
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
        knockoutButton = new JButton("KNOCKOUT");
        knockoutButton.setBounds(970,720,242,90);
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
        accountButton = new JButton("MY ACCOUNT");
        accountButton.setBounds(1065,0,119,90);
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
