package dbCommands;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import display.TableCreatorDisplayer;

public class SQliteCreateTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1394616951970355403L;

	public static String nombre = "";
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();

	public void createNewTable(String nombre) {
		SQliteCreateTable.nombre = nombre;
		Image icon = new ImageIcon(this.getClass().getResource("/resources/DBStorm.png")).getImage();
		Image image = new ImageIcon(this.getClass().getResource("/resources/DBStormCursor.png")).getImage();
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(10, 10), "DBStorm");
		Dimension dimension = new Dimension(width / 2, (height / 2) + 330);
		TableCreatorDisplayer g = new TableCreatorDisplayer();

		setPreferredSize(dimension);
		pack();
		add(g);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Crear Tabla: " + nombre);
		setFocusable(true);
		setIconImage(icon);
		setCursor(cursor);
		setResizable(false);
		setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});

	}
	

}