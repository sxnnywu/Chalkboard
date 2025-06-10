package controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import model.*;
import view.*;

public class MenuController implements ActionListener{
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	private DashboardController dashboardController;
	private MainFrame frame;
	private static MenuPanel menuPanel;
	
	private AnnouncementDAO announcementDAO;
	private TaskDAO taskDAO;
	private Member membershipDAO;
	private ClubDAO clubDAO;

//	CONSTRUCTOR  ---------------------------------------------------------------------------------------------------
	public MenuController(MainFrame frame, DashboardController dashboardController) {
		
		this.dashboardController = dashboardController;
		this.frame = frame;
		menuPanel = frame.getMenuPanel();
		addActionListeners();
	}
	
//	ADD ACTION LISTENERS  ------------------------------------------------------------------------------------------
	private void addActionListeners() {
		for(MenuButton club : menuPanel.getClubs()) 
			club.addActionListener(this); // view clubs
		menuPanel.getJoinButton().addActionListener(this); // join club
		menuPanel.getCreateButton().addActionListener(this); // create club
		menuPanel.getNotifButton().addActionListener(this); // notifications
		menuPanel.getTaskButton().addActionListener(this); // pending tasks
		menuPanel.getProfileButton().addActionListener(this); // my account
		menuPanel.getLogoutButton().addActionListener(this); // log out
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

//	ACTION PERFORMED  ----------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		View club
		for(MenuButton button : menuPanel.getClubs()) {
			if(e.getSource() == button){

				String clubId = clubDAO.getClubIdByName(button.getLabel());
				List<Announcement> announcements = AnnouncementDAO.getAnnouncementsByClubId(clubId);
				List<Task> tasks = taskDAO.getTasksByClubId(clubId);
				List<Member> members = membershipDAO.getMembersByClubId(clubId);

//				Convert these to ArrayList<String[]> if your dashboard still expects them in that format
				dashboardController.showClub(button.getLabel(), formatAnnouncements(announcements), formatTasks(tasks), formatMembers(members));
s
			}
		}
		
//		Join club
		if(e.getSource() == menuPanel.getJoinButton()) 
			frame.showJoinClub();
		
//		Create club
		if(e.getSource() == menuPanel.getCreateButton()) 
			frame.showCreateClub();
		
//		Notifications
		if(e.getSource() == menuPanel.getNotifButton()) {
//			TODO: ADDITIONAL FEATURE IF TIME
		}
		
//		Pending tasks
		if(e.getSource() == menuPanel.getTaskButton()) {
//			TODO: ADDITIONAL FEATURE IF TIME
		}
		
//		My account
		if(e.getSource() == menuPanel.getProfileButton()) {
//			TODO: ADDITIONAL FEATURE IF TIME
		}
		
//		Log out
		if(e.getSource() == menuPanel.getLogoutButton()) {
			frame.remove(frame.getHomePanel());
			frame.add(frame.getStartPanel());
			frame.revalidate();
			frame.repaint();
		}
	}

//	GETTERS  -------------------------------------------------------------------------------------------------------
	public MainFrame getFrame() {
		return frame;
	}
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	public static MenuPanel getMenuPanel() {
		return menuPanel;
	}
	public static void setMenuPanel(MenuPanel menuPanel) {
		MenuController.menuPanel = menuPanel;
	}
}
