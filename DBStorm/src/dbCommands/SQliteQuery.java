package dbCommands;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import display.Graphic;
import utilities.UserDataManagement;

public class SQliteQuery {

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

	public void insert(String key) {
		String sql = key;

		try {
			Connection conn = this.connect();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HashMap<String, Object>> getAllRecords() {
		ArrayList<HashMap<String, Object>> registros = new ArrayList<HashMap<String, Object>>();
		Connection conexion = this.connect();
		String sql = "SELECT * FROM " + SQliteController.usedTable;
		try {
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metaData= rs.getMetaData();
			while(rs.next()) {
				
				HashMap<String, Object>mapa = new HashMap<String, Object>();	
				
				for(int i = 1; i<=metaData.getColumnCount(); i++) {
				mapa.put(metaData.getColumnName(i), rs.getObject(i));
				}
				registros.add(mapa);
	
			}
			return registros;
		} catch (SQLException e) {
		}
		return null;
	}
	
	 public void update(String field, String value, String whereClausule, String whereValue) {
		 	Connection conexion = this.connect();
		 	
	        String sql = "UPDATE " + SQliteController.usedTable + " SET " + field + " = " + value
	                + "WHERE " + whereClausule + " = " + whereValue;
	        try {
				Statement stm = conexion.createStatement();
				stm.executeUpdate(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public void delete(String field, String value) {
		 Connection conexion = this.connect();
		 String sql = "DELETE FROM " + SQliteController.usedTable + " WHERE " + field + " = " + value;
		 
		 try {
				Statement stm = conexion.createStatement();
				stm.executeUpdate(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
	        
}
