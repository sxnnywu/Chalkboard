package dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SchemaRunner {

    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLite JDBC driver not found");
        }
    }

// 	MAIN METHOD
    public static void main(String[] args) {
        String schemaFile = "src/database/schema.sql";  

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connected to SQLite database.");

            // Read entire schema file as a String
            String schema = new String(Files.readAllBytes(Paths.get(schemaFile)));

            // Split SQL statements by semicolon, assuming each ends with ;
            String[] sqlStatements = schema.split(";");

            for (String sql : sqlStatements) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    System.out.println("Executing: " + sql);
                    stmt.execute(sql);
                }
            }

            System.out.println("Schema executed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

