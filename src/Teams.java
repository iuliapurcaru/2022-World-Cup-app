import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teams {
    public static void getTeams() {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("TEAMS");

        int size = 0;
        int align = 0;
        int count = 0;
        String[] teamsID = new String[32];
        JLabel[] teams = new JLabel[32];
        JButton[] teamButtons = new JButton[32];
        ImageIcon icon;

        try {
            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String teamsQuery = "SELECT taraID, denumire, flag FROM teams ORDER BY denumire";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(teamsQuery);

            byte[] image;

            while(resultSet.next()) {
                teamsID[size] = resultSet.getString("taraID");
                teams[size] = new JLabel(resultSet.getString("denumire"));
                image = resultSet.getBytes("flag");
                icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
                teamButtons[size] = new JButton(icon);
                teamButtons[size].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                size++;

            }

            for(int i = 0; i < size; i++) {
                int iAux = i;
                teamButtons[i].addActionListener(
                        e -> {
                            frame.dispose();
                            TeamDetails.getTeam(teamsID[iAux]);
                        }

                );
            }

            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {

            for(int j = 0; (j < 7) && (count < 32) ; j++) {

                teamButtons[count].setBounds(30 + j * 170, 120 + align, 94, 62);
                panel.add(teamButtons[count]);
                teams[count].setBounds(30 + j * 170, 180 + align, 130, 40);
                teams[count].setFont(new Font("Century Gothic", Font.BOLD, 18));
                panel.add(teams[count]);
                count++;
            }
            align += 120;
        }

    }

}
