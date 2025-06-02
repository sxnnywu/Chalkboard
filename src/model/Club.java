package model;

import java.time.*;
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
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Club(String name, List<Member> members, List<Announcement> announcements,
			List<Meeting> meetings, List<Event> events, List<Task> tasks) {
		super();
		this.clubID = UUID.randomUUID().toString();
		this.name = name;
		joinCode = UUID.randomUUID().toString();
		this.members = (members != null) ? members : new ArrayList<>();
		this.announcements = (announcements != null) ? announcements : new ArrayList<>();
		this.meetings = (meetings != null) ? meetings : new ArrayList<>();;
		this.events = (events != null) ? events : new ArrayList<>();
		this.tasks = (tasks != null) ? tasks : new ArrayList<>();;
	}

//	ADD MEMBER ----------------------------------------------------------------------------------------------------
	public void addMember(User user, String role) {
		
		Member member = user.toMember(role);
		
//		Check if user is already a member
		if(members.contains(member))
			throw new IllegalArgumentException("User is already a member.");
		members.add(member);
	}
	
//	REMOVE MEMBER -------------------------------------------------------------------------------------------------
	public void removeMember(Member member) {
		
//		Check if member exists
		if(!members.contains(member)) 
			throw new IllegalArgumentException("Member not found.");
		members.remove(member);
	}
	
//	ADD ANNOUNCEMENT ----------------------------------------------------------------------------------------------
	public void addAnnouncement(Announcement announcement) {
		announcements.add(announcement);
	}
	
//	REMOVE ANNOUNCEMENT -------------------------------------------------------------------------------------------
	public void removeAnnouncement(Announcement announcement) {
		
//		Check if announcement exists
		if(!announcements.contains(announcement))
			throw new IllegalArgumentException("Announcement not found.");
		announcements.remove(announcement);
	}
	
//	ADD MEETING ---------------------------------------------------------------------------------------------------
	public void addMeeting (Meeting meeting) {
		meetings.add(meeting);
	}
	
//	REMOVE MEETING ------------------------------------------------------------------------------------------------
	public void removeMeeting(Meeting meeting) {
		
//		Check if meeting exists
		if(!meetings.contains(meeting))
			throw new IllegalArgumentException("Meeting not found.");
		meetings.remove(meeting);
	}
	
//	ADD EVENT -----------------------------------------------------------------------------------------------------
	public void addEvent(Event event) {
		events.add(event);
	}
	
//	REMOVE EVENT --------------------------------------------------------------------------------------------------
	public void removeEvent(Event event) {
		
//		Check if event exists
		if(!events.contains(event))
			throw new IllegalArgumentException("Event not found.");
		events.remove(event);
	}
	
//	ADD TASK ------------------------------------------------------------------------------------------------------
	public void addTask(Task task) {
		tasks.add(task);
	}
	
//	REMOVE TASK ---------------------------------------------------------------------------------------------------
	public void removeTask(Task task) {
		
//		Check if task exists
		if(!tasks.contains(task))
			throw new IllegalArgumentException("Task not found.");
		tasks.remove(task);
	}
	
//	GET UPCOMING MEETING ------------------------------------------------------------------------------------------
	public Meeting nextMeeting() {
		
//		Current date and time
		LocalDate today = LocalDate.now();
	    LocalTime now = LocalTime.now();
	    
//	    Use a stream to filter, sort, and find first element in meetings
	    return meetings.stream()
	    		
//			Filter meetings that are still upcoming
	        .filter(m -> m.getDate().isAfter(today) || (m.getDate().isEqual(today) && m.getTime().isAfter(now)))
	        
//	        Sort meetings by date, then by time
	        .sorted((m1, m2) -> {
	            if (m1.getDate().equals(m2.getDate())) 
	                return m1.getTime().compareTo(m2.getTime());
	            return m1.getDate().compareTo(m2.getDate());
	        })
	        
//	        Return the first meeting or display error message
	        .findFirst()
	        .orElseThrow(() -> new IllegalStateException("No upcoming meetings found."));
	}
	
//	GET MEMBER ROLE -----------------------------------------------------------------------------------------------
	public String getRole(Member member) {
		return member.getRole();
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

	
//	TO STRING ------------------------------------------------------------------------------------------------------
//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Club [clubID=" + clubID + ", name=" + name + ", joinCode=" + joinCode + ", members=" + members
				+ ", announcements=" + announcements + ", meetings=" + meetings + ", events=" + events + ", tasks="
				+ tasks + "]";
	}
}
