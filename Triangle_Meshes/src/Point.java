
public interface Point extends Comparable<Point> {
	
	public double getX();
	
	public double getY();
	
	public void setX(double _x);
	
	public void setY(double _y);
	
	public double dist(Point _a);
	
	public double distO();
	
	public int compareTo(Point _a);
	
	public String toString();
	
}
