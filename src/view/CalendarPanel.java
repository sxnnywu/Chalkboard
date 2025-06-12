package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

public class CalendarPanel extends RoundedPanel {

//	FIELDS --------------------------------------------------------------------------------------------------------

	// Constants
	private static final int PANEL_WIDTH = 850;
	private static final int PANEL_HEIGHT = 570;
	private static final int HEADER_HEIGHT = 50;

	// Colors
	private final Color red = Color.decode("#fc7b7b");
	private final Color gray = Color.decode("#cccccc");

	// Components
	private JLabel titleLabel;
	private JPanel dayGridPanel;
	private LocalDate currentMonth;

	// Calendar Data (Map of LocalDate to List of Event Titles or IDs — change later)
	private Map<LocalDate, java.util.List<String>> events = new HashMap<>();

//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public CalendarPanel(int radius) {
		
		super(radius);
		initializePanel();
		renderCalendar(LocalDate.now().withDayOfMonth(1));
	}

//	INITIALIZE PANEL ----------------------------------------------------------------------------------------------
	private void initializePanel() {
		setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setBackground(Color.WHITE);

//		Title
		titleLabel = new JLabel("", SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLabel.setBounds(0, 10, PANEL_WIDTH, HEADER_HEIGHT);
		add(titleLabel);

//		Grid Panel
		dayGridPanel = new JPanel(new GridLayout(0, 7)); // 7 columns
		dayGridPanel.setBounds(25, 70, PANEL_WIDTH - 50, PANEL_HEIGHT - 100);
		add(dayGridPanel);
	}
	
	public void clearEvents() {
	    events.clear();
	}

//	RENDER CALENDAR -----------------------------------------------------------------------------------------------
	public void renderCalendar(LocalDate firstOfMonth) {
		
		currentMonth = firstOfMonth;
		dayGridPanel.removeAll();

//		Update title label
		Month month = currentMonth.getMonth();
		int year = currentMonth.getYear();
		titleLabel.setText(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + year);

//		Add weekday headers
		String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (String day : days) {
			JLabel label = new JLabel(day, SwingConstants.CENTER);
			label.setFont(new Font("SansSerif", Font.BOLD, 14));
			label.setForeground(gray);
			dayGridPanel.add(label);
		}

//		Get which day the month starts on (0 = Sunday)
		int firstDayOfWeek = currentMonth.getDayOfWeek().getValue() % 7;
		int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

//		Pad the beginning with empty cells
		for (int i = 0; i < firstDayOfWeek; i++) 
			dayGridPanel.add(new JLabel(""));

//		Add days
		for (int day = 1; day <= daysInMonth; day++) {
			LocalDate date = LocalDate.of(year, month, day);
			JPanel cell = createDayCell(date);
			dayGridPanel.add(cell);
		}
		revalidate();
		repaint();
	}

//	CREATE SINGLE DAY CELL ----------------------------------------------------------------------------------------
	private JPanel createDayCell(LocalDate date) {
		
//		Set up the day panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		panel.setBackground(Color.WHITE);

//		Day number label (top-right)
		JLabel dayLabel = new JLabel(String.valueOf(date.getDayOfMonth()), SwingConstants.RIGHT);
		dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(dayLabel, BorderLayout.NORTH);

//		Event container (center)
		JPanel eventContainer = new JPanel();
		eventContainer.setLayout(new BoxLayout(eventContainer, BoxLayout.Y_AXIS));
		eventContainer.setOpaque(false); // Transparent so background shows through

//		Add events as mini colored labels
		if (events.containsKey(date)) {
			List<String> eventList = events.get(date);

//			Styling and alignment for each event
			for (String event : eventList) {
				JLabel eventLabel = new JLabel(event);
				eventLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
				eventLabel.setOpaque(true);
				eventLabel.setBackground(new Color(255, 235, 235)); // light red background
				eventLabel.setForeground(Color.BLACK);
				eventLabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
				eventLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

				eventContainer.add(eventLabel);
			}
		}

		panel.add(eventContainer, BorderLayout.CENTER);

		return panel;
	}

//	ADD EVENT -----------------------------------------------------------------------------------------------------
	public void addEvent(LocalDate date, String eventName) {
		events.computeIfAbsent(date, k -> new ArrayList<>()).add(eventName);
		revalidate();
		repaint();
	}

//	NEXT MONTH  ---------------------------------------------------------------------------------------------------
	public void nextMonth() {
		renderCalendar(currentMonth.plusMonths(1));
	}

//	PREVIOUS MONTH ------------------------------------------------------------------------------------------------
	public void previousMonth() {
		renderCalendar(currentMonth.minusMonths(1));
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
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
	public JPanel getDayGridPanel() {
		return dayGridPanel;
	}
	public void setDayGridPanel(JPanel dayGridPanel) {
		this.dayGridPanel = dayGridPanel;
	}
	public LocalDate getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(LocalDate currentMonth) {
		this.currentMonth = currentMonth;
	}
	public Map<LocalDate, java.util.List<String>> getEvents() {
		return events;
	}
	public void setEvents(Map<LocalDate, java.util.List<String>> events) {
		this.events = events;
	}
	public static int getHeaderHeight() {
		return HEADER_HEIGHT;
	}
}
