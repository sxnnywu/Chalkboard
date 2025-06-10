package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.Announcement;
import model.Club;
import model.Event;
import model.Meeting;
import model.Member;
import model.Task;
import model.User;

public class ClubController {
	
//	FIELDS ---------------------------------------------------------------------------------------------------------
	private Club club;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubController(Club club) {
		this.club = club;
	}
	
//	ADD MEMBER ----------------------------------------------------------------------------------------------------
	public void addMember(User user, String role) {
		
		Member member = user.toMember(role);
		
//		Check if user is already a member
		if(club.getMembers().contains(member))
			throw new IllegalArgumentException("User is already a member.");
		club.getMembers().add(member);
	}
	
//	REMOVE MEMBER -------------------------------------------------------------------------------------------------
	public void removeMember(Member member) {
		
//		Check if member exists
		if(!club.getMembers().contains(member)) 
			throw new IllegalArgumentException("Member not found.");
		club.getMembers().remove(member);
	}
	
//	ADD ANNOUNCEMENT ----------------------------------------------------------------------------------------------
	public void addAnnouncement(Announcement announcement) {
		club.getAnnouncements().add(announcement);
	}
	
//	REMOVE ANNOUNCEMENT -------------------------------------------------------------------------------------------
	public void removeAnnouncement(Announcement announcement) {
		
//		Check if announcement exists
		if(!club.getAnnouncements().contains(announcement))
			throw new IllegalArgumentException("Announcement not found.");
		club.getAnnouncements().remove(announcement);
	}
	
//	ADD MEETING ---------------------------------------------------------------------------------------------------
	public void addMeeting (Meeting meeting) {
		club.getMeetings().add(meeting);
	}
	
//	REMOVE MEETING ------------------------------------------------------------------------------------------------
	public void removeMeeting(Meeting meeting) {
		
//		Check if meeting exists
		if(!club.getMeetings().contains(meeting))
			throw new IllegalArgumentException("Meeting not found.");
		club.getMeetings().remove(meeting);
	}
	
//	ADD EVENT -----------------------------------------------------------------------------------------------------
	public void addEvent(Event event) {
		club.getEvents().add(event);
	}
	
//	REMOVE EVENT --------------------------------------------------------------------------------------------------
	public void removeEvent(Event event) {
		
//		Check if event exists
		if(!club.getEvents().contains(event))
			throw new IllegalArgumentException("Event not found.");
		club.getEvents().remove(event);
	}
	
//	ADD TASK ------------------------------------------------------------------------------------------------------
	public void addTask(Task task) {
		club.getTasks().add(task);
	}
	
//	REMOVE TASK ---------------------------------------------------------------------------------------------------
	public void removeTask(Task task) {
		
//		Check if task exists
		if(!club.getTasks().contains(task))
			throw new IllegalArgumentException("Task not found.");
		club.getTasks().remove(task);
	}
	
//	GET UPCOMING MEETING ------------------------------------------------------------------------------------------
	public Meeting nextMeeting() {
		
//		Current date and time
		LocalDate today = LocalDate.now();
	    LocalTime now = LocalTime.now();
	    
//	    Use a stream to filter, sort, and find first element in meetings
	    return club.getMeetings().stream()
	    		
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

//	ANNOUNCEMENT LIST
	public ArrayList<String[]> getStringAnnouncements(){
		ArrayList<String[]> data = new ArrayList<>();
		int i = 0;
		for (Announcement announcement : club.getAnnouncements()) {
			data.set(i, new String[]{announcement.getMember().getFirstName(), announcement.getTitle(), announcement.getText()});
			i++;
		}
		return data;
	}
	
//	TASK LIST
	public ArrayList<String[]> getStringTasks(){	
		ArrayList<String[]> data = new ArrayList<>();
		int i = 0;
		for (Task task : club.getTasks()) {
			data.set(i, new String[]{task.getTitle(), task.getStringAssignees(), task.getStringDeadline(), task.getStatus(), task.getNotes()});
			i++;
		}
		return data;
	}
	
//	MEMBER LIST
	public ArrayList<String[]> getStringMembers(){	
		ArrayList<String[]> data = new ArrayList<>();
		int i = 0;
		for (Member member : club.getMembers()) {
			data.set(i, new String[]{member.getFullName(), member.getRole()});
			i++;
		}
		return data;
	}
	
//	
}
