package dao;

import model.Meeting;
import model.RecurrenceFrequency;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAO {
    private final Connection conn;

    public MeetingDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Meeting meeting) throws SQLException {
        String sql = "INSERT INTO meetings (meeting_id, club_id, title, date, time, location, description, recurrence) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, meeting.getMeetingID());
            stmt.setString(2, meeting.getClubID());
            stmt.setString(3, meeting.getTitle());
            stmt.setDate(4, Date.valueOf(meeting.getDate()));
            stmt.setTime(5, (meeting.getTime() != null) ? Time.valueOf(meeting.getTime()) : null);
            stmt.setString(6, meeting.getLocation());
            stmt.setString(7, meeting.getDescription());
            stmt.setString(8, meeting.getFrequency().name());
            stmt.executeUpdate();
        }
    }

    public Meeting getById(String meetingId) throws SQLException {
        String sql = "SELECT * FROM meetings WHERE meeting_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, meetingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Meeting(
                        rs.getString("title"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time") != null ? rs.getTime("time").toLocalTime() : null,
                        rs.getString("location"),
                        rs.getString("description"),
                        RecurrenceFrequency.valueOf(rs.getString("recurrence")),
                        rs.getString("club_id")
                    );
                }
            }
        }
        return null;
    }

    // Update, delete, getAll can be added similarly
}
