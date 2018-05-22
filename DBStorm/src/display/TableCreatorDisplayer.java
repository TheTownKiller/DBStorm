package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import dbCommands.SQliteCreateTable;
import utilities.UserDataManagement;

public class TableCreatorDisplayer extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8642479040502388724L;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private JTextField field1, field2, field3, field4, field5, field6, field7, field8;
	private JRadioButton radio1, radio2, radio3, radio4, radio5;
	private JComboBox<Object> comboBox1, comboBox2, comboBox3, comboBox4, comboBox5;
	private JButton crearTabla = new JButton();
	private boolean display = true;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = this.getWidth();
		g.setColor(Color.decode("#ffad33"));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.BOLD, 30);
		g.setFont(font);
		g.drawString("Creando la tabla: " + SQliteCreateTable.nombre,
				(int) (((width / 60) + 20) * getPercentualScale("width")), (int) ((50) * getPercentualScale("height")));
		g.drawLine(0, 80, width, 80);
		Font font2 = new Font("Arial", Font.BOLD, 18);
		g.setFont(font2);
		g.drawString("PrimaryKey", (width / 2) + 220, 135);
		g.drawLine(0, 180, width, 180);
		g.drawString("PrimaryKey", (width / 2) + 220, 230);
		g.drawLine(0, 280, width, 280);
		g.drawString("PrimaryKey", (width / 2) + 220, 330);
		g.drawLine(0, 380, width, 380);
		g.drawString("PrimaryKey", (width / 2) + 220, 430);
		g.drawLine(0, 480, width, 480);
		g.drawString("PrimaryKey", (width / 2) + 220, 530);
		g.drawLine(0, 580, width, 580);
		g.drawString("Foreign Key ", 20, 630);
		g.drawString(" References ", (width / 2) - 100, 630);
		g.drawString(" on ", (width / 2) + 170, 630);
		g.drawLine(0, 680, width, 680);
		generateFields();

	}

	public void generateFields() {
		if (display) {
			setLayout(null);
			String[] BDChoices = { "Int", "String", "Float", "Blob", "NULL" };
			comboBox1 = new JComboBox<Object>(BDChoices);
			add(comboBox1);
			comboBox2 = new JComboBox<Object>(BDChoices);
			add(comboBox2);
			comboBox3 = new JComboBox<Object>(BDChoices);
			add(comboBox3);
			comboBox4 = new JComboBox<Object>(BDChoices);
			add(comboBox4);
			comboBox5 = new JComboBox<Object>(BDChoices);
			add(comboBox5);
			field1 = new JTextField(20);
			add(field1);
			field2 = new JTextField(20);
			add(field2);
			field3 = new JTextField(20);
			add(field3);
			field4 = new JTextField(20);
			add(field4);
			field5 = new JTextField(20);
			add(field5);
			field6 = new JTextField(20);
			add(field6);
			field7 = new JTextField(20);
			add(field7);
			field8 = new JTextField(20);
			add(field8);
			crearTabla.setText("Crear Tabla");
			crearTabla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					createTable(SQliteCreateTable.nombre);
				}
			});
			this.add(crearTabla);
			radio1 = new JRadioButton();
			radio1.setBackground(Color.decode("#ffad33"));
			radio1.setOpaque(false);
			radio1.setContentAreaFilled(false);
			radio1.setBorderPainted(false);
			add(radio1);
			radio2 = new JRadioButton();
			radio2.setBackground(Color.decode("#ffad33"));
			radio2.setOpaque(false);
			radio2.setContentAreaFilled(false);
			radio2.setBorderPainted(false);
			add(radio2);
			radio3 = new JRadioButton();
			radio3.setBackground(Color.decode("#ffad33"));
			radio3.setOpaque(false);
			radio3.setContentAreaFilled(false);
			radio3.setBorderPainted(false);
			add(radio3);
			radio4 = new JRadioButton();
			radio4.setBackground(Color.decode("#ffad33"));
			radio4.setOpaque(false);
			radio4.setContentAreaFilled(false);
			radio4.setBorderPainted(false);
			add(radio4);
			radio5 = new JRadioButton();
			radio5.setBackground(Color.decode("#ffad33"));
			radio5.setOpaque(false);
			radio5.setContentAreaFilled(false);
			radio5.setBorderPainted(false);
			add(radio5);
			display = false;
		}
		radio1.setBounds((width / 2) + 180, 105, 200, 50);
		radio2.setBounds((width / 2) + 180, 200, 200, 50);
		radio3.setBounds((width / 2) + 180, 300, 200, 50);
		radio4.setBounds((width / 2) + 180, 400, 200, 50);
		radio5.setBounds((width / 2) + 180, 500, 200, 50);
		field1.setBounds(25, 105, 300, 40);
		field2.setBounds(25, 200, 300, 40);
		field3.setBounds(25, 300, 300, 40);
		field4.setBounds(25, 400, 300, 40);
		field5.setBounds(25, 500, 300, 40);
		field6.setBounds(140, 605, 150, 40);
		field7.setBounds(410, 605, 150, 40);
		field8.setBounds(610, 605, 150, 40);
		comboBox1.setBounds(370, 105, 150, 40);
		comboBox2.setBounds(370, 200, 150, 40);
		comboBox3.setBounds(370, 300, 150, 40);
		comboBox4.setBounds(370, 400, 150, 40);
		comboBox5.setBounds(370, 500, 150, 40);
		crearTabla.setBounds((width / 2) - 100, 690, 200, 40);

	}
	
	public void disposePanel() {
		((JFrame) SwingUtilities.getWindowAncestor(this)).setVisible(false);
		 ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
	}

	public void createTable(String nombre) {
		String url = "jdbc:sqlite:" + UserDataManagement.SQliteRoute + "/" + Graphic.filename + ".db";

		String sql = queryGenerated(nombre);
		
		if(sql.isEmpty()) {
			return;
		}
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			disposePanel();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String queryGenerated(String nombre) {
		String[] BDChoices = { "integer", "text", "real", "Blob", "NULL" };
		
		String part2 = "";
		String part3 = "";
		String part4 = "";
		String part5 = "";
		String part6 = "";
		String part7 = "";
		
		if((field1.getText().length()==0) && (field2.getText().length()==0) && (field3.getText().length()==0)
				&& (field4.getText().length()==0) && (field5.getText().length()==0)) {
			
		}else {
			String part1 = "CREATE TABLE IF NOT EXISTS " + nombre + " (\n";
			if(field1.getText().length()!=0) {
				String aux= "";
				if(radio1.isSelected()) {
					aux =  " PRIMARY KEY";
				}
				part2 = field1.getText() + " " + BDChoices[comboBox1.getSelectedIndex()] + aux;
			}
			if(field2.getText().length()!=0) {
				String aux= "";
				if(radio2.isSelected()) {
					aux =  " PRIMARY KEY";
				}
				part3 = field2.getText() + " " + BDChoices[comboBox2.getSelectedIndex()] + aux;
				part2+=",\n";
			}
			if(field3.getText().length()!=0) {
				String aux= "";
				if(radio3.isSelected()) {
					aux =  " PRIMARY KEY";
				}
				part4 = field3.getText() + " " + BDChoices[comboBox3.getSelectedIndex()] + aux;
				part3+=",\n";
			}
			if(field4.getText().length()!=0) {
				String aux= "";
				if(radio4.isSelected()) {
					aux =  " PRIMARY KEY";
				}
				part5 = field4.getText() + " " + BDChoices[comboBox4.getSelectedIndex()] + aux;
				part4+=",\n";
			}
			if(field5.getText().length()!=0) {
				String aux= "";
				if(radio5.isSelected()) {
					aux =  " PRIMARY KEY";
				}
				part6 = field5.getText() + " " + BDChoices[comboBox5.getSelectedIndex()] + aux;
				part5+=",\n";
			}
			if((field6.getText().length()!=0) && (field7.getText().length()!=0) && (field8.getText().length()!=0)) {
				
				part7 = "FOREIGN KEY (" + field6.getText() + ") REFERENCES " + field8.getText() + "(" + field7.getText() +")";
				part6+=",\n";
			}
			
			String query = part1 + part2 + part3 + part4 + part5 + part6 + part7 + "\n);";
			return query;
		}
		return "";
	}

	private double getPercentualScale(String type) {
		int scaledWidth = this.getWidth();
		int scaledHeight = this.getHeight();
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenWitdh = Toolkit.getDefaultToolkit().getScreenSize().width;
		double scale = 0.02;

		if (type == "width" && scaledWidth >= (screenWitdh - 20)) {
			scale = 1;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 200) {
			scale = 0.9;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 300) {
			scale = 0.8;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 400) {
			scale = 0.7;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 550) {
			scale = 0.6;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 750) {
			scale = 0.5;
		} else if (type == "width" && (screenWitdh - scaledWidth) <= 1000) {
			scale = 0.15;
		} else if (type == "height" && scaledHeight >= (screenHeight - 20)) {
			scale = 1;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 200) {
			scale = 0.9;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 300) {
			scale = 0.8;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 400) {
			scale = 0.7;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 550) {
			scale = 0.6;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 750) {
			scale = 0.5;
		} else if (type == "height" && (screenHeight - scaledHeight) <= 1000) {
			scale = 0.4;
		}
		return scale;
	}

}
