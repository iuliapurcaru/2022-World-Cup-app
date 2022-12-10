import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginCheck extends Login {

    LoginCheck(String username, String password) {

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
                this.dispose();
                Homepage homepage = new Homepage();
            }
            else {
                JLabel label = new JLabel("Incorrect username or password!");
                label.setFont(new Font("Arial", Font.PLAIN, 18));
                JOptionPane.showMessageDialog(null, label);
            }
            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }
    }
}

