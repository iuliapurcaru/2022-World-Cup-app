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

        JLabel optionPaneFont = new JLabel();
        optionPaneFont.setFont(new Font("Century Gothic", Font.BOLD, 18));

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("img/logo.png");
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.setVisible(true);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension size = background.getPreferredSize();
        background.setBounds(0, 0, size.width, size.height);
        panel.add(background);
        panel.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));

        JLabel title;
        title = new JLabel("SIGN UP");
        title.setBounds(791, 120, 160, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 37));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JLabel email;
        JTextField emailField;
        email = new JLabel("Email");
        email.setBounds(791,220,110,20);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(email);
        emailField = new JTextField();
        emailField.setBounds(791,252,300,40);
        emailField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(emailField);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(791,330,110,20);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(791,362,300,40);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(791,440,110,20);
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(791,472,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JLabel confirmPassword;
        JPasswordField confirmPasswordField;
        confirmPassword = new JLabel("Confirm password");
        confirmPassword.setBounds(791,550,250,20);
        confirmPassword.setForeground(Color.WHITE);
        confirmPassword.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(confirmPassword);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(791,582,300,40);
        confirmPasswordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(confirmPasswordField);

        JButton registerButton;
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        registerButton.setBounds(791,660, 130,40);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
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
                            optionPaneFont.setText("Username is already taken!");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
                        }
                        else if (Objects.equals(usernameField.getText(), "")) {
                            optionPaneFont.setText("Username cannot be empty!");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
                        }
                        else if (resultSetEmail.next()) {
                            optionPaneFont.setText("There already exists an account with this email!");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
                        }
                        else if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                            optionPaneFont.setText("Passwords do not match!");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
                        }
                        else if (passwordField.getText() == " ") {
                            optionPaneFont.setText("Password cannot be empty!");
                            JOptionPane.showMessageDialog(null, optionPaneFont);
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

        JLabel account = new JLabel("Already have an account?");
        account.setBounds(715, 25, 250,20);
        account.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        account.setForeground(Color.WHITE);
        panel.add(account);

        JButton backButton;
        backButton = new JButton("SIGN IN");
        backButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        backButton.setBounds(970,15,130,40);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
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
