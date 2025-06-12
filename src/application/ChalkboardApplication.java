/*
 * Name: Sunny Wu
 * Deadline: June 13th, 2025
 * Course Code: ICS4U1-03
 * Title: Chalkboard
 * Description:
 * Features:
 * Major Skills:
 * Areas of Concern:
 */

package application;

import java.sql.*;
import controller.*;
import dao.*;

public class ChalkboardApplication {
	
//	MAIN METHOD
	@SuppressWarnings("unused")
	public static void  main(String[] args) {
		
//		Testing database connection
		try (Connection conn = DBUtil.getConnection()) {
		    System.out.println("✅ Success! Connected to MySQL database 'javaproject'.");
		} catch (Exception e) {
		    System.out.println("❌ Failed: " + e.getMessage());
		}
		
//		Create main controller
		try {
			MainController mainController = new MainController();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
