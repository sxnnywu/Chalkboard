package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public HomePanel() { 
		
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
}
