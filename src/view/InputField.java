package view;

import java.awt.*;

import javax.swing.*;

public class InputField {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private JLabel label;
	private JTextField textField;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public InputField(String labelText, int labelWidth, boolean centreLabel) {
		
//		Label
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		label = new JLabel(labelText);
		
//		Text field
		textField.setBackground(Color.WHITE);
		
	}
}
