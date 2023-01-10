package admin;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import pages.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class DeleteMatch {
    public static void deleteMatch() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 18));

        JButton[] buttons = Buttons.getAdminButtons(frame);
        for(int i = 0; i < 4; i++) {
            panel.add(buttons[i]);
        }

        JLabel deleteMatch = new JLabel("Delete a match");
        deleteMatch.setFont(new Font("Century Gothic", Font.BOLD, 25));
        deleteMatch.setBounds(30, 92, 250, 100);
        panel.add(deleteMatch);

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

        JButton deleteButton = new JButton("DELETE MATCH");
        deleteButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        deleteButton.setBounds(30,312, 220,50);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(
                e -> {
                    String country1 = country1ComboBox.getItemAt(country1ComboBox.getSelectedIndex());
                    String country1ID = null;
                    String country2 = country2ComboBox.getItemAt(country2ComboBox.getSelectedIndex());
                    String country2ID = null;

                    for(int i = 0; i < 32; i++) {
                        if(Objects.equals(countriesToChoose[i], country1)) {
                            country1ID = countryID[i];
                        }
                        if(Objects.equals(countriesToChoose[i], country2)) {
                            country2ID = countryID[i];
                        }
                    }

                    int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this match?");
                    if(input == 0) {
                        Connection connection;
                        PreparedStatement preparedStatement;
                        String deleteUser = "DELETE FROM matches WHERE MeciID = ?";
                        UIManager.put("OptionPane.minimumSize", new Dimension(100,50));

                        try {
                            connection = DatabaseConnection.getConnection();
                            preparedStatement = connection.prepareStatement(deleteUser);
                            preparedStatement.setString(1, "0" + matchNumber.getText() + country1ID + country2ID);
                            preparedStatement.executeUpdate();

                            connection.close();
                        }
                        catch (Exception err){
                            err.printStackTrace();
                        }

                    }

                }
        );
        panel.add(deleteButton);
    }
}
