package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClubDisplay extends RoundedPanel{

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 460;
	private static final int PANEL_HEIGHT = 180;
	private static final int RADIUS = 90;
	
//	Colours
	Color backgroundColor;
	private final Color darkGrey = Color.decode("#2e2e2e");
	
//	Components
	ClubDisplayHeader header;
	JLabel meetingLabel;
	JLabel joinCodeLabel;
	
//	Parameters
	String clubName;
	String nextMeeting;
	String joinCode;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubDisplay(Color color, String clubName, String nextMeeting, String joinCode) {
		
//		Parent object constructor
		super(RADIUS);
		
//		Parameters
		backgroundColor = color;
		this.clubName = clubName;
		this.nextMeeting = nextMeeting;
		this.joinCode = joinCode;
				
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpHeader();
		setUpMeetingLabel();
		setUpJoinCodeLabel();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(backgroundColor);
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

//	SET UP HEADER -------------------------------------------------------------------------------------------------
	private void setUpHeader() {
		Color lighterColor = getLighterColor(backgroundColor, 0.35f);
		header = new ClubDisplayHeader(RADIUS, lighterColor, clubName);
		header.setBounds(0, 0, header.getPanelWidth(), header.getPanelHeight());
		add(header);
	}
	
//	SET UP MEETING LABEL ------------------------------------------------------------------------------------------
	private void setUpMeetingLabel() {
		meetingLabel = new JLabel("Next Meeting: " + nextMeeting);
		meetingLabel.setForeground(darkGrey);
		meetingLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 19));
		meetingLabel.setBounds(45, 88, 360, 30);
		add(meetingLabel);
	}
	
//	SET UP JOIN CODE LABEL ----------------------------------------------------------------------------------------
	private void setUpJoinCodeLabel(){
		joinCodeLabel = new JLabel("Join Code: " + joinCode);
		joinCodeLabel.setForeground(darkGrey);
		joinCodeLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 19));
		joinCodeLabel.setBounds(meetingLabel.getX(), meetingLabel.getY() + 27, meetingLabel.getWidth(), 30);
		add(joinCodeLabel);
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public ClubDisplayHeader getHeader() {
		return header;
	}
	public void setHeader(ClubDisplayHeader header) {
		this.header = header;
	}
	public JLabel getMeetingLabel() {
		return meetingLabel;
	}
	public void setMeetingLabel(JLabel meetingLabel) {
		this.meetingLabel = meetingLabel;
	}
	public JLabel getJoinCodeLabel() {
		return joinCodeLabel;
	}
	public void setJoinCodeLabel(JLabel joinCodeLabel) {
		this.joinCodeLabel = joinCodeLabel;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getNextMeeting() {
		return nextMeeting;
	}
	public void setNextMeeting(String nextMeeting) {
		this.nextMeeting = nextMeeting;
	}
	public String getJoinCode() {
		return joinCode;
	}
	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}
	public static int getRadius() {
		return RADIUS;
	}
}
