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

        JTable table;
        String[][] data ={ {"1","Portugal","3", "2", "1", "0", "6"},
                {"2","South Korea","3", "1", "1", "1", "4"},
                {"3","Uruguay","3", "1", "1", "1", "4"},
                {"4","Ghana","3", "1", "0", "2", "0"}};
        String[] column ={"POS", "TEAM", "PLAYED", "WINS", "DRAWS", "LOSSES", "POINTS"};
        table = new JTable(data, column);
        table.setBounds(100,150,200,300);
        table.getTableHeader().setFont(font);
        table.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 182, 1162, 530);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

    }

}
