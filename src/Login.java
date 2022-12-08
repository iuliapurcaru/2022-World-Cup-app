import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    public Login() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("img/logo.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);

        JLabel title;
        title = new JLabel("LOGIN");
        title.setBounds(310,50,70,20);
        title.setFont(new Font("SansSerif Bold", Font.BOLD, 21));
        panel.add(title);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(250,108,70,20);
        //username.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(250,127,193,28);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(250,155,70,20);
        //password.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(250,175,193,28);
        panel.add(passwordField);

        JButton loginButton;
        loginButton = new JButton("Login");
        loginButton.setBounds(250,210, 93,28);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(
                e -> {

                    String usernameFieldText = usernameField.getText();
                    String passwordFieldText = passwordField.getText();
                    LoginCheck check = new LoginCheck(usernameFieldText, passwordFieldText);
                    this.dispose();
                }

        );
        panel.add(loginButton);

        JButton registerButton;
        registerButton = new JButton("Register");
        registerButton.setBounds(349, 210, 93, 28);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {
                    this.dispose();
                    Register register = new Register();
                }

        );
        panel.add(registerButton);

        JButton guestButton;
        guestButton = new JButton("Continue as guest");
        guestButton.setBounds(250,245,193,28);
        guestButton.setForeground(Color.WHITE);
        guestButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestButton.addActionListener(
                e -> {
                    this.dispose();
                    Homepage homepage = new Homepage();
                }

        );
        panel.add(guestButton);

        this.setVisible(true);
    }

}