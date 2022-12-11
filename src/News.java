import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class News extends JFrame{
    News() {

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

        JTextArea tf = new JTextArea();
        tf.setBounds(50,200,500,200);
        tf.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        tf.setEditable(false);
        tf.setWrapStyleWord(true);
        tf.setLineWrap(true);
        tf.setBorder(null);
        panel.add(tf);

        try {
            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String selectUser = "SELECT title, content, image FROM news WHERE NewsID = 200";

            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectUser);
            resultSet = preparedStatement.executeQuery();
            byte[] image = null;

            while(resultSet.next()) {
                JLabel title = new JLabel(resultSet.getString(1));
                title.setBounds(50, 150, 1000, 40);
                title.setFont(new Font("Century Gothic", Font.BOLD, 23));
                panel.add(title);

                tf.setText(resultSet.getString(2));

                image = resultSet.getBytes("image");
                Image img = Toolkit.getDefaultToolkit().createImage(image);
                ImageIcon Icon = new ImageIcon(img);
                JLabel lPhoto = new JLabel();
                lPhoto.setIcon(Icon);
                Dimension size = lPhoto.getPreferredSize();
                lPhoto.setBounds(900, 200, size.width, size.height);
                panel.add(lPhoto);
            }

            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }
    }
}
