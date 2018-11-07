
public class PointClass {

	private Double x, y;
	
	public PointClass(double _x, double _y) {
		setX(_x);
		setY(_y);
	}
	
	public int compareTo(PointClass _p) {
		if(x == _p.getX()) return y.compareTo(_p.getY());
		if(x < _p.getX()) return -1;
		return 1;
	}

	public void setX(double _x) {
		x = _x;
	}

	public void setY(double _y) {
		y = _y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDist(PointClass _p) {
		return Math.hypot(x - _p.getX(), y - _p.getY());
	}
	
	public double getCoTan(PointClass _p) {
		return - (_p.getX() - x) / (_p.getY() - y);
	}
	
	public boolean isCollinear(PointClass _p1, PointClass _p2) {
		if(x * (_p1.getY() - _p2.getY()) + _p1.getX() * (_p2.getY() - y)  + _p2.getX() * (y - _p1.getY()) == 0)
			return true;
		return false;	
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";	
	}

}
