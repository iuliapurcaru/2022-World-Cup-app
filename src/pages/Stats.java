package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Stats {

    public static void getStats(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("STATISTICS");

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 182, 1162, 530);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        JButton goalscorersButton = new JButton("GOALSCORERS");
        goalscorersButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        goalscorersButton.setBounds(25,122, 200,40);
        goalscorersButton.setForeground(Color.WHITE);
        goalscorersButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        goalscorersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goalscorersButton.addActionListener(
                e -> {
                    try {

                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT COUNT(*) " +
                                "FROM goals " +
                                "WHERE tip IN (\"(A)\", \"(P)\", \"(OG)\")";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();
                        int[] nGoals = new int[2];

                        while (resultSet.next()) {
                            nGoals[0] = resultSet.getInt(1);
                            textArea.setText("There were " + nGoals[0]);
                        }

                        String query2 = "SELECT COUNT(*) " +
                                "FROM matches " +
                                "WHERE scor <> \"  -  \"";

                        preparedStatement = connection.prepareStatement(query2);
                        resultSet = preparedStatement.executeQuery();
                        float avg;
                        String avgS;

                        while (resultSet.next()) {
                            nGoals[1] = resultSet.getInt(1);
                            avg = (float)nGoals[0]/nGoals[1];
                            avgS = String.format("%.02f", avg);
                            textArea.setText(textArea.getText().concat( " goals scored in " + nGoals[1] + " matches, " +
                                    "for an average of " + avgS + " goals per match.\n"));
                        }

                        textArea.setText(textArea.getText().concat(
                                "\t\tNAME\t\t        GOALS\n" +
                                "\t\t-------------------------------------" +
                                        "-------------------------------\n"));

                        query = "SELECT P.prenume, P.nume, T.Denumire, (SELECT COUNT(G.JucatorID) " +
                                                                       "FROM goals G " +
                                                                       "WHERE (G.jucatorID = P.jucatorID) AND (G.Tip IN ('(A)', '(P)'))) AS nGoals " +
                                "FROM players P, teams T " +
                                "WHERE P.TaraID = T.TaraID " +
                                "HAVING nGoals > 0 " +
                                "ORDER BY nGoals DESC, T.Denumire";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();


                        while (resultSet.next()) {
                            textArea.setText(textArea.getText().concat("\t\t" +
                                    resultSet.getString(1) + " " +
                                            resultSet.getString(2) + " (" +
                                            resultSet.getString(3) + ")\t        " +
                                            resultSet.getString(4) + "\n"
                            ));
                        }

                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(goalscorersButton);

        JButton disciplineButton = new JButton("DISCIPLINE");
        disciplineButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        disciplineButton.setBounds(245,122, 200,40);
        disciplineButton.setForeground(Color.WHITE);
        disciplineButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        disciplineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        disciplineButton.addActionListener(
                e -> {
                    try {

                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT COUNT(*) " +
                                "FROM cards ";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            textArea.setText("There were given a total of " + resultSet.getString(1));
                        }

                        query = "SELECT COUNT(*) " +
                                "FROM cards " +
                                "WHERE culoare = \"yellow\"";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            textArea.setText(textArea.getText().concat(" cards, where yellow cards " + resultSet.getString(1)));
                        }

                        query = "SELECT COUNT(*) " +
                                "FROM cards " +
                                "WHERE culoare = \"red\"";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            textArea.setText(textArea.getText().concat(" and red cards " + resultSet.getString(1) + ".\n"));
                        }

                        textArea.setText(textArea.getText().concat(
                                "\t          NAME\t\t\tCARD\tMATCH\n" +
                                        "\t          -----------------------------------------------" +
                                        "------------------------------------------------------------\n"));

                        query = "SELECT P.prenume, P.nume, T.Denumire, C.Culoare, CONCAT(A.Denumire,\" - \", B.Denumire) " +
                                "FROM players P, teams T, cards C, matches M, teams A, teams B " +
                                "WHERE (P.TaraID = T.TaraID) " +
                                        "AND (M.MeciID = C.MeciID) " +
                                        "AND (C.JucatorID = P.JucatorID) " +
                                        "AND (M.Tara1ID = A.TaraID and M.Tara2ID = B.TaraID) " +
                                "ORDER BY T.Denumire";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            textArea.setText(textArea.getText().concat("\t          " +
                                    resultSet.getString(1) + " " +
                                    resultSet.getString(2) + " (" +
                                    resultSet.getString(3) + ")\t" +
                                    resultSet.getString(4) + "\t" +
                                    resultSet.getString(5) + "\n"
                            ));
                        }

                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(disciplineButton);

        JButton refereesButton = new JButton("REFEREES");
        refereesButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        refereesButton.setBounds(465,122, 200,40);
        refereesButton.setForeground(Color.WHITE);
        refereesButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        refereesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        refereesButton.addActionListener(
                e -> {
                    textArea.setText("There were a total of 36 referees from all six confederations.\n" +
                            "\t\tNAME\t\tCOUNTRY\n" +
                            "\t\t--------------------------------------------------------------------------\n");

                    try {
                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT prenume, nume, TaraProvenienta FROM referees";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();
                        String tab;

                        while(resultSet.next()) {
                            if (resultSet.getString(1).length() + resultSet.getString(2).length() < 14) {
                                tab = "\t\t";
                            }
                            else {
                                tab = "\t";
                            }

                            textArea.setText(textArea.getText().concat("\t\t" +
                                    resultSet.getString(1) + " " +
                                    resultSet.getString(2) + tab +
                                    resultSet.getString(3) + "\n"));
                        }

                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(refereesButton);

        JButton stadiumsButton = new JButton("STADIUMS");
        stadiumsButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        stadiumsButton.setBounds(685,122, 200,40);
        stadiumsButton.setForeground(Color.WHITE);
        stadiumsButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        stadiumsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stadiumsButton.addActionListener(
                e -> {
                    textArea.setText("The matches were played on 8 stadiums in 5 different cities in Qatar.\n\n" +
                            "\t\tSTADIUM\t\tCITY\tCAPACITY\n" +
                            "\t\t-------------------------------------------------------------------------------\n");

                    try {
                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT denumire, oras, capacitate " +
                                "FROM stadiums";

                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat("\t\t" +
                                    resultSet.getString(1) + "\t" +
                                    resultSet.getString(2) + "\t    " +
                                    resultSet.getString(3) + "\n"));
                        }

                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }
        );
        panel.add(stadiumsButton);

    }

}
