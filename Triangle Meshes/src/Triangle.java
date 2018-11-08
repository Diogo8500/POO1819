/**
	 * This class aims to represent a basic 2D triangle. A triangle is
	 * defined by 3 vertexes. It has methods to
	 * calculate its area and perimetre and the length of its sides.
	 * <p>
	 * It's also possible to get each vertex independently as Point
	 * objects.
	 * <p>
	 * This class is a subclass of PolygonClass.
	 * 
	 * @see PolygonClass
	 */
public class Triangle extends PolygonClass {
	
	private Point a, b, c;

	/**
	 * Creates a new TriangleClass object with the specified vertexes.
	 *
	 * @param   _a The first vertex.
	 * @param   _b the second vertex.
	 * @param   _c the third vertex.
	 * @throws  IllegalArgumentException if the 3 vertexes do not form a triangle.
	 * * @return  A new TriangleClass object.
	 */
	public Triangle(Point _a, Point _b, Point _c) {
		if(_a.isCollinear(_b, _c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		a = _a;
		b = _b;
		c = _c;
	}
	
	/**
	 * Sets the a new A vertex.
	 *
	 * @param   _a The new A vertex.
	 * @throws  IllegalArgumentException if the new vertex modifies the shape to
	 *          not form a triangle.
	 */
	public void setVertexA(Point _a) {
		if(_a.isCollinear(b, c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		a = _a;
	}

	/**
	 * Sets the a new B vertex.
	 *
	 * @param   _b The new B vertex.
	 * @throws  IllegalArgumentException if the new vertex modifies the shape to
	 *          not form a triangle.
	 */
	public void setVertexB(Point _b) {
		if(_b.isCollinear(a, c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		b = _b;
	}

	/**
	 * Sets the a new C vertex.
	 *
	 * @param   _c The new C vertex.
	 * @throws  IllegalArgumentException if the new vertex modifies the shape to
	 *          not form a triangle.
	 */
	public void setVertexC(Point _c) {
		if(_c.isCollinear(a, b))
			throw new IllegalArgumentException("Vertexes are collinear.");
		c = _c;
	}

	/**
	 * Returns the A vertex of this triangle.
	 *
	 * @return  The A vertex.
	 */
	public Point getVertexA() {
		return a;
	}

	/**
	 * Returns the B vertex of this triangle.
	 *
	 * @return  The B vertex.
	 */
	public Point getVertexB() {
		return b;
	}

	/**
	 * Returns the C vertex of this triangle.
	 *
	 * @return  The C vertex.
	 */
	public Point getVertexC() {
		return c;
	}
	
	/**
	 * Returns the length of the side AB of this triangle.
	 * In other words, returns the distance between vertex A and B.
	 *
	 * @return  The lenght of AB.
	 */
	public double getLengthAB() {
		return getVertexA().getDist(getVertexB());
	}
	
	/**
	 * Returns the length of the side BC of this triangle.
	 * In other words, returns the distance between vertex B and C.
	 *
	 * @return  The lenght of BC.
	 */
	public double getLengthBC() {
		return getVertexB().getDist(getVertexC());
	}
	
	/**
	 * Returns the length of the side CA of this triangle.
	 * In other words, returns the distance between vertex C and A.
	 *
	 * @return  The lenght of CA.
	 */
	public double getLengthCA() {
		return getVertexC().getDist(getVertexA());
	}

	@Override
	public double getPerimetre() {
		return getLengthAB() + getLengthBC() + getLengthCA();
	}
	
	@Override
	public double getArea() {
		double parc1 = a.getX() * (b.getY() - c.getY());
		double parc2 = b.getX() * (c.getY() - a.getY());
		double parc3 = c.getX() * (a.getY() - b.getY());
		return Math.abs((parc1 + parc2 + parc3)/2d);
	}
	
	@Override
	public String toString() {
		return "Triangle";
	}
}
