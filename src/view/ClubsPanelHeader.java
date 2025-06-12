package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClubsPanelHeader extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 80;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color grey = Color.decode("#cccccc");
	
//	Components
	private JLabel label = new JLabel("My Clubs");
	private RoundedButton createButton = new RoundedButton("Create Club", Color.WHITE, offWhite, Color.WHITE);
	private RoundedButton joinButton = new RoundedButton("Join Club", Color.WHITE, offWhite, Color.WHITE);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public ClubsPanelHeader() {
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpLabel();
		setUpCreateButton();
		setUpJoinButton();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(grey);
	}
	
//	SET UP LABEL --------------------------------------------------------------------------------------------------
	private void setUpLabel() {
		label.setForeground(darkGrey);
		label.setFont(new Font("Gill Sans MT", Font.BOLD, 36));
		label.setBounds(45, 15, 400, 50);
		add(label);
	}

//	SET UP CREATE BUTTON ------------------------------------------------------------------------------------------
	private void setUpCreateButton() {
//		TODO: Add icon if time
		createButton.setForeground(darkGrey);
		createButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		createButton.setBounds(1100, 15, 180, 50);
		add(createButton);
	}
	
//	SET UP JOIN BUTTON --------------------------------------------------------------------------------------------
	private void setUpJoinButton() {
//		TODO: Add icon if time
		joinButton.setForeground(darkGrey);
		joinButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		joinButton.setBounds(1300, 15, 180, 50);
		add(joinButton);
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public JLabel getLabel() {
		return label;
	}
	public RoundedButton getCreateButton() {
		return createButton;
	}
	public RoundedButton getJoinButton() {
		return joinButton;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public void setCreateButton(RoundedButton createButton) {
		this.createButton = createButton;
	}
	public void setJoinButton(RoundedButton joinButton) {
		this.joinButton = joinButton;
	}
}
