import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons {
    public static JButton[] getButtons(JFrame frame) {
        JButton[] buttons = new JButton[7];
        ImageIcon icon;
        Font font = new Font("Century Gothic", Font.BOLD, 15);

        icon = new ImageIcon("img/buttons/logout.png");
        JButton logoutButton = new JButton("LOGOUT", icon);
        logoutButton.setFont(font);
        logoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        logoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        logoutButton.setBounds(0, 0, 119, 90);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setBorderPainted(false);
        addButtonMouseAdapter(logoutButton);
        logoutButton.addActionListener(
                e -> {
                    frame.dispose();
                    Login login = new Login();
                }

        );
        buttons[0] = logoutButton;


        icon = new ImageIcon("img/buttons/home.png");
        JButton homeButton = new JButton("HOME", icon);
        homeButton.setFont(font);
        homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setBounds(0, 720, 236, 90);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
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


        icon = new ImageIcon("img/buttons/teams.png");
        JButton teamsButton = new JButton("TEAMS", icon);
        teamsButton.setFont(font);
        teamsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        teamsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teamsButton.setBounds(236, 720, 237, 90);
        teamsButton.setForeground(Color.WHITE);
        teamsButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
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


        icon = new ImageIcon("img/buttons/matches.png");
        JButton matchesButton = new JButton("MATCHES", icon);
        matchesButton.setFont(font);
        matchesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        matchesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        matchesButton.setBounds(473, 720, 236, 90);
        matchesButton.setForeground(Color.WHITE);
        matchesButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
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


        icon = new ImageIcon("img/buttons/groups.png");
        JButton groupsButton = new JButton("GROUPS", icon);
        groupsButton.setFont(font);
        groupsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        groupsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        groupsButton.setBounds(709, 720, 237, 90);
        groupsButton.setForeground(Color.WHITE);
        groupsButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
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


        icon = new ImageIcon("img/buttons/knockout.png");
        JButton knockoutButton = new JButton("KNOCKOUT", icon);
        knockoutButton.setFont(font);
        knockoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        knockoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        knockoutButton.setBounds(946, 720, 236, 90);
        knockoutButton.setForeground(Color.WHITE);
        knockoutButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
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


        icon = new ImageIcon("img/buttons/account.png");
        JButton accountButton = new JButton("ACCOUNT", icon);
        accountButton.setFont(font);
        accountButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        accountButton.setHorizontalTextPosition(SwingConstants.CENTER);
        accountButton.setBounds(1065, 0, 119, 90);
        accountButton.setForeground(Color.WHITE);
        accountButton.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));
        accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accountButton.setBorderPainted(false);
        addButtonMouseAdapter(accountButton);
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
                button.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));
            }
        });

    }


}
