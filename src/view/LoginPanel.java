package view;

import java.awt.Color;

import javax.swing.JPanel;

public class LoginPanel extends JPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color grey = Color.decode("#434242");
	private final Color blue = Color.decode("#7fb6e8");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public LoginPanel() {
		
//		Set up the panel
		initializePanel();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(darkGrey);
        setOpaque(true);
	}

}
