package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;

public class Stats {

    public static void getStats() {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("STATS");

    }

}
