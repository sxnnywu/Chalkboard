package view;

import java.awt.Color;

import javax.swing.JPanel;

public class LoginInputPanel extends RoundedPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
//	Constants
	private static final int PANEL_WIDTH = 1300;
	private static final int PANEL_HEIGHT = 250;
	
//	Colors
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Input fields
	InputField userField = new InputField("Username", 115, false);
	InputField passwordField = new InputField("Password", 115, false);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public LoginInputPanel() {
		
//		Set up the panel
		super(60);
		initializePanel();
		setUpInputFields();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
//  	setOpaque(true);
	}
	
//	SET UP INPUT FIELDS -------------------------------------------------------------------------------------------
	private void setUpInputFields() {
		
//		Username
		userField.setBounds(70, 35, 1150, 70);
		add(userField);
		
//		Password
		passwordField.setBounds(70, 125, 1150, 70);
		add(passwordField);
	}

//	GETTERS 
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}

	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
