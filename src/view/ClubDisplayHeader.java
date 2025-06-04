package view;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class ClubDisplayHeader extends RoundedPanel{
	
//	FIELDS ----------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 500;
	private static final int PANEL_HEIGHT = 70;
	
//	Colors
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
		clubLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		clubLabel.setBounds(50, 15, 200, 40);
		add(clubLabel);
	}
	
//	SET UP BUTTON -------------------------------------------------------------------------------------------------
	private void setUpButton() {
		viewButton.setForeground(darkGrey);
		viewButton.setBorderColor(Color.BLACK);
		viewButton.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		viewButton.setBounds(340, 15, 120, 40);
		add(viewButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    int width = getWidth();
	    int height = getHeight();
	    
//	    Rounded rectangle, only top corners are rounded
	    g2.setColor(backgroundColor);
	    g2.fillRoundRect(0, 0, width, radius, radius, radius);
	    
	    g2.dispose();
	    
//	   	Call super to paint child components
	    super.paintChildren(g);
	}

	@Override
	protected void paintChildren(Graphics g) {
	    super.paintChildren(g); 
	}

//	GETTERS -------------------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
