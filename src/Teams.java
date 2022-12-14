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

        JTextArea tf = new JTextArea();
        tf.setBounds(50,170,1000,700);
        tf.setFont(new Font("Century Gothic", Font.BOLD, 20));
        tf.setEditable(false);
        tf.setWrapStyleWord(true);
        tf.setLineWrap(true);
        tf.setBorder(null);
        panel.add(tf);

        try {
            Connection connection;
            ResultSet resultSet;
            Statement statement;
            String teamsQuery = "SELECT denumire FROM teams ORDER BY denumire";

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(teamsQuery);

            while(resultSet.next()) {
                tf.setText(tf.getText().concat(
                        resultSet.getString(1) + "\t\t"));
            }

            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }

    }

}
