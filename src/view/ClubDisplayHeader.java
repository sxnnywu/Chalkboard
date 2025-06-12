package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClubDisplayHeader extends RoundedPanel{
	
//	FIELDS ----------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 460;
	private static final int PANEL_HEIGHT = 70;
	
//	Colours
	private Color backgroundColor;
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color darkGrey = Color.decode("#2e2e2e");
	
//	Parameters
	private String club;
	private int radius;
	
//	Components
	JLabel clubLabel;
	RoundedButton viewButton = new RoundedButton("View Now", Color.WHITE, offWhite, Color.WHITE);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubDisplayHeader(int radius, Color color, String club) {
		
//		Parameters
		super(radius);
		this.radius = radius;
		backgroundColor = color;
		this.club = club;
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpLabel();
		setUpButton();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(backgroundColor);
		setOpaque(false);
	}
	
//	SET UP LABEL --------- ----------------------------------------------------------------------------------------
	private void setUpLabel() {
		clubLabel = new JLabel(club);
		clubLabel.setForeground(darkGrey);
		clubLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 26));
		clubLabel.setBounds(45, 15, 200, 40);
		add(clubLabel);
	}
	
//	SET UP BUTTON -------------------------------------------------------------------------------------------------
	private void setUpButton() {
		viewButton.setForeground(darkGrey);
		viewButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		viewButton.setBounds(290, 15, 130, 40);
		add(viewButton);
	}
	
//	PAINT COMPONENT -----------------------------------------------------------------------------------------------
	@Override
	protected void paintComponent(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    int width = getWidth();
	    int height = getHeight();
	    
//	    Rounded rectangle, only top corners are rounded
	    g2.setColor(backgroundColor);
	    g2.fillRect(0, radius/2, width, height - radius/2);
	    g2.fillRoundRect(0, 0, width, radius, radius, radius);
	    
	    g2.dispose();
	    
//	   	Call super to paint child components
	    super.paintChildren(g);
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
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public JLabel getClubLabel() {
		return clubLabel;
	}
	public void setClubLabel(JLabel clubLabel) {
		this.clubLabel = clubLabel;
	}
	public RoundedButton getViewButton() {
		return viewButton;
	}
	public void setViewButton(RoundedButton viewButton) {
		this.viewButton = viewButton;
	}
}
