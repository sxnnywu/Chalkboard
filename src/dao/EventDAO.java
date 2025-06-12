package dao;

import model.Event;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final Connection conn;

// 	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    // INSERT -----------------------------------------------------------------------------------------------------
    public void insert(Event event) throws SQLException {
    	
//    	SQL query to insert into events table
        String sql = "INSERT INTO events (event_id, club_id, title, date, time, location, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
//     	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getClubID());
            stmt.setString(3, event.getTitle());
            stmt.setDate(4, Date.valueOf(event.getDate()));
            stmt.setTime(5, event.getTime() != null ? Time.valueOf(event.getTime()) : null);
            stmt.setString(6, event.getLocation());
            stmt.setString(7, event.getDescription());
            stmt.executeUpdate();
        }
    }

// 	GET BY ID -----------------------------------------------------------------------------------------------------
    public Event getById(String eventId) throws SQLException {
    	
//    	SQL query
        String sql = "SELECT * FROM events WHERE event_id = ?";
        
//     	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventId);
            
//      	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Event event = new Event(
                        rs.getString("title"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time") != null ? rs.getTime("time").toLocalTime() : null,
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("club_id")
                    );
                    event.setEventID(rs.getString("event_id")); // Preserve event ID
                    return event;
                }
            }
        }
        return null;
    }

// 	UPDATE --------------------------------------------------------------------------------------------------------
    public void update(Event event) throws SQLException {
        
//    	SQL query
    	String sql = "UPDATE events SET title = ?, date = ?, time = ?, location = ?, description = ? WHERE event_id = ?";
    	
//    	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getTitle());
            stmt.setDate(2, Date.valueOf(event.getDate()));
            stmt.setTime(3, event.getTime() != null ? Time.valueOf(event.getTime()) : null);
            stmt.setString(4, event.getLocation());
            stmt.setString(5, event.getDescription());
            stmt.setString(6, event.getEventID());
            stmt.executeUpdate();
        }
    }

// 	DELETE --------------------------------------------------------------------------------------------------------
    public void delete(String eventId) throws SQLException {
        
//    	SQL query to delete an event from the events table
    	String sql = "DELETE FROM events WHERE event_id = ?";
    	
//    	Prepare & execute statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventId);
            stmt.executeUpdate();
        }
    }

// 	GET ALL EVENTS FOR A CLUB -------------------------------------------------------------------------------------
    public List<Event> getAllByClub(String clubId) throws SQLException {
    	
//    	List of events will be stored here
        List<Event> events = new ArrayList<>();
        
//    	SQL query
        String sql = "SELECT * FROM events WHERE club_id = ? ORDER BY date, time";
        
//     	Prepare statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clubId);
            
//        	Execute statement
            try (ResultSet rs = stmt.executeQuery()) {
            	
//            	Loop through results to get event data
                while (rs.next()) {
                    String dateStr = rs.getString("date");
                    String timeStr = rs.getString("time");

                    LocalDate date = LocalDate.parse(dateStr);
                    LocalTime time = (timeStr != null) ? LocalTime.parse(timeStr) : null;

//                 	Create event and add it to list
                    Event event = new Event(
                        rs.getString("title"),
                        date,
                        time,
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("club_id")
                    );
                    event.setEventID(rs.getString("event_id"));
                    events.add(event);
                }
            }
        }
        return events;
    }
}
