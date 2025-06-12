package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import model.*;
import view.*;

public class DashboardController {
	
//	FIELDS
	private DashboardPanel dashboardPanel;
	private MainFrame frame;
	private ClubDAO clubDAO;
	private AnnouncementDAO announcementDAO;
	private TaskDAO taskDAO;
	private MemberDAO memberDAO;
	private EventDAO eventDAO;
	private MeetingDAO meetingDAO;
	
//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public DashboardController(MainFrame frame) throws SQLException {
		
//		View
		this.frame = frame;
		dashboardPanel = frame.getDashboardPanel();
		
//		Data
		Connection conn = DBUtil.getConnection();  
		clubDAO = new ClubDAO(conn);
        announcementDAO = new AnnouncementDAO(conn);
        taskDAO = new TaskDAO(conn);
        memberDAO = new MemberDAO(conn);
        eventDAO = new EventDAO(conn);
        meetingDAO = new MeetingDAO(conn);
	}
	
//	SHOW CLUB ------------------------------------------------------------------------------------------------------
	public void showClub(String clubName, String clubID) throws SQLException {
	    
	    // Club data
	    String nextMeeting = clubDAO.getNextMeetingTime(clubID); // returns something like "June 11th @ 3:00pm"
	    String joinCode = clubDAO.getJoinCode(clubID);
	    List<Announcement> announcements = announcementDAO.getByClubId(clubID);
	    List<Task> tasks = taskDAO.getTasksByClubId(clubID);
	    List<Member> members = memberDAO.getMembersByClubId(clubID);
	    List<ScheduleItem> scheduleItems = fetchScheduleItems(clubID); // fetch events + meetings

	    // Create dashboard panel
	    dashboardPanel = new DashboardPanel(
	        clubName,
	        nextMeeting,
	        joinCode,
	        formatAnnouncements(announcements),
	        formatTasks(tasks),
	        formatMembers(members),
	        scheduleItems
	    );

	    // Update frame with dashboard panel
	    frame.getContentPane().removeAll();
	    frame.add(dashboardPanel);
	    frame.revalidate();
	    frame.repaint();
	}
	
//	FORMAT ANNOUNCEMENTS -------------------------------------------------------------------------------------------
	private ArrayList<String[]> formatAnnouncements(List<Announcement> announcements) {
	    ArrayList<String[]> formatted = new ArrayList<>();
	    for (Announcement a : announcements) {
	        String[] row = new String[] {
	            a.getTitle(),
	            a.getMember().getFirstName() + " " + a.getMember().getLastName(),
	            a.getText(),
	  
	        };
	        formatted.add(row);
	    }
	    return formatted;
	}
	
//	FORMAT TASKS ---------------------------------------------------------------------------------------------------
	private ArrayList<String[]> formatTasks(List<Task> tasks) {
	    ArrayList<String[]> formatted = new ArrayList<>();
	    for (Task t : tasks) {
	        String[] row = new String[] {
	            t.getTitle(),
	            t.getStringAssignees(),
	            t.getStringDeadline(),
	            t.getStatus(),
	            t.getNotes()
	        };
	        formatted.add(row);
	    }
	    return formatted;
	}

//	FORMAT MEMBERS -------------------------------------------------------------------------------------------------
	private ArrayList<String[]> formatMembers(List<Member> members) {
	    ArrayList<String[]> formatted = new ArrayList<>();
	    for (Member m : members) {
	        String[] row = new String[] {
	            m.getFirstName(),
	            m.getLastName(),
	            m.getRole()
	        };
	        formatted.add(row);
	    }
	    return formatted;
	}
	
//	FETCH SCHEDULE ITEMS
	private List<ScheduleItem> fetchScheduleItems(String clubId) throws SQLException {
	    List<ScheduleItem> items = new ArrayList<>();
	    items.addAll(eventDAO.getAllByClub(clubId));
	    items.addAll(meetingDAO.getAllByClub(clubId));
	    return items;
	}
}
