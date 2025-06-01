package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Meeting extends ScheduleItem{
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private RecurrenceFrequency frequency;
	private String meetingID;
	private String clubID;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Meeting(String title, LocalDate date, LocalTime time, String location, String description, 
			RecurrenceFrequency frequency, String clubID) {
		super(title, date, time, location, description);
		this.frequency = frequency;
		meetingID = UUID.randomUUID().toString();
		this.clubID = clubID;
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public RecurrenceFrequency getFrequency() {
		return frequency;
	}
	public void setFrequency(RecurrenceFrequency frequency) {
		this.frequency = frequency;
	}
	public String getMeetingID() {
		return meetingID;
	}
	public void setMeetingID(String meetingID) {
		this.meetingID = meetingID;
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
		return "Meeting [frequency=" + frequency + ", meetingID=" + meetingID + ", clubID=" + clubID + "]";
	}	
	
//	IS RECURRING --------------------------------------------------------------------------------------------------
	public boolean isRecurring() {
		return frequency != RecurrenceFrequency.NONE;
	}
}
