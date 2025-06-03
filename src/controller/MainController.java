package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class MainController implements ActionListener {
	
//	FIELDS  --------------------------------------------------------------------------------------------------------
	private MainFrame frame = new MainFrame();
	
//	CONSTRUCTOR ----------------------------------------------------------------------------------------------------
	public MainController(){
		
		addActionListeners();
	}

//	ACTION LISTENERS -----------------------------------------------------------------------------------------------
	public void addActionListeners() {
		
//		Start panel
		frame.getStartPanel().getLoginButton().addActionListener(this);
		frame.getStartPanel().getSignupButton().addActionListener(this);
		frame.getSignupPanel().getLoginButton().addActionListener(this);
		frame.getSignupPanel().getSignupButton().addActionListener(this);
		frame.getLoginPanel().getSignupButton().addActionListener(this);
		frame.getLoginPanel().getLoginButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		Start --> Login
		if(e.getSource() == frame.getStartPanel().getLoginButton()){
			frame.remove(frame.getStartPanel());
			frame.add(frame.getLoginPanel());
			frame.revalidate();
			frame.repaint();
		}
		
//		Start --> Sign up
		if(e.getSource() == frame.getStartPanel().getSignupButton()) {
			frame.remove(frame.getStartPanel());
			frame.add(frame.getSignupPanel());
			frame.revalidate();
			frame.repaint();
		}
		
//		Sign up --> Login
		if(e.getSource() == frame.getSignupPanel().getLoginButton()) {
			frame.remove(frame.getSignupPanel());
			frame.add(frame.getLoginPanel());
			frame.revalidate();
			frame.repaint();
		}
		
//		Sign up validation
		if(e.getSource() == frame.getSignupPanel().getSignupButton()) {
			
//			CALL BOOLEAN METHOD TO VALIDATE INPUT
			frame.remove(frame.getSignupPanel());
		}
		
//		Login -->> Signup
		if(e.getSource() == frame.getLoginPanel().getSignupButton()) {
			frame.remove(frame.getLoginPanel());
			frame.add(frame.getSignupPanel());
			frame.revalidate();
			frame.repaint();
		}
		
//		Login validation
		if(e.getSource() == frame.getLoginPanel().getLoginButton()) {
			
//			CALL BOOLEAN METHOD TO VALIDATE INPUT
			frame.remove(frame.getLoginPanel());
		}
	}

}
