import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons extends JFrame {
    public static JButton[] getButtons(JFrame frame) {
        JButton[] buttons = new JButton[7];
        ImageIcon icon;


        JButton logoutButton = new JButton("LOGOUT");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        logoutButton.setBounds(0, 0, 119, 90);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(
                e -> {
                    frame.dispose();
                    Login login = new Login();
                }

        );
        buttons[0] = logoutButton;


        icon = new ImageIcon("img/universalButtons/home.png");
        JButton homeButton = new JButton("HOME", icon);
        homeButton.setFont(new Font("Arial", Font.BOLD, 15));
        homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setBounds(0, 720, 236, 90);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setBorderPainted(false);
        addButtonMouseAdapter(homeButton);
        homeButton.addActionListener(
                e -> {
                    frame.dispose();
                    Homepage homepage = new Homepage();
                }
        );
        buttons[1] = homeButton;


        icon = new ImageIcon("img/universalButtons/teams.png");
        JButton teamsButton = new JButton("TEAMS", icon);
        teamsButton.setFont(new Font("Arial", Font.BOLD, 15));
        teamsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        teamsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teamsButton.setBounds(236, 720, 237, 90);
        teamsButton.setForeground(Color.WHITE);
        teamsButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        teamsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teamsButton.setBorderPainted(false);
        addButtonMouseAdapter(teamsButton);
//        homeButton.addActionListener(
//                e -> {
//                    frame.dispose();
//                    Login login = new Login();
//                }
//
//        );
        buttons[2] = teamsButton;


        icon = new ImageIcon("img/universalButtons/matches.png");
        JButton matchesButton = new JButton("MATCHES", icon);
        matchesButton.setFont(new Font("Arial", Font.BOLD, 15));
        matchesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        matchesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        matchesButton.setBounds(473, 720, 236, 90);
        matchesButton.setForeground(Color.WHITE);
        matchesButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        matchesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        matchesButton.setBorderPainted(false);
        addButtonMouseAdapter(matchesButton);
//        matchesButton.addActionListener(
//                e -> {
//                    frame.dispose();
//                    Login login = new Login();
//                }
//
//        );
        buttons[3] = matchesButton;


        icon = new ImageIcon("img/universalButtons/groups.png");
        JButton groupsButton = new JButton("GROUPS", icon);
        groupsButton.setFont(new Font("Arial", Font.BOLD, 15));
        groupsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        groupsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        groupsButton.setBounds(709, 720, 237, 90);
        groupsButton.setForeground(Color.WHITE);
        groupsButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        groupsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        groupsButton.setBorderPainted(false);
        addButtonMouseAdapter(groupsButton);
//        groupsButton.addActionListener(
//                e -> {
//                    frame.dispose();
//                    Login login = new Login();
//                }
//
//        );
        buttons[4] = groupsButton;


        icon = new ImageIcon("img/universalButtons/knockout.png");
        JButton knockoutButton = new JButton("KNOCKOUT", icon);
        knockoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        knockoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        knockoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        knockoutButton.setBounds(946, 720, 236, 90);
        knockoutButton.setForeground(Color.WHITE);
        knockoutButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        knockoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        knockoutButton.setBorderPainted(false);
        addButtonMouseAdapter(knockoutButton);
//        knockoutButton.addActionListener(
//                e -> {
//                    frame.dispose();
//                    Login login = new Login();
//                }
//
//        );
        //panel.add(knockoutButton);
        buttons[5] = knockoutButton;


        icon = new ImageIcon("img/universalButtons/account.png");
        JButton accountButton = new JButton("ACCOUNT", icon);
        accountButton.setFont(new Font("Arial", Font.BOLD, 15));
        accountButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        accountButton.setHorizontalTextPosition(SwingConstants.CENTER);
        accountButton.setBounds(1065, 0, 119, 90);
        accountButton.setForeground(Color.WHITE);
        accountButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        accountButton.addActionListener(
//                e -> {
//                    frame.dispose();
//                    Login login = new Login();
//                }
//
//        );
        buttons[6] = accountButton;


        return buttons;
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
