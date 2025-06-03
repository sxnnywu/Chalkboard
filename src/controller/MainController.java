package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class MainController implements ActionListener {
	
	private MainFrame frame = new MainFrame();
	
//	CONSTRUCTOR
	public MainController(){
		
	}

//	ACTION LISTENERS
	public void addActionListeners() {
		
//		Start panel
		frame.getStartPanel().getLoginButton().addActionListener(this);
		frame.getStartPanel().getSignupButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		Start --> Login
		if(e.equals(frame.getStartPanel().getLoginButton())){
			frame.remove(frame.getStartPanel());
			frame.add(frame.getLoginPanel());
		}
		
//		Start --> Signup
		if(e.equals(frame.getStartPanel().getSignupButton())) {
			frame.remove(frame.getStartPanel());
			frame.add(frame.getSignupPanel());
		}
		
	}

}
