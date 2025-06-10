package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
//	MySQL connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javaproject?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "SMar0318!";

// 	Load the MySQL JDBC driver when the class is loaded
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver");
        }
    }

// 	GET CONNECTION
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}