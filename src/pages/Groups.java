package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;

public class Groups {

    public static void getGroups(String username) {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        Font font = new Font("Century Gothic", Font.BOLD, 20);

        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("GROUPS");

        JLabel groupLabel = new JLabel("Select a group:");
        groupLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        groupLabel.setBounds(30, 92, 180, 100);
        panel.add(groupLabel);

        String[] optionsToChoose = {"Group A", "Group B", "Group C", "Group D", "Group E", "Group F", "Group G", "Group H"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        comboBox.setBounds(200, 122, 130, 40);
        panel.add(comboBox);

        JButton doneButton = new JButton("SELECT");
        doneButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doneButton.setBounds(350, 122, 130, 40);
        doneButton.setForeground(Color.WHITE);
        doneButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        doneButton.addActionListener(
                e -> {

                }
        );
        panel.add(doneButton);

        JTable table;
        String[][] data ={ {"1","Portugal","3", "2", "1", "0", "6"},
                {"2","South Korea","3", "1", "1", "1", "4"},
                {"3","Uruguay","3", "1", "1", "1", "4"},
                {"4","Ghana","3", "1", "0", "2", "0"} };
        String[] column ={"POS", "TEAM", "PLAYED", "WINS", "DRAWS", "LOSSES", "POINTS"};
        table = new JTable(data, column);
        table.setBounds(100,150,200,300);
        table.getTableHeader().setFont(font);
        table.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 182, 1102, 530);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

    }

}
