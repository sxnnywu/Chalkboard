package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

//	FIELDS --------------------------------------------------------------------------------------------------------
    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";

// 	LOAD SQLite JDBC DRIVER ---------------------------------------------------------------------------------------    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load SQLite JDBC driver");
        }
    }

// 	GET CONNECTION
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}