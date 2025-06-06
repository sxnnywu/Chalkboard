package view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class DashboardPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	private final Color grey = Color.decode("#434242");
	private final Color offWhite = Color.decode("#f3f3f3");
	
//	Panels
	private DashboardHeaderPanel header = new DashboardHeaderPanel();
	private DashboardSidePanel sidebar = new DashboardSidePanel();
	private DashboardBodyPanel bodyContent = new DashboardBodyPanel("Math Club", "June 11th @ 3:00pm", "145flj3", 2, 1); // HARD CODED FOR NOW
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public DashboardPanel() {
	
//		Set up the panel
		initializePanel();
		
//		Header
		header.setPreferredSize(new Dimension(0, header.getPanelHeight()));

//     	Side bar
        sidebar.setPreferredSize(new Dimension(sidebar.getPanelWidth(), 0));

//     	Body
        JScrollPane scrollPane = new JScrollPane(bodyContent);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);

        // Assemble
        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(new BorderLayout());
        setBackground(darkGrey);
        setOpaque(true);
	}

}
