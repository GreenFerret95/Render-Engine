import java.awt.Color;

public class Triangle {
	private Point a;
	private Point b;
	private Point c;
	private Color color;
	private double sideA, sideB, sideC; // side BC,AC,AB
	private double thetaA, thetaB, thetaC;

	private double slopeAB, slopeAC, slopeBC; // DeltaY/DeltaX of side AB,DeltaY/DeltaX of side AC,DeltaY/DeltaX of side BC

	private double ABYIntercept, ACYIntercept, BCYIntercept;

	private double minX, minY, maxX, maxY;

	public Triangle(Point a, Point b, Point c, Color color) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;

		// the distance between point B and point C
		sideA = pointDistance(b.getX(), b.getY(), c.getX(), c.getY());
		// the distnace between point A and point C
		sideB = pointDistance(a.getX(), a.getY(), c.getX(), c.getY());
		// the distance between point A and point B
		sideC = pointDistance(a.getX(), a.getY(), b.getX(), b.getY());

		// the angle Point A,B,C makes with the origin 0,0
		thetaA = getAngleA(sideA, sideB, sideC);
		thetaB = getAngleB(sideA, sideB, sideC);
		thetaC = getAngleC(sideA, sideB, sideC);

		// The slope of the lines AB, AC, and BC
		slopeAB = updateSlope(a.getX(), a.getY(), b.getX(), b.getY());
		slopeAC = updateSlope(a.getX(), a.getY(), c.getX(), c.getY());
		slopeBC = updateSlope(b.getX(), b.getY(), c.getX(), c.getY());

		ABYIntercept = getYIntercept(a.getX(), a.getY(), slopeAB);
		ACYIntercept = getYIntercept(c.getX(), c.getY(), slopeAC);
		BCYIntercept = getYIntercept(b.getX(), b.getY(), slopeBC);
		
		

	}

	public void step() {
		slopeAB = updateSlope(a.getX(), a.getY(), b.getX(), b.getY());
		slopeAC = updateSlope(a.getX(), a.getY(), c.getX(), c.getY());
		slopeBC = updateSlope(b.getX(), b.getY(), c.getX(), c.getY());

		ABYIntercept = getYIntercept(a.getX(), a.getY(), slopeAB);

		ACYIntercept = getYIntercept(c.getX(), c.getY(), slopeAC);

		BCYIntercept = getYIntercept(b.getX(), b.getY(), slopeBC);
		
		minX = getMinX(a.getX(),b.getX(),c.getX());
		minY = getMinY(a.getY(),b.getY(),c.getY());
		maxX = getMaxX(a.getX(),b.getX(),c.getX());
		maxY = getMaxY(a.getY(),b.getY(),c.getY());


	}
	
	/*
	 * Returns minimum value from 3 x values
	 */
	public double getMinX(double aX, double bX, double cX){
		double min = Math.min(aX,bX);
		min = Math.min(min, cX);
		return min;
	}
	/*
	 * returns minimum value from 3 y values
	 */
	public double getMinY(double aY, double bY, double cY){
		double min = Math.min(aY,bY);
		min = Math.min(min, cY);
		return min;
	}
	
	/*
	 * returns maximum value from 3 x values
	 */
	public double getMaxX(double aX, double bX, double cX){
		double max = Math.max(aX,bX);
		max = Math.max(max, cX);
		return max;
	}
	
	/*
	 * returns the maximum value from 3 y values
	 */
	public double getMaxY(double aY, double bY, double cY){
		double max = Math.max(aY,bY);
		max = Math.max(max, cY);
		return max;
	}
	
	

	/*
	 * @param Px, x coord of Point-a
	 * 
	 * @param Py, y coord of point-a
	 * 
	 * @param Pm, slope between Point-a and Point-b
	 * 
	 * @return where the line segment crosses the the y axis, aka x = 0
	 */
	public double getYIntercept(double Px, double Py, double Pm) {
		return Py - (Pm * Px);
	}

	public double updateSlope(double x1, double y1, double x2, double y2) {
		return (y2 - y1) / (x2 - x1);
	}

	private double pointDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	// using law of cosines a^2 = b^2 + c^2 - 2bc cos(A)
	// solved for A = cos^-1 (-a^2/2bc +b/2c + c/2b
	// to return angle A in radians
	private double getAngleA(double a, double b, double c) {
		// -a^2 / 2bc
		double constantA = (-1 * (Math.pow(a, 2))) / (2 * b * c);
		// b/2c
		double constantB = (b) / (2 * c);
		// c/2b
		double constantC = (c) / (2 * b);
		return Math.acos(constantA + constantB + constantC);
	}

	// returns angleB in raidans using law of cosines
	private double getAngleB(double a, double b, double c) {
		// -a^2 / 2bc
		double constantA = (-1 * (Math.pow(b, 2))) / (2 * a * c);
		// b/2c
		double constantB = (a) / (2 * c);
		// c/2b
		double constantC = (c) / (2 * a);
		return Math.acos(constantA + constantB + constantC);
	}

	private double getAngleC(double a, double b, double c) {
		// -a^2 / 2bc
		double constantA = (-1 * (Math.pow(c, 2))) / (2 * b * a);
		// b/2c
		double constantB = (b) / (2 * a);
		// c/2b
		double constantC = (a) / (2 * b);
		return Math.acos(constantA + constantB + constantC);
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Point getC() {
		return c;
	}

	public void setC(Point c) {
		this.c = c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getSideA() {
		return sideA;
	}

	public void setSideA(double sideA) {
		this.sideA = sideA;
	}

	public double getSideB() {
		return sideB;
	}

	public void setSideB(double sideB) {
		this.sideB = sideB;
	}

	public double getSideC() {
		return sideC;
	}

	public void setSideC(double sideC) {
		this.sideC = sideC;
	}

	public double getThetaA() {
		return thetaA;
	}

	public void setThetaA(double thetaA) {
		this.thetaA = thetaA;
	}

	public double getThetaB() {
		return thetaB;
	}

	public void setThetaB(double thetaB) {
		this.thetaB = thetaB;
	}

	public double getThetaC() {
		return thetaC;
	}

	public void setThetaC(double thetaC) {
		this.thetaC = thetaC;
	}

	public double getSlopeAB() {
		return slopeAB;
	}

	public void setSlopeAB(double slopeAB) {
		this.slopeAB = slopeAB;
	}

	public double getSlopeAC() {
		return slopeAC;
	}

	public void setSlopeAC(double slopeAC) {
		this.slopeAC = slopeAC;
	}

	public double getSlopeBC() {
		return slopeBC;
	}

	public void setSlopeBC(double slopeBC) {
		this.slopeBC = slopeBC;
	}

	public double getABYIntercept() {
		return ABYIntercept;
	}

	public void setABYIntercept(double aBYIntercept) {
		ABYIntercept = aBYIntercept;
	}

	public double getACYIntercept() {
		return ACYIntercept;
	}

	public void setACYIntercept(double aCYIntercept) {
		ACYIntercept = aCYIntercept;
	}

	public double getBCYIntercept() {
		return BCYIntercept;
	}

	public void setBCYIntercept(double bCYIntercept) {
		BCYIntercept = bCYIntercept;
	}

}