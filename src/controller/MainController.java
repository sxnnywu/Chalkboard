package controller;

import java.awt.event.*;

import javax.swing.JPanel;

import view.*;

public class MainController implements ActionListener {
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	private MainFrame frame = new MainFrame();
	
//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public MainController(){
		
		addActionListeners();
	}

//	ADD ACTION LISTENERS -------------------------------------------------------------------------------------------
	public void addActionListeners() {
		
//		Start panel
		frame.getStartPanel().getLoginButton().addActionListener(this); // login
		frame.getStartPanel().getSignupButton().addActionListener(this); // sign up
		
//		Sign up panel
		frame.getSignupPanel().getLoginButton().addActionListener(this); // login
		frame.getSignupPanel().getSignupButton().addActionListener(this); // sign up
		
//		Login panel
		frame.getLoginPanel().getSignupButton().addActionListener(this); // sign up
		frame.getLoginPanel().getLoginButton().addActionListener(this); // login
		
//		Home panel
		frame.getHomePanel().getMenuIcon().addActionListener(this); // menu
		frame.getHomePanel().getClubPanel().getHeader().getJoinButton().addActionListener(this); // join club
		frame.getHomePanel().getClubPanel().getHeader().getCreateButton().addActionListener(this); // create club
		for(ClubDisplay club : frame.getHomePanel().getClubPanel().getClubs()) { 
			club.getHeader().getViewButton().addActionListener(this); // view club
		}
	}
	
//	ACTION PERFORMED -----------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		Start --> Login
		if(e.getSource() == frame.getStartPanel().getLoginButton())
			switchPanel(frame.getStartPanel(), frame.getLoginPanel());
		
//		Start --> Sign up
		if(e.getSource() == frame.getStartPanel().getSignupButton()) 
			switchPanel(frame.getStartPanel(), frame.getSignupPanel());
		
//		Sign up --> Login
		if(e.getSource() == frame.getSignupPanel().getLoginButton()) 
			switchPanel(frame.getSignupPanel(), frame.getLoginPanel());
		
//		Sign up validation
		if(e.getSource() == frame.getSignupPanel().getSignupButton()) 
			signUp();
		
//		Login --> Signup
		if(e.getSource() == frame.getLoginPanel().getSignupButton()) 
			switchPanel(frame.getLoginPanel(), frame.getSignupPanel());
		
//		Login validation
		if(e.getSource() == frame.getLoginPanel().getLoginButton()) 
			login();
		
//		Home --> Menu display
		if(e.getSource() == frame.getHomePanel().getMenuIcon()) {
			
		}
		
//		Home --> Join Club
		if(e.getSource() == frame.getHomePanel().getClubPanel().getHeader().getJoinButton()) {
			frame.showJoinClub();
		}
		
//		Home --> Create Club
		if(e.getSource() == frame.getHomePanel().getClubPanel().getHeader().getCreateButton()) {
			
		}
	}
	
//	SWITCH PANEL ---------------------------------------------------------------------------------------------------
	private void switchPanel(JPanel panel1, JPanel panel2) {
		frame.remove(panel1);
		frame.add(panel2);
		frame.revalidate();
		frame.repaint();
	}
	
//	SIGN UP VALIDATION ---------------------------------------------------------------------------------------------
	private void signUp() {
		
//		CALL BOOLEAN METHOD TO VALIDATE INPUT
		frame.remove(frame.getSignupPanel());
	}
	
//	LOGIN VALIDATION -----------------------------------------------------------------------------------------------
	private void login() {
		
//		CALL BOOLEAN METHOD TO VALIDATE INPUT
		frame.remove(frame.getLoginPanel());
	}

}
