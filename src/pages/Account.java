package pages;

import awt.BuildFrame;
import awt.Buttons;
import database.DatabaseConnection;
import database.LoginCheck;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class Account {

    public static void getAccount(String username) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 18));

        JButton[] buttons = Buttons.getButtons(frame, username);
        for (int i = 0; i < 8; i++) {
            panel.add(buttons[i]);
        }
        buttons[7].setText("MY ACCOUNT");

        JLabel user = new JLabel("HELLO, " +username);
        user.setBounds(70, 140, 500, 40);
        user.setFont(new Font("Century Gothic", Font.BOLD, 33));
        user.setForeground(Color.BLACK);
        panel.add(user);

        JLabel newPassword = new JLabel("New password");
        newPassword.setBounds(70,268,800,20);
        newPassword.setForeground(Color.BLACK);
        newPassword.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(newPassword);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(70,300,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JLabel confirmNewPassword = new JLabel("Confirm new password");
        confirmNewPassword.setBounds(70,360,800,20);
        confirmNewPassword.setForeground(Color.BLACK);
        confirmNewPassword.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(confirmNewPassword);
        JPasswordField confirmPassword;
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(70,392,300,40);
        confirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(confirmPassword);

        JButton changePassword = new JButton("CHANGE PASSWORD");
        changePassword.setFont(new Font("Century Gothic", Font.BOLD, 20));
        changePassword.setBounds(70,470, 300,40);
        changePassword.setForeground(Color.WHITE);
        changePassword.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePassword.addActionListener(
                e -> {
                    if (!Objects.equals(passwordField.getText(), confirmPassword.getText())) {
                        optionPaneFont.setText("Passwords do not match!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                    }
                    else if (Objects.equals(passwordField.getText(), "")) {
                        optionPaneFont.setText("Password cannot be empty!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);
                    }
                    else {

                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String updateUser = "UPDATE users SET password = ? WHERE username = ?";
                        UIManager.put("OptionPane.minimumSize", new Dimension(100, 50));

                        try {
                            connection = DatabaseConnection.getConnection();
                            preparedStatement = connection.prepareStatement(updateUser);
                            preparedStatement.setString(1, passwordField.getText());
                            preparedStatement.setString(2, username);
                            preparedStatement.executeUpdate();

                            connection.close();
                        } catch (Exception err) {
                            err.printStackTrace();
                        }

                        optionPaneFont.setText("Password successfully changed!");
                        JOptionPane.showMessageDialog(null, optionPaneFont);

                    }

                    }
        );
        panel.add(changePassword);

        JButton deleteAccount = new JButton("DELETE ACCOUNT");
        deleteAccount.setFont(new Font("Century Gothic", Font.BOLD, 20));
        deleteAccount.setBounds(70,650, 300,40);
        deleteAccount.setForeground(Color.WHITE);
        deleteAccount.setBackground(Color.RED);
        deleteAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteAccount.addActionListener(
                e -> {
                    int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?");
                    if(input == 0) {
                        Connection connection;
                        ResultSet resultSet;
                        PreparedStatement preparedStatement;
                        String deleteUser = "DELETE FROM users WHERE username = ?";
                        UIManager.put("OptionPane.minimumSize", new Dimension(100,50));

                        try {
                            connection = DatabaseConnection.getConnection();
                            preparedStatement = connection.prepareStatement(deleteUser);
                            preparedStatement.setString(1, username);
                            preparedStatement.executeUpdate();

                            connection.close();
                        }
                        catch (Exception err){
                            err.printStackTrace();
                        }

                        frame.dispose();
                        Login.getLogin();
                    }

                }

        );

        panel.add(deleteAccount);

    }

}
