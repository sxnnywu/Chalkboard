package view;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ClubsPanel extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 360;
	
//	Colors
	private final Color offWhite = Color.decode("#f3f3f3");
	private Color[] colours = new Color[5];
	
//	Components
	private ClubsPanelHeader header = new ClubsPanelHeader();
	private List<ClubDisplay> clubs = new ArrayList<>();
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubsPanel() {
		
//		Set up the panel
		initializePanel();
		
//		Header
		header.setBounds(0, 0, header.getPanelWidth(), header.getPanelHeight());
		add(header);
		
//		Set up colours
		setUpColours();
		
//		Add clubs (HARDCODING PURPOSES, REMOVE LATER)
		addAllClubs();
		
//		Club Display
		int x = 30;
		for(ClubDisplay club : clubs) {
			club.setBounds(x, 115, club.getPanelWidth(), club.getPanelHeight());
			add(club);
			x += 500;
		}
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	public void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}
	
//	ADD ALL COLOURS -----------------------------------------------------------------------------------------------
	private void setUpColours() {
		colours[0] = Color.decode("#afcebb");
		colours[1] = Color.decode("#70bdfa");
		colours[2] = Color.decode("#df87fc");
		colours[3] = Color.decode("#ff6868");
		colours[4] = Color.decode("#55e5e1");
	}
	
//	ADD ALL CLUBS (HARDCODING PURPOSES, REMOVE LATER) 
	private void addAllClubs() {
		addClub(colours[0], "Math Club", "June 11th at 3:00 pm", "145flj3"); 
		addClub(colours[1], "DECA", "June 17th at 7:30am", "4fh3j0i");
		addClub(colours[2], "Breeze Code", "June 19th at 3:00pm", "8jhg39d");
	}
	
//	ADD CLUB ------------------------------------------------------------------------------------------------------
	public void addClub(Color color, String clubName, String nextMeeting, String joinCode) {	
		ClubDisplay newClub = new ClubDisplay(color, clubName, nextMeeting, joinCode);
		clubs.add(newClub);
	}

//	GETTERS -------------------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
