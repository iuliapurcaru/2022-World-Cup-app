package admin;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class UpdateMatch {
    public static void updateMatch() {
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

        JLabel addMatchScore = new JLabel("Insert match score:");
        addMatchScore.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addMatchScore.setBounds(30, 292, 270, 100);
        panel.add(addMatchScore);
        JTextField matchScore = new JTextField();
        matchScore.setBounds(260,322,60,40);
        matchScore.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(matchScore);

        JLabel addMatchAttendance = new JLabel("Insert match attendance:");
        addMatchAttendance.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addMatchAttendance.setBounds(30, 352, 270, 100);
        panel.add(addMatchAttendance);
        JTextField matchAttendance = new JTextField();
        matchAttendance.setBounds(280,382,60,40);
        matchAttendance.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(matchAttendance);

        JButton updateButton = new JButton("UPDATE MATCH");
        updateButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        updateButton.setBounds(30, 442, 220,50);
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(
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

                    Connection connection;
                    PreparedStatement preparedStatement;
                    String updateUser = "UPDATE matches SET Scor = ?, NrSpectatori = ? WHERE MeciID = ?";
                    UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

                    try {
                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(updateUser);
                        preparedStatement.setString(1, matchScore.getText());
                        preparedStatement.setString(2, matchAttendance.getText());
                        preparedStatement.setString(3, "0" + matchNumber.getText() + country1ID + country2ID);
                        preparedStatement.executeUpdate();

                        connection.close();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                    optionPaneFont.setText("Match successfully updated!");
                    JOptionPane.showMessageDialog(null, optionPaneFont);
                }
        );
        panel.add(updateButton);

    }
}
