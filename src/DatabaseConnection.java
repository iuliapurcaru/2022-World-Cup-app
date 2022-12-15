import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/proiect", "root", "ED308"
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
