package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class MenuButton extends JButton{

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int BUTTON_WIDTH = 330;
	private static final int BUTTON_HEIGHT = 55;
	
//	Parameters
	private String text;
	private ImageIcon image;
	
//	Components
	private JLabel icon;
	private JLabel label;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public MenuButton(String text, ImageIcon image) {
		
//		Parameters
		this.text = text;
		this.image = image;
		
//		Set up the button
		initializeButton();
		
//		Set up components
		setUpLabel();
		setUpIcon();
	}
	
//	INITIALIZE BUTTON ----------------------------------------------------------------------------------------------
	private void initializeButton() {
		setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		setBorderPainted(false);
        setLayout(null);
        setBackground(Color.WHITE);
	}
	
//	SET UP LABEL --------------------------------------------------------------------------------------------------
	private void setUpLabel() {
		label = new JLabel(text);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		label.setBounds(60, 8, 130, 40);
		add(label);
	}
	
//	SET UP ICON ---------------------------------------------------------------------------------------------------
	private void setUpIcon() {
		icon = new JLabel(image);
		icon.setBounds(20, 8, 30, 40);
		add(icon);
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public void setText(String text) {
		this.text = text;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public void setIcon(JLabel icon) {
		this.icon = icon;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public static int getButtonWidth() {
		return BUTTON_WIDTH;
	}
	public static int getButtonHeight() {
		return BUTTON_HEIGHT;
	}
}
