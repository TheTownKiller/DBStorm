package dbCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utilities.UserDataManagement;

public class SQliteCreateDatabase {
	
	public static void createNewDatabase(String fileName) {

		String url = "jdbc:sqlite:" + UserDataManagement.SQliteRoute + "/" + fileName + ".db";

		try (Connection conn = DriverManager.getConnection(url)) {
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
