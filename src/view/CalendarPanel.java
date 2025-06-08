package view;

import java.awt.Color;

import javax.swing.JLabel;

public class CalendarPanel extends RoundedPanel{

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 450;
	private static final int PANEL_HEIGHT = 900;
	
//	Colours
	private final Color red = Color.decode("#fc7b7b");
	
//	Components
	private JLabel titleLabel = new JLabel("Members");
	
	public CalendarPanel(int radius) {
		super(radius);
		// TODO Auto-generated constructor stub
	}

}
