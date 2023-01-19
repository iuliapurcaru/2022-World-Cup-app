package teampages;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import awt.*;
import database.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class TeamDetails {

    public static void getTeam(String teamID, String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String query = "SELECT denumire, flag FROM teams WHERE TaraID = ?";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teamID);
            resultSet = preparedStatement.executeQuery();

            ImageIcon icon;
            byte[] image;
            JLabel nPhoto;

            while(resultSet.next()) {
                buttons[7].setText(toUpperCase(resultSet.getString("denumire")));
                image = resultSet.getBytes("flag");
                icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
                nPhoto = new JLabel();
                nPhoto.setIcon(icon);
                Dimension size = nPhoto.getPreferredSize();
                nPhoto.setBounds(20, 110, size.width + 1, size.height + 1);
                nPhoto.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                panel.add(nPhoto);
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 182, 1162, 530);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        JButton statsButton = new JButton("STATS");
        statsButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        statsButton.setBounds(132,122, 130,40);
        statsButton.setForeground(Color.WHITE);
        statsButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        statsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        statsButton.addActionListener(
                e -> {
                    textArea.setText("");
                    try {
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT DISTINCT T.confederatie, T.antrenor, P.prenume, P.nume, T.Grupa " +
                                        "FROM matches M, teams T, players P " +
                                        "WHERE (T.taraID = ?) AND (T.capitanID = P.jucatorID)";

                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, teamID);
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    "Confederation: " + resultSet.getString(1) + "\n" +
                                            "Head coach: " + resultSet.getString(2) + "\n" +
                                            "Captain: " + resultSet.getString(3) + " " +
                                            resultSet.getString(4) + "\n" +
                                            "Group: " + resultSet.getString(5) + "\n\n"));
                        }

                        query = "SELECT DISTINCT (SELECT COUNT(MeciID) " +
                                        "FROM matches " +
                                        "WHERE (Tara1ID = ? OR Tara2ID = ?) AND (Scor <> \"  -  \")), " +

                                        "(SELECT COUNT(G.GolID)" +
                                        "FROM goals G, matches M, players P " +
                                        "WHERE (P.TaraID = ?) AND (M.MeciID = G.MeciID) AND (G.JucatorID = P.JucatorID) " +
                                        "AND (G.Tip IN ('(A)', '(P)'))), " +

                                        "(SELECT COUNT(G.GolID) " +
                                        "FROM goals G, matches M, players P " +
                                        "WHERE(P.TaraID = ?) " +
                                        "AND (M.MeciID = G.MeciID) AND (G.JucatorID = P.JucatorID) AND (G.Tip = \"(OG)\")), " +

                                        "(SELECT COUNT(G.GolID) " +
                                        "FROM goals G, matches M, players P " +
                                        "WHERE(P.TaraID = ?) " +
                                        "AND (M.MeciID = G.MeciID) AND (G.JucatorID = P.JucatorID) AND (G.Tip = \"(P)\"))," +

                                        "(SELECT COUNT(C.CardID)" +
                                        "FROM cards C, matches M, players P " +
                                        "WHERE (P.TaraID = ?) AND (M.MeciID = C.MeciID) AND (C.JucatorID = P.JucatorID)" +
                                        "AND (C.Culoare = \"yellow\")), " +

                                        "(SELECT COUNT(C.CardID)" +
                                        "FROM cards C, matches M, players P " +
                                        "WHERE (P.TaraID = ?) AND (M.MeciID = C.MeciID) AND (C.JucatorID = P.JucatorID)" +
                                        "AND (C.Culoare = \"red\")) " +

                                "FROM matches M, teams T, players P " +
                                "WHERE (T.taraID = ?)";

                        preparedStatement = connection.prepareStatement(query);
                        for(int i = 0; i < 8; i++) {
                            preparedStatement.setString(i + 1, teamID);
                        }
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                            "Matches played: " + resultSet.getString(1) + "\n" +
                                            "Goals scored: " + resultSet.getString(2) + "\n" +
                                            "Own goals: " + resultSet.getString(3) + "\n" +
                                            "Penalties scored: " + resultSet.getString(4) + "\n" +
                                            "Yellow cards: " + resultSet.getString(5) + "\n" +
                                            "Red cards: " + resultSet.getString(6) + "\n\n"));
                        }

                        query = "SELECT P.prenume, P.nume, (SELECT COUNT(G.JucatorID) " +
                                "                           FROM goals G " +
                                "                           WHERE (G.jucatorID = P.jucatorID) AND (G.Tip IN ('(A)', '(P)'))) " +
                                "FROM players P " +
                                "WHERE P.TaraID = ? " +
                                "ORDER BY (SELECT COUNT(G.JucatorID) " +
                                "          FROM goals G " +
                                "          WHERE (G.jucatorID = P.jucatorID) AND (G.Tip IN ('(A)', '(P)'))) DESC " +
                                "limit 1 ";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, teamID);

                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    "Top scorer: " + resultSet.getString(1) + " " +
                                            resultSet.getString(2) + " (" +
                                            resultSet.getInt(3) + " goals)\n"));
                        }

                        String query1 = "SELECT prenume, nume, DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0 " +
                                "FROM players " +
                                "WHERE DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0 = (SELECT MIN(DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0) FROM players WHERE TaraID = ?) " +
                                "AND (TaraID = ?) " +
                                "limit 1";

                        preparedStatement = connection.prepareStatement(query1);
                        preparedStatement.setString(1, teamID);
                        preparedStatement.setString(2, teamID);

                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    "Youngest player: " + resultSet.getString(1) + " " +
                                            resultSet.getString(2) + " (age " +
                                            resultSet.getInt(3) + ")\n"));
                        }

                        String query2 = "SELECT prenume, nume, DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0 " +
                                "FROM players " +
                                "WHERE DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0 = (SELECT MAX(DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), DataNasterii)), '%Y') + 0) FROM players WHERE TaraID = ?) " +
                                "AND (TaraID = ?) " +
                                "limit 1";

                        preparedStatement = connection.prepareStatement(query2);
                        preparedStatement.setString(1, teamID);
                        preparedStatement.setString(2, teamID);

                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                            "Oldest player: " + resultSet.getString(1) + " " +
                                                                resultSet.getString(2) + " (age " +
                                                                resultSet.getInt(3) + ")"));
                        }

                        connection.close();
                    }
                    catch (Exception err){
                        err.printStackTrace();

                    }
                }
        );
        panel.add(statsButton);

        JButton playersButton = new JButton("PLAYERS");
        playersButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        playersButton.setBounds(282,122, 130,40);
        playersButton.setForeground(Color.WHITE);
        playersButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        playersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playersButton.addActionListener(
                e -> {
                    textArea.setText("Name\t\tNumber\tPosition\tDate of Birth\tHeight (m)\tGoals scored\n" +
                            "--------------------------------------------------------------------------" +
                            "--------------------------------------------------------------------------\n");
                    try {
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT P.prenume, P.nume, P.numar, P.pozitie, P.datanasterii, P.inaltime, (SELECT COUNT(G.JucatorID) " +
                                "                                                                FROM goals G " +
                                "                                                                WHERE (G.jucatorID = P.jucatorID) AND (G.Tip IN ('(A)', '(P)'))) " +
                                "FROM players P " +
                                "WHERE P.TaraID = ? " +
                                "ORDER BY P.positionID, P.numar";

                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, teamID);
                        resultSet = preparedStatement.executeQuery();

                        String tab;

                        while(resultSet.next()) {
                            if (resultSet.getString(1).length() + resultSet.getString(2).length() < 18) {
                                tab = "\t\t";
                            }
                            else {
                                tab = "\t";
                            }

                            textArea.setText(textArea.getText().concat(
                                    resultSet.getString(1) + " " +
                                            resultSet.getString(2) + tab +
                                            resultSet.getString(3) + "\t" +
                                            resultSet.getString(4) + "\t" +
                                            resultSet.getString(5) + "\t" +
                                            resultSet.getString(6) + "\t         " +
                                            resultSet.getString(7) + "\n"));
                        }

                        connection.close();
                    }
                    catch (Exception err){
                        err.printStackTrace();
                    }
                }

        );
        panel.add(playersButton);

        JButton matchesButton = new JButton("MATCHES");
        matchesButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        matchesButton.setBounds(432,122, 130,40);
        matchesButton.setForeground(Color.WHITE);
        matchesButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        matchesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        matchesButton.addActionListener(
                e -> {
                    textArea.setText("");
                    try {
                        Connection connection = DatabaseConnection.getConnection();
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String query = "SELECT A.Denumire, M.scor, B.Denumire, M.ora, M.data, S.Denumire, S.Oras, M.NrSpectatori, M.Etapa, R.Prenume, R.Nume, R.TaraProvenienta " +
                                       "FROM matches M, teams A, teams B, stadiums S, referees R, referees_matches RM " +
                                       "WHERE (M.Tara1ID = ? OR M.Tara2ID = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID) " +
                                                "AND (M.StadionID = S.StadionID) AND (RM.MeciID = M.MeciID) " +
                                                "AND (RM.ArbitruID = R.ArbitruSefID) AND (RM.Sef = \"yes\") " +
                                       "ORDER BY M.Data";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, teamID);
                        preparedStatement.setString(2, teamID);
                        resultSet = preparedStatement.executeQuery();

                        int i = 0;

                        while(resultSet.next()) {
                            i++;
                            textArea.setText(textArea.getText().concat(
                                    i + ".  " + resultSet.getString(1) + "\t   " +     //team 1
                                            resultSet.getString(2) + "          " +   //score
                                            resultSet.getString(3) + "\n" +   //team 2
                                            resultSet.getString(4) + "  " +   //time
                                            resultSet.getString(5) + "\n" +   //date
                                            "Stage: " + resultSet.getString(9) + "\n" +
                                            resultSet.getString(7) + ", " +   //city
                                            resultSet.getString(6) + "\n" +   //stadium
                                            "Attendance: " + resultSet.getString(8) + "\n" +
                                            "Referee: " + resultSet.getString(10) + " " +
                                            resultSet.getString(11) +
                                            " (" + resultSet.getString(12) + ")\n\n"));
                        }

                        connection.close();
                    }
                    catch (Exception err){
                        err.printStackTrace();

                    }
                }
        );
        panel.add(matchesButton);

    }
}
