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
        ImageIcon icon = new ImageIcon("img/logo.png");
        this.setIconImage(icon.getImage());
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
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
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
        icon = new ImageIcon("img/universalButtons/home.png");
        homeButton = new JButton("HOME", icon);
        homeButton.setFont(new Font("Arial", Font.BOLD, 15));
        homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setBounds(0,720,238,90);
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
        icon = new ImageIcon("img/universalButtons/teams.png");
        teamsButton = new JButton("TEAMS", icon);
        teamsButton.setFont(new Font("Arial", Font.BOLD, 15));
        teamsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        teamsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teamsButton.setBounds(238,720,237,90);
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
        icon = new ImageIcon("img/universalButtons/matches.png");
        matchesButton = new JButton("MATCHES", icon);
        matchesButton.setFont(new Font("Arial", Font.BOLD, 15));
        matchesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        matchesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        matchesButton.setBounds(475,720,238,90);
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
        icon = new ImageIcon("img/universalButtons/groups.png");
        groupsButton = new JButton("GROUPS", icon);
        groupsButton.setFont(new Font("Arial", Font.BOLD, 15));
        groupsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        groupsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        groupsButton.setBounds(713,720,237,90);
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
        icon = new ImageIcon("img/universalButtons/knockout.png");
        knockoutButton = new JButton("KNOCKOUT", icon);
        knockoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        knockoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        knockoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        knockoutButton.setBounds(950,720,238,90);
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
        icon = new ImageIcon("img/universalButtons/account.png");
        accountButton = new JButton("ACCOUNT", icon);
        accountButton.setFont(new Font("Arial", Font.BOLD, 15));
        accountButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        accountButton.setHorizontalTextPosition(SwingConstants.CENTER);
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
