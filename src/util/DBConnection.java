package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    public DBConnection() {}
    public static Connection getConnection()
    {
        try
        {
            if (connection == null || connection.isClosed())
            {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database");
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    return connection;
    }
}
