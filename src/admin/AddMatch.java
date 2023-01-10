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

public class AddMatch {

    public static void addMatch() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

        JButton[] buttons = Buttons.getAdminButtons(frame);
        for(int i = 0; i < 4; i++) {
            panel.add(buttons[i]);
        }

        JLabel addMatch = new JLabel("Add a match");
        addMatch.setFont(new Font("Century Gothic", Font.BOLD, 25));
        addMatch.setBounds(30, 92, 180, 100);
        panel.add(addMatch);

        JLabel addCountry = new JLabel("Teams");
        addCountry.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addCountry.setBounds(30, 132, 210, 100);
        panel.add(addCountry);

        String[] countryID = new String[32];
        String[] countriesToChoose = new String[32];
        
        String[] stagesToChoose = {"Group A", "Group B", "Group C", "Group D", "Group E", "Group F", "Group G", "Group H",
                                   "Round of 16", "Quarter-finals", "Semifinals", "Finals"};
        String[] datesToChoose = {"2022-12-05", "2022-12-06", "2022-12-07", "2022-12-08", "2022-12-09", "2022-12-10", "2022-12-11",
                    "2022-12-12", "2022-12-13", "2022-12-14", "2022-12-15", "2022-12-16", "2022-12-17", "2022-12-18"};

