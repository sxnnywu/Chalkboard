package view;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.ScheduleItem;

public class DashboardBodyPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1625;
	private static final int PANEL_HEIGHT = 950;
	
//	Parameters
	private String clubName;
	private String nextMeet;
	private String joinCode;
	private int pendingTasks;
	private int newAnnouncements;
	private List<String[]> taskList = new ArrayList<>();
	private List<String[]> announcementList = new ArrayList<>();
	
//	Components
	private JLabel clubLabel;
	private JLabel nextMeetingLabel;
	private JLabel joinCodeLabel;
	private JLabel tasksLabel;
	private JLabel announcementsLabel;
	
//	Panels
	private AnnouncementsPanel announcementsPanel;
	private TasksPanel tasksPanel;
	private MembersPanel membersPanel;
	private CalendarPanel calendarPanel;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public DashboardBodyPanel(String clubName, String nextMeet, String joinCode, int tasks, int newAnnouncements, 
			ArrayList<String[]> announcementList, ArrayList<String[]> taskList, ArrayList<String[]> memberList,
			List<ScheduleItem> scheduleItems) {
		
		this.clubName = clubName;
		this.nextMeet = nextMeet;
		this.joinCode = joinCode;
		pendingTasks = tasks;
		this.newAnnouncements = newAnnouncements;
		this.taskList = taskList;
		this.announcementList = announcementList;
		
//		Set up the panel
		initializePanel();
		
//		Set up the components
		setUpSummary(); // summary at the top
		setUpPanels(); // panels
		
//		Data
		for(String[] announcement : announcementList) 
			announcementsPanel.addAnnouncement(announcement[0], announcement[1], announcement[2]);
		for(String[] member : memberList) 
			membersPanel.addMember(member[0], member[1]);
		calendarPanel.clearEvents();
		for (ScheduleItem item : scheduleItems) 
		    calendarPanel.addEvent(item.getDate(), item.getTitle());	
		calendarPanel.renderCalendar(LocalDate.now().withDayOfMonth(1));
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(Color.WHITE);
        setOpaque(true);
	}
	
//	SET UP COMPONENTS ---------------------------------------------------------------------------------------------
	private void setUpSummary() {
		
//		Club 
		clubLabel = new JLabel(clubName);
		clubLabel.setForeground(Color.BLACK);
		clubLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 75));
		clubLabel.setBounds(55, 70, 370, 60);
		add(clubLabel);
		
//		Next meeting
		nextMeetingLabel = new JLabel("<html><b>Next Meeting</b><br>"+nextMeet+"</html>");
		nextMeetingLabel.setForeground(Color.BLACK);
		nextMeetingLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		nextMeetingLabel.setBounds(clubLabel.getX() + 680, clubLabel.getY() - 20, 200, 45);
		add(nextMeetingLabel);
		
//		Join code
		joinCodeLabel = new JLabel("<html><b>Join Code</b><br>"+joinCode+"</html>");
		joinCodeLabel.setForeground(Color.BLACK);
		joinCodeLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		joinCodeLabel.setBounds(nextMeetingLabel.getX() + 240, nextMeetingLabel.getY(), 370, 45);
		add(joinCodeLabel);
		
//		Tasks
		tasksLabel = new JLabel("<html><b>Your Tasks</b><br>"+pendingTasks+" pending</html>");
		tasksLabel.setForeground(Color.BLACK);
		tasksLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		tasksLabel.setBounds(nextMeetingLabel.getX(), nextMeetingLabel.getY() + 60, 420, 45);
		add(tasksLabel);
		
//		Announcements
		announcementsLabel = new JLabel("<html><b>Announcements</b><br>"+newAnnouncements+" new announcement</html>");
		announcementsLabel.setForeground(Color.BLACK);
		announcementsLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		announcementsLabel.setBounds(joinCodeLabel.getX(), nextMeetingLabel.getY() + 60, 370, 45);
		add(announcementsLabel);
	}

//	SET UP PANELS -------------------------------------------------------------------------------------------------
	@SuppressWarnings("static-access")
	private void setUpPanels() {
		
//		Announcements
		announcementsPanel = new AnnouncementsPanel(80, announcementList);
		announcementsPanel.setBounds(clubLabel.getX(), clubLabel.getY() + 150, announcementsPanel.getPanelWidth(), announcementsPanel.getPanelHeight());
		add(announcementsPanel);
		
//		Tasks
		tasksPanel = new TasksPanel(80, taskList);
		tasksPanel.setBounds(announcementsPanel.getX() + announcementsPanel.getWidth() + 40, clubLabel.getY() + 150, tasksPanel.getPanelWidth(), tasksPanel.getPanelHeight());
		add(tasksPanel);
		
//		Members
		membersPanel = new MembersPanel(80);
		membersPanel.setBounds(announcementsPanel.getX(), announcementsPanel.getY() + announcementsPanel.getHeight() + 30, membersPanel.getPanelWidth(), membersPanel.getPanelHeight());
		add(membersPanel);
		
//		Calendar
		calendarPanel = new CalendarPanel(80);
		calendarPanel.setBounds(membersPanel.getX() + membersPanel.getPanelWidth() + 40, membersPanel.getY(), calendarPanel.getPanelWidth(), calendarPanel.getPanelHeight());
		add(calendarPanel);
	}
	
//	SIZING --------------------------------------------------------------------------------------------------------
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(800, 1500); // Make height larger than your window
	}
}
