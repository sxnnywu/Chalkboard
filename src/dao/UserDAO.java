package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert new user
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, first_name, last_name, username, email, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getUserName());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPasswordHash());
            stmt.executeUpdate();
        }
    }

    // Get user by ID
    public User getById(String userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        null  // joinedClubs - you may fill this later
                    );
                }
            }
        }
        return null;
    }

    // Update user (example: email and password)
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, password_hash = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUserName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPasswordHash());
            stmt.setString(6, user.getUserID());
            stmt.executeUpdate();
        }
    }

    // Delete user
    public void delete(String userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    }

    // Get all users
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    null
                ));
            }
        }
        return users;
    }
}
