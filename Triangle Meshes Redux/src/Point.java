/**
 * This class aims to implement a simple Cartesian 2D point
 * with basic methods that allow simple point math like distance
 * between 2 points and checking if 2 points are collinear.
 * <p>
 * Coordinates can be of double precision and negative.
 */
public class Point implements Comparable<Point> {

	private double x, y;
	
	/**
	 * Creates a new PointClass object. 
	 *
	 * @param  _x  The x coordinate of this point.
	 * @param  _y  the y coordinate of this point.
	 */
	public Point(double _x, double _y) {
		setX(_x);
		setY(_y);
	}

	/**
	 * Modifies the x coordinate of this Point. 
	 *
	 * @param  _x  The new x coordinate of this point.
	 */
	public void setX(double _x) {
		x = _x;
	}

	/**
	 * Modifies the y coordinate of this Point. 
	 *
	 * @param  _y  The new y coordinate of this point.
	 */
	public void setY(double _y) {
		y = _y;
	}

	/**
	 * Returns the x coordinate of this Point as a double. 
	 *
	 * @return  The x coordinate of this point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of this Point as a double. 
	 *
	 * @return  The y coordinate of this point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns the distance between this and some other Point as a double. 
	 *
	 * @param   _p The point which we want to calculate the distance to.
	 * @return  The distance between this and _p.
	 */
	public double getDist(Point _p) {
		return Math.hypot(x - _p.getX(), y - _p.getY());
	}
	
	/**
	 * Returns the CoTangent between this and some other point. 
	 *
	 * @param   _p The point which we want to calculate the CoTangent to.
	 * @return  The CoTangent between this and _p.
	 */
	public double getCoTan(Point _p) {
		return (_p.getX() - x)/(_p.getY() - y);
	}
	
	/**
	 * Checks if the reference point is collinear with 2 others by calculating
	 * the determinant, which happens to be the double of the signed area
	 * formed by the 3 points.
	 *
	 * @param   _p1 The first point.
	 * @param   _p2 the second point
	 * @return  less than 0 if the Points are oriented clockwise.
	 * 			0 if they are collinear.
	 * 			greater than 0 if they are oriented counterclockwise
	 */
	public double isCcw(Point _p1, Point _p2) {
		return (_p1.getX() - x)*(_p2.getY() - y) - (_p1.getY() - y)*(_p2.getX() - x);
	}
	
	/**
	 * Checks the reference point with another for order.
	 * This method overrides the method with the same name 
	 * in Comparable. <p>
	 * It defines the natural order of a point as follows:
	 * Points with greater Y coordinates are bigger. If the Y coordinate
	 * is equal, points with the greater X coordinate are bigger.
	 * If both X and Y coordinates are equal the point is equal.
	 *
	 * @param   _p The point to be compared.
	 * @return  0 if the points are equal.<p>
	 *          1 if the reference point is bigger.<p>
	 *          -1 if the reference point is smaller.
	 */
	@Override
	public int compareTo(Point _p) {
		if(y == _p.getY()) return ((Double)x).compareTo(_p.getX());
		if(y < _p.getY()) return -1;
		return 1;
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";	
	}
}
