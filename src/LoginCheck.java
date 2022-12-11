import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginCheck {

        public static void loginCheck(String username, String password, JFrame frame) {

            JLabel optionPaneFont = new JLabel();
            optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 18));

            Connection connection;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String selectUser = "SELECT password FROM users WHERE username = ?";
            UIManager.put("OptionPane.minimumSize", new Dimension(100,50));

            try {
                connection = DatabaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(selectUser);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next() && Objects.equals(resultSet.getString(1), password)) {
                    frame.dispose();
                    Homepage homepage = new Homepage();
                }
                else {
                    JLabel label = new JLabel("Incorrect username or password!");
                    label.setFont(new Font("Arial", Font.PLAIN, 18));
                    JOptionPane.showMessageDialog(null, label);
                }
                connection.close();
            }
            catch (Exception e){
                System.out.println(e);
            }

        }

}

