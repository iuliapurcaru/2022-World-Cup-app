package teampages;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import awt.*;
import database.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class TeamDetails {

    public static void getTeam(String teamID) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel);

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String newsQuery = "SELECT denumire, flag FROM teams WHERE TaraID = ?";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(newsQuery);
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
                    textArea.setText("Stats");
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
                    textArea.setText("\t" + "Name\t\tNumber\tPosition\tDate of Birth\tHeight\n" +
                            "\t" + "------------------------------------------------------------" +
                            "------------------------------------------------------------\n");
                    try {
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String Query = "SELECT * FROM players WHERE TaraID = ? ORDER BY positionID, numar";

                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(Query);
                        preparedStatement.setString(1, teamID);
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    "\t" + resultSet.getString(3) +
                                            resultSet.getString(2) + "\t\t" +
                                            resultSet.getString(5) + "\t" +
                                            resultSet.getString(6) + "\t" +
                                            resultSet.getString(7) + "\t" +
                                            resultSet.getString(8) + " m\n"));
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
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String Query = "SELECT A.Denumire, M.scor, B.Denumire " +
                                "FROM matches M, teams A, teams B " +
                                "WHERE (M.Tara1ID = ? OR M.Tara2ID = ?) AND (M.Tara1ID = A.TaraID AND M.Tara2ID = B.TaraID) " +
                                "ORDER BY data";

                        connection = DatabaseConnection.getConnection();
                        preparedStatement = connection.prepareStatement(Query);
                        preparedStatement.setString(1, teamID);
                        preparedStatement.setString(2, teamID);
                        resultSet = preparedStatement.executeQuery();

                        while(resultSet.next()) {
                            textArea.setText(textArea.getText().concat(
                                    resultSet.getString(1) + "\t" +
                                            resultSet.getString(2) + "        " +
                                            resultSet.getString(3) + "\n"));
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
