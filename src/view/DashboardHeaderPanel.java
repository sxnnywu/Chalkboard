package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

@SuppressWarnings("serial")
public class DashboardHeaderPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 130;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Logo text
	private JLabel logoLabel = new JLabel("Chalkboard");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private Image scaledLogo = getScaledImage(unscaledLogo.getImage(), 80, 80);
	private JLabel logo = new JLabel(new ImageIcon(scaledLogo));
	
//	Menu icon
	private ImageIcon unscaledMenu = new ImageIcon("icons/menu.png");
	private JButton menuIcon = new JButton(new ImageIcon(unscaledMenu.getImage().getScaledInstance(42, 42, java.awt.Image.SCALE_SMOOTH)));
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public DashboardHeaderPanel() {
		
//		Set up the panel & components
		initializePanel(); // Panel
		setUpLogo(); // Logo
		setUpMenuIcon(); // Menu icon
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
		logo.setBounds(40, 30, 80, 80);
		add(logo);
		
//		Logo label
		logoLabel.setForeground(offWhite);
		logoLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		logoLabel.setBounds(logo.getX() + 100, logo.getY() - 15, 200, 100);
		add(logoLabel);
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

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
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
}
