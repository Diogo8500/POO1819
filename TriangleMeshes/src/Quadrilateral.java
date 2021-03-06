/**
 * Subclass of Polygon to represent a 2D convex quadrilateral.
 * It is defined by a set of four distinct vertexes.
 * 
 * @see Polygon
 */
public class Quadrilateral extends Polygon {

	private Point a, b, c, d;
	
	/**
	 * Quadrilateral constructor. Arguments must be given either counterclockwise
	 * or clockwise, otherwise an {@link IllegalArgumentException} is thrown
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         quadrilateral.
	 */
	public Quadrilateral(Point _a, Point _b, Point _c, Point _d) {
		double angle1 = _a.isCcw(_b, _c);
		double angle2 = _b.isCcw(_c, _d);
		double angle3 = _c.isCcw(_d, _a);
		double angle4 = _d.isCcw(_a, _b);
		if(angle1 == 0 || angle2 == 0 || angle3 == 0 || angle4 == 0 || 
		   !(Math.signum(angle1) == Math.signum(angle2) && Math.signum(angle2) == Math.signum(angle3) && Math.signum(angle3) == Math.signum(angle4)))
			throw new IllegalArgumentException("Points do not define a quadrilateral.");
		a = _a;
		b = _b;
		c = _c;
		d = _d;
	}
	
	/**
	 * Sets a new vertex A
	 * @param _a the new vertex to replace A
	 * @throws IllegalArgumentException if the new vertex defines a non
	 *         quadrilateral.
	 */
	public void setVertexA(Point _a) {
		if(_a.isCcw(b, c) == 0 || _a.isCcw(c, d) == 0)
			throw new IllegalArgumentException("Points are collinear.");
		a = _a;
	}

	/**
	 * Sets a new vertex B
	 * @param _b the new vertex to replace B
	 * @throws IllegalArgumentException if the new vertex defines a non
	 *         quadrilateral.
	 */
	public void setVertexB(Point _b) {
		if(_b.isCcw(c, d) == 0 || _b.isCcw(d, a) == 0)
			throw new IllegalArgumentException("Points are collinear.");
		b = _b;
	}

	/**
	 * Sets a new vertex C
	 * @param _c the new vertex to replace C
	 * @throws IllegalArgumentException if the new vertex defines a non
	 *         quadrilateral.
	 */
	public void setVertexC(Point _c) {
		if(_c.isCcw(d, a) == 0 || _c.isCcw(a, b) == 0)
			throw new IllegalArgumentException("Points are collinear.");
		c = _c;
	}

	/**
	 * Sets a new vertex D
	 * @param _d the new vertex to replace D
	 * @throws IllegalArgumentException if the new vertex defines a non
	 *         quadrilateral.
	 */
	public void setVertexD(Point _d) {
		if(_d.isCcw(a, b) == 0 || _d.isCcw(b, c) == 0)
			throw new IllegalArgumentException("Points are collinear.");
		d = _d;
	}

	/**
	 * Returns the vertex A as a Point.
	 * @return Vertex A
	 */
	public Point getVertexA() {
		return a;
	}

	/**
	 * Returns the vertex B as a Point.
	 * @return Vertex B
	 */
	public Point getVertexB() {
		return b;
	}

	/**
	 * Returns the vertex C as a Point.
	 * @return Vertex C
	 */
	public Point getVertexC() {
		return c;
	}

	/**
	 * Returns the vertex D as a Point.
	 * @return Vertex D
	 */
	public Point getVertexD() {
		return d;
	}

	/**
	 * Returns the length of the side defined by vertex A and B. In
	 * other words, returns the distance between vertex A and B.
	 * @return Length of AB side.
	 */
	public double getLengthAB() {
		return a.getDist(b);
	}

	/**
	 * Returns the length of the side defined by vertex B and C. In
	 * other words, returns the distance between vertex B and C.
	 * @return Length of BC side.
	 */
	public double getLengthBC() {
		return b.getDist(c);
	}

	/**
	 * Returns the length of the side defined by vertex C and D. In
	 * other words, returns the distance between vertex C and D.
	 * @return Length of CD side.
	 */
	public double getLengthCD() {
		return c.getDist(d);
	}

	/**
	 * Returns the length of the side defined by vertex D and A. In
	 * other words, returns the distance between vertex D and A.
	 * @return Length of DA side.
	 */
	public double getLengthDA() {
		return d.getDist(a);
	}
	
	@Override
	public double getPerimetre() {
		return getLengthAB() + getLengthBC() + getLengthCD() + getLengthDA();
	}
	
	@Override
	public double getArea() {
		Point vectorA = new Point(b.getX() - a.getX(), b.getY() - a.getY());
		Point vectorB = new Point(c.getX() - b.getX(), c.getY() - b.getY());
		Point vectorC = new Point(d.getX() - c.getX(), d.getY() - c.getY());
		
		Point vectorP = new Point(vectorB.getX() + vectorC.getX(), vectorB.getY() + vectorC.getY());
		Point vectorQ = new Point(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
		
		double crossPQ = vectorP.getX() * vectorQ.getY() - vectorP.getY() * vectorQ.getX();
		return (1d/2d) * Math.abs(crossPQ);
	}
	
	/**
	 * Checks whether this quadrilateral is a parallelogram.
	 * @return True if it is, false is its not.
	 */
	@Override
	public boolean isMoreSpecific() {
		if(b.getX() - a.getX() == c.getX() - d.getX() && b.getY() - a.getY() == c.getY() - d.getY()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Quadrilateral";
	}
}
