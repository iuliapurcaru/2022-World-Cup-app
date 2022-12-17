package database;

import database.DatabaseConnection;
import pages.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class RegisterAccount {
    
    public static void registerAccount(String username, String email, String password, String confirmPassword, JFrame frame) {

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 18));

        Connection connection;
        ResultSet resultSetUser, resultSetEmail;
        PreparedStatement preparedStatementUser, preparedStatementEmail;
        String selectUser = "SELECT username FROM users WHERE username = ?";
        String selectEmail = "SELECT email FROM users WHERE email = ?";

        try {
            connection = DatabaseConnection.getConnection();

            preparedStatementUser = connection.prepareStatement(selectUser);
            preparedStatementUser.setString(1, username);
            resultSetUser = preparedStatementUser.executeQuery();

            preparedStatementEmail = connection.prepareStatement(selectEmail);
            preparedStatementEmail.setString(1, email);
            resultSetEmail = preparedStatementEmail.executeQuery();

            if (resultSetEmail.next()) {
                optionPaneFont.setText("There already exists an account with this email!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else if (Objects.equals(email, "")) {
                optionPaneFont.setText("Email cannot be empty!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else if (resultSetUser.next()) {
                optionPaneFont.setText("Username is already taken!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else if (Objects.equals(username, "")) {
                optionPaneFont.setText("Username cannot be empty!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else if (!Objects.equals(password, confirmPassword)) {
                optionPaneFont.setText("Passwords do not match!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else if (Objects.equals(password, "")) {
                optionPaneFont.setText("Password cannot be empty!");
                JOptionPane.showMessageDialog(null, optionPaneFont);
            }
            else {
                String insertUser = "INSERT INTO users (`Username`, `Email`, `Password`) values (?, ?, ?)";
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(insertUser);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
                frame.dispose();
                Login.getLogin();
            }

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
