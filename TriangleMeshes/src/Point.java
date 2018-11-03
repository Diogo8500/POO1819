
public interface Point extends Comparable<Point> {
	public void setX(double _x) throws IllegalArgumentException;
	
	public void setY(double _y) throws IllegalArgumentException;
	
	public double getX();
	
	public double getY();
	
	public double getDist(Point _p);
	
	public double getCoTan(Point _p);
	
	public boolean isCollinear(Point _p1, Point _p2);
}
