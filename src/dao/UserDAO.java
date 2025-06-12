package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

//  INSERT --------------------------------------------------------------------------------------------------------
    public void insert(User user) throws SQLException {
    	
//    	SQL query to insert a new user into the users table
        String sql = "INSERT INTO users (user_id, first_name, last_name, username, email, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
//        	Set parameters for the prepared statement using the user object's fields
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getUserName());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPasswordHash());
            
//        	Execute the insert operation
            stmt.executeUpdate();
        }
    }

//  GET BY ID -----------------------------------------------------------------------------------------------------
    public User getById(String userId) throws SQLException {
       
//    	SQL query to retrieve a user by their ID
        String sql = "SELECT * FROM users WHERE user_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
//        	Set the user ID parameter
            stmt.setString(1, userId);
            
//        	Execute the query and process the result
            try (ResultSet rs = stmt.executeQuery()) {
               
//            	If a result exists, create and return a User object populated with the data
                if (rs.next()) {
                    return new User(
                        rs.getString("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                    );
                }
            }
        }
//    	Return null if no user was found
        return null;
    }

//  UPDATE --------------------------------------------------------------------------------------------------------
    public void update(User user) throws SQLException {
       
//    	SQL query to update user details based on user ID
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, password_hash = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
          
//        	Set parameters from the user object to update the fields
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUserName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPasswordHash());
            stmt.setString(6, user.getUserID());
           
//         	Execute the update operation
            stmt.executeUpdate();
        }
    }

//  DELETE --------------------------------------------------------------------------------------------------------
    public void delete(String userId) throws SQLException {
        
//    	SQL query to delete a user by their ID
        String sql = "DELETE FROM users WHERE user_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
//        	Set the user ID parameter
            stmt.setString(1, userId);
            
//        	Execute the delete operation
            stmt.executeUpdate();
        }
    }

//  GET ALL -------------------------------------------------------------------------------------------------------
    public List<User> getAll() throws SQLException {
        
//    	List to store all users
    	List<User> users = new ArrayList<>(); 
        
//    	SQL query to select all users
        String sql = "SELECT * FROM users";
        
        try (
            Statement stmt = conn.createStatement(); // Create SQL statement
            ResultSet rs = stmt.executeQuery(sql)     // Execute query and store the result
        ) {
//          Iterate over the result set and create User objects
            while (rs.next()) {
                users.add(new User(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash")
                ));
            }
        }
//      Return the list of all users
        return users;
    }
}
