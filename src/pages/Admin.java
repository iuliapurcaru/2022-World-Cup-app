package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {

    public static void getEdit() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, "");
        panel.add(buttons[0]);

        JLabel addMatch = new JLabel("Add a match");
        addMatch.setFont(new Font("Century Gothic", Font.BOLD, 25));
        addMatch.setBounds(30, 92, 180, 100);
        panel.add(addMatch);

        JLabel addCountry = new JLabel("Select countries:");
        addCountry.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addCountry.setBounds(30, 132, 210, 100);
        panel.add(addCountry);

        String[] country1 = new String[32];
        country1[0] = "Select a country";

        String[] country2 = new String[32];
        country2[0] = "Select a country";

        try {

            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String query = "SELECT Denumire FROM teams ORDER BY Denumire";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int i = 0;

            while(resultSet.next()) {
                country1[i] = resultSet.getString(1);
                country2[i] = resultSet.getString(1);
                i++;
            }

            connection.close();
        }
        catch (Exception err){
            err.printStackTrace();

        }

        JComboBox<String> country1ComboBox = new JComboBox<>(country1);
        country1ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        country1ComboBox.setBounds(30, 202, 300, 40);
        panel.add(country1ComboBox);

        JComboBox<String> country2ComboBox = new JComboBox<>(country2);
        country2ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        country2ComboBox.setBounds(30, 262, 300, 40);
        panel.add(country2ComboBox);

    }
}
