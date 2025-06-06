package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TasksPanel extends RoundedPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------
	
//	Constants
	private static final int PANEL_WIDTH = 700;
	private static final int PANEL_HEIGHT = 350;
	
//	Colours
	private final Color blue = Color.decode("#70bdfa");
	private final Color navy = Color.decode("#004aad");
	
//	Components
	private JLabel titleLabel = new JLabel("Tasks");
	private JTable taskTable;
	private JScrollPane scrollPane;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public TasksPanel(int radius) {
		
		super(radius);
		
//		Set up the panel
		initializePanel();
		
//		Set up components
		setUpTitleLabel();
		setUpTable();
	}
	
//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setLayout(null);
        setBackground(getLighterColor(blue, 0.5f));
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

//	SET UP TABLE ---------------------------------------------------------------------------------------------------
	private void setUpTable() {

		String[] columns = { "Deliverables", "Assignee", "Deadline", "Status", "Notes" };
		Object[][] data = {};

		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		taskTable = new JTable(model);
		taskTable.setFillsViewportHeight(true);
		taskTable.setBackground(Color.WHITE);
		taskTable.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		taskTable.setRowHeight(42); // taller rows

		// Add light borders between cells
		taskTable.setShowGrid(true);
		taskTable.setGridColor(new Color(220, 220, 220)); // light grey grid

		// Header styling
		JTableHeader header = taskTable.getTableHeader();
		header.setBackground(navy);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
		header.setReorderingAllowed(false); // optional: prevents dragging columns

		// Wrap in scroll pane
		scrollPane = new JScrollPane(taskTable);
		scrollPane.setBounds(40, 80, 620, 230);
		scrollPane.setBorder(null);

		add(scrollPane);
	}
	
	public static int getPanelWidth() {
		return PANEL_WIDTH;
	}

	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}
	
	
}
