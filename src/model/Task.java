package model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Task {

//	FIELDS --------------------------------------------------------------------------------------------------------
	private String title;
	private LocalDate deadline;
	private List<Member> assignees;
	private String status;
	private String notes;
	private String taskID;
	private String clubID;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Task(String title, LocalDate deadline, List<Member> assignees, String status, String notes, String clubID) {
		super();
		this.title = title;
		setDeadline(deadline);
		this.assignees = assignees;
		this.status = status;
		this.notes = notes;
		this.taskID = UUID.randomUUID().toString();
		this.clubID = clubID;
	}
	
//	ADD ASSIGNEE --------------------------------------------------------------------------------------------------
	public void addAssignee(Member member) {
		
//		Check if user is already an assignee
		if (assignees.contains(member))
			throw new IllegalArgumentException("User is already an assignee");
		assignees.add(member);
	}
	
//	REMOVE ASSIGNEE -----------------------------------------------------------------------------------------------
	public void removeAssignee(Member member) {
		
//		Check if member is an assignee
		if (!assignees.contains(member))
			throw new IllegalArgumentException("User is not an assignee");
		assignees.remove(member);
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public String getStringDeadline() {
		if (deadline == null) return "";

	    String month = deadline.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
	    int day = deadline.getDayOfMonth();
	    String suffix = getDaySuffix(day);

	    return month + " " + day + suffix;
	}
	private String getDaySuffix(int day) {
	    if (day >= 11 && day <= 13) {
	        return "th";
	    }
	    switch (day % 10) {
	        case 1: return "st";
	        case 2: return "nd";
	        case 3: return "rd";
	        default: return "th";
	    }
	}
	public void setDeadline(LocalDate deadline) {
		
//		Check if deadline is in the past
	    if (deadline.isBefore(LocalDate.now())) 
	        throw new IllegalArgumentException("Deadline cannot be in the past.");
	    this.deadline = deadline;
	}
	public List<Member> getAssignees() {
		return assignees;
	}
	public String getStringAssignees() {
		if (assignees == null || assignees.isEmpty()) 
	        return "";
	    StringBuilder list = new StringBuilder();

	    for (int i = 0; i < assignees.size(); i++) {
	        list.append(assignees.get(i).getFirstName());
	        if (i < assignees.size() - 1) 
	            list.append(", ");
	    }
	    return list.toString();
	}
	public void setAssignees(List<Member> assignees) {
		this.assignees = assignees;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
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
		return "Task [title=" + title + ", deadline=" + deadline + ", assignees=" + assignees + ", status=" + status
				+ ", notes=" + notes + ", taskID=" + taskID + ", clubID=" + clubID + "]";
	}
}
