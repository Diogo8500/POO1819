/**
 * Subclass of Quadrilateral to represent a 2D parallelogram.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way opposite sides are parallel.
 * 
 * @see Quadrilateral
 */
public class Parallelogram extends Quadrilateral {

	/**
	 * Parallelogram constructor.
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         parallelogram.
	 */
	public Parallelogram(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!(_b.getX() - _a.getX() == _c.getX() - _d.getX() && _b.getY() - _a.getY() == _c.getY() - _d.getY()))
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	@Override
	public String toString() {
		return "Parallelogram";
	}

}
