package view;

import java.awt.Color;

import javax.swing.JPanel;

public class SignupInputPanel extends RoundedPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
//	Constants
	private static final int PANEL_WIDTH = 1300;
	private static final int PANEL_HEIGHT = 420;
	
//	Colors
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public SignupInputPanel() {
		super(60);
		initializePanel();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(offWhite);
//  	setOpaque(true);
	}
	
	
}
