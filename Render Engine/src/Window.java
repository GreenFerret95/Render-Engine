
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window {
	static JFrame frame;
	static DisplayHandler handler;

	public static boolean fullScreen = false;
	public int width;
	public int height;

	public Window() {
		frame = new JFrame("Render Engine V0.1");

		//Windowed Mode
		if (!fullScreen) {
			width = 640;
			height = 480;

			frame.setSize(width, height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		}
		//Full Screen Mode
		else {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int) screenSize.getWidth();
			height = (int) screenSize.getHeight();
		}
	}

	public static void main(String[] args) {
		Window w = new Window();
		handler = new DisplayHandler(w);

		frame.setVisible(true);
		frame.add(handler);
		frame.addKeyListener(handler);

		handler.timer.start();

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
