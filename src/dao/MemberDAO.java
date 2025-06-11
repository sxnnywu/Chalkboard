package dao;

import model.Member;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private final Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert member (membership)
    public void insert(Member member, String clubId) throws SQLException {
        String sql = "INSERT INTO memberships (user_id, club_id, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getUserID());
            stmt.setString(2, clubId);
            stmt.setString(3, member.getRole());
            stmt.executeUpdate();
        }
    }


    // Get all members of a club
    public List<Member> getMembersByClubId(String clubId) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT u.*, m.role FROM users u JOIN memberships m ON u.user_id = m.user_id WHERE m.club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	User user = new User(
                		    rs.getString("user_id"),
                		    rs.getString("first_name"),
                		    rs.getString("last_name"),
                		    rs.getString("username"),
                		    rs.getString("email"),
                		    rs.getString("password_hash")
                	);
                    Member member = new Member(user, rs.getString("role"));
                    members.add(member);
                }
            }
        }
        return members;
    }

    // Update member role
    public void updateRole(String userId, String clubId, String newRole) throws SQLException {
        String sql = "UPDATE memberships SET role = ? WHERE user_id = ? AND club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newRole);
            stmt.setString(2, userId);
            stmt.setString(3, clubId);
            stmt.executeUpdate();
        }
    }

    // Remove member from club
    public void delete(String userId, String clubId) throws SQLException {
        String sql = "DELETE FROM memberships WHERE user_id = ? AND club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, clubId);
            stmt.executeUpdate();
        }
    }
}
