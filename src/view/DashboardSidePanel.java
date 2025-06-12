package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DashboardSidePanel extends JPanel{
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 225;
	private static final int PANEL_HEIGHT = 950;
	private static final int BUTTON_HEIGHT = MenuButton.getButtonHeight();
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color red = Color.decode("#fc7b7b");
	
//	Icons
	private final ImageIcon announcementsIcon = new ImageIcon("icons/announce.png");
	private final ImageIcon taskIcon = new ImageIcon("icons/checklist.png");
	private final ImageIcon calendarIcon = new ImageIcon("icons/calendar.png");
	private final ImageIcon membersIcon = new ImageIcon("icons/people.png");
	private final ImageIcon addAnnouncementIcon = new ImageIcon("icons/addNotif.png");
	private final ImageIcon addMeetingIcon = new ImageIcon("icons/addCall.png");
	private final ImageIcon addEventIcon = new ImageIcon("icons/addEvent.png");
	private final ImageIcon addTaskIcon = new ImageIcon("icons/addTask.png");
	
//	Labels
	private JLabel toolsLabel = new JLabel("Club Tools");
	private JLabel actionsLabel = new JLabel("Quick Actions");
	
//	Exit button
	private RoundedButton exitButton = new RoundedButton("Exit Club", red, getLighterColor(red, 0.5f), Color.BLACK);
	
//	Menu buttons
	private MenuButton announcementsButton = new MenuButton("Announcements", announcementsIcon, PANEL_WIDTH, offWhite);
	private MenuButton tasksButton = new MenuButton("Tasks", taskIcon, PANEL_WIDTH, offWhite);
	private MenuButton calendarButton = new MenuButton("Calendar", calendarIcon, PANEL_WIDTH, offWhite);
	private MenuButton memberButton = new MenuButton("Members", membersIcon, PANEL_WIDTH, offWhite);
	private MenuButton addAnnounceButton = new MenuButton("Add Announcement", addAnnouncementIcon, PANEL_WIDTH, offWhite);
	private MenuButton addMeetingButton = new MenuButton("Add Meeting", addMeetingIcon, PANEL_WIDTH,offWhite);
	private MenuButton addEventButton = new MenuButton("Add Event", addEventIcon, PANEL_WIDTH, offWhite);
	private MenuButton addTaskButton = new MenuButton("Add Task", addTaskIcon, PANEL_WIDTH, offWhite);

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public DashboardSidePanel() {	
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpToolsLabel();
		setUpToolButtons();
		setUpActionsLabel();
		setUpActionButtons();
		setUpExitButton();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
        setOpaque(true);
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
//		Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

//		Alter RGB values
		int r = (int) (color.getRed() + (255 - color.getRed()) * factor);
		int g = (int) (color.getGreen() + (255 - color.getGreen()) * factor);
		int b = (int) (color.getBlue() + (255 - color.getBlue()) * factor);

		return new Color(r, g, b);
	}

//	SET UP TOOLS LABEL --------------------------------------------------------------------------------------------
	private void setUpToolsLabel() {	
		toolsLabel.setForeground(darkGrey);
		toolsLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 22));
		toolsLabel.setBounds(20, 10, 190, 60);
		add(toolsLabel);
	}
	
//	SET UP TOOL BUTTONS -------------------------------------------------------------------------------------------
	private void setUpToolButtons() {
		
//		Announcements
		announcementsButton.setBounds(0, toolsLabel.getY() + 60, 330, BUTTON_HEIGHT);
		add(announcementsButton);
		
//		Tasks
		tasksButton.setBounds(0, announcementsButton.getY() + BUTTON_HEIGHT, 330, BUTTON_HEIGHT);
		add(tasksButton);
		
//		Calendar
		calendarButton.setBounds(0, tasksButton.getY() + BUTTON_HEIGHT, 330, BUTTON_HEIGHT);
		add(calendarButton);
		
//		Members
		memberButton.setBounds(0, calendarButton.getY() + BUTTON_HEIGHT, 330, BUTTON_HEIGHT);
		add(memberButton);
	}
	
