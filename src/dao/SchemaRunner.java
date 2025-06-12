package dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SchemaRunner {

//	FIELDS --------------------------------------------------------------------------------------------------------
    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";

// 	LOAD SQLite JDBC DRIVER ---------------------------------------------------------------------------------------
    static {
        try {
//         	Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLite JDBC driver not found");
        }
    }

// 	MAIN METHOD ---------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

//     	Path to the SQL schema file
        String schemaFile = "src/database/schema.sql";

        try (
            Connection conn = DriverManager.getConnection(DB_URL);  // Establish database connection
            Statement stmt = conn.createStatement()                 // Create a statement to run SQL
        ) {
            System.out.println("Connected to SQLite database.");

//         	Read the entire schema file as a single String
            String schema = new String(Files.readAllBytes(Paths.get(schemaFile)));

//         	Split the schema into individual SQL statements using semicolons
            String[] sqlStatements = schema.split(";");

//          Loop through each SQL statement
            for (String sql : sqlStatements) {
                sql = sql.trim(); // Remove any leading/trailing whitespace
                if (!sql.isEmpty()) {
                    System.out.println("Executing: " + sql); // Log the SQL being executed
                    stmt.execute(sql); // Execute the SQL statement
                }
            }
            System.out.println("Schema executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
