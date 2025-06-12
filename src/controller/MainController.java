package controller;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dao.*;
import model.*;
import view.*;

public class MainController implements ActionListener {
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	private MainFrame frame = new MainFrame();
	private DashboardController dashboardController;
//	private MenuController menuController;
	private ClubDAO clubDAO;
	private MemberDAO memberDAO;
	private UserDAO userDAO;
	
	private User currentUser;
	
//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public MainController() throws SQLException{

		clubDAO = new ClubDAO(DBUtil.getConnection());
		memberDAO = new MemberDAO(DBUtil.getConnection()); 
		userDAO = new UserDAO(DBUtil.getConnection());
		
		dashboardController = new DashboardController(frame);
//		menuController = new MenuController(frame, dashboardController);
		
		addActionListeners();
	}
	
//	LOAD USER CLUBS 
	private void loadUserClubs() throws SQLException {

		List<Member> memberships = memberDAO.getClubsByUserId(currentUser.getUserID()); 

		List<String[]> clubData = new ArrayList<>();
		for (Member m : memberships) {
			Club club = clubDAO.getById(m.getClubID()); 
			String[] data = {
				club.getName(),
				clubDAO.getNextMeetingTime(club.getClubID()),
				club.getJoinCode()
			};
			clubData.add(data);
		}

		// Replace hardcoded panel with live data
		frame.initializeHomePanel("Sunny", clubData); // TODO: fetch actual name later
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
		
//		Join panel
		frame.getJoinPanel().getJoinButton().addActionListener(this); // join club
		
//		Create panel
		frame.getCreatePanel().getCreateButton().addActionListener(this); // create club
	}
	
//	ADD HOME ACTION LISTENERS --------------------------------------------------------------------------------------
	public void addHomeActionListeners() {
		frame.getHomePanel().getMenuIcon().addActionListener(this); // menu
		frame.getHomePanel().getClubPanel().getHeader().getJoinButton().addActionListener(this); // join club
		frame.getHomePanel().getClubPanel().getHeader().getCreateButton().addActionListener(this); // create club
		
		for(ClubDisplay club : frame.getHomePanel().getClubPanel().getClubs()) 
			club.getHeader().getViewButton().addActionListener(this); // view club
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
		
//		TODO: Sign up validation
		if(e.getSource() == frame.getSignupPanel().getSignupButton()) 
			signUp();
		
//		Login --> Signup
		if(e.getSource() == frame.getLoginPanel().getSignupButton()) 
			switchPanel(frame.getLoginPanel(), frame.getSignupPanel());
		
//		TODO: Login validation
		if(e.getSource() == frame.getLoginPanel().getLoginButton()) 
			login();
		
//		Home panel
		if(frame.getHomePanel() != null){
			
//			Home --> Menu display
			if(e.getSource() == frame.getHomePanel().getMenuIcon()) 
				frame.showMenu();
			
//			Home --> Join Club
			if(e.getSource() == frame.getHomePanel().getClubPanel().getHeader().getJoinButton()) 
				frame.showJoinClub();
			
//			Home --> Create Club
			if(e.getSource() == frame.getHomePanel().getClubPanel().getHeader().getCreateButton()) 
				frame.showCreateClub();
			
//			View club
			for(ClubDisplay club : frame.getHomePanel().getClubPanel().getClubs()) { 
				if(e.getSource() == club.getHeader().getViewButton()) 
					showClub(club.getClubName());
			}
		}
		
//		TODO: Join club validation
		if(e.getSource() == frame.getJoinPanel().getJoinButton()) 
			joinClub();
		
//		TODO: Create club validation
		if(e.getSource() == frame.getCreatePanel().getCreateButton()) 
			createClub();		
	}
	
//	SWITCH PANEL ---------------------------------------------------------------------------------------------------
	private void switchPanel(JPanel panel1, JPanel panel2) {
		frame.remove(panel1);
		frame.add(panel2);
		frame.revalidate();
		frame.repaint();
	}
	
//	SHOW CLUB
	private void showClub(String clubName) {
		String clubID = clubDAO.getClubIdByName(clubName);
		try {
			dashboardController.showClub(clubName, clubID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
//	SIGN UP VALIDATION ---------------------------------------------------------------------------------------------
	private void signUp() {
		
		frame.remove(frame.getSignupPanel());
	}
	
//	LOGIN VALIDATION -----------------------------------------------------------------------------------------------
	private void login() {
	    
//		Get username and password from the login input panel
	    String username = frame.getLoginPanel().getInputPanel().getUserName();
	    String password = frame.getLoginPanel().getInputPanel().getPasswordHash();

	    try {    	
//	    	Find user by username
	        User user = userDAO.getByUsername(username);

//	        If no user was found
	        if (user == null) {
	            JOptionPane.showMessageDialog(frame, "Username not found.", "Login Failed", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
//	        If incorrect password
	        if (!user.getPasswordHash().equals(password)) {
	            JOptionPane.showMessageDialog(frame, "Incorrect password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
//	        Success -- store current user and load clubs
	        currentUser = user;
	        List<Member> memberships = memberDAO.getClubsByUserId(currentUser.getUserID());

//	        Club data
	        List<String[]> clubData = new ArrayList<>();
	        for (Member m : memberships) {
	        	Club club = clubDAO.getById(m.getClubID());
	            String[] data = {club.getName(), "meeting", // TODO: implement next meeting
	                club.getJoinCode()
	            };
	            clubData.add(data);
	        }
//	        Display name
	        String displayName = currentUser.getFirstName();
	        loadUserClubs();
	        frame.initializeHomePanel(displayName, clubData);
	        addHomeActionListeners();
	        switchPanel(frame.getLoginPanel(), frame.getHomePanel());

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Database error during login.", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}

//	JOIN CLUB VALIDATION -------------------------------------------------------------------------------------------
	private void joinClub() {
		
		String joinCode = frame.getJoinPanel().getJoinCodeInput().getInput();
	    String role = frame.getJoinPanel().getRoleInput().getInput(); 

	    try {
	        
//	    	Find club by join code
	        Club club = clubDAO.getByJoinCode(joinCode);

//	        If club not found
	        if (club == null) {
	            JOptionPane.showMessageDialog(frame, "Invalid join code.", "Join Failed", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

//	        Check if user is already a member (optional but recommended)
	        boolean alreadyMember = memberDAO.exists(currentUser.getUserID(), club.getClubID());
	        if (alreadyMember) {
	            JOptionPane.showMessageDialog(frame, "You are already a member of this club.", "Join Failed", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }

//	        Add user to database as member
	        Member newMember = new Member(currentUser, role, club.getClubID());
	        memberDAO.insert(newMember, club.getClubID());

//	        Confirm success
	        JOptionPane.showMessageDialog(frame, "You joined the club successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

//	        Return to home panel
	        switchPanel(frame.getJoinPanel(), frame.getHomePanel());
	        
//	        Add club to UI
	        frame.getHomePanel().getClubPanel().addClub(
	        		frame.getHomePanel().getClubPanel().getRandomColour(),
	        		club.getName(),
	        		clubDAO.getNextMeetingTime(club.getClubID()),
	        		joinCode
	        		);
//	        TODO: Add menu button on menu panel for the new club
	        frame.removeJoinClub();

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Error while joining club.", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}
	
//	CREATE CLUB VALIDATION -----------------------------------------------------------------------------------------
	private void createClub() {
//		TODO: implementation
	}
}
