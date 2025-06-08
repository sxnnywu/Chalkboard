package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MembersPanel extends RoundedPanel{

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 450;
	private static final int PANEL_HEIGHT = 650;
	
//	Colours
	private final Color red = Color.decode("#fc7b7b");
	
//	Components
	private JLabel titleLabel = new JLabel("Members");
	private JScrollPane scrollPane;
	private JPanel scrollContent = new JPanel();
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public MembersPanel(int radius) {
		
		super(radius);

//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpTitleLabel();
		setUpScrollPane();
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(getLighterColor(red, 0.2f));
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
		titleLabel.setBounds(40, 25, 150, 35);
		add(titleLabel);
	}
	
//	SET UP SCROLLPANE ----------------------------------------------------------------------------------------------
	private void setUpScrollPane() {
	    
		scrollContent.setLayout(new javax.swing.BoxLayout(scrollContent, javax.swing.BoxLayout.Y_AXIS));
	    scrollContent.setBackground(getLighterColor(red, 0.15f));
	    
	    scrollPane = new JScrollPane(scrollContent);
	    scrollPane.setBounds(20, 80, PANEL_WIDTH - 40, PANEL_HEIGHT - 100);
	    scrollPane.setBorder(null);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Ensure scrolling

	    add(scrollPane);
	}
	
//	ADD MEMBER -----------------------------------------------------------------------------------------------------
	public void addMember(String name, String role) {
	    
//		Create member panel
	    JPanel memberPanel = new JPanel();
	    memberPanel.setLayout(null);
	    memberPanel.setBackground(Color.WHITE);
	    memberPanel.setBounds(0, 0, PANEL_WIDTH - 20, 60); // Adjust size
	    memberPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Add subtle border

	    
//	    Name label (Bolded)
	    JLabel nameLabel = new JLabel(name);
	    nameLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
	    nameLabel.setBounds(20, 5, PANEL_WIDTH - 40, 20);
	    
//	    Role label (Unbolded)
	    JLabel roleLabel = new JLabel(role);
	    roleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
	    roleLabel.setBounds(20, 30, PANEL_WIDTH - 40, 20);

//	    Add components to member panel
	    memberPanel.add(nameLabel);
	    memberPanel.add(roleLabel);

//	    Add member panel to scroll content
	    scrollContent.add(memberPanel);
	    scrollContent.revalidate();
	    scrollContent.repaint();
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}

	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	
	
}
