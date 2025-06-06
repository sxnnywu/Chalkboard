package view;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class MenuPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 330;
	private static final int PANEL_HEIGHT = 1080;
	private static final int BUTTON_HEIGHT = MenuButton.getButtonHeight();
	
//	Colours
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color red = Color.decode("#fc7b7b");
	
//	Icons
	private final ImageIcon addIcon = new ImageIcon("icons/add.png");
	private final ImageIcon arrowIcon = new ImageIcon("icons/arrowFWD.png");
	private final ImageIcon checklistIcon = new ImageIcon("icons/checklist.png");
	private final ImageIcon groupIcon = new ImageIcon("icons/group.png");
	private final ImageIcon notifIcon = new ImageIcon("icons/notifs.png");
	private final ImageIcon profileIcon = new ImageIcon("icons/profile.png");
	
//	Labels
	private JLabel myClubsLabel = new JLabel("My Clubs");
	private JLabel actionsLabel = new JLabel("More Actions");
	private JLabel shortcutsLabel = new JLabel("Shortcuts");
	
//	Menu buttons
	private int y = 80; // y value of FIRST button
	private List<MenuButton> clubs = new ArrayList<>();
	private MenuButton joinButton = new MenuButton("Join Club", arrowIcon);
	private MenuButton createButton = new MenuButton("Create Club", addIcon);
	private MenuButton notifButton = new MenuButton("Notifications", notifIcon);
	private MenuButton taskButton = new MenuButton("Pending Tasks", checklistIcon);
	private MenuButton profileButton = new MenuButton("My Account", profileIcon);
	
//	Logout button
	private RoundedButton logoutButton = new RoundedButton("Log Out", red, getLighterColor(red, 0.2f), Color.BLACK);
	
//	CONSTRUCTOR --------------------------------------------------------------------------------------------------
	public MenuPanel() {
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpClubsLabel();
		setUpClubs();
		setUpActionsLabel();
		setUpActionButtons();
		setUpShortcutsLabel();
		setUpShortcutButtons();
		setUpLogoutButton();
	}
	
//	INITIALIZE PANEL ---------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}

//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
		// Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

		int r = (int) (color.getRed() + (255 - color.getRed()) * factor);
		int g = (int) (color.getGreen() + (255 - color.getGreen()) * factor);
		int b = (int) (color.getBlue() + (255 - color.getBlue()) * factor);

		return new Color(r, g, b);
	}
	
//	SET UP CLUBS LABEL -------------------------------------------------------------------------------------------
	private void setUpClubsLabel() {
		
//		My clubs
		myClubsLabel.setForeground(darkGrey);
		myClubsLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 27));
		myClubsLabel.setBounds(30, 40, 330, 40);
		add(myClubsLabel);
	}
	
//	SET UP CLUB BUTTONS ------------------------------------------------------------------------------------------
	private void setUpClubs() {
		
//		List of clubs HARD CODED FOR TESTING PURPOSES
		for(ClubDisplay clubDisplay : ClubsPanel.getClubs()) 
			addClub(clubDisplay.getClubName());
		
//		Menu buttons
		for(MenuButton club : clubs) {
			club.setBounds(0, y, 330, BUTTON_HEIGHT);
			add(club);
			y += 50;
		}
	}
	
//	ADD CLUB -----------------------------------------------------------------------------------------------------
	private void addClub(String clubName) {
		clubs.add(new MenuButton(clubName, groupIcon));
	}
	
//	SET UP ACTIONS LABEL -----------------------------------------------------------------------------------------
	private void setUpActionsLabel() {
		actionsLabel.setForeground(darkGrey);
		actionsLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 27));
		actionsLabel.setBounds(30, y + 25, 330, 40);
		add(actionsLabel);
	}
	
//	SET UP ACTION BUTTONS ----------------------------------------------------------------------------------------
	private void setUpActionButtons() {
		
//		Join club
		joinButton.setBounds(0, actionsLabel.getY() + 50, 330, BUTTON_HEIGHT);
		add(joinButton);
		
//		Create club
		createButton.setBounds(0, joinButton.getY() + 50, 330, BUTTON_HEIGHT);
		add(createButton);
	}
	
//	SET UP SHORTCUTS LABEL ---------------------------------------------------------------------------------------
	private void setUpShortcutsLabel() {
		shortcutsLabel.setForeground(darkGrey);
		shortcutsLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 27));
		shortcutsLabel.setBounds(30, createButton.getY() + 85, 330, 40);
		add(shortcutsLabel);
	}
	
//	SET UP SHORTCUT BUTTONS --------------------------------------------------------------------------------------
	private void setUpShortcutButtons() {
		
//		Notifications
		notifButton.setBounds(0, shortcutsLabel.getY() + 50, 330, BUTTON_HEIGHT);
		add(notifButton);
		
//		Pending tasks
		taskButton.setBounds(0, notifButton.getY() + 50, 330, BUTTON_HEIGHT);
		add(taskButton);
		
//		My account
		profileButton.setBounds(0, taskButton.getY() + 50, 330, BUTTON_HEIGHT);
		add(profileButton);
	}
	
//	SET UP LOGOUT BUTTON -----------------------------------------------------------------------------------------
	private void setUpLogoutButton() {
		logoutButton.setBackground(red);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		logoutButton.setBounds(27, 710, 280, 50);
		add(logoutButton);
	}
	
//	GETTERS ------------------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public JLabel getMyClubsLabel() {
		return myClubsLabel;
	}
	public void setMyClubsLabel(JLabel myClubsLabel) {
		this.myClubsLabel = myClubsLabel;
	}
	public JLabel getActionsLabel() {
		return actionsLabel;
	}
	public void setActionsLabel(JLabel actionsLabel) {
		this.actionsLabel = actionsLabel;
	}
	public JLabel getShortcutsLabel() {
		return shortcutsLabel;
	}
	public void setShortcutsLabel(JLabel shortcutsLabel) {
		this.shortcutsLabel = shortcutsLabel;
	}
	public List<MenuButton> getClubs() {
		return clubs;
	}
	public void setClubs(List<MenuButton> clubs) {
		this.clubs = clubs;
	}
	public MenuButton getJoinButton() {
		return joinButton;
	}
	public void setJoinButton(MenuButton joinButton) {
		this.joinButton = joinButton;
	}
	public MenuButton getCreateButton() {
		return createButton;
	}
	public void setCreateButton(MenuButton createButton) {
		this.createButton = createButton;
	}
	public MenuButton getNotifButton() {
		return notifButton;
	}
	public void setNotifButton(MenuButton notifButton) {
		this.notifButton = notifButton;
	}
	public MenuButton getTaskButton() {
		return taskButton;
	}
	public void setTaskButton(MenuButton taskButton) {
		this.taskButton = taskButton;
	}
	public MenuButton getProfileButton() {
		return profileButton;
	}
	public void setProfileButton(MenuButton profileButton) {
		this.profileButton = profileButton;
	}
	public RoundedButton getLogoutButton() {
		return logoutButton;
	}
	public void setLogoutButton(RoundedButton logoutButton) {
		this.logoutButton = logoutButton;
	}
	public static int getButtonHeight() {
		return BUTTON_HEIGHT;
	}
	public ImageIcon getAddIcon() {
		return addIcon;
	}
	public ImageIcon getArrowIcon() {
		return arrowIcon;
	}
	public ImageIcon getChecklistIcon() {
		return checklistIcon;
	}
	public ImageIcon getGroupIcon() {
		return groupIcon;
	}
	public ImageIcon getNotifIcon() {
		return notifIcon;
	}
	public ImageIcon getProfileIcon() {
		return profileIcon;
	}
}
