import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class TeamDetails {

    public static void getTeam(String teamID) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        frame.setTitle("World Cup Qatar 2022");
        frame.setSize(1200, 857);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

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
