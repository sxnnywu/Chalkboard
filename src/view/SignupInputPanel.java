package view;

import java.awt.Color;

@SuppressWarnings("serial")
public class SignupInputPanel extends RoundedPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1300;
	private static final int PANEL_HEIGHT = 420;
	
//	Colors
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Input fields
	InputField firstNameField = new InputField("First name", 55, false);
	InputField lastNameField = new InputField("Last name", 55, false);
	InputField userField = new InputField("Choose a username", 55, false);
	InputField emailField = new InputField("Enter your email", 55, false);
	InputField passwordField = new InputField("Choose a secure password", 115, false);
	InputField confirmField = new InputField("Confirm password", 115, false);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public SignupInputPanel() {
		
//		Parent object constructor
		super(60);
		
//		Set up the panel & components
		initializePanel();
		setUpInputFields();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}
	
//	SET UP INPUT FIELDS -------------------------------------------------------------------------------------------
	private void setUpInputFields() {
		
//		First name
		firstNameField.setBounds(70, 35, 550, 70);
		add(firstNameField);
		
//		Last name
		lastNameField.setBounds(670, 35, 550, 70);
		add(lastNameField);
		
//		Username
		userField.setBounds(70, 125, 550, 70);
		add(userField);
		
//		Email
		emailField.setBounds(670, 125, 550, 70);
		add(emailField);
		
//		Password
		passwordField.setBounds(70, 215, 1150, 70);
		add(passwordField);
		
//		Confirm password
		confirmField.setBounds(70, 305, 1150, 70);
		add(confirmField);
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public InputField getFirstNameField() {
		return firstNameField;
	}
	public void setFirstNameField(InputField firstNameField) {
		this.firstNameField = firstNameField;
	}
	public InputField getLastNameField() {
		return lastNameField;
	}
	public void setLastNameField(InputField lastNameField) {
		this.lastNameField = lastNameField;
	}
	public InputField getUserField() {
		return userField;
	}
	public void setUserField(InputField userField) {
		this.userField = userField;
	}
	public InputField getEmailField() {
		return emailField;
	}
	public void setEmailField(InputField emailField) {
		this.emailField = emailField;
	}
	public InputField getPasswordField() {
		return passwordField;
	}
	public void setPasswordField(InputField passwordField) {
		this.passwordField = passwordField;
	}
	public InputField getConfirmField() {
		return confirmField;
	}
	public void setConfirmField(InputField confirmField) {
		this.confirmField = confirmField;
	}
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
