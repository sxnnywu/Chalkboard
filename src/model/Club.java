package model;

import java.util.*;

public class Club {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private String clubID;
	private String name;
	private String joinCode;
	private List<Member> members;
	private List<Announcement> announcements;
	private List<Meeting> meetings;
	private List<Event> events;
	private List<Task> tasks;
	
//	CONSTRUCTOR (NEW CLUB) -----------------------------------------------------------------------------------------
	public Club(String name) {
		super();
		this.clubID = UUID.randomUUID().toString();
		this.name = name;
		joinCode = UUID.randomUUID().toString();
		this.members = new ArrayList<>();
		this.announcements = new ArrayList<>();
		this.meetings = new ArrayList<>();;
		this.events = new ArrayList<>();
		this.tasks = new ArrayList<>();;
	}
	
//	CONSTRUCTOR (EXISTING CLUB) -----------------------------------------------------------------------------------
	public Club(String clubID, String name, String joinCode) {
	    this.clubID = clubID;
	    this.name = name;
	    this.joinCode = joinCode;
	    this.members = new ArrayList<>();
	    this.announcements = new ArrayList<>();
	    this.meetings = new ArrayList<>();
	    this.events = new ArrayList<>();
	    this.tasks = new ArrayList<>();
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getClubID() {
		return clubID;
	}
	public void setClubID(String clubID) {
		this.clubID = clubID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJoinCode() {
		return joinCode;
	}
	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	public List<Meeting> getMeetings() {
		return meetings;
	}
	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Club [clubID=" + clubID + ", name=" + name + ", joinCode=" + joinCode + ", members=" + members
				+ ", announcements=" + announcements + ", meetings=" + meetings + ", events=" + events + ", tasks="
				+ tasks + "]";
	}
}
