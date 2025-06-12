package view;

import java.util.List;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Logo text
	private JLabel logoLabel = new JLabel("Chalkboard");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private Image scaledLogo = getScaledImage(unscaledLogo.getImage(), 100, 100);
	private JLabel logo = new JLabel(new ImageIcon(scaledLogo));
	
//	Welcome label
	private JLabel welcomeLabel;
	
//	Clubs panel
	private ClubsPanel clubPanel;
	
//	Menu icon
	private ImageIcon unscaledMenu = new ImageIcon("icons/menu.png");
	private JButton menuIcon = new JButton(new ImageIcon(unscaledMenu.getImage().getScaledInstance(42, 42, java.awt.Image.SCALE_SMOOTH)));
	
//	Parameters
	private String firstName;
	
//	EMPTY CONSTRUCTOR 
	public HomePanel() { 
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpLogo();
		setUpWelcome();
		setUpMenuIcon();
		
	}
	
//	CONSTRUCTOR --------------------------------------------------------------------------------------------------
	public HomePanel(String firstName, List<String[]> clubData) { 
		
//		Parameters
		this.firstName = firstName;
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpLogo();
		setUpWelcome();
		setUpMenuIcon();
		
//		Club panel
//		CLUB DATA: CLUB NAME, NEXT MEETING, JOIN CODE
		clubPanel = new ClubsPanel(clubData);
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
	    
//		New buffered image with desired width, height, and transparency support (ARGB)
	    BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    
//	   	Graphics2D context from the buffered image
	    Graphics2D g2 = resizedImg.createGraphics();
	    
//	    Rendering hints to improve image scaling quality
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC); // Smooth interpolation
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);             // High-quality rendering
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);            // Enable antialiasing for edges
	    
//	    Draw the original image scaled to the new dimensions
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
		welcomeLabel.setBounds(310, 140, 1000, 120);
		add(welcomeLabel);
	}
	
//	SET UP MENU ICON ----------------------------------------------------------------------------------------------
	private void setUpMenuIcon() {
		menuIcon.setForeground(offWhite);
		menuIcon.setBackground(darkGrey);
		menuIcon.setBorderPainted(false);
		menuIcon.setOpaque(false);
		menuIcon.setBounds(1450, 40, 42, 42);
		add(menuIcon);
	}

//	GETTER + SETTERS ----------------------------------------------------------------------------------------------
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
	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}
	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}
	public ClubsPanel getClubPanel() {
		return clubPanel;
	}
	public void setClubPanel(ClubsPanel clubPanel) {
		this.clubPanel = clubPanel;
	}
	public ImageIcon getUnscaledMenu() {
		return unscaledMenu;
	}
	public void setUnscaledMenu(ImageIcon unscaledMenu) {
		this.unscaledMenu = unscaledMenu;
	}
	public JButton getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(JButton menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}