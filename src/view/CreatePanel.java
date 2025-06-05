package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class CreatePanel extends RoundedPanel {
	
//	FIELDS

//	Constants
	private static final int PANEL_WIDTH = 700;
	private static final int PANEL_HEIGHT = 480;
	
//	Colours
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color blue = Color.decode("#70bdfa");
	
//	Components
	private JLabel titleLabel = new JLabel("Create Club");
	private RoundedButton createButton = new RoundedButton("Create", blue, getLighterColor(blue, 0.3f), Color.BLACK);
	private InputField nameInput = new InputField("Club Name", 140, true);
	private InputField roleInput = new InputField("My Role", 60, true);
	private InputField meetingInput = new InputField("Meeting Date & Time", 60, true);
	private InputField locationInput = new InputField("Meeting Location", 60, true);
	private JLabel frequencyLabel = new JLabel("Meeting Frequency");
	private JComboBox frequencyBox = new JComboBox();
	
//	CONSTRUCTOR
	public CreatePanel(int radius) {
		super(radius);
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpTitle();
		setUpCreateButton();
		setUpInputFields();
		setUpComboBox();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	public void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
		// Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

		int r = (int) (color.getRed() + (255 - color.getRed()) * factor);
		int g = (int) (color.getGreen() + (255 - color.getGreen()) * factor);
		int b = (int) (color.getBlue() + (255 - color.getBlue()) * factor);

		return new Color(r, g, b);
	}
	
//	SET UP TITLE --------------------------------------------------------------------------------------------------
	private void setUpTitle() {
		titleLabel.setForeground(darkGrey);
		titleLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 40));
		titleLabel.setBounds(240, 40, 250, 50);
		add(titleLabel);
	}
	
//	SET UP CREATE BUTTON ------------------------------------------------------------------------------------------
	private void setUpCreateButton() {
		createButton.setBackground(blue);
		createButton.setForeground(Color.WHITE);
		createButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		createButton.setBounds(280, 400, 160, 50);
		add(createButton);
	}
	
//	SET UP INPUT FIELDS -------------------------------------------------------------------------------------------
	private void setUpInputFields() {
		
//		Name input
		nameInput.setBounds(30, 120, 640, 70);
		add(nameInput);
		
//		Role input
		roleInput.setBounds(nameInput.getX(), nameInput.getY() + 90, 310, nameInput.getHeight());
		add(roleInput);
		
//		Meeting input
		meetingInput.setBounds(nameInput.getX() + 330, roleInput.getY(), roleInput.getWidth(), roleInput.getHeight());
		add(meetingInput);
		
//		Location input
		locationInput.setBounds(nameInput.getX(), nameInput.getY() + 180, roleInput.getWidth(), nameInput.getHeight());
		add(locationInput);
	}
	
//	ADD FREQUENCY OPTIONS -----------------------------------------------------------------------------------------
	private void setUpComboBox() {
		
//		Frequency label
		frequencyLabel.setForeground(darkGrey);
		frequencyLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyLabel.setBounds(meetingInput.getX(), locationInput.getY() - 10, roleInput.getWidth(), 40);
		add(frequencyLabel);
		
//		Add options
		frequencyBox.addItem("Weekly");
		frequencyBox.addItem("Biweekly");
		frequencyBox.addItem("Monthly");
		
//		Customize
		frequencyBox.setBackground(Color.WHITE);
		frequencyBox.setForeground(darkGrey);
		frequencyBox.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		frequencyBox.setBounds(frequencyLabel.getX(), frequencyLabel.getY() + 38, frequencyLabel.getWidth(), 42);
		add(frequencyBox);
	}

//	GETTERS -------------------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	
	
}
