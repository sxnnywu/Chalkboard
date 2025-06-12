package view;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import model.*;

@SuppressWarnings("serial")
public class DashboardPanel extends JPanel {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 1920;
	private static final int PANEL_HEIGHT = 1080;
	
//	Colours
	private final Color darkGrey = Color.decode("#2e2e2e");
	
//	Panels
	private DashboardHeaderPanel header = new DashboardHeaderPanel();
	private DashboardSidePanel sidebar = new DashboardSidePanel();
	private DashboardBodyPanel bodyContent;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public DashboardPanel(String clubName, String nextMeeting, String joinCode, ArrayList<String[]> announcementList, 
			ArrayList<String[]> taskList, ArrayList<String[]> memberList, List<ScheduleItem> scheduleItems) {
	
//		Set up the panel
		initializePanel();
		
//		Header
		header.setPreferredSize(new Dimension(0, header.getPanelHeight()));

//     	Side bar
        sidebar.setPreferredSize(new Dimension(sidebar.getPanelWidth(), 0));

//     	Body
        bodyContent = new DashboardBodyPanel(clubName, nextMeeting, joinCode, 2, 1, announcementList, taskList, memberList, scheduleItems);
        JScrollPane scrollPane = new JScrollPane(bodyContent);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);

//    	Assemble panels
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
