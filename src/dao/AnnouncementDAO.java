package dao;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public AnnouncementDAO(Connection conn) {
        this.conn = conn;
    }

// 	INSERT --------------------------------------------------------------------------------------------------------
    public void insert(Announcement announcement, String clubId) throws SQLException {
        
//    	SQL query
    	String sql = "INSERT INTO announcements (club_id, user_id, title, body) VALUES (?, ?, ?, ?)";
        
//    	Prepare & execute statement
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.setString(2, announcement.getMember().getUserID());
            stmt.setString(3, announcement.getTitle());
            stmt.setString(4, announcement.getText());
            stmt.executeUpdate();
        }
    }

// 	GET BY CLUB ID ------------------------------------------------------------------------------------------------
    public List<Announcement> getByClubId(String clubId) throws SQLException {
        
//    	List of announcements
        List<Announcement> announcements = new ArrayList<>();
        
//     	SQL query
        String sql = "SELECT a.*, u.first_name, u.last_name, u.username, u.email, u.password_hash, m.role, m.club_id " +
                     "FROM announcements a " +
                     "JOIN users u ON a.user_id = u.user_id " +
                     "LEFT JOIN memberships m ON a.user_id = m.user_id AND a.club_id = m.club_id " +
                     "WHERE a.club_id = ? ORDER BY a.created_at DESC";
        
//    	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            
//         	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    
//                	Construct User
                    User user = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        null // user_id not needed in Member
                    );
                    String role = rs.getString("role");
                    String clubIdFromResult = rs.getString("club_id"); // might be null if no membership row

//         			Fallback if LEFT JOIN returned null for membership (optional)
                    if (clubIdFromResult == null) 
                        clubIdFromResult = clubId;

                    Member member = new Member(user, role, clubIdFromResult);
                    announcements.add(new Announcement(
                        member,
                        rs.getString("title"),
                        rs.getString("body")
                    ));
                }
            }
        }
        return announcements;
    }

// 	TODO: Deleting announcements by id could be added here...
}
