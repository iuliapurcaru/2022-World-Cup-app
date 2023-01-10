package admin;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateMatch {
    public static void updateMatch() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

        JButton[] buttons = Buttons.getAdminButtons(frame);
        for(int i = 0; i < 4; i++) {
            panel.add(buttons[i]);
        }

        JLabel updateMatch = new JLabel("Update a match");
        updateMatch.setFont(new Font("Century Gothic", Font.BOLD, 25));
        updateMatch.setBounds(30, 92, 250, 100);
        panel.add(updateMatch);

        JLabel selectCountry = new JLabel("Teams");
        selectCountry.setFont(new Font("Century Gothic", Font.BOLD, 21));
        selectCountry.setBounds(30, 132, 210, 100);
        panel.add(selectCountry);

        String[] countryID = new String[32];
        String[] countriesToChoose = new String[32];

        try {

            Connection connection = DatabaseConnection.getConnection();
            ResultSet resultSet;
            Statement statement;

            String query = "SELECT TaraID, Denumire FROM teams ORDER BY Denumire";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int i = 0;

            while(resultSet.next()) {
                countryID[i] = resultSet.getString(1);
                countriesToChoose[i] = resultSet.getString(2);
                i++;
            }

            connection.close();
        }
        catch (Exception err){
            err.printStackTrace();
        }

        JComboBox<String> country1ComboBox = new JComboBox<>(countriesToChoose);
        country1ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        country1ComboBox.setBounds(30, 202, 300, 40);
        panel.add(country1ComboBox);

        JComboBox<String> country2ComboBox = new JComboBox<>(countriesToChoose);
        country2ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        country2ComboBox.setBounds(340, 202, 300, 40);
        panel.add(country2ComboBox);

        JLabel addMatchLabel = new JLabel("Insert match number:");
        addMatchLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addMatchLabel.setBounds(30, 232, 270, 100);
        panel.add(addMatchLabel);
        JTextField matchNumber = new JTextField();
        matchNumber.setBounds(260,262,60,40);
        matchNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(matchNumber);

    }
}
