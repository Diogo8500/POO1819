
public interface Triangle {

	Point getPointA();

	Point getPointB();

	Point getPointC();

	/**
	 * Tests if a 2D point lies inside this 2D triangle.
	 * 
	 * @param point
	 *            The point to be tested
	 * @return Returns true iff the point lies inside this 2D triangle
	 */
	boolean contains(Point point);

	/**
	 * Tests if a given point lies in the circumcircle of this triangle. Let the
	 * triangle ABC appear in counterclockwise (CCW) order. Then when det >
	 * 0, the point lies inside the circumcircle through the three points a, b
	 * and c. If instead det < 0, the point lies outside the circumcircle.
	 * When det = 0, the four points are cocircular. If the triangle is oriented
	 * clockwise (CW) the result is reversed.
	 * 
	 * @param point
	 *            The point to be tested
	 * @return Returns true iff the point lies inside the circumcircle through
	 *         the three points a, b, and c of the triangle
	 */
	boolean isPointInCircumcircle(Point point);

	/**
	 * Test if this triangle is oriented counterclockwise (CCW). Let A, B and C
	 * be three 2D points. If det > 0, C lies to the left of the directed
	 * line AB. Equivalently the triangle ABC is oriented counterclockwise. When
	 * det < 0, C lies to the right of the directed line AB, and the triangle
	 * ABC is oriented clockwise. When det = 0, the three points are colinear.
	 * 
	 * @return Returns true iff the triangle ABC is oriented counterclockwise
	 *         (CCW)
	 */
	boolean isOrientedCCW();

	/**
	 * Returns true if this triangle contains the given edge.
	 * 
	 * @param edge
	 *            The edge to be tested
	 * @return Returns true if this triangle contains the edge
	 */
	boolean isNeighbour(Segment edge);

	/**
	 * Returns the vertex of this triangle that is not part of the given edge.
	 * 
	 * @param edge
	 *            The edge
	 * @return The vertex of this triangle that is not part of the edge
	 */
	Point getNoneEdgeVertex(Segment edge);

	/**
	 * Returns true if the given vertex is one of the vertices describing this
	 * triangle.
	 * 
	 * @param vertex
	 *            The vertex to be tested
	 * @return Returns true if the Vertex is one of the vertices describing this
	 *         triangle
	 */
	boolean hasVertex(Point vertex);

	/**
	 * Returns an EdgeDistancePack containing the edge and its distance nearest
	 * to the specified point.
	 * 
	 * @param point
	 *            The point the nearest edge is queried for
	 * @return The edge of this triangle that is nearest to the specified point
	 */
	SegmentDistance findNearestEdge(Point point);

}