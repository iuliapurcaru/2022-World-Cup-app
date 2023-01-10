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

        JLabel matchesLabel = new JLabel("Select a match:");
        matchesLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        matchesLabel.setBounds(30, 378, 180, 100);
        panel.add(matchesLabel);

        JLabel groupLabel = new JLabel();
        groupLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        groupLabel.setBounds(520, 92, 180, 100);
        panel.add(groupLabel);

        String[] optionsToChoose = {"Group A", "Group B", "Group C", "Group D", "Group E", "Group F", "Group G", "Group H"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        comboBox.setBounds(200, 122, 130, 40);
        panel.add(comboBox);

        String[] matchesToChoose = new String[6];
        matchesToChoose[0] = "Select a group";
        String[] matchesID = new String[6];
        JComboBox<String> matchComboBox = new JComboBox<>(matchesToChoose);
        matchComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        matchComboBox.setBounds(200, 408, 300, 40);
        panel.add(matchComboBox);

        JTextArea tableTextArea = new JTextArea();
        tableTextArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        tableTextArea.setEditable(false);
        tableTextArea.setWrapStyleWord(true);
        tableTextArea.setLineWrap(true);

        JScrollPane tableScrollPane = new JScrollPane(tableTextArea);
        tableScrollPane.setBounds(10, 182, 1162, 215);
        panel.add(tableScrollPane);

        JTextArea matchesTextArea = new JTextArea();
        matchesTextArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        matchesTextArea.setEditable(false);
        matchesTextArea.setWrapStyleWord(true);
        matchesTextArea.setLineWrap(true);

        JScrollPane matchesScrollPane = new JScrollPane(matchesTextArea);
        matchesScrollPane.setBounds(10, 460, 1162, 250);
        panel.add(matchesScrollPane);

        JButton matchButton = new JButton("SELECT");
        matchButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        matchButton.setBounds(520, 408, 130, 40);
        matchButton.setForeground(Color.WHITE);
        matchButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        matchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(matchButton);

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
                    int wins;
                    int draws;
                    int losses;

                    tableTextArea.setText("\n\t                POS     TEAM\tPLAYED      WINS     DRAWS     LOSSES     POINTS\n" +
                            "\t                ----------------------------------------" +
                            "--------------------------------------------------------\n");
                    try {
                        Connection connection;
                        connection = DatabaseConnection.getConnection();

                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT T.denumire, T.PunctajGrupe " +
                                       "FROM teams T " +
                                       "WHERE T.Grupa = ? " +
                                       "ORDER BY T.PunctajGrupe DESC";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, input.substring(input.length() - 1));
                        resultSet = preparedStatement.executeQuery();

                        int counter = 0;
                        while(resultSet.next()) {
                            counter++;
                            wins = 0;
                            draws = 0;
                            losses = 0;

                            switch(resultSet.getString(2)) {
                                case "9":
                                    wins = 3;
                                    break;
                                case "7":
                                    wins = 2;
                                    draws = 1;
                                    break;
                                case "6":
                                    wins = 2;
                                    losses = 1;
                                    break;
                                case "5":
                                    wins = 1;
                                    draws = 2;
                                    break;
                                case "4":
                                    wins = 1;
                                    draws = 1;
                                    losses = 1;
                                    break;
                                case "3":
                                    wins = 1;
                                    losses = 2;
                                    break;
                                case "2":
                                    draws = 2;
                                    losses = 1;
                                    break;
                                case "1":
                                    draws = 1;
                                    losses = 2;
                                    break;
                                default:
                                    losses = 3;
                            }

                            tableTextArea.setText(tableTextArea.getText().concat(
                                          "\t                 " + counter + "\t  " +
                                                resultSet.getString(1) + "\t" +
                                                "     3       " +
                                                "       " + wins + "      " +
                                                "       " + draws + "        " +
                                                "       " + losses + "              " +
                                                resultSet.getString(2) + "\n"));
                        }

                        query = "SELECT A.Denumire, B.Denumire, M.MeciID " +
                                "FROM matches M, teams A, teams B " +
                                "WHERE (M.Etapa = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID)" +
                                "ORDER BY M.data, M.ora";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, input);
                        resultSet = preparedStatement.executeQuery();
                        int i = 0;

                        while(resultSet.next()) {
                            matchesToChoose[i] = resultSet.getString(1) + " - " +
                                                 resultSet.getString(2);
                            matchesID[i] = resultSet.getString(3);
                            i++;
                        }

                        matchComboBox.setModel(new DefaultComboBoxModel<>(matchesToChoose));

                        connection.close();
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
                    for(int j = 0; j < 6; j++) {
                        if(Objects.equals(matchesToChoose[j], input)) {
                            inputMatch = matchesID[j];
                            break;
                        }
                    }

                    Matches.getMatchDetails(matchesTextArea, inputMatch);

                }
        );

    }

}
