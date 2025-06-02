package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
//	FIELDS
	StartPanel startPanel = new StartPanel();
	
//	CONSTRUCTOR
	public MainFrame() {
		
//		Set up the frame
		setTitle("Chalkboard");
		setSize(1920, 1080);
		setLayout(null);
		
//		Closing the program
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//		Starting panel
		add(startPanel);
		
//		Make the frame appear
		setVisible(true);
	}

}
