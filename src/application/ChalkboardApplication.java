package application;

import java.sql.Connection;
import java.sql.SQLException;

import controller.MainController;
import dao.DBUtil;

public class ChalkboardApplication {
	
	public static void  main(String[] args) {
		
//		Testing database connection
		try (Connection conn = DBUtil.getConnection()) {
		    System.out.println("✅ Success! Connected to MySQL database 'javaproject'.");
		} catch (Exception e) {
		    System.out.println("❌ Failed: " + e.getMessage());
		}
		
		try {
			MainController mainController = new MainController();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
