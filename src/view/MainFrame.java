package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	StartPanel startPanel = new StartPanel();
	LoginPanel loginPanel = new LoginPanel();
	SignupPanel signupPanel = new SignupPanel();
	HomePanel homePanel = new HomePanel();
	
//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public MainFrame() {
		
//		Set up the frame
		setTitle("Chalkboard");
		setSize(1920, 1080);
		setLayout(null);
		
//		Closing the program
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//		Starting panel
//		add(startPanel);
//		add(signupPanel);
//		add(loginPanel);
		add(homePanel);
		
//		Make the frame appear
		setVisible(true);
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
