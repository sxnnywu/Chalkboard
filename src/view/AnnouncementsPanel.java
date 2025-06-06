package view;

import java.awt.*;
import javax.swing.*;

public class AnnouncementsPanel extends RoundedPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 450;
	private static final int PANEL_HEIGHT = 270;
	
//	Colours
	private final Color green = Color.decode("#afcebb");
	
//	Components
	private JLabel titleLabel = new JLabel("Announcements");
	private JScrollPane scrollPane;
	private JPanel scrollContent = new JPanel();

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public AnnouncementsPanel(int radius) {
		super(radius);
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpTitleLabel(); // title
		
		setUpScrollContent(); // scroll content
		scrollPane =  new JScrollPane(scrollContent); 
		setUpScrollPane();
		
//		TESTING PURPOSES -- HARD CODED ANNOUNCEMENTS
		addAnnouncement("Meeting Cancelled", "John", "Hey everyone! Please note that this week's meeting is cancelled due to the snow storm.");
		addAnnouncement("Budget Complete", "Jane", "I have completed the budget, please see.");
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(getLighterColor(green, 0.5f));
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
		// Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

		int r = (int) (color.getRed() + (255 - color.getRed()) * factor);
		int g = (int) (color.getGreen() + (255 - color.getGreen()) * factor);
		int b = (int) (color.getBlue() + (255 - color.getBlue()) * factor);

		return new Color(r, g, b);
	}
	
//	SET UP TITLE LABEL ---------------------------------------------------------------------------------------------
	private void setUpTitleLabel() {		
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		titleLabel.setBounds(40, 25, 250, 35);
		add(titleLabel);
	}
	
//	SCROLL CONTENT ------------------------------------------------------------------------------------------------
	private void setUpScrollContent() {
		
//		Set up panel
		scrollContent.setBounds(0, 0, 400, 170);
		scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
		scrollContent.setBackground(green);
		scrollContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
//	SET UP SCROLL PANE ---------------------------------------------------------------------------------------------
	private void setUpScrollPane() {
		scrollPane.setBounds(25, titleLabel.getY() + 50, scrollContent.getWidth(), scrollContent.getHeight());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		add(scrollPane);
	}
	
//	ADD ANNOUNCEMENT -----------------------------------------------------------------------------------------------
	public void addAnnouncement(String title, String memberName, String body) {
		
//		New announcement block
        JPanel block = new JPanel();
        block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
        block.setBackground(green);
        block.setAlignmentX(Component.LEFT_ALIGNMENT);
        block.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
//    	Announcement title
        JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
        titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//    	Announcement body
        String bodyText = "<html><div style='width:280px'>" + memberName + ": " + body + "</div></html>";
        JLabel bodyLabel = new JLabel(bodyText);
        bodyLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
        bodyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//     	Add components to the block
        block.add(titleLabel);
        block.add(Box.createRigidArea(new Dimension(0, 4)));
        block.add(bodyLabel);
        block.add(Box.createRigidArea(new Dimension(0, 12)));

//    	Refresh the scroll content
        scrollContent.add(block);
        scrollContent.revalidate();
        scrollContent.repaint();
    }

//	GETTERS + SETTERS ----------------------------------------------------------------------------------------------
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public JPanel getScrollContent() {
		return scrollContent;
	}
	public void setScrollContent(JPanel scrollContent) {
		this.scrollContent = scrollContent;
	}	
}
