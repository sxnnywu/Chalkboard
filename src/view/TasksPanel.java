package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class TasksPanel extends RoundedPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 700;
	private static final int PANEL_HEIGHT = 350;
	
//	Colours
	private final Color blue = Color.decode("#70bdfa");
	private final Color navy = Color.decode("#004aad");
	
//	Parameters
	List<String[]> data;
	
//	Components
	private JLabel titleLabel = new JLabel("Tasks");
	private JTable taskTable;
	private JScrollPane scrollPane;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public TasksPanel(int radius, List<String[]> data) {
		
//		Parameters
		super(radius);
		this.data = data;
		
//		Set up the panel
		initializePanel();
			
//		Set up components
		setUpTitleLabel();
		setUpTable(data);
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(getLighterColor(blue, 0.5f));
	}
	
//	GET LIGHTER COLOUR --------------------------------------------------------------------------------------------
	private Color getLighterColor(Color color, float factor) {
		
//		Clamp factor between 0 and 1
		factor = Math.max(0f, Math.min(factor, 1f));

//		RGB values
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

//	SET UP TABLE ---------------------------------------------------------------------------------------------------
	private void setUpTable(List<String[]> data) {

//		Columns
		String[] columns = { "Deliverables", "Assignee", "Deadline", "Status", "Notes" };
	    
//		Convert data array list to array
	    String[][] dataArray = new String[data.size()][5];
	    for(int i = 0; i < data.size(); i++) 
	        dataArray[i] = data.get(i);
	    
//	    Table model
		DefaultTableModel model = new DefaultTableModel(dataArray, columns) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

//		Create the table
		taskTable = new JTable(model);
		taskTable.setFillsViewportHeight(true);
		taskTable.setBackground(Color.WHITE);
		taskTable.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		taskTable.setRowHeight(32); // taller rows

//		Light borders between cells
		taskTable.setShowGrid(true);
		taskTable.setGridColor(Color.LIGHT_GRAY); 

//		Header styling
		JTableHeader header = taskTable.getTableHeader();
		header.setBackground(navy);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
		header.setReorderingAllowed(false); // optional: prevents dragging columns

//		Wrap in scroll pane
		scrollPane = new JScrollPane(taskTable);
		scrollPane.setBounds(40, 80, 620, 230);
		scrollPane.setBorder(null);

		add(scrollPane);
	}
	
//	GETTERS + SETTERS
	public int getPanelWidth() {
		return PANEL_WIDTH;
	}
	public int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	public List<String[]> getData() {
		return data;
	}
	public void setData(List<String[]> data) {
		this.data = data;
	}
	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public JTable getTaskTable() {
		return taskTable;
	}
	public void setTaskTable(JTable taskTable) {
		this.taskTable = taskTable;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
}
