package view;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClubsPanel extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 360;
	
//	Colours
	private final Color offWhite = Color.decode("#f3f3f3");
	private Color[] colours = new Color[5];
	
//	Components
	private ClubsPanelHeader header = new ClubsPanelHeader();
	private List<ClubDisplay> clubs = new ArrayList<>();
	
//	Parameters
	private List<String[]> data;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubsPanel(List<String[]> data) {
		
//		Parameters
		this.data = data;
		
//		Set up the panel
		initializePanel();
		
//		Header
		header.setBounds(0, 0, header.getPanelWidth(), header.getPanelHeight());
		add(header);
		
//		Set up colours
		setUpColours();
		
//		Add clubs
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
	
//	ADD ALL CLUBS -------------------------------------------------------------------------------------------------
	private void addAllClubs() {	
		int i = 0;
		for(String[] s : data) {
			addClub(colours[i], s[0], s[1], s[2]);
			i++;
		}
	}
	
//	ADD CLUB ------------------------------------------------------------------------------------------------------
	public void addClub(Color color, String clubName, String nextMeeting, String joinCode) {	
		ClubDisplay newClub = new ClubDisplay(color, clubName, nextMeeting, joinCode);
		clubs.add(newClub);
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public Color[] getColours() {
		return colours;
	}
	public void setColours(Color[] colours) {
		this.colours = colours;
	}
	public ClubsPanelHeader getHeader() {
		return header;
	}
	public void setHeader(ClubsPanelHeader header) {
		this.header = header;
	}
	public List<ClubDisplay> getClubs() {
		return clubs;
	}
	public void setClubs(List<ClubDisplay> clubs) {
		this.clubs = clubs;
	}
}
