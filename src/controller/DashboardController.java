package controller;

import java.util.ArrayList;

import view.DashboardPanel;
import view.MainFrame;

public class DashboardController {
	
//	FIELDS
	private DashboardPanel dashboardPanel;
	private MainFrame frame;
	
//	CONSTRUCTOR
	public DashboardController(MainFrame frame) {
		this.frame = frame;
		dashboardPanel = frame.getDashboardPanel();
	}

//	TASKS (HARD CODED FOR NOW)
	private ArrayList<String[]> tasksData() {
		
		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[] {"Sample Task", "John", "June 16th", "In Progress", "Test note"});
		data.add(new String[] {"Sample Task", "John", "June 16th", "In Progress", "Test note"});
		data.add(new String[] {"Sample Task", "John", "June 16th", "In Progress", "Test note"});
		data.add(new String[] {"Sample Task", "John", "June 16th", "In Progress", "Test note"});
		data.add(new String[] {"Sample Task", "John", "June 16th", "In Progress", "Test note"});
		data.add(new String[] {"Another Task", "Jane", "June 19th", "Pending", "Another note"});
		data.add(new String[] {"Another Task", "Jane", "June 19th", "Pending", "Another note"});
		data.add(new String[] {"Another Task", "Jane", "June 19th", "Pending", "Another note"});
		data.add(new String[] {"Another Task", "Jane", "June 19th", "Pending", "Another note"});
		data.add(new String[] {"Task 3", "Anita", "June 19th", "Complete", "Note"});
		data.add(new String[] {"Task 3", "Anita", "June 19th", "Complete", "Note"});
		data.add(new String[] {"Task 3", "Anita", "June 19th", "Complete", "Note"});
		data.add(new String[] {"Task 3", "Anita", "June 19th", "Complete", "Note"});
		data.add(new String[] {"Task 3", "Anita", "June 19th", "Complete", "Note"});
		data.add(new String[] {"Task!!", "Theodore", "June 20th", "In progress", "Need help"});
		
		return data;
	}
	
//	ANNOUNCEMENTS (HARD CODED FOR NOW)
	private ArrayList<String[]> announcementData(){
		
		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[] {"Meeting Cancelled", "John", "Hey everyone! Please note that this week's meeting is cancelled due to the snow storm."});
		data.add(new String[] {"Budget Complete", "Jane", "I have completed the budget, please see."});
		data.add(new String[] {"Budget Complete", "Jane", "I have completed the budget, please see."});
		data.add(new String[] {"Budget Complete", "Jane", "I have completed the budget, please see."});
		data.add(new String[] {"Budget Complete", "Jane", "I have completed the budget, please see."});
		
		return data;
	}
	
//	MEMBERS (HARD CODED FOR NOW)
	private ArrayList<String[]> memberData(){
		
		ArrayList<String[]> data = new ArrayList<>();
		
		data.add(new String[] {"John", "President"});
		data.add(new String[] {"Jane", "Head of Finance"});
		data.add(new String[] {"Michelle", "Head of Marketing"});
		data.add(new String[] {"Ella", "Member"});
		data.add(new String[] {"Thomas", "Marketing Executive"});
		data.add(new String[] {"Paul", "Member"});
		data.add(new String[] {"Paul", "Member"});
		data.add(new String[] {"Paul", "Member"});
		data.add(new String[] {"Paul", "Member"});
		data.add(new String[] {"Paul", "Member"});
		data.add(new String[] {"George", "Member"});
		data.add(new String[] {"Hannah", "Member"});
		
		return data;
	}
	
//	SHOW CLUB ------------------------------------------------------------------------------------------------------
	public void showClub(String clubName, ArrayList<String[]> announcementList, ArrayList<String[]> taskList, ArrayList<String[]> memberList) {
		dashboardPanel = new DashboardPanel(clubName, "June 11th @ 3:00pm", "145flj3", announcementList, taskList, memberList);
		frame.getContentPane().removeAll();
		frame.add(dashboardPanel);
	}
}
