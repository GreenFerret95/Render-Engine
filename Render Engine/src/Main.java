import java.awt.Color;
import java.util.ArrayList;

public class Main {

	ArrayList<Triangle> tri = new ArrayList<Triangle>();
	public double thetaOffset = 1;

	public void createTriangles() {
		double maxR = Math.random() * 500;
		double maxT = Math.random() * 360;
		int totalTriangles = 1;

		for (int i = 1; i <= totalTriangles; i++) {
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			Color c = new Color(r, g, b);

			//tri.add(new Triangle(new Point(Math.random() * maxR, Math.random() * maxT), new Point(Math.random() * maxR, Math.random() * maxT), new Point(Math.random() * maxR, Math.random() * maxT), c));

			
			  tri.add(new Triangle(new Point(100, 100, 100), new Point(-100,
			  -100, 100), new Point(-100, 100, -100), Color.WHITE));
			  tri.add(new Triangle(new Point(100, 100, 100), new Point(-100,
			  -100, 100), new Point(100, -100, -100), Color.RED)); tri.add(new
			  Triangle(new Point(-100, 100, -100), new Point(100, -100, -100),
			  new Point(100, 100, 100), Color.GREEN)); tri.add(new Triangle(new
			  Point(-100, 100, -100), new Point(100, -100, -100), new
			  Point(-100, -100, 100), Color.BLUE));
			  
			 

		}

	}

	// xy axis clockwise rotate
	public void xyCWRotate() {
		double[] Matrix = { Math.cos(Math.toRadians(thetaOffset)), -1 * Math.sin(Math.toRadians(thetaOffset)), 0, Math.sin(Math.toRadians(thetaOffset)), Math.cos(Math.toRadians(thetaOffset)), 0, 0, 0, 1 };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

	// xy axis counter clockwise rotate
	public void xyCCRotate() {
		double[] Matrix = { Math.cos(Math.toRadians(thetaOffset)), Math.sin(Math.toRadians(thetaOffset)), 0, -1 * Math.sin(Math.toRadians(thetaOffset)), Math.cos(Math.toRadians(thetaOffset)), 0, 0, 0, 1 };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

	public void yzCWRotate() {
		double[] Matrix = { 1, 0, 0, 0, Math.cos(Math.toRadians(thetaOffset)), Math.sin(Math.toRadians(thetaOffset)), 0, -1 * Math.sin(Math.toRadians(thetaOffset)), Math.cos(Math.toRadians(thetaOffset)) };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

	public void yzCCRotate() {
		double[] Matrix = { 1, 0, 0, 0, Math.cos(Math.toRadians(thetaOffset)), -1 * Math.sin(Math.toRadians(thetaOffset)), 0, Math.sin(Math.toRadians(thetaOffset)), Math.cos(Math.toRadians(thetaOffset)) };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

	public void xzCWRotate() {
		double[] Matrix = { Math.cos(Math.toRadians(thetaOffset)), 0, -1 * Math.sin(Math.toRadians(thetaOffset)), 0, 1, 0, Math.sin(Math.toRadians(thetaOffset)), 0, Math.cos(Math.toRadians(thetaOffset)) };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

	public void xzCCRotate() {
		double[] Matrix = { Math.cos(Math.toRadians(thetaOffset)), 0, Math.sin(Math.toRadians(thetaOffset)), 0, 1, 0, -1 * Math.sin(Math.toRadians(thetaOffset)), 0, Math.cos(Math.toRadians(thetaOffset)) };
		Matrix3 m = new Matrix3(Matrix);

		for (Triangle t : tri) {
			double[] inputA = { t.getA().getX(), t.getA().getY(), t.getA().getZ() };
			double[] inputB = { t.getB().getX(), t.getB().getY(), t.getB().getZ() };
			double[] inputC = { t.getC().getX(), t.getC().getY(), t.getC().getZ() };

			double[] newA = m.multiply(inputA);
			double[] newB = m.multiply(inputB);
			double[] newC = m.multiply(inputC);

			t.getA().setPoints(newA);
			t.getB().setPoints(newB);
			t.getC().setPoints(newC);

		}
	}

}
