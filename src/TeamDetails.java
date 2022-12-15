import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class TeamDetails {

    public static void getTeam(String teamID) {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        ImageIcon icon;

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String newsQuery = "SELECT denumire FROM teams WHERE TaraID = ?";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(newsQuery);
            preparedStatement.setString(1, teamID);
            resultSet = preparedStatement.executeQuery();
            byte[] image = null;

            while(resultSet.next()) {
                buttons[7].setText(toUpperCase(resultSet.getString("denumire")));
            }

            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }


    }
}