        String[] hoursToChoose = {"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};
        
        String[] stadiumsToChoose = new String[8];
        String[] stadiumsID = new String[8];
        
        String[] refereesToChoose = new String[36];
        String[] refereesID = new String[36];

        countriesToChoose[0] = "Select a country";

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

            query = "SELECT StadionID, Denumire FROM stadiums";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            i = 0;

            while(resultSet.next()) {
                stadiumsID[i] = resultSet.getString(1);
                stadiumsToChoose[i] = resultSet.getString(2);
                i++;
            }

            query = "SELECT ArbitruSefID, Prenume, Nume, TaraProvenienta FROM referees";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            i = 0;

            while(resultSet.next()) {
                refereesID[i] = resultSet.getString(1);
                refereesToChoose[i] = resultSet.getString(2) + " " + resultSet.getString(3) +
                                " (" + resultSet.getString(4) + ")";
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
//        String country1 = country1ComboBox.getItemAt(country1ComboBox.getSelectedIndex());
//        String country1ID = null;

        JComboBox<String> country2ComboBox = new JComboBox<>(countriesToChoose);
        country2ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        country2ComboBox.setBounds(340, 202, 300, 40);
        panel.add(country2ComboBox);
//        String country2 = country2ComboBox.getItemAt(country2ComboBox.getSelectedIndex());
//        String country2ID = null;
//
//        for(int i = 0; i < 32; i++) {
//            if(Objects.equals(countriesToChoose[i], country1)) {
//                country1ID = countryID[i];
//            }
//            if(Objects.equals(countriesToChoose[i], country2)) {
//                country2ID = countryID[i];
//            }
//        }

        JLabel addMatchLabel = new JLabel("Insert match number:");
        addMatchLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addMatchLabel.setBounds(30, 232, 270, 100);
        panel.add(addMatchLabel);
        JTextField matchNumber = new JTextField();
        matchNumber.setBounds(260,262,60,40);
        matchNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(matchNumber);

        JLabel addStage = new JLabel("Stage");
        addStage.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addStage.setBounds(30, 292, 210, 100);
        panel.add(addStage);
        JComboBox<String> stagesComboBox = new JComboBox<>(stagesToChoose);
        stagesComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        stagesComboBox.setBounds(140, 322, 300, 40);
        panel.add(stagesComboBox);

        JLabel addDate = new JLabel("Date");
        addDate.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addDate.setBounds(30, 352, 210, 100);
        panel.add(addDate);
        JComboBox<String> datesComboBox = new JComboBox<>(datesToChoose);
        datesComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        datesComboBox.setBounds(140, 382, 300, 40);
        panel.add(datesComboBox);

        JLabel addHour = new JLabel("Time");
        addHour.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addHour.setBounds(30, 412, 210, 100);
        panel.add(addHour);
        JComboBox<String> hoursComboBox = new JComboBox<>(hoursToChoose);
        hoursComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        hoursComboBox.setBounds(140, 442, 300, 40);
        panel.add(hoursComboBox);

        JLabel addStadium = new JLabel("Stadium");
        addStadium.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addStadium.setBounds(30, 472, 210, 100);
        panel.add(addStadium);
        JComboBox<String> stadiumsComboBox = new JComboBox<>(stadiumsToChoose);
        stadiumsComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        stadiumsComboBox.setBounds(140, 502, 300, 40);
        panel.add(stadiumsComboBox);
//        String stadium = stadiumsComboBox.getItemAt(stadiumsComboBox.getSelectedIndex());
//        String stadiumID = null;
//        for(int i = 0; i < 8; i++) {
//            if(Objects.equals(stadiumsToChoose[i], stadium)) {
//                stadiumID = stadiumsID[i];
//            }
//        }

        JLabel addReferees = new JLabel("Referees");
        addReferees.setFont(new Font("Century Gothic", Font.BOLD, 21));
        addReferees.setBounds(30, 532, 210, 100);
        panel.add(addReferees);
        JComboBox<String> referee1ComboBox = new JComboBox<>(refereesToChoose);
        referee1ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        referee1ComboBox.setBounds(140, 562, 300, 40);
        panel.add(referee1ComboBox);
        JComboBox<String> referee2ComboBox = new JComboBox<>(refereesToChoose);
        referee2ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        referee2ComboBox.setBounds(450, 562, 300, 40);
        panel.add(referee2ComboBox);
        JComboBox<String> referee3ComboBox = new JComboBox<>(refereesToChoose);
        referee3ComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        referee3ComboBox.setBounds(760, 562, 300, 40);
        panel.add(referee3ComboBox);
//        String referee1 = referee1ComboBox.getItemAt(referee1ComboBox.getSelectedIndex());
//        String referee1ID = null;
//        String referee2 = referee2ComboBox.getItemAt(referee2ComboBox.getSelectedIndex());
//        String referee2ID = null;
//        String referee3 = referee3ComboBox.getItemAt(referee3ComboBox.getSelectedIndex());
//        String referee3ID = null;
//        for(int i = 0; i < 36; i++) {
//            if(Objects.equals(refereesToChoose[i], referee1)) {
//                referee1ID = refereesID[i];
//            }
//            if(Objects.equals(refereesToChoose[i], referee2)) {
//                referee2ID = refereesID[i];
//            }
//            if(Objects.equals(refereesToChoose[i], referee3)) {
//                referee3ID = refereesID[i];
//            }
//        }

        JButton addButton = new JButton("ADD MATCH");
        addButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addButton.setBounds(30,622, 220,50);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        String finalCountry1ID = country1ID;
//        String finalCountry2ID = country2ID;
//        String finalStadiumID = stadiumID;
//        String finalReferee1ID = referee1ID;
//
//        String finalReferee2ID = referee2ID;
//        String finalReferee3ID = referee3ID;
        addButton.addActionListener(
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

                    String stadium = stadiumsComboBox.getItemAt(stadiumsComboBox.getSelectedIndex());
                    String stadiumID = null;
                    for(int i = 0; i < 8; i++) {
                        if(Objects.equals(stadiumsToChoose[i], stadium)) {
                            stadiumID = stadiumsID[i];
                        }
                    }

                    String referee1 = referee1ComboBox.getItemAt(referee1ComboBox.getSelectedIndex());
                    String referee1ID = null;
                    String referee2 = referee2ComboBox.getItemAt(referee2ComboBox.getSelectedIndex());
                    String referee2ID = null;
                    String referee3 = referee3ComboBox.getItemAt(referee3ComboBox.getSelectedIndex());
                    String referee3ID = null;
                    for(int i = 0; i < 36; i++) {
                        if(Objects.equals(refereesToChoose[i], referee1)) {
                            referee1ID = refereesID[i];
                        }
                        if(Objects.equals(refereesToChoose[i], referee2)) {
                            referee2ID = refereesID[i];
                        }
                        if(Objects.equals(refereesToChoose[i], referee3)) {
                            referee3ID = refereesID[i];
                        }
                    }
                    try {
                        Connection connection = DatabaseConnection.getConnection();

                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String insertMatch = "INSERT INTO matches " +
                                "(MeciID, Tara1ID, Tara2ID, Etapa, Data, Ora, StadionID) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";

                        preparedStatement = connection.prepareStatement(insertMatch);
                        preparedStatement.setString(1, "0" + matchNumber.getText() + country1ID + country2ID);
                        preparedStatement.setString(2, country1ID);
                        preparedStatement.setString(3, country2ID);
                        preparedStatement.setString(4, stagesComboBox.getItemAt(stagesComboBox.getSelectedIndex()));
                        preparedStatement.setString(5, datesComboBox.getItemAt(datesComboBox.getSelectedIndex()));
                        preparedStatement.setString(6, hoursComboBox.getItemAt(hoursComboBox.getSelectedIndex()));
                        preparedStatement.setString(7, stadiumID);
                        preparedStatement.executeUpdate();

                        String insertReferees = "INSERT INTO referees_matches " +
                                            "(MeciID, ArbitruID, Sef) " +
                                            "VALUES (?, ?, \"yes\")";

                        preparedStatement = connection.prepareStatement(insertReferees);
                        preparedStatement.setString(1, "0" + matchNumber.getText() + country1ID + country2ID);
                        preparedStatement.setString(2, referee1ID);
                        preparedStatement.executeUpdate();

                        insertReferees = "INSERT INTO referees_matches " +
                                "(MeciID, ArbitruID) " +
                                "VALUES (?, ?)";

                        preparedStatement = connection.prepareStatement(insertReferees);
                        preparedStatement.setString(1, "0" + matchNumber.getText() + country1ID + country2ID);
                        preparedStatement.setString(2, referee2ID);
                        preparedStatement.executeUpdate();

                        preparedStatement = connection.prepareStatement(insertReferees);
                        preparedStatement.setString(1, "0" + matchNumber.getText() + country1ID + country2ID);
                        preparedStatement.setString(2, referee3ID);
                        preparedStatement.executeUpdate();

                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(addButton);

    }
}
