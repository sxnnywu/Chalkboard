package dao;

import model.Club;

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
        String sql = "SELECT * FROM clubs WHERE club_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // For simplicity, empty lists here
                    return new Club(
                        rs.getString("name"),
                        null, null, null, null, null
                    );
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
                    rs.getString("name"),
                    null, null, null, null, null
                ));
            }
        }
        return clubs;
    }
    
//	GET CLUB ID BY NAME
    public String getClubIdByName(String name) {
        String query = "SELECT club_id FROM clubs WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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
}
