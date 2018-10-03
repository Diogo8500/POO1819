public class Point {
	
	private double x, y;
	
	//Default contructor. X and Y = 0
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double _x, double _y) {
		x = _x;
		y = _y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distP(Point _a) {
		double xDif, yDif;
		xDif = x - _a.getX();
		yDif = y - _a.getY();
		
		return Math.sqrt(xDif*xDif + yDif*yDif);
	}
}
