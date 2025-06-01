package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Meeting extends ScheduleItem{
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private boolean isRecurring;
	private String frequency;
	private String meetingID;
	private String clubID;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Meeting(String title, LocalDate date, LocalTime time, String location, String description,
			boolean isRecurring, String frequency, String clubID) {
		super(title, date, time, location, description);
		this.isRecurring = isRecurring;
		this.frequency = frequency;
		meetingID = UUID.randomUUID().toString();
		this.clubID = clubID;
	}

//	NOTE!!!! FIGURE OUT FLOW BETWEEN ISRECURRING AND FREQUENCY + DATA TYPE FOR FREQUENCY
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public boolean isRecurring() {
		return isRecurring;
	}
	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
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
		return "Meeting [isRecurring=" + isRecurring + ", frequency=" + frequency + ", meetingID=" + meetingID
				+ ", clubID=" + clubID + "]";
	}

	
}
