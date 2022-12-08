import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Objects;

public class Register extends JFrame {
    Register() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("img/logo.png");
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.setVisible(true);

        JLabel title;
        title = new JLabel("REGISTER");
        title.setBounds(300,50,140,20);
        title.setFont(new Font("SansSerif Bold", Font.BOLD, 21));
        panel.add(title);

        JLabel email;
        JTextField emailField;
        email = new JLabel("Email");
        email.setBounds(250,108,70,20);
        //username.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(email);
        emailField = new JTextField();
        emailField.setBounds(250,127,193,28);
        panel.add(emailField);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(250,155,70,20);
        //username.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(250,174,193,28);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(250,202,70,20);
        //password.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(250,221,193,28);
        panel.add(passwordField);

        JLabel passwordConfirmation;
        JPasswordField passwordConfirmationField;
        passwordConfirmation = new JLabel("Confirm password");
        passwordConfirmation.setBounds(250,249,140,20);
        //passwordConfirmation.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(passwordConfirmation);
        passwordConfirmationField = new JPasswordField();
        passwordConfirmationField.setBounds(250,268,193,28);
        panel.add(passwordConfirmationField);

        JButton registerButton;
        registerButton = new JButton("Register");
        registerButton.setBounds(250,350,193,28);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {

                    Connection connection;
                    ResultSet resultSetUser, resultSetEmail;
                    PreparedStatement preparedStatementUser, preparedStatementEmail;
                    String selectUser = "SELECT username FROM users WHERE username = ?";
                    String selectEmail = "SELECT email FROM users WHERE email = ?";

                    try {
                        connection = DatabaseConnection.getConnection();

                        preparedStatementUser = connection.prepareStatement(selectUser);
                        preparedStatementUser.setString(1, usernameField.getText());
                        resultSetUser = preparedStatementUser.executeQuery();

                        preparedStatementEmail = connection.prepareStatement(selectEmail);
                        preparedStatementEmail.setString(1, emailField.getText());
                        resultSetEmail = preparedStatementEmail.executeQuery();

                        if (resultSetUser.next()) {
                            JOptionPane.showMessageDialog(null, "Username is already taken!");
                        }
                        else if (Objects.equals(usernameField.getText(), "")) {
                            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
                        }
                        else if (resultSetEmail.next()) {
                            JOptionPane.showMessageDialog(null, "There already exists an account with this email!");
                        }
                        else if (!Arrays.equals(passwordField.getPassword(), passwordConfirmationField.getPassword())) {
                            JOptionPane.showMessageDialog(null, "Passwords do not match!");
                        }
                        else {
                            String insertUser = "INSERT INTO users (`Username`, `Email`, `Password`, `FavouriteCountryID`) values (?, ?, ?, NULL)";
                            PreparedStatement preparedStatement;
                            preparedStatement = connection.prepareStatement(insertUser);
                            preparedStatement.setString(1, usernameField.getText());
                            preparedStatement.setString(2, emailField.getText());
                            preparedStatement.setString(3, passwordField.getText());
                            preparedStatement.executeUpdate();
                            this.dispose();
                            Login login = new Login();
                        }

                        connection.close();
                    }
                    catch (Exception err){
                        System.out.println(err);
                    }

                }

        );
        panel.add(registerButton);

        JButton backButton;
        backButton = new JButton("Back");
        backButton.setBounds(10,10,93,28);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(
                e -> {
                    this.dispose();
                    Login login = new Login();
                }

        );
        panel.add(backButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
    }
}
