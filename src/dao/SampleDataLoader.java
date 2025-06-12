package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class SampleDataLoader {

//	FIELDS --------------------------------------------------------------------------------------------------------
    private static final String DB_URL = "jdbc:sqlite:chalkboard.db";

// 	MAIN METHOD ---------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
    	
//    	Connection to SQLite
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connected to SQLite");

//         	Clear all existing data from all tables to avoid duplicates
            clearAllData(conn);

//         	Insert sample data into each table
            insertClubs(conn);
            insertUsers(conn);
            insertMemberships(conn);
            insertAnnouncements(conn);
            insertTasks(conn);
            insertMeetings(conn);
            insertEvents(conn);

            System.out.println("Sample data inserted successfully.");
            
//    	Print any errors
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// 	CLEAR ALL DATA ------------------------------------------------------------------------------------------------
    private static void clearAllData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM memberships");
            stmt.executeUpdate("DELETE FROM announcements");
            stmt.executeUpdate("DELETE FROM tasks");
            stmt.executeUpdate("DELETE FROM meetings");
            stmt.executeUpdate("DELETE FROM events");
            stmt.executeUpdate("DELETE FROM users");
            stmt.executeUpdate("DELETE FROM clubs");
        }
    }

// 	INSERT CLUBS --------------------------------------------------------------------------------------------------
    private static void insertClubs(Connection conn) throws SQLException {
        
//    	SQL query to insert into clubs table
    	String sql = "INSERT INTO clubs (club_id, name, join_code) VALUES (?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
    		
//    		Club 1
            ps.setString(1, "club1");
            ps.setString(2, "Math Club");
            ps.setString(3, "MATH123");
            ps.executeUpdate();

//         	Club 2
            ps.setString(1, "club2");
            ps.setString(2, "DECA");
            ps.setString(3, "DECA456");
            ps.executeUpdate();

//         	Club 3
            ps.setString(1, "club3");
            ps.setString(2, "Breeze Code");
            ps.setString(3, "BREEZE789");
            ps.executeUpdate();
        }
    }

// 	INSERT USERS --------------------------------------------------------------------------------------------------
    private static void insertUsers(Connection conn) throws SQLException {
       
//    	SQL query to insert into users 
    	String sql = "INSERT INTO users (user_id, first_name, last_name, username, email, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 1; i <= 10; i++) {
                ps.setString(1, "user" + i);
                ps.setString(2, "FirstName" + i);
                ps.setString(3, "LastName" + i);
                ps.setString(4, "user" + i);
                ps.setString(5, "user" + i + "@example.com");
                ps.setString(6, "hash" + i); // Placeholder password hash
                ps.executeUpdate();
            }
        }
    }

//  INSERT MEMBERS ------------------------------------------------------------------------------------------------
    private static void insertMemberships(Connection conn) throws SQLException {
        
//    	SQL query to insert into members table
    	String sql = "INSERT INTO memberships (user_id, club_id, role) VALUES (?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
    }

//  INSERT ANNOUNCEMENTS ------------------------------------------------------------------------------------------
    private static void insertAnnouncements(Connection conn) throws SQLException {
        
//    	SQl query to insert into announcements table
    	String sql = "INSERT INTO announcements (club_id, user_id, title, body) VALUES (?, ?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int c = 1; c <= 3; c++) {
                String clubId = "club" + c;
                for (int a = 1; a <= 5; a++) {
                    ps.setString(1, clubId);
                    ps.setString(2, "user1"); // Posted by user1 for simplicity
                    ps.setString(3, "Announcement " + a + " for " + clubId);
                    ps.setString(4, "This is the body of announcement " + a + " for " + clubId + ".");
                    ps.executeUpdate();
                }
            }
        }
    }

// 	INSERT TASKS --------------------------------------------------------------------------------------------------
    private static void insertTasks(Connection conn) throws SQLException {
        
//    	SQL query to insert into tasks table
    	String sql = "INSERT INTO tasks (task_id, club_id, title, deadline, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int c = 1; c <= 3; c++) {
                String clubId = "club" + c;
                for (int t = 1; t <= 5; t++) {
                    String taskId = UUID.randomUUID().toString();
                    ps.setString(1, taskId);
                    ps.setString(2, clubId);
                    ps.setString(3, "Task " + t + " for " + clubId);
                    ps.setString(4, LocalDate.now().plusDays(t).toString()); // Deadline t days from now
                    ps.setString(5, "Not Started");
                    ps.setString(6, "Details for task " + t + " of " + clubId);
                    ps.executeUpdate();
                }
            }
        }
    }

// 	INSERT MEETINGS -----------------------------------------------------------------------------------------------
    private static void insertMeetings(Connection conn) throws SQLException {
        
//    	SQL query to insert into meetings table
    	String sql = "INSERT INTO meetings (meeting_id, club_id, title, date, time, location, description, recurrence) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int c = 1; c <= 3; c++) {
                String clubId = "club" + c;

                for (int m = 1; m <= 3; m++) { // 3 weekly meetings
                    String meetingId = UUID.randomUUID().toString();
                    LocalDate meetingDate = LocalDate.now().plusWeeks(m); // 1,2,3 weeks from now

                    ps.setString(1, meetingId);
                    ps.setString(2, clubId);
                    ps.setString(3, "Weekly Meeting " + m);
                    ps.setString(4, meetingDate.toString());
                    ps.setString(5, "15:30"); // 3:30 PM fixed time
                    ps.setString(6, "Room " + (100 + m));
                    ps.setString(7, "This is meeting #" + m + " for " + clubId);
                    ps.setString(8, "WEEKLY"); // recurrence type
                    ps.executeUpdate();
                }
            }
        }
    }

//	INSERT EVENTS -------------------------------------------------------------------------------------------------
    private static void insertEvents(Connection conn) throws SQLException {
    	
//    	SQL query to insert into events table
        String sql = "INSERT INTO events (event_id, club_id, title, date, time, location, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
//     	Prepare & execute statement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int c = 1; c <= 3; c++) {
                String clubId = "club" + c;
                String eventId = UUID.randomUUID().toString();
                LocalDate eventDate = LocalDate.now().plusDays(10); // 10 days from now

                ps.setString(1, eventId);
                ps.setString(2, clubId);
                ps.setString(3, "Special " + clubId);
                ps.setString(4, eventDate.toString());
                ps.setString(5, "18:00"); // 6 PM
                ps.setString(6, "Auditorium");
                ps.setString(7, "This is a one-time event for " + clubId);
                ps.executeUpdate();
            }
        }
    }
}
