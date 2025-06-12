package dao;

import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

// 	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public ClubDAO(Connection conn) {
        this.conn = conn;
    }

//	INSERT --------------------------------------------------------------------------------------------------------
    public void insert(Club club) throws SQLException {
        
//    	SQL query to insert into clubs table
    	String sql = "INSERT INTO clubs (club_id, name, join_code) VALUES (?, ?, ?)";
    	
//    	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, club.getClubID());
            stmt.setString(2, club.getName());
            stmt.setString(3, club.getJoinCode());
            stmt.executeUpdate();
        }
    }

//	GET BY ID -----------------------------------------------------------------------------------------------------
    public Club getById(String clubId) throws SQLException {
    	
//    	SQL query
        String sql = "SELECT * FROM clubs WHERE club_id = ? AND is_active = TRUE";
        
//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            
//         	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	Club club = new Club(
                		    rs.getString("club_id"),
                		    rs.getString("name"),
                		    rs.getString("join_code")
                	);
                    club.setClubID(rs.getString("club_id")); // Preserve ID
                    club.setJoinCode(rs.getString("join_code"));
                    return club;
                }
            }
        }
        return null;
    }

// 	UPDATE --------------------------------------------------------------------------------------------------------
    public void update(Club club) throws SQLException {
        
//    	SQL query
    	String sql = "UPDATE clubs SET name = ?, join_code = ? WHERE club_id = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, club.getName());
            stmt.setString(2, club.getJoinCode());
            stmt.setString(3, club.getClubID());
            stmt.executeUpdate();
        }
    }

//	DELETE --------------------------------------------------------------------------------------------------------
    public void delete(String clubId) throws SQLException {
        
//    	SQL query to delete from clubs table
    	String sql = "DELETE FROM clubs WHERE club_id = ?";
    	
//    	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.executeUpdate();
        }
    }

// 	GET ALL -------------------------------------------------------------------------------------------------------
    public List<Club> getAll() throws SQLException {
    	
//    	List of clubs will be stored here
        List<Club> clubs = new ArrayList<>();
        
//     	SQL query
        String sql = "SELECT * FROM clubs";
        
//     	Prepare & execute statement
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
//        	Loop through results, add clubs to list
        	while (rs.next()) {
                clubs.add(new Club(
                    rs.getString("name")
                ));
            }
        }
        return clubs;
    }
    
//	GET CLUB ID BY NAME -------------------------------------------------------------------------------------------
    public String getClubIdByName(String name) {
        
//    	SQL query
    	String query = "SELECT club_id FROM clubs WHERE name = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            
//        	Return club ID if found
            if (rs.next()) 
                return rs.getString("club_id");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
//    	If nothing was found
        return null;
    }
       
//	DEACTIVATE ----------------------------------------------------------------------------------------------------
    public void deactivate(String clubId) throws SQLException {
        
//    	SQL query
    	String sql = "UPDATE clubs SET is_active = FALSE WHERE club_id = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.executeUpdate();
        }
    }
    
// 	GET CLUB MEMBERS ----------------------------------------------------------------------------------------------
    public List<Member> getClubMembers(String clubId) throws SQLException {
        
//    	Members will be stored here
    	List<Member> members = new ArrayList<>();
        
//    	SQL query
    	String sql = """
            SELECT m.role, u.user_id, u.first_name, u.last_name, u.username, u.email, u.password_hash
            FROM members m
            JOIN users u ON m.user_id = u.user_id
            WHERE m.club_id = ?
        """;

//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            
//        	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    
//                	Build User object
                    User user = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                    );
                    user.setUserID(rs.getString("user_id"));

//                 	Build Member object and add it to the list
                    Member member = new Member(user, rs.getString("role"), rs.getString("club_id"));
                    members.add(member);
                }
            }
        }
        return members;
    }

// 	ADD MEMBER ----------------------------------------------------------------------------------------------------
    public void addMember(String clubId, String userId, String role) throws SQLException {
        
//    	SQL query to insert into members table
    	String sql = "INSERT INTO members (user_id, club_id, role) VALUES (?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, clubId);
            stmt.setString(3, role);
            stmt.executeUpdate();
        }
    }

// 	REMOVE MEMBER -------------------------------------------------------------------------------------------------
    public void removeMember(String clubId, String userId) throws SQLException {
        
//    	SQL query to delete from members table
    	String sql = "DELETE FROM members WHERE club_id = ? AND user_id = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        }
    }
   
// 	UPDATE MEMBER ROLE --------------------------------------------------------------------------------------------
    public void updateMemberRole(String clubId, String userId, String newRole) throws SQLException {
        
//    	SQL query
    	String sql = "UPDATE members SET role = ? WHERE club_id = ? AND user_id = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newRole);
            stmt.setString(2, clubId);
            stmt.setString(3, userId);
            stmt.executeUpdate();
        }
    }

// 	GET CLUBS BY USER ---------------------------------------------------------------------------------------------
    public List<Club> getClubsByUser(String userId) throws SQLException {
        
//    	Clubs will be stored here
    	List<Club> clubs = new ArrayList<>();
    	
//    	SQL query
        String sql = "SELECT c.* FROM clubs c " +
                     "JOIN members m ON c.club_id = m.club_id " +
                     "WHERE m.user_id = ? AND c.is_active = TRUE";        
        
//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            
//         	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                
//            	Loop through results and add clubs to list
            	while (rs.next()) {
                    Club club = new Club(rs.getString("name"));
                    club.setClubID(rs.getString("club_id"));
                    club.setJoinCode(rs.getString("join_code"));
                    clubs.add(club);
                }
            }
        }
        return clubs;
    }

// 	VALIDATE JOIN CODE --------------------------------------------------------------------------------------------
    public String validateJoinCode(String joinCode) throws SQLException {
        
//    	SQL query
    	String sql = "SELECT club_id FROM clubs WHERE join_code = ? AND is_active = TRUE";
        
//    	Prepare statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, joinCode);
            
//        	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
            	
//            	If a club was found, return its id
                if (rs.next())
                    return rs.getString("club_id");
            }
        }
//    	If no club was found
        return null;
    }
    
// 	GET NEXT MEETING TIME -----------------------------------------------------------------------------------------
    public String getNextMeetingTime(String clubId) {
        
//    	SQL query
    	String query = """
            SELECT date, time FROM meetings
            WHERE club_id = ?
            ORDER BY date ASC, time ASC
            LIMIT 1
        """;

//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, clubId);
            
//         	Execute statement
            ResultSet rs = stmt.executeQuery();
            
//         	If there is an upcoming meeting
            if (rs.next()) {
                String date = rs.getString("date"); 
                String time = rs.getString("time");

//            	Parse and format
                LocalDateTime dateTime;
                
//             	Check if time is empty/null
                if (time != null && !time.isEmpty()) 
                    dateTime = LocalDateTime.parse(date + "T" + time);
                else 
                    dateTime = LocalDateTime.parse(date + "T00:00");  // default to midnight

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d 'at' h:mma");
                return dateTime.format(formatter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//    	If no upcoming meetings were found
        return "No upcoming meeting";
    }

// 	GET JOIN CODE -------------------------------------------------------------------------------------------------
    public String getJoinCode(String clubId) {
    	
//    	SQL query
        String query = "SELECT join_code FROM clubs WHERE club_id = ?";
        
//     	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, clubId);
            
//         	Execute statement
            ResultSet rs = stmt.executeQuery();
            
//         	If a club was found, return its join code
            if (rs.next()) 
                return rs.getString("join_code");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//     	If no club was found
        return "Unavailable";
    }
}
