package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
    private final Connection conn;

// 	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
    public ClubDAO(Connection conn) {
        this.conn = conn;
    }

//	INSERT ---------------------------------------------------------------------------------------------------------
    public void insert(Club club) throws SQLException {
        String sql = "INSERT INTO clubs (club_id, name, join_code) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, club.getClubID());
            stmt.setString(2, club.getName());
            stmt.setString(3, club.getJoinCode());
            stmt.executeUpdate();
        }
    }

//	GET BY ID ------------------------------------------------------------------------------------------------------
    public Club getById(String clubId) throws SQLException {
        String sql = "SELECT * FROM clubs WHERE club_id = ? AND is_active = TRUE";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
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

// 	UPDATE ---------------------------------------------------------------------------------------------------------
    public void update(Club club) throws SQLException {
        String sql = "UPDATE clubs SET name = ?, join_code = ? WHERE club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, club.getName());
            stmt.setString(2, club.getJoinCode());
            stmt.setString(3, club.getClubID());
            stmt.executeUpdate();
        }
    }

//	DELETE
    public void delete(String clubId) throws SQLException {
        String sql = "DELETE FROM clubs WHERE club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.executeUpdate();
        }
    }

// 	GET ALL 
    public List<Club> getAll() throws SQLException {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT * FROM clubs";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clubs.add(new Club(
                    rs.getString("name")
                ));
            }
        }
        return clubs;
    }
    
//	GET CLUB ID BY NAME
    public String getClubIdByName(String name) {
        String query = "SELECT club_id FROM clubs WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
 
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("club_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
//	DEACTIVATE
    public void deactivate(String clubId) throws SQLException {
        String sql = "UPDATE clubs SET is_active = FALSE WHERE club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.executeUpdate();
        }
    }
    
// 	GET CLUB MEMBERS
    public List<Member> getClubMembers(String clubId) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = """
            SELECT m.role, u.user_id, u.first_name, u.last_name, u.username, u.email, u.password_hash
            FROM members m
            JOIN users u ON m.user_id = u.user_id
            WHERE m.club_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Build User object
                    User user = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                    );
                    user.setUserID(rs.getString("user_id"));

                    // Build Member object
                    Member member = new Member(user, rs.getString("role"));

                    members.add(member);
                }
            }
        }

        return members;
    }

    // 2. Add a Member to Club
    public void addMember(String clubId, String userId, String role) throws SQLException {
        String sql = "INSERT INTO members (user_id, club_id, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, clubId);
            stmt.setString(3, role);
            stmt.executeUpdate();
        }
    }

    // 3. Remove Member from Club
    public void removeMember(String clubId, String userId) throws SQLException {
        String sql = "DELETE FROM members WHERE club_id = ? AND user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        }
    }

    // 4. Update Member Role
    public void updateMemberRole(String clubId, String userId, String newRole) throws SQLException {
        String sql = "UPDATE members SET role = ? WHERE club_id = ? AND user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newRole);
            stmt.setString(2, clubId);
            stmt.setString(3, userId);
            stmt.executeUpdate();
        }
    }

    // 5. Get Clubs for a Specific User
    public List<Club> getClubsByUser(String userId) throws SQLException {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT c.* FROM clubs c " +
                     "JOIN members m ON c.club_id = m.club_id " +
                     "WHERE m.user_id = ? AND c.is_active = TRUE";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
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

    // 6. Validate Join Code
    public String validateJoinCode(String joinCode) throws SQLException {
        String sql = "SELECT club_id FROM clubs WHERE join_code = ? AND is_active = TRUE";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, joinCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("club_id");
                }
            }
        }
        return null;
    }
}
