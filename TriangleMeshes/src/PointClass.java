
public class PointClass implements Point {

	private Double x, y;
	
	public PointClass(double _x, double _y) {
		setX(_x);
		setY(_y);
	}
	
	@Override
	public int compareTo(Point _p) {
		if(x == _p.getX()) return y.compareTo(_p.getY());
		if(x < _p.getX()) return -1;
		return 1;
	}

	@Override
	public void setX(double _x) throws IllegalArgumentException{
		x = _x;
	}

	@Override
	public void setY(double _y) throws IllegalArgumentException {
		y = _y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getDist(Point _p) {
		return Math.hypot(x - _p.getX(), y - _p.getY());
	}
	
	@Override
	public double getCoTan(Point _p) {
		return - (_p.getX() - x) / (_p.getY() - y);
	}
	
	@Override
	public boolean isCollinear(Point _p1, Point _p2) {
		if(x * (_p1.getY() - _p2.getY()) + _p1.getX() * (_p2.getY() - y)  + _p2.getX() * (y - _p1.getY())==0)
			return true;
		return false;	
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";	
	}

}
