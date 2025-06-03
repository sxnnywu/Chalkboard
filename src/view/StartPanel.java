package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

public class StartPanel extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	private final String fullTitle = "Chalkboard";
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color grey = Color.decode("#434242");
	private final Color blue = Color.decode("#7fb6e8");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Labels
	private JLabel titleLabel = new JLabel(""); // start empty for animation 
	private JLabel sloganLabel = new JLabel("The smarter way to manage student clubs.");
	
//	Logo image
	private ImageIcon unscaledLogo = new ImageIcon("images/logo.png");
	private Image scaledLogo = getScaledImage(unscaledLogo.getImage(), 200, 200);
	private JLabel logo = new JLabel(new ImageIcon(scaledLogo));
	
//	Line
	private JSeparator line = new JSeparator();
	
//	Login button
	private RoundedButton loginButton = new RoundedButton(
		    "<html><div style='text-align: center;'>"
		    + "<span style='font-size: 28pt; font-weight: bold;'>Login</span><br>"
		    + "<span style='font-size: 16pt;'>Get started with an existing account</span></div></html>",
		    Color.WHITE, offWhite, new Color(255, 255, 255, 50)
		);
	
//	Login button
	private RoundedButton signupButton = new RoundedButton(
			"<html><div style='text-align: center;'>"
			+ "<span style='font-size: 28pt; font-weight: bold;'>Sign Up</span><br>"
			+ "<span style='font-size: 16pt;'>Create a new account</span></div></html>",
			darkGrey, grey, new Color(255, 255, 255, 50)
    );
	
//	Typing title animation
	private int titleCharIndex = 0;
	private Timer typewriterTimer;
	private Timer fadeInTimer;
	private float fadeAlpha = 0f;
	private boolean showRest = false;

	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public StartPanel() {
		
//		Set up the panel
		initializePanel();
		
//		Logo
		logo.setBounds(670, 60, 200, 200);
		add(logo);

//		Set up all elements
		setUpTitle();
		startTypewriterAnimation();
		setUpLine();
		setUpSlogan();
		setUpButtons();
		
//		Set all components not visible other than title label
		for (Component comp : getComponents()) {
		    if (comp != titleLabel) 
		    	comp.setVisible(false);
		}

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
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(darkGrey);
        setOpaque(true);
	}
	
//	SET UP TITLE --------------------------------------------------------------------------------------------------
	private void setUpTitle() {
		titleLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 160));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(330, 260, 910, 220);
		add(titleLabel);
	}
	
//	TYPEWRITER ANIMATION  -----------------------------------------------------------------------------------------
	private void startTypewriterAnimation() {
		
//		Set up timer
	    typewriterTimer = new Timer(100, e -> {
	    	
//	    	If the title isn't fully typed out yet, add the next letter to the text
	        if (titleCharIndex < fullTitle.length()) {
	            titleLabel.setText(fullTitle.substring(0, titleCharIndex + 1));
	            titleCharIndex++;
	        } 
//	        Otherwise, if fully typed out, stop the timer and start fading in the other elements
	        else {
	            typewriterTimer.stop();
	            startFadeInAnimation();
	        }
	    });
	    
//	    Start the timer
	    typewriterTimer.start();
	}

//	FADE IN ANIMATION ---------------------------------------------------------------------------------------------
	private void startFadeInAnimation() {
	    
//		Let paintComponent start fading other elements
		showRest = true;
		
	    // Set everything visible so hover + paintComponent works
	    for (Component comp : getComponents()) {
	        if (comp != titleLabel) 
	            comp.setVisible(true);
	    }
		
//		Set up timer
	    fadeInTimer = new Timer(20, e -> {
	    	
//	    	Stop fading after 1f
	        fadeAlpha += 0.02f;
	        if (fadeAlpha >= 1f) {
	            fadeAlpha = 1f;
	            fadeInTimer.stop();
	        }
	        if (fadeAlpha >= 1f) {
	            showRest = false;
	        }
	        repaint();
	    });
	    
//	    Start the timer
	    fadeInTimer.start();
	}
	
//	SET UP LINE ---------------------------------------------------------------------------------------------------
	private void setUpLine() {
		line.setForeground(Color.WHITE);
		line.setBounds(titleLabel.getX(), titleLabel.getY() + 210, titleLabel.getWidth(), 20);
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
		loginButton.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		loginButton.setBounds(titleLabel.getX() + 10, sloganLabel.getY() + 150, 400, 100);
		add(loginButton);
		
//		Sign up button
		signupButton.setBorderColor(Color.WHITE);
		signupButton.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		signupButton.setBounds(loginButton.getX() + 500, loginButton.getY(), loginButton.getWidth(), loginButton.getHeight());
		add(signupButton);
	}
	
//	PAINT COMPONENT -----------------------------------------------------------------------------------------------
	@Override
	protected void paintComponent(Graphics g) {
	    
		super.paintComponent(g);

	    if (showRest) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
	        g2.dispose();
	    }
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public JLabel getSloganLabel() {
		return sloganLabel;
	}
	public void setSloganLabel(JLabel sloganLabel) {
		this.sloganLabel = sloganLabel;
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
	public JSeparator getLine() {
		return line;
	}
	public void setLine(JSeparator line) {
		this.line = line;
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
	public int getTitleCharIndex() {
		return titleCharIndex;
	}
	public void setTitleCharIndex(int titleCharIndex) {
		this.titleCharIndex = titleCharIndex;
	}
	public Timer getTypewriterTimer() {
		return typewriterTimer;
	}
	public void setTypewriterTimer(Timer typewriterTimer) {
		this.typewriterTimer = typewriterTimer;
	}
	public Timer getFadeInTimer() {
		return fadeInTimer;
	}
	public void setFadeInTimer(Timer fadeInTimer) {
		this.fadeInTimer = fadeInTimer;
	}
	public float getFadeAlpha() {
		return fadeAlpha;
	}
	public void setFadeAlpha(float fadeAlpha) {
		this.fadeAlpha = fadeAlpha;
	}
	public boolean isShowRest() {
		return showRest;
	}
	public void setShowRest(boolean showRest) {
		this.showRest = showRest;
	}
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
}
