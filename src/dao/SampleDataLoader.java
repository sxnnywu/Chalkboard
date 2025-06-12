package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class SampleDataLoader {
	
//	FIELDS
    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";

// 	MAIN METHOD
    public static void main(String[] args) {
    	
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connected to SQLite");
            
//         	Wipe existing data to prevent duplicates
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM memberships");
                stmt.executeUpdate("DELETE FROM announcements");
                stmt.executeUpdate("DELETE FROM tasks");
                stmt.executeUpdate("DELETE FROM meetings");
                stmt.executeUpdate("DELETE FROM events");
                stmt.executeUpdate("DELETE FROM users");
                stmt.executeUpdate("DELETE FROM clubs");
            }

//        	Insert clubs
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

//        	Insert users
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

//        	Insert members (all users into all clubs)
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

//          Insert announcements
            String insertAnnouncementSQL = "INSERT INTO announcements (club_id, user_id, title, body) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertAnnouncementSQL)) {
                for (int c = 1; c <= 3; c++) {
                    String clubId = "club" + c;
                    for (int a = 1; a <= 5; a++) {
                        ps.setString(1, clubId);
                        ps.setString(2, "user1");  // posted by user1 for simplicity
                        ps.setString(3, "Announcement " + a + " for " + clubId);
                        ps.setString(4, "This is the body of announcement " + a + " for " + clubId + ".");
                        ps.executeUpdate();
                    }
                }
            }

//       	Insert tasks
            String insertTaskSQL = "INSERT INTO tasks (task_id, club_id, title, deadline, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertTaskSQL)) {
                for (int c = 1; c <= 3; c++) {
                    String clubId = "club" + c;
                    for (int t = 1; t <= 5; t++) {
                        String taskId = UUID.randomUUID().toString();
                        ps.setString(1, taskId);
                        ps.setString(2, clubId);
                        ps.setString(3, "Task " + t + " for " + clubId);
                        ps.setString(4, LocalDate.now().plusDays(t).toString()); // deadlines in next 5 days
                        ps.setString(5, "Not Started");
                        ps.setString(6, "Details for task " + t + " of " + clubId);
                        ps.executeUpdate();
                    }
                }
            }
            
//          Insert weekly meetings for each club
            String insertMeetingSQL = "INSERT INTO meetings (meeting_id, club_id, title, date, time, location, description, recurrence) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertMeetingSQL)) {
                for (int c = 1; c <= 3; c++) {
                    String clubId = "club" + c;

                    for (int m = 1; m <= 3; m++) {  // create 3 weekly meetings
                        String meetingId = UUID.randomUUID().toString();
                        LocalDate meetingDate = LocalDate.now().plusWeeks(m); // 1, 2, 3 weeks from now

                        ps.setString(1, meetingId);
                        ps.setString(2, clubId);
                        ps.setString(3, "Weekly Meeting " + m);
                        ps.setString(4, meetingDate.toString());
                        ps.setString(5, "15:30"); // 3:30 PM
                        ps.setString(6, "Room " + (100 + m));
                        ps.setString(7, "This is meeting #" + m + " for " + clubId);
                        ps.setString(8, "WEEKLY"); // recurrence
                        ps.executeUpdate();
                    }
                }
            }
            
//          Insert one event per club
            String insertEventSQL = "INSERT INTO events (event_id, club_id, title, date, time, location, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertEventSQL)) {
                for (int c = 1; c <= 3; c++) {
                    String clubId = "club" + c;
                    String eventId = UUID.randomUUID().toString();
                    LocalDate eventDate = LocalDate.now().plusDays(10); // 10 days from now

                    ps.setString(1, eventId);
                    ps.setString(2, clubId);
                    ps.setString(3, "Special " + clubId);
                    ps.setString(4, eventDate.toString());
                    ps.setString(5, "18:00"); // 6:00 PM
                    ps.setString(6, "Auditorium");
                    ps.setString(7, "This is a one-time event for " + clubId);
                    ps.executeUpdate();
                }
            }
            System.out.println("Sample data inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
