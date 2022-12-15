import javax.swing.*;
import java.awt.*;

public class Register {
    public static void getRegister() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

//        frame.setTitle("World Cup Qatar 2022");
//        frame.setSize(1200, 857);
//        frame.setLocationRelativeTo(null);
//        ImageIcon img = new ImageIcon("img/logo.png");
//        frame.setIconImage(img.getImage());
//        frame.setResizable(false);
//        frame.setVisible(true);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension size = background.getPreferredSize();
        background.setBounds(0, 0, size.width, size.height);
        panel.add(background);
        panel.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));

        JLabel title;
        title = new JLabel("SIGN UP");
        title.setBounds(791, 100, 160, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 37));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JLabel email;
        JTextField emailField;
        email = new JLabel("Email");
        email.setBounds(791,190,110,20);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(email);
        emailField = new JTextField();
        emailField.setBounds(791,222,300,40);
        emailField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(emailField);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(791,300,110,20);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(791,332,300,40);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(791,410,110,20);
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(791,442,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JLabel confirmPassword;
        JPasswordField confirmPasswordField;
        confirmPassword = new JLabel("Confirm password");
        confirmPassword.setBounds(791,520,250,20);
        confirmPassword.setForeground(Color.WHITE);
        confirmPassword.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(confirmPassword);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(791,552,300,40);
        confirmPasswordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(confirmPasswordField);

        JButton registerButton;
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        registerButton.setBounds(791,630, 130,40);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {

                    RegisterAccount.registerAccount(usernameField.getText(),
                                                    emailField.getText(),
                                                    passwordField.getText(),
                                                    confirmPasswordField.getText(),
                                              frame);
                }

        );
        panel.add(registerButton);

        JLabel account = new JLabel("Already have an account?");
        account.setBounds(740, 710, 250,20);
        account.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        account.setForeground(Color.WHITE);
        panel.add(account);

        JButton signInButton;
        signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        signInButton.setBounds(995,700,130,40);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> {
                    frame.dispose();
                    Login.getLogin();
                }

        );
        panel.add(signInButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }
}
