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
import display.Graphic;

public class SQliteController extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3507056712304302878L;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();
	public static String usedTable = "";
	private boolean firstDisplay = true;
    public static int selected = 1;
	
	public void showController() {
		if(firstDisplay) {
			Image icon = new ImageIcon(this.getClass().getResource("/resources/DBStorm.png")).getImage();
			Image image = new ImageIcon(this.getClass().getResource("/resources/DBStormCursor.png")).getImage();
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(10, 10), "DBStorm");
			Dimension dimension = new Dimension(width / 2, (height / 2) + 330);
			usedTable = Graphic.actualTable;

			setPreferredSize(dimension);
			pack();
			
			setLocationRelativeTo(null);
			setVisible(true);
			setTitle("Control sobre: " + usedTable);
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
		SQLiteTableController g = new SQLiteTableController();
		add(g);

	}

}
