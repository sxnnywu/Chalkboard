package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignupPanel extends JPanel{
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color grey = Color.decode("#434242");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Labels
	private JLabel titleLabel = new JLabel("Sign Up");
	private JLabel loginLabel = new JLabel("Already have an account?");
	
//	Buttons
	private RoundedButton loginButton = new RoundedButton("Log In", darkGrey, grey,  new Color(255, 255, 255, 50));
	private RoundedButton signupButton = new RoundedButton("Sign Up", darkGrey, grey,  new Color(255, 255, 255, 50));
	
//	Logo text
	private JLabel logoLabel = new JLabel("Chalkboard");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private Image scaledLogo = getScaledImage(unscaledLogo.getImage(), 100, 100);
	private JLabel logo = new JLabel(new ImageIcon(scaledLogo));
	
//	Input panel
	SignupInputPanel inputPanel = new SignupInputPanel();

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public SignupPanel() {

//		Set up the panel
		initializePanel();
		
//		Logo image
		logo.setBounds(40, 30, 100, 100);
		add(logo);
		
//		Logo label
		logoLabel.setForeground(offWhite);
		logoLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		logoLabel.setBounds(logo.getX() + 100, logo.getY(), 200, 100);
		add(logoLabel);
		
//		Input panel
		inputPanel.setBounds(110, 220, 1300, 400);
		add(inputPanel);

//		Set up all elements
		setUpTitle();
		setUpLoginButton();
		setUpSignupButton();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(darkGrey);
        setOpaque(true);
	}
	
//	GET SCALED IMAGE ----------------------------------------------------------------------------------------------
	private Image getScaledImage(Image srcImg, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();
		return resizedImg;
	}
	
//	SET UP TITLE --------------------------------------------------------------------------------------------------
	private void setUpTitle() {
		titleLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 90));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(600, 30, 400, 200);
		add(titleLabel);
	}
	
//	SET UP LOGIN BUTTON -------------------------------------------------------------------------------------------
	private void setUpLoginButton() {
		
//		Label
		loginLabel.setForeground(offWhite);
		loginLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 22));
		loginLabel.setBounds(logoLabel.getX() - 50, 640, 230, 60);
		add(loginLabel);
		
//		Button
		loginButton.setForeground(offWhite);
		loginButton.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		loginButton.setBorderColor(Color.WHITE);
		loginButton.setBounds(loginLabel.getX(), loginLabel.getY() + 60, loginLabel.getWidth(), 50);
		add(loginButton);
	}
	
//	SET UP SIGN UP BUTTON -----------------------------------------------------------------------------------------
	private void setUpSignupButton() {
		signupButton.setForeground(offWhite);
		signupButton.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		signupButton.setBorderColor(Color.WHITE);
		signupButton.setBounds(1200, loginLabel.getY() + 30, loginLabel.getWidth() + 20, 70);
		add(signupButton);
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public JLabel getLoginLabel() {
		return loginLabel;
	}
	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}
	public RoundedButton getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(RoundedButton loginButton) {
		this.loginButton = loginButton;
	}
	public RoundedButton getSignupButton() {
		return signupButton;
	}
	public void setSignupButton(RoundedButton signupButton) {
		this.signupButton = signupButton;
	}
	public JLabel getLogoLabel() {
		return logoLabel;
	}
	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}
	public ImageIcon getUnscaledLogo() {
		return unscaledLogo;
	}
	public void setUnscaledLogo(ImageIcon unscaledLogo) {
		this.unscaledLogo = unscaledLogo;
	}
	public Image getScaledLogo() {
		return scaledLogo;
	}
	public void setScaledLogo(Image scaledLogo) {
		this.scaledLogo = scaledLogo;
	}
	public JLabel getLogo() {
		return logo;
	}
	public void setLogo(JLabel logo) {
		this.logo = logo;
	}
	public SignupInputPanel getInputPanel() {
		return inputPanel;
	}
	public void setInputPanel(SignupInputPanel inputPanel) {
		this.inputPanel = inputPanel;
	}
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
