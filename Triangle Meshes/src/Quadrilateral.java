/**
 * Subclass of Polygon to represent a 2D convex quadrilateral.
 * It is defined by a set of four distinct vertexes.
 * 
 * @see PolygonClass
 */
public class Quadrilateral extends PolygonClass {

	private Point a, b, c, d;
	
	/**
	 * Quadrilateral constructor.
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         quadrilateral.
	 */
	public Quadrilateral(Point _a, Point _b, Point _c, Point _d) {
		if(_a.isCollinear(_b, _c) || _a.isCollinear(_c, _d))
			throw new IllegalArgumentException("Points are collinear.");
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
		if(_a.isCollinear(b, c) || _a.isCollinear(c, d))
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
		if(_b.isCollinear(c, d) || _b.isCollinear(d, a))
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
		if(_c.isCollinear(d, a) || _c.isCollinear(a, b))
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
		if(_d.isCollinear(a, b) || _d.isCollinear(b, c))
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
	
	@Override
	public String toString() {
		return "Quadrilateral";
	}
}
