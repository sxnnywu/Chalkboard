package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClubsPanelHeader extends JPanel {

//	FIELDS
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 80;
	
//	Colors
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color grey = Color.decode("#cccccc");
	
//	Components
	private JLabel label = new JLabel("My Clubs");
	private RoundedButton createButton = new RoundedButton("Create Club", offWhite, Color.WHITE, Color.WHITE);
	private RoundedButton joinButton = new RoundedButton("Join Club", offWhite, Color.WHITE, Color.WHITE);
	
//	CONSTRUCTOR
	public ClubsPanelHeader() {
		
		initializePanel();
		setUpLabel();
		setUpCreateButton();
	}
	
//	INITIALIZE PANEL
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(grey);
	}
	
//	SET UP LABEL
	private void setUpLabel() {
		label.setForeground(darkGrey);
		label.setFont(new Font("Gill Sans MT", Font.BOLD, 36));
		label.setBounds(25, 15, 400, 50);
		add(label);
	}

//	SET UP CREATE BUTTON
	private void setUpCreateButton() {
		
//		ADD ICON!!!!!!!!!
		createButton.setForeground(darkGrey);
		createButton.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		createButton.setBounds(1000, 15, 150, 40);
		add(createButton);
	}
	
//	GETTERS
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
