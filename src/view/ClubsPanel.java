package view;

import java.awt.Color;

import javax.swing.JPanel;

public class ClubsPanel extends JPanel {

//	FIELDS
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 400;
	
//	Colors
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Components
	ClubsPanelHeader header = new ClubsPanelHeader();
	
//	CONSTRUCTOR
	public ClubsPanel() {
		
//		Set up the panel
		initializePanel();
		
		header.setBounds(0, 0, header.getPanelWidth(), header.getPanelHeight());
		add(header);
	}
	
//	INITIALIZE PANEL
	public void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}

//	GETTERS
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}

	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	
	
}
