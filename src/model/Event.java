package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Event extends ScheduleItem{

//	FIELDS --------------------------------------------------------------------------------------------------------
	private String eventID;
	private String clubID;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Event(String title, LocalDate date, LocalTime time, String location, String description, String clubID) {
		super(title, date, time, location, description);
		eventID = UUID.randomUUID().toString();
		this.clubID = clubID;
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getClubID() {
		return clubID;
	}
	public void setClubID(String clubID) {
		this.clubID = clubID;
	}

//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", clubID=" + clubID + "]";
	}
}
