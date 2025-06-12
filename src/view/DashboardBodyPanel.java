package view;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.ScheduleItem;

@SuppressWarnings("serial")
public class DashboardBodyPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1625;
	private static final int PANEL_HEIGHT = 950;
	
//	Parameters (club information)
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
		
//		Parameters
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
		
//		Data (club information)
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
	
//	SET UP SUMMARY ------------------------------------------------------------------------------------------------
	private void setUpSummary() {
		
//		Club 
		clubLabel = new JLabel(clubName);
		clubLabel.setForeground(Color.BLACK);
		clubLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 75));
		clubLabel.setBounds(55, 70, 470, 60);
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
	    return new Dimension(800, 1500); 
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getNextMeet() {
		return nextMeet;
	}
	public void setNextMeet(String nextMeet) {
		this.nextMeet = nextMeet;
	}
	public String getJoinCode() {
		return joinCode;
	}
	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}
	public int getPendingTasks() {
		return pendingTasks;
	}
	public void setPendingTasks(int pendingTasks) {
		this.pendingTasks = pendingTasks;
	}
	public int getNewAnnouncements() {
		return newAnnouncements;
	}
	public void setNewAnnouncements(int newAnnouncements) {
		this.newAnnouncements = newAnnouncements;
	}
	public List<String[]> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<String[]> taskList) {
		this.taskList = taskList;
	}
	public List<String[]> getAnnouncementList() {
		return announcementList;
	}
	public void setAnnouncementList(List<String[]> announcementList) {
		this.announcementList = announcementList;
	}
	public JLabel getClubLabel() {
		return clubLabel;
	}
	public void setClubLabel(JLabel clubLabel) {
		this.clubLabel = clubLabel;
	}
	public JLabel getNextMeetingLabel() {
		return nextMeetingLabel;
	}
	public void setNextMeetingLabel(JLabel nextMeetingLabel) {
		this.nextMeetingLabel = nextMeetingLabel;
	}
	public JLabel getJoinCodeLabel() {
		return joinCodeLabel;
	}
	public void setJoinCodeLabel(JLabel joinCodeLabel) {
		this.joinCodeLabel = joinCodeLabel;
	}
	public JLabel getTasksLabel() {
		return tasksLabel;
	}
	public void setTasksLabel(JLabel tasksLabel) {
		this.tasksLabel = tasksLabel;
	}
	public JLabel getAnnouncementsLabel() {
		return announcementsLabel;
	}
	public void setAnnouncementsLabel(JLabel announcementsLabel) {
		this.announcementsLabel = announcementsLabel;
	}
	public AnnouncementsPanel getAnnouncementsPanel() {
		return announcementsPanel;
	}
	public void setAnnouncementsPanel(AnnouncementsPanel announcementsPanel) {
		this.announcementsPanel = announcementsPanel;
	}
	public TasksPanel getTasksPanel() {
		return tasksPanel;
	}
	public void setTasksPanel(TasksPanel tasksPanel) {
		this.tasksPanel = tasksPanel;
	}
	public MembersPanel getMembersPanel() {
		return membersPanel;
	}
	public void setMembersPanel(MembersPanel membersPanel) {
		this.membersPanel = membersPanel;
	}
	public CalendarPanel getCalendarPanel() {
		return calendarPanel;
	}
	public void setCalendarPanel(CalendarPanel calendarPanel) {
		this.calendarPanel = calendarPanel;
	}
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
