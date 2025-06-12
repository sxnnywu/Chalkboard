package dao;

import model.Announcement;
import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

//	CONSTRUCTOR --------------------------------------------------------------------------------------------------
    public AnnouncementDAO(Connection conn) {
        this.conn = conn;
    }

// 	INSERT ------------------------------------------------------------------------------------------------------
    public void insert(Announcement announcement, String clubId) throws SQLException {
        String sql = "INSERT INTO announcements (club_id, user_id, title, body) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.setString(2, announcement.getMember().getUserID());
            stmt.setString(3, announcement.getTitle());
            stmt.setString(4, announcement.getText());
            stmt.executeUpdate();
        }
    }

// 	GET BY CLUB ID
    public List<Announcement> getByClubId(String clubId) throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String sql = "SELECT a.*, u.first_name, u.last_name, u.username, u.email, u.password_hash, m.role " +
                     "FROM announcements a JOIN users u ON a.user_id = u.user_id " +
                     "LEFT JOIN memberships m ON a.user_id = m.user_id AND a.club_id = m.club_id " +
                     "WHERE a.club_id = ? ORDER BY a.created_at DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Member member = new Member(
                        new model.User(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            null
                        ),
                        rs.getString("role")
                    );
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

    // Deleting announcements by id could be added here...
    
// 	GET ANNOUNCEMENTS BY CLUB ID
    public List<Announcement> getAnnouncementsByClubId(String clubId) {
        List<Announcement> announcements = new ArrayList<>();
        String query = "SELECT a.*, u.first_name, u.last_name " +
                       "FROM announcements a " +
                       "JOIN users u ON a.user_id = u.user_id " +
                       "WHERE club_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clubId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Member member = new Member(
                    new model.User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        null
                    ),
                    rs.getString("role")
                );
                announcements.add(new Announcement(
                    rs.getInt("announcement_id"),
                    rs.getString("club_id"),
                    member,
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announcements;
    }
}
