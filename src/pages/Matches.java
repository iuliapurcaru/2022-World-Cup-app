package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

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

        String[] optionsToChoose = {"Round of 16", "Quarter-finals", "Semifinals", "Finals"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        comboBox.setBounds(200, 122, 160, 40);
        panel.add(comboBox);

        String[] matchesAux = new String[8];
        matchesAux[0] = "Select a stage";
        String[] matchesID = new String[8];
        JComboBox<String> matchComboBox = new JComboBox<>(matchesAux);
        matchComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        matchComboBox.setBounds(540, 122, 300, 40);
        panel.add(matchComboBox);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 182, 1162, 530);
        panel.add(scrollPane);

        JButton matchButton = new JButton("SELECT");
        matchButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        matchButton.setBounds(860, 122, 130, 40);
        matchButton.setForeground(Color.WHITE);
        matchButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        matchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(matchButton);

        JButton doneButton = new JButton("SELECT");
        doneButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doneButton.setBounds(380, 122, 130, 40);
        doneButton.setForeground(Color.WHITE);
        doneButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(doneButton);
        doneButton.addActionListener(
                e -> {
                    String input = comboBox.getItemAt(comboBox.getSelectedIndex());

                    try {
                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;

                        String query = "SELECT A.Denumire, B.Denumire, M.MeciID " +
                                        "FROM matches M, teams A, teams B " +
                                        "WHERE (M.Etapa = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID)" +
                                        "ORDER BY M.data, M.ora";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, input);
                        resultSet = preparedStatement.executeQuery();

                        int i = 0;
                        int matchesCounter = 0;
                        while (resultSet.next()) {
                            matchesAux[i] = resultSet.getString(1) + " - " +
                                            resultSet.getString(2);
                            matchesID[i] = resultSet.getString(3);
                            i++;
                            matchesCounter++;
                        }

                        String[] matchesToChoose = new String[matchesCounter];
                        System.arraycopy(matchesAux, 0, matchesToChoose, 0, matchesCounter);
                        matchComboBox.setModel(new DefaultComboBoxModel<>(matchesToChoose));
                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }

                }
        );
        panel.add(doneButton);

        matchButton.addActionListener(
                r -> {
                    String input = matchComboBox.getItemAt(matchComboBox.getSelectedIndex());
                    String inputMatch = null;
                    for(int j = 0; j < 8; j++) {
                        if(Objects.equals(matchesAux[j], input)) {
                            inputMatch = matchesID[j];
                            break;
                        }
                    }

                    getMatchDetails(textArea, inputMatch);

                }
        );

    }

    public static void getMatchDetails(JTextArea textArea, String inputMatch) {
        try {

            Connection connection = DatabaseConnection.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String query = "SELECT A.Denumire, M.scor, B.Denumire, M.ora, M.data, S.Denumire, S.Oras, M.NrSpectatori " +
                    "FROM matches M, teams A, teams B, stadiums S " +
                    "WHERE (M.MeciID = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID) AND (M.StadionID = S.StadionID) " +
                    "ORDER BY M.Data";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputMatch);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                textArea.setText(
                        resultSet.getString(1) + "\t" +   //team 1
                                resultSet.getString(2) + "          " +         //
                                resultSet.getString(3) + "\n" +         //team 2
                                resultSet.getString(4) + "  " +         //time
                                resultSet.getString(5) + "\n" +         //date
                                resultSet.getString(7) + ", " +         //city
                                resultSet.getString(6) + "\n" +         //stadium
                                "Attendance: " + resultSet.getString(8) + "\n" +
                                "Referees:\n");
            }

            query = "SELECT R.Prenume, R.Nume, R.TaraProvenienta " +
                    "FROM referees R, referees_matches RM, matches M " +
                    "WHERE (M.MeciID = ?) AND (RM.MeciID = M.MeciID) AND (RM.ArbitruID = R.ArbitruSefID)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputMatch);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                textArea.setText(textArea.getText().concat(
                        resultSet.getString(1) + " " + resultSet.getString(2) +
                                " (" + resultSet.getString(3) + ")\n"));
            }

            textArea.setText(textArea.getText().concat("\n"));

            query = "SELECT P.Prenume, P.Nume, G.Minut, G.Tip, T.Denumire " +
                    "FROM goals G, players P, teams T " +
                    "WHERE (G.MeciID = ?) AND (P.JucatorID = G.JucatorID) AND (T.TaraID = P.TaraID) " +
                    "ORDER BY G.GolID";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputMatch);
            resultSet = preparedStatement.executeQuery();

            textArea.setText(textArea.getText().concat("Goals:\n"));
            String name;
            String pName;
            String type;

            while (resultSet.next()) {
                name = resultSet.getString(1);
                if(Objects.equals(name, "")) {
                    pName = "";
                }
                else {
                    pName = name.charAt(0) + ". ";
                }

                type = resultSet.getString(4);
                if(Objects.equals(type, "(A)")) {
                    type = "    ";
                }

                textArea.setText(textArea.getText().concat(
                        pName +   //initial
                                resultSet.getString(2) + " (" +         //name
                                resultSet.getString(5) + ")\t" +        //country
                                resultSet.getString(3) + " " +          //minute
                                type + "\n"));                                    //type
            }
        }
        catch (Exception err){
            err.printStackTrace();
        }
    }

}
