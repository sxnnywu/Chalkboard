package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RoundedButton extends JButton {
    
//	FIELDS --------------------------------------------------------------------------------------------------------
    private Color backgroundColor;
    private Color hoverColor;
    private Color borderColor = null; // Optional border color
    private boolean hovered = false;
    private Color shadowColor; // TODO: Add if time

// 	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
    public RoundedButton(String text, Color backgroundColor, Color hoverColor, Color shadowColor) {
        
//    	Fields
    	super(text);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        
//    	Set up button
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

//    	Mouse listener
        addMouseListener(new MouseAdapter() {
        	
//        	Mouse entered
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }
//     		Mouse exited
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

// 	SET BORDER COLOUR ---------------------------------------------------------------------------------------------
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

//	PAINT COMPONENT -----------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        int arc = 30;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
//     	Fill background
        Color fill = hovered ? hoverColor : backgroundColor;
        g2.setColor(fill);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

//      Optional border
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arc, arc);
        }
        super.paintComponent(g2);
        g2.dispose();
    }

// 	Unused
    @Override
    protected void paintBorder(Graphics g) { }
    @Override
    public boolean isOpaque() { return false; }

// 	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public Color getShadowColor() {
		return shadowColor;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getHoverColor() {
		return hoverColor;
	}
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}
	public boolean isHovered() {
		return hovered;
	}
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}
}
