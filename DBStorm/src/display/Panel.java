package display;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Panel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 294617894264858348L;

	public Panel() {

		Image icon = new ImageIcon(this.getClass().getResource("/resources/DBStorm.png")).getImage();
		Image image = new ImageIcon(this.getClass().getResource("/resources/DBStormCursor.png")).getImage();
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(10, 10), "DBStorm");
		Dimension dimension = new Dimension(550, 550);
		Graphic g = new Graphic();
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setMinimumSize(dimension);
		pack();
		add(g);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("DB Storm");
		setFocusable(true);
		setIconImage(icon);
		setCursor(cursor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
