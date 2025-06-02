package view;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class StartPanel extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color blue = Color.decode("#7fb6e8");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Labels
	private JLabel titleLabel = new JLabel("Chalkboard");
	private JLabel sloganLabel = new JLabel("The smarter way to manage student clubs.");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private JLabel logo = new JLabel(new ImageIcon(unscaledLogo.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
	
//	Line
	private JSeparator line = new JSeparator();
	
//	Buttons
	private JButton loginButton = new RoundedButton("Login \nGet started with an existing account", Color.WHITE, offWhite);
	private JButton signupButton = new RoundedButton("Sign Up \nCreate a new account", Color.WHITE, offWhite);
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public StartPanel() {
		
//		Set up the panel
		initializePanel();
		
//		Logo
		logo.setBounds(670, 60, 200, 200);
		add(logo);

//		Set up all elements
		setUpTitle();
		setUpLine();
		setUpSlogan();
		setUpButtons();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(darkGrey);
        setOpaque(true);
	}
	
//	SET UP TITLE --------------------------------------------------------------------------------------------------
	private void setUpTitle() {
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 180));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(330, 290, 910, 220);
		add(titleLabel);
	}
	
//	SET UP LINE ---------------------------------------------------------------------------------------------------
	private void setUpLine() {
		line.setForeground(Color.WHITE);
		line.setBounds(titleLabel.getX(), titleLabel.getY() + 180, titleLabel.getWidth(), 20);
		add(line);
	}
	
//	SET UP SLOGAN -------------------------------------------------------------------------------------------------
	private void setUpSlogan() {
		sloganLabel.setFont(new Font("Calibri", Font.ITALIC, 52));
		sloganLabel.setForeground(blue);
		sloganLabel.setBounds(titleLabel.getX(), line.getY() + 5, titleLabel.getWidth(), 130);
		add(sloganLabel);
	}
	
//	SET UP BUTTONS ------------------------------------------------------------------------------------------------
	private void setUpButtons() {
		
//		Login button
		loginButton.setForeground(Color.BLACK);
		loginButton.setBounds(titleLabel.getX() + 10, sloganLabel.getY() + 140, 400, 100);
		add(loginButton);
		
//		Sign up button
		signupButton.setBounds(loginButton.getX() + 500, loginButton.getY(), loginButton.getWidth(), loginButton.getHeight());
		add(signupButton);
	}
}
