package uz.falconmobile.uz.connection;


import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "maqsad05"; // o'zgartiring

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
