package view;

import java.awt.event.*;

public class MenuController implements ActionListener{
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	private MainFrame frame;
	private static MenuPanel menuPanel;

//	CONSTRUCTOR  ---------------------------------------------------------------------------------------------------
	public MenuController(MainFrame frame) {
		
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

//	ACTION PERFORMED  ----------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		View club
		for(MenuButton button : menuPanel.getClubs()) {
			if(e.getSource() == button)
				frame.showClub(button.getText());
		}
		
//		Join club
		if(e.getSource() == menuPanel.getJoinButton()) 
			frame.showJoinClub();
		
//		Create club
		if(e.getSource() == menuPanel.getCreateButton()) 
			frame.showCreateClub();
		
//		Notifications
		if(e.getSource() == menuPanel.getNotifButton()) {
//			ADDITIONAL FEATURE IF TIME
		}
		
//		Pending tasks
		if(e.getSource() == menuPanel.getTaskButton()) {
//			ADDITIONAL FEATURE IF TIME
		}
		
//		My account
		if(e.getSource() == menuPanel.getProfileButton()) {
//			ADDITIONAL FEATURE IF TIME
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
