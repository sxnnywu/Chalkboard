package view;

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
	HomePanel homePanel = new HomePanel("Sunny"); // HARD CODE FOR NOW
	JoinPanel joinPanel = new JoinPanel(80);
	CreatePanel createPanel = new CreatePanel(90);
	MenuPanel menuPanel = new MenuPanel();
	BlurWrapper blurWrapper;
	
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
//		add(signupPanel);
//		add(loginPanel);
		add(homePanel);
		
//		homePanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
//	    blurWrapper = new BlurWrapper(homePanel);
//	    blurWrapper.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
//	    add(blurWrapper);
		
		showMenu();
		
//		Make the frame appear
		setVisible(true);
	}
	
//	UNBLUR --------------------------------------------------------------------------------------------------------
	public void unBlur(RoundedPanel panel) {
		blurWrapper.clearBlur();
		blurWrapper.remove(panel);  // remove the panel
		blurWrapper.repaint();
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
}
