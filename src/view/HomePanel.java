package view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class HomePanel extends JPanel {
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color grey = Color.decode("#434242");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Logo text
	private JLabel logoLabel = new JLabel("Chalkboard");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private Image scaledLogo = getScaledImage(unscaledLogo.getImage(), 100, 100);
	private JLabel logo = new JLabel(new ImageIcon(scaledLogo));
	
//	Labels
	private JLabel welcomeLabel;
	
//	Clubs panel
	private ClubsPanel clubPanel = new ClubsPanel();
	
//	First name
	private String firstName;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public HomePanel(String firstName) { 
		
		this.firstName = firstName;
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpLogo();
		setUpWelcome();
		
//		Club panel
		clubPanel.setBounds(0, 280, clubPanel.getPanelWidth(), clubPanel.getPanelHeight());
		add(clubPanel);
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
	
//	SET UP LOGO ---------------------------------------------------------------------------------------------------
	private void setUpLogo() {
//		Logo image
		logo.setBounds(40, 30, 100, 100);
		add(logo);
		
//		Logo label
		logoLabel.setForeground(offWhite);
		logoLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		logoLabel.setBounds(logo.getX() + 100, logo.getY(), 200, 100);
		add(logoLabel);
	}
	
//	SET UP WELCOME ------------------------------------------------------------------------------------------------
	private void setUpWelcome() {
		welcomeLabel = new JLabel("Welcome back, " + firstName);
		welcomeLabel.setForeground(offWhite);
		welcomeLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 70));
		welcomeLabel.setBounds(410, 140, 800, 120);
		add(welcomeLabel);
	}
}