package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserDataManagement {
	
	public static String userName = "";
	public static String password = "";
	public static String SQliteRoute= "C:/sqlite/";
	
	public void dataSaver() {
		BufferedWriter writer = null;
		if(userName == "" || password == "") {
			return;
		}
		try {
			File saveData = new File("UserInfo_saveData");
			writer = new BufferedWriter(new FileWriter(saveData));
			writer.write(userName);
			writer.newLine();
			writer.write(password);
		} catch (Exception e) {
			System.out.println("Write Exception");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Writer on close Exception");
			}
		}
	}
	
	
	public void updateSaveData(ArrayList<String>input) {
		BufferedWriter writer = null;
		ArrayList<String>entrada = input; 
		try {
			File saveData = new File("UserInfo_saveData");
			writer = new BufferedWriter(new FileWriter(saveData));
			for(int i=0; i<entrada.size();i++) {
				writer.write(entrada.get(i));
				writer.newLine();
			}
			writer.write(userName);
			writer.newLine();
			writer.write(password);
		} catch (Exception e) {
			System.out.println("Write Exception");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Writer on close Exception");
			}
		}
	}
	
	public void sqLitePreferencesSaver() {
		BufferedWriter writer = null;
		try {
			File saveData = new File("UserSQLitePreferences_saveData");
			writer = new BufferedWriter(new FileWriter(saveData));
			writer.write(userName + "%" + SQliteRoute);
		} catch (Exception e) {
			System.out.println("Write Exception");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Writer on close Exception");
			}
		}
	}
	
	public void sqLitePreferencesUpdater(ArrayList<String>input) {
		BufferedWriter writer = null;
		ArrayList<String>entrada = input; 
		try {
			File saveData = new File("UserSQLitePreferences_saveData");
			writer = new BufferedWriter(new FileWriter(saveData));
			for(int i=0; i<entrada.size();i++) {
				writer.write(entrada.get(i));
				writer.newLine();
			}
			writer.write(userName + "%" + SQliteRoute +userName);
		} catch (Exception e) {
			System.out.println("Write Exception");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Writer on close Exception");
			}
		}
	}

}
