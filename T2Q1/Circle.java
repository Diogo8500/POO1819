
public class Circle {
	private Point centre;
	private double radious;
	
	public Circle() {
		centre = new Point();
		radious = -1;
	}
	
	public Circle(double _r) {
		centre = new Point();
		if (_r <= 0) {
			_r = -_r;
			System.out.println("Invalid value for radious (can't be negative). New radious = " + _r);
		}
		radious = _r;
	}
	
	public Circle(Point _c, double _r) {
		centre = _c;
		if (_r <= 0) {
			_r = -_r;
			System.out.println("Invalid value for radious (can't be negative). New radious = " + _r);
		}
		radious = _r;
	}
	
	public Circle(double _x, double _y, double _r) {
		centre = new Point(_x, _y);
		if (_r <= 0) {
			_r = -_r;
			System.out.println("Invalid value for radious (can't be negative). New radious = " + _r);
		}
		radious = _r;
	}
	
	public void setR(double _r) {
		if (_r <= 0) {
			_r = -_r;
			System.out.println("Invalid value for radious (can't be negative). New radious = " + _r);
		}
		radious = _r;
	}
	
	public Point getC() {
		return centre;
	}
	
	public double getR() {
		return radious;
	}
	
	public double getP() {
		return 2 * Math.PI * radious;
	}
	
	public double getA() {
		return Math.PI * radious * radious;
	}
}
