package dao;

import model.Member;
import model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private final Connection conn;

    public TaskDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (task_id, club_id, title, deadline, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskID());
            stmt.setString(2, task.getClubID());
            stmt.setString(3, task.getTitle());
            stmt.setDate(4, Date.valueOf(task.getDeadline()));
            stmt.setString(5, task.getStatus());
            stmt.setString(6, task.getNotes());
            stmt.executeUpdate();
        }

        // Insert assignees
        String assigneeSql = "INSERT INTO task_assignees (task_id, user_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(assigneeSql)) {
            for (Member m : task.getAssignees()) {
                stmt.setString(1, task.getTaskID());
                stmt.setString(2, m.getUserID());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public Task getById(String taskId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        Task task = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taskId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    task = new Task(
                        rs.getString("title"),
                        rs.getDate("deadline").toLocalDate(),
                        new ArrayList<>(), // Load assignees separately
                        rs.getString("status"),
                        rs.getString("notes"),
                        rs.getString("club_id")
                    );
                    task.setTaskID(taskId);
                }
            }
        }
        if (task != null) {
            // Load assignees
            List<Member> assignees = new ArrayList<>();
            String assigneesSql = "SELECT u.*, m.role FROM users u JOIN memberships m ON u.user_id = m.user_id WHERE u.user_id IN (SELECT user_id FROM task_assignees WHERE task_id = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(assigneesSql)) {
                stmt.setString(1, taskId);
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
                        assignees.add(member);
                    }
                }
            }
            task.setAssignees(assignees);
        }
        return task;
    }

    // Update, delete, and getAll methods can be added similarly
    
    public List<Task> getTasksByClubId(String clubId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE club_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clubId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tasks.add(new Task(
                	rs.getString("task_id"),
                	rs.getString("title"),
                	LocalDate.parse(rs.getString("deadline")),
                	rs.getString("status"),
                	rs.getString("notes"),
                	rs.getString("club_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
