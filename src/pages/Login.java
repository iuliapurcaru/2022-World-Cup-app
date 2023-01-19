package pages;

import database.LoginCheck;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    public static void getLogin() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        panel.setLayout(null);

        frame.setTitle("World Cup Qatar 2022");
        frame.setSize(1200, 857);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension size = background.getPreferredSize();
        background.setBounds(0, 0, size.width, size.height);
        panel.add(background);
        panel.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.401f));

        JLabel title;
        title = new JLabel("SIGN IN");
        title.setBounds(791, 150, 136, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 37));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(791,250,110,20);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(791,282,300,40);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(791,360,110,20);
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Century Gothic", Font.BOLD, 20));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(791,392,300,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        panel.add(passwordField);

        JButton signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        signInButton.setBounds(791,470, 130,40);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> LoginCheck.loginCheck(usernameField.getText(), String.valueOf(passwordField.getPassword()), frame)

        );
        panel.add(signInButton);

        JLabel noAccount = new JLabel("Don't have an account?");
        noAccount.setBounds(791, 548, 250,20);
        noAccount.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        noAccount.setForeground(Color.WHITE);
        panel.add(noAccount);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        registerButton.setBounds(791,588, 130,40);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.getHSBColor(190.74f, 0.6909f, 0.516f));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {
                    frame.dispose();
                    Register.getRegister();
                }

        );
        panel.add(registerButton);

        frame.setVisible(true);
    }

}