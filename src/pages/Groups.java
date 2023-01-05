package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Groups {

    public static void getGroups(String username) {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("GROUPS");

        JLabel selectLabel = new JLabel("Select a group:");
        selectLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        selectLabel.setBounds(30, 92, 180, 100);
        panel.add(selectLabel);

        JLabel groupLabel = new JLabel();
        groupLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        groupLabel.setBounds(520, 92, 180, 100);
        panel.add(groupLabel);

        String[] optionsToChoose = {"Group A", "Group B", "Group C", "Group D", "Group E", "Group F", "Group G", "Group H"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        comboBox.setBounds(200, 122, 130, 40);
        panel.add(comboBox);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 182, 1162, 530);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        JButton doneButton = new JButton("SELECT");
        doneButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doneButton.setBounds(350, 122, 130, 40);
        doneButton.setForeground(Color.WHITE);
        doneButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        doneButton.addActionListener(
                e -> {
                    String input = comboBox.getItemAt(comboBox.getSelectedIndex());
                    groupLabel.setText(input);
                    textArea.setText("\t                POS     TEAM\tPLAYED      WINS     DRAWS     LOSSES     POINTS\n" +
                            "\t                ----------------------------------------" +
                            "--------------------------------------------------------\n");
                    try {
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT T.denumire, T.PunctajGrupe\n" +
                                       "FROM teams T\n" +
                                       "WHERE T.Grupa = ?\n" +
                                       "ORDER BY T.PunctajGrupe DESC";

                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, input.substring(input.length() - 1));
                        resultSet = preparedStatement.executeQuery();
                        int i = 0;

                        while(resultSet.next()) {
                            i++;
                            textArea.setText(textArea.getText().concat(
                                            "\t                 " + i + "\t  " +
                                                resultSet.getString(1) + "\t" +
                                                "     3       " +
                                                "       4      " +
                                                "       5        " +
                                                "       6              " +
                                                resultSet.getString(2) + "\n"));
                        }

                        textArea.setText(textArea.getText().concat(
                                    "\t                ----------------------------------------" +
                                        "--------------------------------------------------------\n\n" +
                                            "MATCHES\n" +
                                            "------------------------------------------------------\n"));

                        query = "SELECT A.Denumire, M.scor, B.Denumire, M.ora, M.data, S.Denumire, S.Oras, M.NrSpectatori, R.Prenume, R.Nume, R.TaraProvenienta " +
                                "FROM matches M, teams A, teams B, stadiums S, referees R " +
                                "WHERE (M.Etapa = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID) AND (M.StadionID = S.StadionID) AND (M.ArbitruSefID = R.ArbitruSefID) " +
                                "ORDER BY data";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, input);
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    "  " + resultSet.getString(1) + "\t   " +     //team 1
                                            resultSet.getString(2) + "\t" +   //
                                            resultSet.getString(3) + "\t" +   //team 2
                                            resultSet.getString(4) + "  " +   //time
                                            resultSet.getString(5) + "\n" +   //date
                                            resultSet.getString(6) + ", " +   //stadium
                                            resultSet.getString(7) + "\n" +   //city
                                            "Attendance: " + resultSet.getString(8) + "\n" +
                                            "Referee: " + resultSet.getString(9) + " " + resultSet.getString(10) +
                                            " (" + resultSet.getString(11) + ")\n\n"));
                        }

                        connection.close();
                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(doneButton);

    }

}
