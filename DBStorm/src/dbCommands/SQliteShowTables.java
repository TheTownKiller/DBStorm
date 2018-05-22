package dbCommands;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import display.Graphic;
import utilities.UserDataManagement;

public class SQliteShowTables {

	private Connection connect() {

		String url = "jdbc:sqlite:" + UserDataManagement.SQliteRoute + "/" + Graphic.filename + ".db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void getDatabaseMetaData() {

		try {

			DatabaseMetaData dbmd = connect().getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				Graphic.tablas.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
