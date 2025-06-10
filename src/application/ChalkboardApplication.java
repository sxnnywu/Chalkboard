package application;

import java.sql.Connection;

import controller.MainController;
import dao.DBUtil;

public class ChalkboardApplication {
	
	public static void  main(String[] args) {
		
		try (Connection conn = DBUtil.getConnection()) {
		    System.out.println("✅ Success! Connected to MySQL database 'javaproject'.");
		} catch (Exception e) {
		    System.out.println("❌ Failed: " + e.getMessage());
		}
		MainController mainController = new MainController();
	}

}
