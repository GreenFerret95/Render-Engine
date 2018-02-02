import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DisplayHandler extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	public Window window;
	public Main main;
	public BufferedImage bi;
	public Timer timer;

	public int fps = 1000 / 90;
	public boolean xyCWRotate = false;
	public boolean xyCCRotate = false;
	public boolean yzCWRotate = false;
	public boolean yzCCRotate = false;
	public boolean xzCWRotate = false;
	public boolean xzCCRotate = false;
	public boolean verbose = false;
	public boolean paused = false;

	public double startTime;
	public double estimatedTimePassed = 0;
	public double frameCount = 0;
	public double actualFrameRate = 0;

	public int screenCenterX;
	public int screenCenterY;

	public DisplayHandler(Window w) {
		this.window = w;
		main = new Main();
		timer = new Timer(fps, this);

		init();
	}

	public void init() {
		main.createTriangles();
		startTime = System.nanoTime();

		screenCenterX = window.getWidth() / 2;
		screenCenterY = window.getHeight() / 2;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g.create();

		bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = bi.getRaster();

		for (Triangle t : main.tri) {
			Path2D p2d = new Path2D.Double();

			// draw triangle
			g2d.setColor(t.getColor());
			p2d.moveTo(t.getA().getX(), t.getA().getY());
			p2d.lineTo(t.getB().getX(), t.getB().getY());
			p2d.lineTo(t.getC().getX(), t.getC().getY());
			p2d.closePath();
			g2d.draw(p2d);

			g2d.setColor(Color.WHITE);

			//fillTriangle(g2d);

			/******************
			 * DEBUG INFO
			 *****************
			 */
			if (verbose) {

				g2d.setColor(Color.WHITE);
				// draw xAxis
				g2d.drawLine(0, screenCenterY, getWidth(), screenCenterY);
				g2d.drawString("100", screenCenterX + 100, screenCenterY);
				g2d.drawString("-100", screenCenterX - 100, screenCenterY);

				//draw yAxis
				g2d.drawLine(screenCenterX, 0, screenCenterX, getHeight());
				g2d.drawString("100", screenCenterX, screenCenterY + 100);
				g2d.drawString("-100", screenCenterX, screenCenterY - 100);

				//draw point coordinates
				drawInfo(g2d, t.getA().getX(), t.getA().getY(), "Ax " + (int) t.getA().getX() + " Ay " + (int) t.getA().getY());
				drawInfo(g2d, t.getB().getX(), t.getB().getY(), "Bx " + (int) t.getB().getX() + " By " + (int) t.getB().getY());
				drawInfo(g2d, t.getC().getX(), t.getC().getY(), "Cx " + (int) t.getC().getX() + " Ay " + (int) t.getC().getY());

				//draw frameRate
				g2d.setColor(Color.RED);
				g2d.drawString("FrameRate: " + actualFrameRate, window.getWidth() - 150, 25);
			}
		}
		g2d.dispose();
	}

	public void fillTriangle(Graphics2D g2d) {

		for (Triangle t : main.tri) {
			//Minimum xy and maximum xy coordinates of the triangle
			int minX = (int) t.getMinX(t.getA().getX(), t.getB().getX(), t.getC().getX());
			int minY = (int) t.getMinY(t.getA().getY(), t.getB().getY(), t.getC().getY());
			int maxX = (int) t.getMaxX(t.getA().getX(), t.getB().getX(), t.getC().getX());
			int maxY = (int) t.getMaxY(t.getA().getY(), t.getB().getY(), t.getC().getY());

			//loop through each pixel withen the triangles parameters
			for (int x = minX; x < maxX; x++) {
				for (int y = minY; y < maxY; y++) {

				}
			}

		}
	}

	/*
	 * Easier way to draw coordinates of a point on the screen
	 */
	public void drawInfo(Graphics2D g2d, double pointX, double pointY, String string) {
		g2d.drawString(string, (int) pointX, (int) pointY);
	}

	public void calculateFPS() {
		frameCount++;
		estimatedTimePassed = (System.nanoTime() - startTime) / 1000000000; //time elapsed in seconds

		if (estimatedTimePassed >= 1.0) {
			actualFrameRate = (int) (frameCount / estimatedTimePassed); // the ammount of times the frames have changed in a single second
			estimatedTimePassed = 0;
			frameCount = 0;
			startTime = System.nanoTime();

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		calculateFPS();

		for (Triangle t : main.tri) {
			t.step();
		}

		if (xyCWRotate) {
			main.xyCWRotate();
		}
		if (xyCCRotate) {
			main.xyCCRotate();
		}
		if (yzCWRotate) {
			main.yzCWRotate();
		}
		if (yzCCRotate) {
			main.yzCCRotate();
		}
		if (xzCWRotate) {
			main.xzCWRotate();
		}

		if (xzCCRotate) {
			main.xzCCRotate();
		}

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			xyCWRotate = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			xyCCRotate = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			yzCWRotate = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			yzCCRotate = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_E) {
			xzCWRotate = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_Q) {
			xzCCRotate = true;
		}

		//Debut info
		if (e.getKeyCode() == KeyEvent.VK_INSERT) {
			if (verbose) {
				verbose = false;
			} else {
				verbose = true;
			}
		}

		//pause timer
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (paused) {
				timer.stop();
				paused = false;
			} else if (paused = true) {
				timer.start();
				paused = true;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			xyCWRotate = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			xyCCRotate = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			yzCWRotate = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			yzCCRotate = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_E) {
			xzCWRotate = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_Q) {
			xzCCRotate = false;
		}

	}

}
