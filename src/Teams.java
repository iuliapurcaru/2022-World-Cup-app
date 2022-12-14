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

//        JTextArea tf = new JTextArea();
//        tf.setBounds(50,170,1000,700);
//        tf.setFont(new Font("Century Gothic", Font.BOLD, 20));
//        tf.setEditable(false);
//        tf.setWrapStyleWord(true);
//        tf.setLineWrap(true);
//        tf.setBorder(null);
//        panel.add(tf);

        int size = 0;
        int align = 0;
        int count = 0;
        JLabel[] teams = new JLabel[32];
        JLabel nPhoto = new JLabel();
        String arg = "ARG";

        try {
            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String teamsQuery = "SELECT denumire FROM teams ORDER BY denumire";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(teamsQuery);

            byte[] image = null;

            while(resultSet.next()) {
                teams[size] = new JLabel(resultSet.getString(1));
                size++;

            }

            String Query = "SELECT flag FROM teams WHERE TaraID = 'ARG'";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Query);

            while(resultSet.next()) {

                image = resultSet.getBytes("flag");
                icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
                JButton button = new JButton(icon);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                button.setBounds(30, 115, 94, 62);
                panel.add(button);



//                image = resultSet.getBytes("flag");
//                ImageIcon Icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(image));
//                nPhoto.setIcon(Icon);
//                Dimension size2 = nPhoto.getPreferredSize();
//                nPhoto.setBounds(30, 115, size2.width, size2.height);
//                panel.add(button);
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        for(int i = 0; i < 5; i++) {

            for(int j = 0; (j < 7) && (count < 32) ; j++) {

                teams[count].setBounds(30 + j * 170, 180 + align, 130, 40);
                teams[count].setFont(new Font("Century Gothic", Font.BOLD, 18));
                panel.add(teams[count]);
                count++;
            }
            align += 120;
        }

    }

}
