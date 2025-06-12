package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JoinPanel extends RoundedPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 700;
	private static final int PANEL_HEIGHT = 390;
	
//	Colours
	private final Color offWhite = Color.decode("#f3f3f3");
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color blue = Color.decode("#70bdfa");
	
//	Components
	private JLabel titleLabel = new JLabel("Join Club");
	private RoundedButton joinButton = new RoundedButton("Join", blue, getLighterColor(blue, 0.3f), Color.BLACK);
	private InputField joinCodeInput = new InputField("Join Code", 60, true);
	private InputField roleInput = new InputField("My Role", 60, true);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public JoinPanel(int radius) {
		
//		Parent object constructor
		super(radius);
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpTitle();
		setUpJoinButton();
		setUpInputFields();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	public void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
//		Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

//		Alter RGB values
		int r = (int) (color.getRed() + (255 - color.getRed()) * factor);
		int g = (int) (color.getGreen() + (255 - color.getGreen()) * factor);
		int b = (int) (color.getBlue() + (255 - color.getBlue()) * factor);

		return new Color(r, g, b);
	}
	
//	SET UP TITLE --------------------------------------------------------------------------------------------------
	private void setUpTitle() {
		titleLabel.setForeground(darkGrey);
		titleLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 40));
		titleLabel.setBounds(275, 40, 200, 50);
		add(titleLabel);
	}
	
//	SET UP JOIN BUTTON --------------------------------------------------------------------------------------------
	private void setUpJoinButton() {
		joinButton.setBackground(blue);
		joinButton.setForeground(Color.WHITE);
		joinButton.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		joinButton.setBounds(280, 310, 160, 50);
		add(joinButton);
	}
	
//	SET UP INPUT FIELDS -------------------------------------------------------------------------------------------
	private void setUpInputFields() {
		
//		Join Code
		joinCodeInput.setBounds(35, 120, 640, 70);
		add(joinCodeInput);
		
//		Role
		roleInput.setBounds(joinCodeInput.getX(), joinCodeInput.getY() + 90, joinCodeInput.getWidth(), joinCodeInput.getHeight());
		add(roleInput);
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public RoundedButton getJoinButton() {
		return joinButton;
	}
	public void setJoinButton(RoundedButton joinButton) {
		this.joinButton = joinButton;
	}
	public InputField getJoinCodeInput() {
		return joinCodeInput;
	}
	public void setJoinCodeInput(InputField joinCodeInput) {
		this.joinCodeInput = joinCodeInput;
	}
	public InputField getRoleInput() {
		return roleInput;
	}
	public void setRoleInput(InputField roleInput) {
		this.roleInput = roleInput;
	}
}
