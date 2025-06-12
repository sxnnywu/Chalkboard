package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SampleDataLoader {
    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connected to SQLite");

            // Insert clubs
            String insertClubSQL = "INSERT INTO clubs (club_id, name, join_code) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertClubSQL)) {
                ps.setString(1, "club1");
                ps.setString(2, "Math Club");
                ps.setString(3, "MATH123");
                ps.executeUpdate();

                ps.setString(1, "club2");
                ps.setString(2, "DECA");
                ps.setString(3, "DECA456");
                ps.executeUpdate();

                ps.setString(1, "club3");
                ps.setString(2, "Breeze Code");
                ps.setString(3, "BREEZE789");
                ps.executeUpdate();
            }

            // Insert users
            String insertUserSQL = "INSERT INTO users (user_id, first_name, last_name, username, email, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertUserSQL)) {
                for (int i = 1; i <= 10; i++) {
                    ps.setString(1, "user" + i);
                    ps.setString(2, "FirstName" + i);
                    ps.setString(3, "LastName" + i);
                    ps.setString(4, "user" + i);
                    ps.setString(5, "user" + i + "@example.com");
                    ps.setString(6, "hash" + i);  // placeholder password hash
                    ps.executeUpdate();
                }
            }

            // Insert memberships (all users into all clubs)
            String insertMembershipSQL = "INSERT INTO memberships (user_id, club_id, role) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertMembershipSQL)) {
                for (int i = 1; i <= 10; i++) {
                    String userId = "user" + i;
                    for (int c = 1; c <= 3; c++) {
                        String clubId = "club" + c;
                        ps.setString(1, userId);
                        ps.setString(2, clubId);
                        ps.setString(3, "Member");
                        ps.executeUpdate();
                    }
                }
            }

            System.out.println("Sample data inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
