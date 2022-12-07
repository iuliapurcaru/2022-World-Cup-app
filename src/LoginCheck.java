import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginCheck extends Login {

    LoginCheck(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/proiect";

        Connection connection;
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        String selectUser = "SELECT password FROM users WHERE username = ?";

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
                JOptionPane.showMessageDialog(null, "Incorrect username or password!");
            }
            connection.close();
        }
        catch (Exception r){
            System.out.println(r);
        }
    }
}

