import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class News {
    public static void getNews(int news) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }

        JTextArea contentArea = new JTextArea();
        contentArea.setBounds(50,170,560,700);
        contentArea.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        contentArea.setEditable(false);
        contentArea.setWrapStyleWord(true);
        contentArea.setLineWrap(true);
        contentArea.setBorder(null);
        panel.add(contentArea);

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String newsQuery = "SELECT title, content, image FROM news WHERE NewsID = ?";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(newsQuery);
            preparedStatement.setInt(1, news);
            resultSet = preparedStatement.executeQuery();
            byte[] image = null;

            while(resultSet.next()) {
                JLabel title = new JLabel(resultSet.getString(1));
                title.setBounds(50, 120, 1000, 40);
                title.setFont(new Font("Century Gothic", Font.BOLD, 23));
                panel.add(title);
                contentArea.setText(resultSet.getString(2));

                image = resultSet.getBytes("image");
                ImageIcon Icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
                JLabel nPhoto = new JLabel();
                nPhoto.setIcon(Icon);
                Dimension size = nPhoto.getPreferredSize();
                nPhoto.setBounds(660, (660 - size.width)/2 + 90, size.width, size.height);
                panel.add(nPhoto);
            }

            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }
    }
}
