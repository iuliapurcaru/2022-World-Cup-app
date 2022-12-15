import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teams extends JFrame {
    Teams() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        JButton[] buttons = Buttons.getButtons(this);
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
        JLabel nPhoto = new JLabel();
        String arg = "ARG";

        try {
            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String teamsQuery = "SELECT taraID, denumire, flag FROM teams ORDER BY denumire";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(teamsQuery);

            byte[] image = null;

            while(resultSet.next()) {
                teamsID[size] = new String(resultSet.getString("taraID"));
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
                            this.dispose();
                            TeamDetails.getTeam(teamsID[iAux]);
                        }

                );
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
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
