package view;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RoundedPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private final int cornerRadius;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); 
    }

// 	PAINT COMPONENT  ----------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        
//    	Enable antialiasing for smooth corners
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//    	Set background color
        g2.setColor(getBackground());

//     	Draw rounded rectangle
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();

//    	Paint children
        super.paintComponent(g);
    }

// 	GET CORNER RADIUS ---------------------------------------------------------------------------------------------
	public int getCornerRadius() {
		return cornerRadius;
	}   
}

