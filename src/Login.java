import java.awt.*;
import java.sql.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class Login implements ActionListener {

    JFrame frame;
    JTextArea textArea;

    public Login() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame("World Cup Qatar 2022");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("2022_FIFA_World_Cup.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        Rectangle rectangle = frame.getBounds();
        int height = rectangle.height;
        int width = rectangle.width;

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
                    frame.dispose();
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
                    frame.dispose();
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
                    frame.dispose();
                    Homepage homepage = new Homepage();
                }

        );
        panel.add(guestButton);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ev) {

        String url = "jdbc:mysql://localhost:3306/proiect";
        String username = "root";
        String password = "ED308";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection successful!");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utilizatori");

            while (resultSet.next()) {
                textArea.setText(textArea.getText().concat(
                        resultSet.getString(1) + " " +
                                resultSet.getString(2) + " " +
                                resultSet.getString(3) + " " +
                                resultSet.getInt(4) + " " +
                                resultSet.getString(5) + " " +
                                resultSet.getString(6) + " " +
                                resultSet.getString(7) + "\n"));
            }

            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

//    public static void main(String args[]) {
//        Login test = new Login();
//    }
}