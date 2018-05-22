package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import dbCommands.SQliteController;
import dbCommands.SQliteCreateDatabase;
import dbCommands.SQliteCreateTable;
import dbCommands.SQliteShowTables;
import utilities.TextCorrector;
import utilities.TimeCounter;
import utilities.UserDataManagement;

public class Graphic extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1945079383671655212L;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private TextCorrector txc = new TextCorrector();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();
	private static HashMap<String, String> namesAndRoutes = new HashMap<String, String>();
	private static ArrayList<JLabel> Jlabels = new ArrayList<JLabel>();
	public static ArrayList<JLabel> Jtables = new ArrayList<JLabel>();
	public static ArrayList<String> tablas = new ArrayList<String>();
	private Image initStageImg = new ImageIcon(this.getClass().getResource("/resources/DBStorm.png")).getImage();
	private Image loading = (new ImageIcon(this.getClass().getResource("/resources/Loading.gif")).getImage())
			.getScaledInstance(460, 50, Image.SCALE_DEFAULT);
	private TimeCounter tc = new TimeCounter();
	private JTextField userName = new JTextField(20);
	private JTextField route = new JTextField(20);
	private JTextField name = new JTextField(20);
	private JTextField nameTable = new JTextField(20);
	private JPasswordField password = new JPasswordField(20);
	private JButton jbutton = new JButton();
	private JButton SQlite = new JButton();
	private JButton PostgreSQL = new JButton();
	private JButton aceptar = new JButton();
	private JButton crear = new JButton();
	private JButton insert = new JButton();
	private JButton crearTabla = new JButton();
	public static String actualTable = "";
	private UserDataManagement udm = new UserDataManagement();
	private SQliteShowTables showTables = new SQliteShowTables();
	private SQliteCreateTable sqLiteTable = new SQliteCreateTable();
	private SQliteController sqController = new SQliteController();
	private boolean alreadyUsedName = false;
	private boolean showPreferences = true;
	private boolean invalidUrl = false;
	private boolean loggedIn = false;
	private boolean userOrPasswordShort = false;
	private boolean waiting = true;
	private boolean display = true;
	private boolean timeCounting = true;
	private boolean displayRoute = true;
	private boolean userSettings = true;
	private boolean SQliteSelected = false;
	private boolean listing = true;
	private boolean invalid = false;
	private boolean invalidTable = false;
	public boolean displayingTables = false;
	private boolean displayInsert = false;
	public static String filename = "";
	private File folder = null;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		height = this.getHeight();
		width = this.getWidth();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(initStageImg, (int) ((width / 3) * getPercentualScale("width")),
				(int) ((height / 8) * getPercentualScale("height")), this);
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.BOLD, 100);
		Font font4 = new Font("Arial", Font.BOLD, 18);
		g.setFont(font);
		g.drawString("DB Storm", (int) ((width / 3) * getPercentualScale("width")),
				(int) (600 * getPercentualScale("height")));
		g.drawImage(loading, (int) ((width / 3) * getPercentualScale("width")),
				(int) (650 * getPercentualScale("height")), this);
		if (timeCounting) {
			tc.Contar();
			timeCounting = false;
		}
		if (tc.getMillis() >= 90) {
			tc.Detener();
			waiting = false;
		}
		if (!waiting) {
			g.setColor(Color.decode("#ffad33"));
			g.fillRect(0, 0, getWidth(), getHeight());
			Font font2 = new Font("Arial", Font.BOLD, 25);
			g.setFont(font2);
			displayForm();
			g.setColor(Color.BLACK);
			g.drawString("Nombre de Usuario", (int) (680 * getPercentualScale("width")),
					(int) (260 * getPercentualScale("height")));
			g.drawString("Contraseña", (int) (720 * getPercentualScale("width")),
					(int) (480 * getPercentualScale("height")));
			Font font3 = new Font("Arial", Font.BOLD, 60);
			g.setFont(font3);
			if (this.getWidth() >= 730) {
				g.drawString("¡Bienvenido a DB Storm!", (int) ((width / 60) * getPercentualScale("width")),
						(int) ((120) * getPercentualScale("height")));
			} else {
				g.drawString("¡Bienvenido a", (int) ((width / 60) * getPercentualScale("width")),
						(int) ((80) * getPercentualScale("height")));
				g.drawString("DB Storm!", (int) ((width / 60) * getPercentualScale("width")),
						(int) ((160) * getPercentualScale("height")));
			}
			if (userOrPasswordShort) {
				g.setFont(font4);
				g.setColor(Color.RED);
				if (this.getWidth() >= 730) {
					g.drawString("El usuario y contraseña deben tener al menos 6 caractéres",
							(int) ((550) * getPercentualScale("width")), (int) ((580) * getPercentualScale("height")));
				} else {
					g.drawString("El usuario y contraseña deben", (int) ((600) * getPercentualScale("width")),
							(int) ((580) * getPercentualScale("height")));
					g.drawString("tener al menos 6 caractéres", (int) ((600) * getPercentualScale("width")),
							(int) ((610) * getPercentualScale("height")));
				}
				validate();
				repaint();
			}

			if (alreadyUsedName) {
				g.setFont(font4);
				g.setColor(Color.RED);
				g.drawString("Contraseña incorrecta o nombre ya en uso", (int) ((610) * getPercentualScale("width")),
						(int) ((580) * getPercentualScale("height")));
				validate();
				repaint();
			} else if (loggedIn) {
				userName.setVisible(false);
				password.setVisible(false);
				jbutton.setVisible(false);
				g.setColor(Color.decode("#ffad33"));
				g.fillRect(0, 0, getWidth(), getHeight());
				this.remove(userName);
				this.remove(password);
				this.remove(jbutton);
				g.setColor(Color.BLACK);
				if (this.getWidth() >= 840) {
					g.drawString("¡Hola " + UserDataManagement.userName + ", Bienvenido!",
							(int) ((width / 60) * getPercentualScale("width")),
							(int) ((120) * getPercentualScale("height")));
				} else {
					g.drawString("¡Hola " + UserDataManagement.userName,
							(int) ((width / 60) * getPercentualScale("width")),
							(int) ((80) * getPercentualScale("height")));
					g.drawString("Empecemos!", (int) ((width / 60) * getPercentualScale("width")),
							(int) ((160) * getPercentualScale("height")));
				}
				g.setFont(font2);
				g.drawString("Seleccione un Sistema de gestión", (int) ((580) * getPercentualScale("width")),
						(int) ((250) * getPercentualScale("height")));
				displayDBSelector();
				repaint();
				if (SQliteSelected) {
					SQlite.setVisible(false);
					PostgreSQL.setVisible(false);
					this.remove(SQlite);
					this.remove(PostgreSQL);
					if (showPreferences) {
						g.setColor(Color.decode("#ffad33"));
						g.fillRect(0, 0, getWidth(), getHeight());
						g.setFont(font2);
						g.setColor(Color.BLACK);
						g.drawString("Selecciona la ruta de instalación de SQlite",
								(int) ((80) * getPercentualScale("width")),
								(int) ((100) * getPercentualScale("height")));
						displayOptions("SQlite");

						if (invalidUrl) {
							g.setFont(font4);
							g.setColor(Color.RED);
							g.drawString("No es una dirección válida.", (int) ((220) * getPercentualScale("width")),
									(int) ((270) * getPercentualScale("height")));
							validate();
							repaint();
						}
					} else {
						g.setColor(Color.decode("#ffad33"));
						g.fillRect(0, 0, getWidth(), getHeight());
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(0, 0, getWidth(), 140);
						g.setFont(font3);
						g.setColor(Color.BLACK);
						g.drawLine(0, 140, getWidth(), 140);
						g.drawString("SQlite Manager", (int) ((width / 60) * getPercentualScale("width")),
								(int) ((100) * getPercentualScale("height")));
						g.drawLine(400, 140, 400, this.getHeight());
						g.drawLine(0, (height / 2) + 50, 400, (height / 2) + 50);
						g.setFont(font4);
						g.drawString("Bases de Datos", 120, 170);
						g.drawLine(0, 180, 400, 180);
						g.drawString("Tablas", 150, (height / 2) + 70);
						g.drawLine(0, height / 2 + 80, 400, height / 2 + 80);
						if (listing) {
							folder = new File(UserDataManagement.SQliteRoute);
							listFilesForFolder(folder);
							display = true;
						}
						createDataBase();
						if (namesAndRoutes.isEmpty()) {
							g.drawString("No se han encontrado", 100, 200);
							g.drawString("bases de datos.", 100, 220);
						} else {
							displayDBS();
						}
						if (invalid) {
							g.setColor(Color.RED);
							g.drawString("Nombre invalido o vacío",
									(int) minimumSize((600 * getPercentualScale("width")), 420), 190);
						}

						if (invalidTable) {
							g.setColor(Color.RED);
							g.drawString("Nombre invalido o vacío",
									(int) minimumSize((600 * getPercentualScale("width")), 430), 320);
						}

						displayTables();
						crearTabla.setBounds((int) minimumSize((680 * getPercentualScale("width")), 420), 400, 100, 40);
						nameTable.setBounds((int) minimumSize((550 * getPercentualScale("width")), 420), 340, 400, 40);
						insert.setBounds((int) minimumSize((630 * getPercentualScale("width")), 420), 500, 200, 40);
						validate();
						repaint();

					}
				}
			}
		}
	}

	public void displayForm() {
		if (userSettings) {
			setLayout(null);
			jbutton.setText("Aceptar");
			userName.setFont(userName.getFont().deriveFont(20f));
			password.setFont(password.getFont().deriveFont(20f));
			jbutton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent actEv) {
					String temporaryUserName = txc.removeFirstAndLastSpace(userName.getText());
					String temporaryPassword = txc.removeFLSPassword(password.getPassword());
					boolean write = true;
					if (alreadyUsedName || userOrPasswordShort) {
						alreadyUsedName = false;
						userOrPasswordShort = false;
					}
					if ((temporaryUserName.length()) < 6 || (temporaryPassword.length() < 6)) {
						userOrPasswordShort = true;
						return;
					}
					try {
						BufferedReader fb = new BufferedReader(new FileReader("UserInfo_saveData"));
						ArrayList<String> updater = new ArrayList<String>();
						while (fb.ready()) {
							String line = fb.readLine();
							updater.add(line);

							if (line.equals(temporaryUserName)) {
								if (fb.readLine().equals(temporaryPassword)) {
									UserDataManagement.userName = temporaryUserName;
									UserDataManagement.password = temporaryPassword;
									loggedIn = true;
									write = false;
								} else {
									alreadyUsedName = true;
									write = false;
								}
							}
						}
						fb.close();
						if (write) {
							UserDataManagement.userName = temporaryUserName;
							UserDataManagement.password = temporaryPassword;
							udm.updateSaveData(updater);
							loggedIn = true;
						}

					} catch (FileNotFoundException e) {
						UserDataManagement.userName = temporaryUserName;
						UserDataManagement.password = temporaryPassword;
						udm.dataSaver();
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("IOException");
					}
					userName.setText(temporaryUserName);
					password.setText(temporaryPassword);
				}
			});
			this.add(userName);
			this.add(password);
			this.add(jbutton);
			validate();
			repaint();
			userSettings = false;
		}
		userName.setBounds((int) (550 * getPercentualScale("width")), (int) (280 * getPercentualScale("height")), 500,
				40);
		password.setBounds((int) (550 * getPercentualScale("width")), (int) (500 * getPercentualScale("height")), 500,
				40);
		jbutton.setBounds((int) (665 * getPercentualScale("width")), (int) (650 * getPercentualScale("height")), 250,
				40);
		validate();
		repaint();
	}

	public void displayDBS() {
		if (display) {
			setLayout(null);
			ArrayList<String> nombres = new ArrayList<String>();
			for (Entry<String, String> entry : namesAndRoutes.entrySet()) {
				String[] removeExtension = entry.getKey().split("\\.");
				nombres.add(removeExtension[0]);
				JLabel clickable = new JLabel();
				clickable.setText(removeExtension[0]);
				Font font = new Font("Arial", Font.BOLD, 25);
				clickable.setFont(font);
				clickable.setForeground(Color.BLACK);
				clickable.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent ME) {
						for (int i = 0; i < Jtables.size(); i++) {
							Jtables.get(i).setVisible(false);
							remove(Jtables.get(i));
						}
						insert.setVisible(false);
						remove(insert);
						invalidTable = false;
						invalid = false;
						filename = removeExtension[0];
						displayingTables = true;
						tableCreator();
						tablas.clear();
						Jtables.clear();
						showTables.getDatabaseMetaData();
						for (int j = 0; j < Jlabels.size(); j++) {
							Jlabels.get(j).setForeground(Color.BLACK);
						}
						clickable.setForeground(Color.BLUE);
					}

					public void mouseEntered(MouseEvent ME) {
						if (!clickable.getForeground().equals(Color.BLUE)) {
							clickable.setForeground(Color.YELLOW);
						}
					}

					public void mouseExited(MouseEvent ME) {
						if (!clickable.getForeground().equals(Color.BLUE)) {
							clickable.setForeground(Color.BLACK);
						}
					}

				});
				Jlabels.add(clickable);
				this.add(clickable);
			}
			display = false;
		}
		for (int i = 0; i < Jlabels.size(); i++) {
			JLabel label = Jlabels.get(i);
			label.setBounds(20, (int) ((165 + (25 * i))), 400, 60);
		}

	}

	public void displayTables() {
		if (displayingTables) {
			setLayout(null);
			ArrayList<String> nombres = new ArrayList<String>();
			for (int i = 0; i < tablas.size(); i++) {
				String tabla = tablas.get(i);
				nombres.add(tabla);
				JLabel clickable = new JLabel();
				clickable.setText(tabla);
				Font font = new Font("Arial", Font.BOLD, 25);
				clickable.setFont(font);
				clickable.setForeground(Color.BLACK);
				clickable.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent ME) {
						actualTable = tabla;
						invalidTable = false;
						invalid = false;
						displayInsert = true;
						displayingInsert();
						for (int j = 0; j < Jtables.size(); j++) {
							Jtables.get(j).setForeground(Color.BLACK);
						}
						clickable.setForeground(Color.BLUE);
					}

					public void mouseEntered(MouseEvent ME) {
						if (!clickable.getForeground().equals(Color.BLUE)) {
							clickable.setForeground(Color.YELLOW);
						}
					}

					public void mouseExited(MouseEvent ME) {
						if (!clickable.getForeground().equals(Color.BLUE)) {
							clickable.setForeground(Color.BLACK);
						}
					}

				});
				clickable.setVisible(true);
				Jtables.add(clickable);
				this.add(clickable);
			}
			displayingTables = false;
		}
		for (int i = 0; i < Jtables.size(); i++) {
			JLabel label = Jtables.get(i);
			label.setBounds(20, ((height / 2) + 70) + (25 * i), 400, 60);
		}

	}

	public void tableCreator() {
		if (displayingTables) {
			setLayout(null);
			nameTable.setText("Crear Tabla");
			nameTable.addMouseListener((new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					nameTable.setText("");
				}
			}));
			crearTabla.setText("Crear");
			crearTabla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					if (!(nameTable.getText().equals("Crear Tabla")) && nameTable.getText().length() > 0) {
						sqLiteTable.createNewTable(nameTable.getText());
						for (int i = 0; i < Jtables.size(); i++) {
							remove(Jtables.get(i));
						}
						Jtables.clear();
						invalidTable = false;
						displayingTables = true;
						displayTables();
					} else {
						invalidTable = true;
					}
				}
			});
			this.add(nameTable);
			this.add(crearTabla);
			validate();
			repaint();
		}
	}

	public void createDataBase() {
		if (display) {
			setLayout(null);
			name.setText("Crear Base de Datos");
			name.addMouseListener((new MouseAdapter() {
				public void mouseClicked(MouseEvent ME) {
					name.setText("");
				}
			}));
			crear.setText("Crear");
			crear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					if (!(name.getText().equals("Crear Base de Datos")) && name.getText().length() > 0) {
						SQliteCreateDatabase.createNewDatabase(name.getText());
						for (int i = 0; i < Jlabels.size(); i++) {
							remove(Jlabels.get(i));
						}
						Jlabels.clear();
						listing = true;
						invalid = false;
						for (int i = 0; i < Jtables.size(); i++) {
							remove(Jtables.get(i));
						}
						Jtables.clear();
						displayingTables = true;
						displayTables();
					} else {
						invalid = true;
					}
				}
			});
			this.add(name);
			this.add(crear);
			validate();
			repaint();
		}
		crear.setBounds((int) minimumSize((680 * getPercentualScale("width")), 420), 250, 100, 40);
		name.setBounds((int) minimumSize((550 * getPercentualScale("width")), 420), 200, 400, 40);

	}

	public void displayOptions(String type) {
		if (displayRoute) {
			if (type.equals("SQlite")) {
				route.setText(UserDataManagement.SQliteRoute);
				aceptar.setText("Aceptar");
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actEv) {
						invalidUrl = false;
						File file = new File(route.getText());
						if (!file.exists()) {
							invalidUrl = true;
							route.setText("C:/sqlite/");
						} else {
							new File(route.getText() + UserDataManagement.userName).mkdir();
							UserDataManagement.SQliteRoute = route.getText() + UserDataManagement.userName;
							showPreferences = false;
							route.setVisible(false);
							aceptar.setVisible(false);
							remove(route);
							remove(aceptar);
						}
					}
				});

			} else if (type.equals("PostgreSQL")) {

			}
			this.add(route);
			this.add(aceptar);
			validate();
			repaint();
			displayRoute = false;
		}
		route.setBounds((int) (90 * getPercentualScale("width")), (int) (180 * getPercentualScale("height")), 500, 40);
		aceptar.setBounds((int) (260 * getPercentualScale("width")), (int) (380 * getPercentualScale("height")), 150,
				40);
	}

	public void displayDBSelector() {
		if (display) {
			setLayout(null);
			SQlite.setText("SQ Lite");
			PostgreSQL.setText("PostgreSQL");

			SQlite.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent actEv) {
					readSQliteSavedPreferences();
					SQliteSelected = true;
					display = true;

				}
			});

			PostgreSQL.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent actEv) {
				}
			});
			this.add(SQlite);
			this.add(PostgreSQL);
			validate();
			repaint();
			display = false;
		}
		SQlite.setBounds((int) (580 * getPercentualScale("width")), (int) (320 * getPercentualScale("height")), 400,
				60);
		PostgreSQL.setBounds((int) (580 * getPercentualScale("width")), (int) (560 * getPercentualScale("height")), 400,
				60);
	}

	public void listFilesForFolder(final File folder) {
		namesAndRoutes.clear();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				namesAndRoutes.put(fileEntry.getName(), fileEntry.getPath().replaceAll("\\\\", "/"));
			}
		}
		listing = false;
	}

	private void displayingInsert() {
		if (displayInsert) {
			setLayout(null);
			insert.setVisible(false);
			remove(insert);
			insert.setText("Controlador");
			insert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent actEv) {
					sqController.showController();

				}
			});
			insert.setVisible(true);
			add(insert);
			displayInsert = false;
		}
	}

	private void readSQliteSavedPreferences() {
		try {
			BufferedReader fb = new BufferedReader(new FileReader("UserSQLitePreferences_saveData"));
			ArrayList<String> updater = new ArrayList<String>();
			while (fb.ready()) {
				String linea = fb.readLine();
				updater.add(linea);
				String[] splitArray = linea.split("%");
				if (splitArray[0].equals(UserDataManagement.userName)) {
					UserDataManagement.SQliteRoute = splitArray[1];
					showPreferences = false;
					fb.close();
					return;
				} else {
					showPreferences = true;
					continue;
				}
			}
			fb.close();
			udm.sqLitePreferencesUpdater(updater);
		} catch (FileNotFoundException e) {
			udm.sqLitePreferencesSaver();
			userSettings = true;

		} catch (IOException e) {
			System.out.println("IOException");
		}
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

	private double minimumSize(double actual, double min) {
		if (actual <= min) {
			return min;
		} else {
			return actual;
		}
	}

}
