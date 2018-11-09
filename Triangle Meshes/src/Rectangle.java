/**
 * Subclass of Parallelogram to represent a 2D rectangle.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way opposite sides are parallel and all vertex angles
 * are 90 degrees.
 * @see Parallelogram
 */
public class Rectangle extends Parallelogram {

	/**
	 * Rectangle constructor.
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         rectangle.
	 */
	public Rectangle(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!((_b.getX() - _a.getX()) * (_b.getX() - _c.getX()) + (_b.getY() - _a.getY()) * (_b.getY() - _c.getY()) == 0 && (_d.getX() - _c.getX()) * (_d.getX() - _a.getX()) + (_d.getY() - _c.getY()) * (_d.getY() - _a.getY()) == 0))
			throw new IllegalArgumentException("Is not a rectangle");
	}
	
	@Override
	public String toString() {
		return "Rectangle";
	}

}
