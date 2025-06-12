package dao;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;
    private UserDAO userDAO;

// 	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public MemberDAO(Connection conn) {
        this.conn = conn;
        userDAO = new UserDAO(conn);
    }

// 	INSERT -------------------------------------------------------------------------------------------------------
    public void insert(Member member, String clubId) throws SQLException {
        
//    	SQL query to insert a new membership record 
        String sql = "INSERT INTO memberships (user_id, club_id, role) VALUES (?, ?, ?)";
        
//     	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getUserID());
            stmt.setString(2, clubId);
            stmt.setString(3, member.getRole());
            stmt.executeUpdate();
        }
    }

// 	GET MEMBERS BY CLUB ID ---------------------------------------------------------------------------------------
    public List<Member> getMembersByClubId(String clubId) throws SQLException {
        
//    	Store members here
        List<Member> members = new ArrayList<>();
        
//    	SQL query
        String sql = "SELECT u.*, m.role, m.club_id " +
                     "FROM users u JOIN memberships m ON u.user_id = m.user_id " +
                     "WHERE m.club_id = ?";
        
//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            
//         	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	
//                	Build User object
                    User user = new User(
                        rs.getString("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                    );
                    
//                	Create new member from user + add to list
                    String role = rs.getString("role");
                    String clubIDFromResult = rs.getString("club_id");                   
                    Member member = new Member(user, role, clubIDFromResult);
                    members.add(member);
                }
            }
        }       
        return members;
    }

//	UPDATE ROLE --------------------------------------------------------------------------------------------------
    public void updateRole(String userId, String clubId, String newRole) throws SQLException {
        
//    	SQL query to update role
        String sql = "UPDATE memberships SET role = ? WHERE user_id = ? AND club_id = ?";
        
//     	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newRole);
            stmt.setString(2, userId);
            stmt.setString(3, clubId);
            stmt.executeUpdate();
        }
    }

// 	REMOVE MEMBER FROM CLUB --------------------------------------------------------------------------------------
    public void delete(String userId, String clubId) throws SQLException {
        
//    	SQL query to delete a membership
        String sql = "DELETE FROM memberships WHERE user_id = ? AND club_id = ?";
        
//     	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, clubId);
            stmt.executeUpdate();
        }
    }

//	GET CLUBS BY USER ID -----------------------------------------------------------------------------------------
    public List<Member> getClubsByUserId(String currentUserID) {
    	
//    	Members list
    	List<Member> memberships = new ArrayList<>();
    	
//    	SQL query
    	String sql = "SELECT * FROM memberships WHERE user_id = ?";
    	
//    	Prepare statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    		stmt.setString(1, currentUserID);
    		
//    		Execute query
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			String clubId = rs.getString("club_id");
    			String role = rs.getString("role");
    			
//    			Fetch the full User object
    			User user = userDAO.getById(currentUserID); 
    			
//    			Create the Member object
    			Member member = new Member(user, role, clubId);
    			memberships.add(member);
    		}	
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace(); 
    	}
    	return memberships;
    }
    
//	EXISTS -------------------------------------------------------------------------------------------------------
    public boolean exists(String userId, String clubId) throws SQLException {
        
//    	SQL query
    	String query = "SELECT 1 FROM Memberships WHERE user_id = ? AND club_id = ?";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setString(2, clubId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if found
        }
    }
}
