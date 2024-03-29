package awt;

import admin.AddMatch;
import admin.DeleteMatch;
import admin.UpdateMatch;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import pages.*;
import teampages.Teams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons {
    public static JButton[] getButtons(JFrame frame, String username) {
        JButton[] buttons = new JButton[8];
        ImageIcon icon;
        Font font = new Font("Century Gothic", Font.BOLD, 15);

        icon = new ImageIcon("img/buttons/logout.png");
        buttons[0] = new JButton("LOGOUT", icon);
        buttons[0].setBounds(0, 0, 119, 90);
        buttons[0].addActionListener(
                e -> {
                    frame.dispose();
                    Login.getLogin();
                }

        );


        icon = new ImageIcon("img/buttons/home.png");
        buttons[1] = new JButton("HOME", icon);
        buttons[1].setBounds(0, 720, 236, 90);
        buttons[1].addActionListener(
                e -> {
                    frame.dispose();
                    Homepage.getHomepage(username);
                }
        );


        icon = new ImageIcon("img/buttons/teams.png");
        buttons[2] = new JButton("TEAMS", icon);
        buttons[2].setBounds(236, 720, 237, 90);
        buttons[2].addActionListener(
                e -> {
                    frame.dispose();
                    Teams.getTeams(username);
                }

        );

        icon = new ImageIcon("img/buttons/matches.png");
        buttons[3] = new JButton("MATCHES", icon);
        buttons[3].setBounds(473, 720, 236, 90);
        buttons[3].addActionListener(
                e -> {
                    frame.dispose();
                    Matches.getMatches(username);
                }

        );


        icon = new ImageIcon("img/buttons/groups.png");
        buttons[4] = new JButton("GROUPS", icon);
        buttons[4].setBounds(709, 720, 237, 90);
        buttons[4].addActionListener(
                e -> {
                    frame.dispose();
                    Groups.getGroups(username);
                }

        );


        icon = new ImageIcon("img/buttons/stats.png");
        buttons[5] = new JButton("STATS", icon);
        buttons[5].setBounds(946, 720, 236, 90);
        buttons[5].addActionListener(
                e -> {
                    frame.dispose();
                    Stats.getStats(username);
                }

        );


        icon = new ImageIcon("img/buttons/account.png");
        buttons[6] = new JButton("ACCOUNT", icon);
        buttons[6].setBounds(1065, 0, 119, 90);
        buttons[6].addActionListener(
                e -> {
                    frame.dispose();
                    Account.getAccount(username);
                }

        );

        for(int i = 0; i < 7; i++) {
            buttons[i].setFont(font);
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));
            buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons[i].setBorderPainted(false);
            addButtonMouseAdapter(buttons[i]);
        }

        buttons[7] = new JButton();
        buttons[7].setFont(new Font("Century Gothic", Font.BOLD, 40));
        buttons[7].setBounds(119, 0, 947, 90);
        buttons[7].setHorizontalTextPosition(SwingConstants.CENTER);
        buttons[7].setForeground(Color.WHITE);
        buttons[7].setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));
        buttons[7].setEnabled(false);
        buttons[7].setBorderPainted(false);

        return buttons;
    }

    public static JButton[] getAdminButtons(JFrame frame) {

        JButton[] buttons = new JButton[4];
        Font font = new Font("Century Gothic", Font.BOLD, 20);

        buttons[0] = new JButton("LOGOUT");
        buttons[0].setBounds(0, 0, 119, 90);
        buttons[0].addActionListener(
                e -> {
                    frame.dispose();
                    Login.getLogin();
                }

        );

        buttons[1] = new JButton("ADD MATCH");
        buttons[1].setBounds(119, 0, 236, 90);
        buttons[1].addActionListener(
                e -> {
                    frame.dispose();
                    AddMatch.addMatch();
                }

        );

        buttons[2] = new JButton("UPDATE MATCH");
        buttons[2].setBounds(355, 0, 236, 90);
        buttons[2].addActionListener(
                e -> {
                    frame.dispose();
                    UpdateMatch.updateMatch();
                }

        );

        buttons[3] = new JButton("DELETE MATCH");
        buttons[3].setBounds(591, 0, 236, 90);
        buttons[3].addActionListener(
                e -> {
                    frame.dispose();
                    DeleteMatch.deleteMatch();
                }

        );

        for(int i = 0; i < 4; i++) {
            buttons[i].setFont(font);
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));
            buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons[i].setBorderPainted(false);
            addButtonMouseAdapter(buttons[i]);
        }


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
