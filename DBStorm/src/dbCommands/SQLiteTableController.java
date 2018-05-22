package dbCommands;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SQLiteTableController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8308158685516215889L;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private JLabel select, insert, update, delete;
	private JTextField field1, field2, field3, field4, field5, field6, field7, field8;
	private JButton button1, button2, button3;
	private SQliteQuery sqQuery = new SQliteQuery();
	private boolean displaying = true;
	private boolean executeOnce = false;
	private boolean inSelect = true;
	private boolean inInsert = false;
	private boolean inUpdate = false;
	private boolean inDelete = false;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = this.getWidth();
		g.setColor(Color.decode("#ffad33"));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.BOLD, 20);
		displayLabels();
		g.drawLine(0, 50, width, 50);
		g.drawLine(180, 0, 180, 50);
		g.drawLine(380, 0, 380, 50);
		g.drawLine(580, 0, 580, 50);
		if (inSelect) {
			g.setFont(font);
			g.setColor(Color.decode("#ffad33"));
			g.fillRect(0, 55, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			ArrayList<HashMap<String, Object>> records = sqQuery.getAllRecords();
			if (records.isEmpty()) {
				g.drawString("Tabla vacía", 20, 100);
			} else {
				for (int i = 0; i < records.size(); i++) {
					int counter = 0;
					if (records.get(i).size() == 1) {
						g.drawLine(180, 50, 180, this.getHeight());
						g.drawLine(0, 100, 180, 100);
						for (String key : records.get(i).keySet()) {
							g.drawString(key, 10, 80);
							g.drawString(records.get(i).get(key).toString(), 10, (128 + (i * 40)));
							g.drawLine(0, (140 + (i * 40)), 180, (140 + (i * 40)));
						}
					}
					if (records.get(i).size() == 2) {
						g.drawLine(180, 50, 180, this.getHeight());
						g.drawLine(380, 50, 380, this.getHeight());
						g.drawLine(0, 100, 380, 100);
						for (String key : records.get(i).keySet()) {
							g.drawString(key, (10 + (200 * counter)), 80);
							g.drawString(records.get(i).get(key).toString(), 10 + (200 * counter), (128 + (i * 40)));
							g.drawLine(0 + (180 * counter), (140 + (i * 40)), 180 + (200 * counter), (140 + (i * 40)));
							counter++;
						}
					}
					if (records.get(i).size() == 3) {
						g.drawLine(180, 50, 180, this.getHeight());
						g.drawLine(380, 50, 380, this.getHeight());
						g.drawLine(580, 50, 580, this.getHeight());
						g.drawLine(0, 100, 580, 100);
						for (String key : records.get(i).keySet()) {
							g.drawString(key, (10 + (200 * counter)), 80);
							g.drawString(records.get(i).get(key).toString(), 10 + (200 * counter), (128 + (i * 40)));
							g.drawLine(0 + (180 * counter), (140 + (i * 40)), 180 + (200 * counter), (140 + (i * 40)));
							counter++;
						}
					}
					if (records.get(i).size() == 4) {
						g.drawLine(180, 50, 180, this.getHeight());
						g.drawLine(380, 50, 380, this.getHeight());
						g.drawLine(580, 50, 580, this.getHeight());
						g.drawLine(0, 100, this.getWidth(), 100);
						for (String key : records.get(i).keySet()) {
							g.drawString(key, (10 + (200 * counter)), 80);
							g.drawString(records.get(i).get(key).toString(), 10 + (200 * counter), (128 + (i * 40)));
							g.drawLine(0, (140 + (i * 40)), this.getWidth(), (140 + (i * 40)));
							counter++;
						}
					}
					if (records.get(i).size() == 5) {
						g.drawLine(153, 50, 153, this.getHeight());
						g.drawLine(306, 50, 306, this.getHeight());
						g.drawLine(459, 50, 459, this.getHeight());
						g.drawLine(612, 50, 612, this.getHeight());
						g.drawLine(0, 100, this.getWidth(), 100);
						for (String key : records.get(i).keySet()) {
							g.drawString(key, (10 + (153 * counter)), 80);
							g.drawString(records.get(i).get(key).toString(), 10 + (153 * counter), (128 + (i * 40)));
							g.drawLine(0, (140 + (i * 40)), this.getWidth(), (140 + (i * 40)));
							counter++;
						}
					}
				}
			}
		}
		if (inInsert) {
			g.setFont(font);
			g.setColor(Color.decode("#ffad33"));
			g.fillRect(0, 55, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			if (SQliteController.usedTable.length() <= 8) {
				g.drawString("INSERT INTO " + SQliteController.usedTable, 20, 160);
			} else {
				g.drawString("INSERT INTO ", 20, 160);
				g.drawString(SQliteController.usedTable, 20, 190);
			}
			g.drawString(" VALUES ", 440, 160);
			validate();
			repaint();
			insertInto();
		}
		if (inUpdate) {
			g.setFont(font);
			g.setColor(Color.decode("#ffad33"));
			g.fillRect(0, 55, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			if (SQliteController.usedTable.length() <= 11) {
				g.drawString("UPDATE " + SQliteController.usedTable, 20, 160);
			} else {
				g.drawString("UPDATE ", 20, 160);
				g.drawString(SQliteController.usedTable, 20, 190);
			}
			g.drawString(" TO VALUE ", 420, 160);
			g.drawString("WHERE ", 20, 260);
			g.drawString(" = ", 460, 260);
			displayUpdate();
			validate();
			repaint();
		}
		if(inDelete) {
			g.setFont(font);
			g.setColor(Color.decode("#ffad33"));
			g.fillRect(0, 55, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString("DELETE FROM  " + SQliteController.usedTable + "  WHERE ", 10, 160);
			g.drawString("EQUALS ", 10, 260);
			displayDelete();
			validate();
			repaint();
		}
	}

	public void displayLabels() {
		if (displaying) {
			setLayout(null);
			select = new JLabel();
			insert = new JLabel();
			update = new JLabel();
			delete = new JLabel();
			select.setText("Select");
			insert.setText("Insert");
			update.setText("Update");
			delete.setText("Delete");
			Font font = new Font("Arial", Font.BOLD, 25);
			select.setFont(font);
			insert.setFont(font);
			update.setFont(font);
			delete.setFont(font);
			select.setForeground(Color.BLACK);
			insert.setForeground(Color.BLACK);
			update.setForeground(Color.BLACK);
			delete.setForeground(Color.BLACK);
			if (SQliteController.selected == 1) {
				select.setForeground(Color.BLUE);
			} else if (SQliteController.selected == 2) {
				insert.setForeground(Color.BLUE);
			} else if (SQliteController.selected == 3) {
				update.setForeground(Color.BLUE);
			} else {
				delete.setForeground(Color.BLUE);
			}
			select.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					try {
						field1.setVisible(false);
						field2.setVisible(false);
						button1.setVisible(false);
						remove(field1);
						remove(field2);
						remove(button1);
					} catch (Exception e) {
					}try {
						field3.setVisible(false);
						field4.setVisible(false);
						field5.setVisible(false);
						field6.setVisible(false);
						button2.setVisible(false);
						remove(field3);
						remove(field4);
						remove(field5);
						remove(field6);
						remove(button2);
					} catch (Exception e) {
					}try {
						field7.setVisible(false);
						field8.setVisible(false);
						button3.setVisible(false);
						remove(field7);
						remove(field8);
						remove(button3);
					} catch (Exception e) {
					}
					inSelect = true;
					inInsert = false;
					inUpdate = false;
					inDelete = false;
					SQliteController.selected = 1;
					select.setForeground(Color.BLUE);
					insert.setForeground(Color.BLACK);
					update.setForeground(Color.BLACK);
					delete.setForeground(Color.BLACK);
				}

				public void mouseEntered(MouseEvent ME) {
					if (!(SQliteController.selected == 1)) {
						select.setForeground(Color.YELLOW);
					}
				}

				public void mouseExited(MouseEvent ME) {
					if (!(SQliteController.selected == 1)) {
						select.setForeground(Color.BLACK);
					}
				}

			});
			insert.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					if (SQliteController.selected != 2) {
						try {
							field3.setVisible(false);
							field4.setVisible(false);
							field5.setVisible(false);
							field6.setVisible(false);
							button2.setVisible(false);
							remove(field3);
							remove(field4);
							remove(field5);
							remove(field6);
							remove(button2);
						} catch (Exception e) {
						}try {
							field7.setVisible(false);
							field8.setVisible(false);
							button3.setVisible(false);
							remove(field7);
							remove(field8);
							remove(button3);
						} catch (Exception e) {
						}
						executeOnce = true;
						inSelect = false;
						inInsert = true;
						inUpdate = false;
						inDelete = false;
						SQliteController.selected = 2;
						select.setForeground(Color.BLACK);
						insert.setForeground(Color.BLUE);
						update.setForeground(Color.BLACK);
						delete.setForeground(Color.BLACK);
					}
				}

				public void mouseEntered(MouseEvent ME) {
					if (!(SQliteController.selected == 2)) {
						insert.setForeground(Color.YELLOW);
					}
				}

				public void mouseExited(MouseEvent ME) {
					if (!(SQliteController.selected == 2)) {
						insert.setForeground(Color.BLACK);
					}
				}

			});
			update.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					if (SQliteController.selected != 3) {
						try {
							field1.setVisible(false);
							field2.setVisible(false);
							button1.setVisible(false);
							remove(field1);
							remove(field2);
							remove(button1);
						} catch (Exception e) {
						}try {
							field7.setVisible(false);
							field8.setVisible(false);
							button3.setVisible(false);
							remove(field7);
							remove(field8);
							remove(button3);
						} catch (Exception e) {
						}
						executeOnce = true;
						inSelect = false;
						inInsert = false;
						inUpdate = true;
						inDelete = false;
						SQliteController.selected = 3;
						select.setForeground(Color.BLACK);
						insert.setForeground(Color.BLACK);
						update.setForeground(Color.BLUE);
						delete.setForeground(Color.BLACK);
					}
				}

				public void mouseEntered(MouseEvent ME) {
					if (!(SQliteController.selected == 3)) {
						update.setForeground(Color.YELLOW);
					}
				}

				public void mouseExited(MouseEvent ME) {
					if (!(SQliteController.selected == 3)) {
						update.setForeground(Color.BLACK);
					}
				}

			});
			delete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					try {
						field1.setVisible(false);
						field2.setVisible(false);
						button1.setVisible(false);
						remove(field1);
						remove(field2);
						remove(button1);
					} catch (Exception e) {
					}try {
						field3.setVisible(false);
						field4.setVisible(false);
						field5.setVisible(false);
						field6.setVisible(false);
						button2.setVisible(false);
						remove(field3);
						remove(field4);
						remove(field5);
						remove(field6);
						remove(button2);
					} catch (Exception e) {
					}
					executeOnce = true;
					SQliteController.selected = 4;
					select.setForeground(Color.BLACK);
					insert.setForeground(Color.BLACK);
					update.setForeground(Color.BLACK);
					delete.setForeground(Color.BLUE);
					inSelect = false;
					inInsert = false;
					inUpdate = false;
					inDelete = true;

				}

				public void mouseEntered(MouseEvent ME) {
					if (!(SQliteController.selected == 4)) {
						delete.setForeground(Color.YELLOW);
					}
				}

				public void mouseExited(MouseEvent ME) {
					if (!(SQliteController.selected == 4)) {
						delete.setForeground(Color.BLACK);
					}
				}

			});
			select.setVisible(true);
			insert.setVisible(true);
			update.setVisible(true);
			delete.setVisible(true);
			add(select);
			add(insert);
			add(update);
			add(delete);
			displaying = false;
		}
		select.setBounds(40, 5, 100, 40);
		insert.setBounds(240, 5, 100, 40);
		update.setBounds(440, 5, 100, 40);
		delete.setBounds(640, 5, 100, 40);
	}

	public void insertInto() {
		if (executeOnce) {
			field1 = new JTextField();
			field2 = new JTextField();
			button1 = new JButton();
			button1.setText("Insertar");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					String query = "INSERT INTO " + SQliteController.usedTable + " (" + field1.getText()
							+ ") \nVALUES (" + field2.getText() + ");";
					sqQuery.insert(query);
				}
			});
			add(button1);
			add(field1);
			add(field2);
			field1.setBounds(250, 140, 180, 30);
			field2.setBounds(540, 140, 220, 30);
			button1.setBounds(400, 200, 100, 40);
			executeOnce = false;
		}
	}

	public void displayUpdate() {
		if (executeOnce) {
			field3 = new JTextField();
			field4 = new JTextField();
			field5 = new JTextField();
			field6 = new JTextField();
			button2 = new JButton();
			button2.setText("Actualizar");
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					sqQuery.update(field3.getText(), field4.getText(), field5.getText(),field6.getText());
				}
			});
			add(button2);
			add(field3);
			add(field4);
			add(field5);
			add(field6);
			field3.setBounds(230, 140, 180, 30);
			field4.setBounds(540, 140, 220, 30);
			field5.setBounds(230, 240, 180, 30);
			field6.setBounds(540, 240, 220, 30);
			button2.setBounds(340, 320, 100, 40);
			executeOnce = false;
		}
	}
	public void displayDelete() {
		if (executeOnce) {
			field7 = new JTextField();
			field8 = new JTextField();
			button3 = new JButton();
			button3.setText("Borrar");
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					sqQuery.delete(field7.getText(), field8.getText());
				}
			});
			add(button3);
			add(field7);
			add(field8);
			field7.setBounds(380, 140, 260, 30);
			field8.setBounds(140, 240, 260, 30);
			button3.setBounds(340, 320, 100, 40);
			executeOnce = false;
		}
	}
}
