import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Homepage extends JFrame{
    Homepage() {

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
        buttons[7].setText("HOME");

        JButton line1 = new JButton();
        line1.setBounds(0, 307, 1200, 1);
        line1.setBorderPainted(false);
        line1.setEnabled(false);
        line1.setBackground(Color.LIGHT_GRAY);
        panel.add(line1);

        JButton line2 = new JButton();
        line2.setBounds(0, 502, 1200, 1);
        line2.setBorderPainted(false);
        line2.setEnabled(false);
        line2.setBackground(Color.LIGHT_GRAY);
        panel.add(line2);


        JLabel[] newsTitles = new JLabel[3];
        JButton [] detailsButtons = new JButton[3];
        String [] news = {"100", "200", "300"};

        for(int i = 0; i < 3; i++) {

            try {
                Connection connection;
                ResultSet resultSet;
                PreparedStatement preparedStatement;
                String newsQuery = "SELECT title FROM news WHERE NewsID = ?";

                connection = DatabaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(newsQuery);
                preparedStatement.setString(1, news[i]);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    newsTitles[i] = new JLabel(resultSet.getString(1));
                    newsTitles[i].setFont(new Font("Century Gothic", Font.BOLD, 25));
                    panel.add(newsTitles[i]);
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
            int iAux = i;
            detailsButtons[i].addActionListener(
                    e -> {
                        this.dispose();
                        News.getNews(news[iAux]);
                    }

            );
            panel.add(detailsButtons[i]);
        }

        newsTitles[0].setBounds(50, 188, 800, 40);
        newsTitles[1].setBounds(50, 383, 800, 40);
        newsTitles[2].setBounds(50, 578, 800, 40);
        detailsButtons[0].setBounds(900, 180, 130, 60);
        detailsButtons[1].setBounds(900, 375, 130, 60);
        detailsButtons[2].setBounds(900, 570, 130, 60);

    }

}