//	SET UP ACTIONS LABEL ------------------------------------------------------------------------------------------
	private void setUpActionsLabel() {
		actionsLabel.setForeground(darkGrey);
		actionsLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 22));
		actionsLabel.setBounds(20, memberButton.getY() + BUTTON_HEIGHT, 190, 60);
		add(actionsLabel);
	}
	
//	SET UP ACTION BUTTONS -----------------------------------------------------------------------------------------
	private void setUpActionButtons() {
		
//		Add announcement
		addAnnounceButton.setBounds(0, actionsLabel.getY() + 60, 330, BUTTON_HEIGHT);
		add(addAnnounceButton);
	
//		Add meeting
		addMeetingButton.setBounds(0, addAnnounceButton.getY() + 55, 330, BUTTON_HEIGHT);
		add(addMeetingButton);
		
//		Add event
		addEventButton.setBounds(0, addMeetingButton.getY() + 55, 330, BUTTON_HEIGHT);
		add(addEventButton);
		
//		Add task
		addTaskButton.setBounds(0, addEventButton.getY() + 55, 330, BUTTON_HEIGHT);
		add(addTaskButton);
	}
	
//	SET UP EXIT BUTTON --------------------------------------------------------------------------------------------
	private void setUpExitButton() {
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		exitButton.setBounds(20, addTaskButton.getY() + 70, 170, 50);
		add(exitButton);
	}
	
//	GETTERS
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public JLabel getToolsLabel() {
		return toolsLabel;
	}
	public void setToolsLabel(JLabel toolsLabel) {
		this.toolsLabel = toolsLabel;
	}
	public JLabel getActionsLabel() {
		return actionsLabel;
	}
	public void setActionsLabel(JLabel actionsLabel) {
		this.actionsLabel = actionsLabel;
	}
	public RoundedButton getExitButton() {
		return exitButton;
	}
	public void setExitButton(RoundedButton exitButton) {
		this.exitButton = exitButton;
	}
	public MenuButton getAnnouncementsButton() {
		return announcementsButton;
	}
	public void setAnnouncementsButton(MenuButton announcementsButton) {
		this.announcementsButton = announcementsButton;
	}
	public MenuButton getTasksButton() {
		return tasksButton;
	}
	public void setTasksButton(MenuButton tasksButton) {
		this.tasksButton = tasksButton;
	}
	public MenuButton getCalendarButton() {
		return calendarButton;
	}
	public void setCalendarButton(MenuButton calendarButton) {
		this.calendarButton = calendarButton;
	}
	public MenuButton getMemberButton() {
		return memberButton;
	}
	public void setMemberButton(MenuButton memberButton) {
		this.memberButton = memberButton;
	}
	public MenuButton getAddAnnounceButton() {
		return addAnnounceButton;
	}
	public void setAddAnnounceButton(MenuButton addAnnounceButton) {
		this.addAnnounceButton = addAnnounceButton;
	}
	public MenuButton getAddMeetingButton() {
		return addMeetingButton;
	}
	public void setAddMeetingButton(MenuButton addMeetingButton) {
		this.addMeetingButton = addMeetingButton;
	}
	public MenuButton getAddEventButton() {
		return addEventButton;
	}
	public void setAddEventButton(MenuButton addEventButton) {
		this.addEventButton = addEventButton;
	}
	public MenuButton getAddTaskButton() {
		return addTaskButton;
	}
	public void setAddTaskButton(MenuButton addTaskButton) {
		this.addTaskButton = addTaskButton;
	}
	public static int getButtonHeight() {
		return BUTTON_HEIGHT;
	}
	public ImageIcon getAnnouncementsIcon() {
		return announcementsIcon;
	}
	public ImageIcon getTaskIcon() {
		return taskIcon;
	}
	public ImageIcon getCalendarIcon() {
		return calendarIcon;
	}
	public ImageIcon getMembersIcon() {
		return membersIcon;
	}
	public ImageIcon getAddAnnouncementIcon() {
		return addAnnouncementIcon;
	}
	public ImageIcon getAddMeetingIcon() {
		return addMeetingIcon;
	}
	public ImageIcon getAddEventIcon() {
		return addEventIcon;
	}
	public ImageIcon getAddTaskIcon() {
		return addTaskIcon;
	}
}
