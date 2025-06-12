package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

// 	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public TaskDAO(Connection conn) {
        this.conn = conn;
    }

// 	INSERT --------------------------------------------------------------------------------------------------------
    public void insert(Task task) throws SQLException {
    	
//    	SQL query to insert task data into the tasks table
        String sql = "INSERT INTO tasks (task_id, club_id, title, deadline, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
//        	Set the values from the Task object
            stmt.setString(1, task.getTaskID());
            stmt.setString(2, task.getClubID());
            stmt.setString(3, task.getTitle());
            stmt.setDate(4, Date.valueOf(task.getDeadline())); // Convert LocalDate to SQL Date
            stmt.setString(5, task.getStatus());
            stmt.setString(6, task.getNotes());
            
//       	Execute the insert command
            stmt.executeUpdate();
        }

//      SQL query to insert assignee relationships into task_assignees table
        String assigneeSql = "INSERT INTO task_assignees (task_id, user_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(assigneeSql)) {
            
//        	For each assigned member, create a batch insert entry
            for (Member m : task.getAssignees()) {
                stmt.setString(1, task.getTaskID());
                stmt.setString(2, m.getUserID());
                stmt.addBatch(); // Add to batch
            }
//        	Execute all batch insert operations
            stmt.executeBatch();
        }
    }

// 	GET BY ID -----------------------------------------------------------------------------------------------------
    public Task getById(String taskId) throws SQLException {
    	
//    	SQL query to retrieve the task record
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        Task task = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taskId); // Set task ID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                
//            	If a result is found, create the base Task object
                if (rs.next()) {
                    task = new Task(
                        rs.getString("title"),
                        rs.getDate("deadline").toLocalDate(), // Convert SQL Date to LocalDate
                        new ArrayList<>(), // Placeholder for assignees to load separately
                        rs.getString("status"),
                        rs.getString("notes"),
                        rs.getString("club_id")
                    );
//                 	Set task ID after object construction
                    task.setTaskID(taskId);
                }
            }
        }

//     	If task was found, retrieve its list of assignees
        if (task != null) 
        	task.setAssignees(getAssigneesForTask(taskId));
        return task;
    }
    
// 	GET ASSIGNEES FOR TASK ----------------------------------------------------------------------------------------
    private List<Member> getAssigneesForTask(String taskId) throws SQLException {
	    
//    	List to hold the assignees
    	List<Member> assignees = new ArrayList<>();

//    	SQL query to retrieve all users assigned to the task
    	String assigneesSql = "SELECT u.*, m.role, m.club_id " +
    	                      "FROM users u " +
    	                      "JOIN memberships m ON u.user_id = m.user_id " +
    	                      "WHERE u.user_id IN (SELECT user_id FROM task_assignees WHERE task_id = ?)";

//    	Prepare and execute the query
    	try (PreparedStatement stmt = conn.prepareStatement(assigneesSql)) {
    		stmt.setString(1, taskId); 

    		try (ResultSet rs = stmt.executeQuery()) {
    			
//    			Iterate over the result set to build Member objects
    			while (rs.next()) {
    				
//    				Construct a User object
    				model.User user = new model.User(
    						rs.getString("user_id"),
    						rs.getString("first_name"),
    						rs.getString("last_name"),
    						rs.getString("username"),
    						rs.getString("email"),
    						rs.getString("password_hash")
    					);
    					String role = rs.getString("role");
    					String clubID = rs.getString("club_id");
    					Member member = new Member(user, role, clubID);
    				assignees.add(member);
    			}
    		}
    	}
//    	Completed list of assignees
    	return assignees;
    }
    
// 	GET TASKS BY CLUB ID ------------------------------------------------------------------------------------------
    public List<Task> getTasksByClubId(String clubId) {
    	
//    	Tasks associated with the given club
        List<Task> tasks = new ArrayList<>();

//    	SQL query to retrieve all tasks for the given club ID
        String query = "SELECT * FROM tasks WHERE club_id = ?";

        try (
            Connection conn = DBUtil.getConnection(); // Get database connection
            PreparedStatement stmt = conn.prepareStatement(query) // Prepare SQL statement
        ) {
            stmt.setString(1, clubId); // Set club ID in the query
            ResultSet rs = stmt.executeQuery(); // Execute the query

            while (rs.next()) {
//            	Construct Task object from result set
                Task task = new Task(
                    rs.getString("task_id"),
                    rs.getString("title"),
                    LocalDate.parse(rs.getString("deadline")),
                    rs.getString("status"),
                    rs.getString("notes"),
                    rs.getString("club_id")
                );
//             	Add task to the list
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    
// 	UPDATE TASK ---------------------------------------------------------------------------------------------------
    public void update(Task task) throws SQLException {

//	 	SQL query to update task information
	    String sql = "UPDATE tasks SET title = ?, deadline = ?, status = ?, notes = ?, club_id = ? WHERE task_id = ?";
	
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	
//	    	Set updated values from the Task object
	        stmt.setString(1, task.getTitle());
	        stmt.setDate(2, Date.valueOf(task.getDeadline())); // Convert LocalDate to SQL Date
	        stmt.setString(3, task.getStatus());
	        stmt.setString(4, task.getNotes());
	        stmt.setString(5, task.getClubID());
	        stmt.setString(6, task.getTaskID());
	
//	     	Execute update command
	        stmt.executeUpdate();
	    }
    }
    
// 	DELETE TASK ---------------------------------------------------------------------------------------------------
    public void delete(String taskId) throws SQLException {

//	    Delete assignees from the task_assignees table
	    String deleteAssigneesSql = "DELETE FROM task_assignees WHERE task_id = ?";	
	    try (PreparedStatement stmt = conn.prepareStatement(deleteAssigneesSql)) {
	        stmt.setString(1, taskId);
	        stmt.executeUpdate();
	    }
	
//	    Delete the task from the tasks table
	    String deleteTaskSql = "DELETE FROM tasks WHERE task_id = ?";	
	    try (PreparedStatement stmt = conn.prepareStatement(deleteTaskSql)) {
	        stmt.setString(1, taskId);
	        stmt.executeUpdate();
	    }
	}
    
// 	GET ALL TASKS -------------------------------------------------------------------------------------------------
	public List<Task> getAll() throws SQLException {
	    
//	    All task records
	    List<Task> tasks = new ArrayList<>();
	
//	    SQL query to select all rows from the tasks table
	    String sql = "SELECT * FROM tasks";
	
	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	
//	        Loop through each result and create a Task object
	        while (rs.next()) {
	            Task task = new Task(
	                rs.getString("title"),
	                rs.getDate("deadline").toLocalDate(),
	                getAssigneesForTask(rs.getString("task_id")), // Load assignees via helper
	                rs.getString("status"),
	                rs.getString("notes"),
	                rs.getString("club_id")
	            );
	            task.setTaskID(rs.getString("task_id")); // Set the task ID
	
//	            Add task to list
	            tasks.add(task);
	        }
	    }
	    return tasks;
	}
}
