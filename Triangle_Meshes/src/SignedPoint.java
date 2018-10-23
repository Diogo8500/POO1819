
public class SignedPoint implements Point {
	
	private double x, y;
	
	public SignedPoint(double _x, double _y) throws IllegalArgumentException {
		if(_x<0 || _y<0) throw new IllegalArgumentException("X or Y less than 0");
		x = _x;
		y = _y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double _x) {
		if(_x<0) throw new IllegalArgumentException("X less than 0");
		x = _x;
	}
	
	public void setY(double _y) {
		if(_y<0) throw new IllegalArgumentException("Y less than 0");
		y = _y;
	}

	public double dist(Point _a) {
		return Math.sqrt(Math.pow(_a.getX() - x, 2) + Math.pow(_a.getY() - y, 2));
	}

	public double distO() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public int compareTo(Point _a) {
		if(_a.getX() == x && _a.getY() == y) return 0;
		if(distO() <= _a.distO()) return -1;
		return 1;
	}
	
	public String toString() {
		return x + " " + y;
	}
}
