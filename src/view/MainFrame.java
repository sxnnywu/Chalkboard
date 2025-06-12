package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainFrame extends JFrame{
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int FRAME_WIDTH = 1920;
	private static final int FRAME_HEIGHT = 1080;
	
//	Panels & Components
	StartPanel startPanel = new StartPanel();
	LoginPanel loginPanel = new LoginPanel();
	SignupPanel signupPanel = new SignupPanel();
	
	HomePanel homePanel = new HomePanel("Sunny", sampleHomeClubs()); // HARD CODE FOR NOW
	JoinPanel joinPanel = new JoinPanel(80);
	CreatePanel createPanel = new CreatePanel(90);
	MenuPanel menuPanel = new MenuPanel(sampleHomeClubs()); // HARD CODE FOR NOW
	
	DashboardPanel dashboardPanel;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public MainFrame() {
		
//		Set up the frame
		setTitle("Chalkboard");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(null);
		
//		Closing the program
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//		Starting panel
//		add(startPanel);
		add(homePanel);
		
//		Make the frame appear
		setVisible(true);
	}
	
//	SAMPLE HOME CLUBS (HARD CODED FOR NOW, FIX LATER)
	private List<String[]> sampleHomeClubs(){
		List<String[]> list = new ArrayList<>();
		list.add(new String[] {"Math Club", "meeting", "joinCode"});
		list.add(new String[] {"DECA", "meeting", "joinCode"});
		list.add(new String[] {"Breeze Code", "meeting", "joinCode"});
		return list;
	}
	
//	SHOW JOIN CLUB ------------------------------------------------------------------------------------------------
	public void showJoinClub() {
		joinPanel.setBounds(430, 200, joinPanel.getPanelWidth(), joinPanel.getPanelHeight());
		homePanel.add(joinPanel);
		homePanel.setComponentZOrder(joinPanel, 0);
		revalidate();
		repaint();
	}
	
//	SHOW CREATE CLUB -----------------------------------------------------------------------------------------------
	public void showCreateClub() {
		createPanel.setBounds(430, 130, createPanel.getPanelWidth(), createPanel.getPanelHeight());
		homePanel.add(createPanel);
		homePanel.setComponentZOrder(createPanel, 0);
		revalidate();
		repaint();
	}
	
//	SHOW MENU ------------------------------------------------------------------------------------------------------
	public void showMenu() {
		menuPanel.setBounds(1200, 0, menuPanel.getPanelWidth(), menuPanel.getPanelHeight());
		homePanel.add(menuPanel);
		homePanel.setComponentZOrder(menuPanel, 0);
		revalidate();
		repaint();
	}

//	GETTERS --------------------------------------------------------------------------------------------------------
	public StartPanel getStartPanel() {
		return startPanel;
	}
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	public SignupPanel getSignupPanel() {
		return signupPanel;
	}
	public HomePanel getHomePanel() {
		return homePanel;
	}
	public JoinPanel getJoinPanel() {
		return joinPanel;
	}
	public CreatePanel getCreatePanel() {
		return createPanel;
	}
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}
	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}
	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}
	public DashboardPanel getDashboardPanel() {
		return dashboardPanel;
	}
	public void setDashboardPanel(DashboardPanel dashboardPanel) {
		this.dashboardPanel = dashboardPanel;
	}
	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}
	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	public void setSignupPanel(SignupPanel signupPanel) {
		this.signupPanel = signupPanel;
	}
	public void setHomePanel(HomePanel homePanel) {
		this.homePanel = homePanel;
	}
	public void setJoinPanel(JoinPanel joinPanel) {
		this.joinPanel = joinPanel;
	}
	public void setCreatePanel(CreatePanel createPanel) {
		this.createPanel = createPanel;
	}
	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}
}
