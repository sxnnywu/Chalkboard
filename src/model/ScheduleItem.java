package model;

import java.time.*;

public abstract class ScheduleItem {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private String title;
	private LocalDate date;
	private LocalTime time;
	private String location;
	private String description;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ScheduleItem(String title, LocalDate date, LocalTime time, String location, String description) {
		super();
		this.title = title;
		setDate(date);
		setTime(time);
		this.location = location;
		this.description = description;
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
	
//		Check if date is in the past
	    if (date.isBefore(LocalDate.now())) 
	        throw new IllegalArgumentException("Date cannot be in the past.");
	    this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

//	TO STRING ------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "ScheduleItem [title=" + title + ", date=" + date + ", time=" + time + ", location=" + location
				+ ", description=" + description + "]";
	}
}
