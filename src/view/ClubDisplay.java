package view;

import java.awt.Color;

import javax.swing.*;

public class ClubDisplay extends RoundedPanel{

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 500;
	private static final int PANEL_HEIGHT = 200;
	private static final int RADIUS = 90;
	
//	Colours
	Color backgroundColor;
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Components
	ClubDisplayHeader header;
	JLabel meetingLabel;
	JLabel joinCode;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubDisplay(Color color, String clubName) {
		
		super(RADIUS);
		backgroundColor = color;
		
//		Set up the panel
		initializePanel();
		
//		Header
		Color lighterColor = getLighterColor(backgroundColor, 0.15f);
		header = new ClubDisplayHeader(RADIUS, lighterColor, clubName);
		header.setBounds(0, 0, header.getPanelWidth(), header.getPanelHeight());
		add(header);
		
//		Meeting label
		
//		Join code
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(backgroundColor);
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
	    float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
	    hsb[2] = Math.min(1.0f, hsb[2] + factor); // Brightness
	    return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}

//	GETTERS -------------------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
