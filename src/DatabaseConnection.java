import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/habit_tracker_db";
    private static final String USER = "root";   // your MySQL username
    private static final String PASSWORD = "shijal4062"; // your MySQL password

    public static Connection getConnection() {
        try {
            // Load the driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }
}
