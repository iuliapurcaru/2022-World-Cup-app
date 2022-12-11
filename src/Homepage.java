import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Homepage extends JFrame{
    Homepage() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.getHSBColor(0, 0, 1f));

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
        buttons[7].setText("HOME");


        JLabel[] news = new JLabel[3];
        JButton [] detailsButtons = new JButton[3];

        for(int i = 0; i < 3; i++) {

            try {
                Connection connection;
                ResultSet resultSet;
                PreparedStatement preparedStatement;
                String selectUser = "SELECT title FROM news WHERE NewsID = 100";

                connection = DatabaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(selectUser);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    news[i] = new JLabel(resultSet.getString(1));
                    news[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
                    panel.add(news[i]);
                }

                connection.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            detailsButtons[i] = new JButton("DETAILS");
            detailsButtons[i].setFont(new Font("Century Gothic", Font.PLAIN, 18));
            detailsButtons[i].setForeground(Color.WHITE);
            detailsButtons[i].setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));
            detailsButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            detailsButtons[i].addActionListener(
                    e -> {
                        this.dispose();
                        News news2 = new News();
                    }

            );
            panel.add(detailsButtons[i]);
        }

        news[0].setBounds(50, 188, 1000, 40);
        news[1].setBounds(50, 383, 1000, 40);
        news[2].setBounds(50, 578, 1000, 40);
        detailsButtons[0].setBounds(900, 180, 130, 60);
        detailsButtons[1].setBounds(900, 375, 130, 60);
        detailsButtons[2].setBounds(900, 570, 130, 60);

    }

}
