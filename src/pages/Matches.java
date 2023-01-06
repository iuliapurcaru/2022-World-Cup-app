package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;

public class Matches {

    public static void getMatches(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("MATCHES");

        JLabel selectLabel = new JLabel("Select a stage:");
        selectLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        selectLabel.setBounds(30, 92, 180, 100);
        panel.add(selectLabel);

        JLabel stageLabel = new JLabel();
        stageLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        stageLabel.setBounds(520, 92, 180, 100);
        panel.add(stageLabel);

        String[] optionsToChoose = {"Upcoming", "Groups", "Round of 16", "Quarter-finals", "Semifinals", "Finals"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        comboBox.setBounds(200, 122, 160, 40);
        panel.add(comboBox);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 182, 1162, 530);
        panel.add(scrollPane);

        JButton doneButton = new JButton("SELECT");
        doneButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doneButton.setBounds(380, 122, 130, 40);
        doneButton.setForeground(Color.WHITE);
        doneButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(doneButton);

    }

}
