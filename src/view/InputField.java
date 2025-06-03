package view;

import java.awt.*;

import javax.swing.*;

public class InputField extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private JLabel label;
	private JTextField textField;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public InputField(String labelText, int columns, boolean centred) {
		
		setLayout(new BorderLayout(0, 5)); // vertical spacing
        setOpaque(false); // important if you want transparency
		
//    	Label
        label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        label.setHorizontalAlignment(centred ? SwingConstants.CENTER : SwingConstants.LEFT);
        
//    	Text field
        textField = new JTextField(columns);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

//   	Add components
        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);	
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
