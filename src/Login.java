import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    public Login() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setTitle("World Cup Qatar 2022");
        this.setSize(1200, 857);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.add(panel);
        this.setContentPane(panel);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("img/background.png"));
        Dimension size = label.getPreferredSize();
        label.setBounds(0, 0, size.width, size.height);
        panel.add(label);

//        Image img = Toolkit.getDefaultToolkit().getImage("img/background.png");
//        this.setContentPane(new JPanel() {
//            @Override
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(img, 0, 0, null);
//            }
//        });

        panel.setBackground(Color.getHSBColor(233.74f, 0.97f, 0.451f));

        JLabel title;
        title = new JLabel("SIGN IN");
        title.setBounds(791, 150, 136, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(791,250,105,20);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Century Gothic", Font.BOLD, 21));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(791,280,300,38);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(791,355,100,20);
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Century Gothic", Font.BOLD, 21));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(791,385,300,38);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        panel.add(passwordField);

        JButton loginButton;
        loginButton = new JButton("SIGN IN");
        loginButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        loginButton.setBounds(791,500, 180,38);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.getHSBColor(348.92f, 0.828f, 0.6157f));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(
                e -> {
                    this.revalidate();
                    String usernameFieldText = usernameField.getText();
                    String passwordFieldText = passwordField.getText();
                    LoginCheck check = new LoginCheck(usernameFieldText, passwordFieldText);
                    this.dispose();
                }

        );
        panel.add(loginButton);

        JButton registerButton;
        registerButton = new JButton("REGISTER");
        loginButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        registerButton.setBounds(791,600, 180,38);
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
        guestButton.setBounds(844,705,193,28);
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

//    public void paint(Graphics g)
//    {
//        Image img = Toolkit.getDefaultToolkit().getImage("img/background.png");
//        g.drawImage(img, 0, 0, null);
//    }


}