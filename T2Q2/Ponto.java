
public class Ponto {
	private double x, y;
	
	public Ponto(double _x, double _y) {
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
		x = _x;
	}
	
	public void setY(double _y) {
		y = _y;
	}
	
	public boolean equal(Ponto _z) {
		return ((x == _z.getX()) && (y == _z.getY()));
	}
}
