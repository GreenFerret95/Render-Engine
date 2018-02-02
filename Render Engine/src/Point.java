public class Point {
	private double x;
	private double y;
	private double z;
	private double r;
	private double theta;

	

	public Point(double x, double y, double z) {
		
		this.x = x; 
		this.y = y;
		this.z = z;

		this.r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

		this.theta = Math.atan2(y, x);

	}

	public Point(double r, double theta) {
		
		this.r = r;
		this.theta = Math.toRadians(theta);

		this.x = (r * Math.cos(theta));
		this.y = (r * Math.sin(theta));
		this.z = 100;

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public void setPoints(double[] points) {
		this.x = points[0];
		this.y = points[1];
		this.z = points[2];
	}

}