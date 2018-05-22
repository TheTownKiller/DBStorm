package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQliteConnector {

	public static void connect(String db) {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:C:/sqlite/db/" + db + ".db";
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
