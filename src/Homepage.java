import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

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

        JTextArea[] newsTitles = new JTextArea[4];
        JButton [] detailsButtons = new JButton[4];
        int [] news = {100, 200, 300, 400, 500, 600};
        int [] newsHome = new int[4];

        for(int i = 0; i < 4; i++) {
            newsHome[i] = news[new Random().nextInt(news.length)];
            for(int j = 0; j < i; j++) {
                if(newsHome[i] == newsHome[j]) {
                    i--;
                    j = 4;
                }
            }
        }

        byte[] image = null;

        for(int i = 0; i < 4; i++) {

            try {
                Connection connection;
                ResultSet resultSet;
                PreparedStatement preparedStatement;
                String newsQuery = "SELECT title, image FROM news WHERE NewsID = ?";

                connection = DatabaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(newsQuery);
                preparedStatement.setInt(1, newsHome[i]);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    newsTitles[i] = new JTextArea(resultSet.getString("title"));
                    newsTitles[i].setFont(new Font("Century Gothic", Font.BOLD, 20));
                    newsTitles[i].setEditable(false);
                    newsTitles[i].setWrapStyleWord(true);
                    newsTitles[i].setLineWrap(true);
                    newsTitles[i].setBorder(null);
                    panel.add(newsTitles[i]);
                    
                    image = resultSet.getBytes("image");
                    icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
                    detailsButtons[i] = new JButton(icon);
                }

                connection.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            detailsButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            int iAux = i;
            detailsButtons[i].addActionListener(
                    e -> {
                        this.dispose();
                        News.getNews(newsHome[iAux]);
                    }

            );
            panel.add(detailsButtons[i]);
        }

        newsTitles[0].setBounds(80, 347, 451, 60);
        newsTitles[1].setBounds(650, 347, 451, 60);
        newsTitles[2].setBounds(80, 647, 451, 60);
        newsTitles[3].setBounds(650, 647, 451, 60);

        detailsButtons[0].setBounds(80, 120, 451, 225);
        detailsButtons[1].setBounds(650, 120, 451, 225);
        detailsButtons[2].setBounds(80, 420, 451, 225);
        detailsButtons[3].setBounds(650, 420, 451, 225);

    }

}
