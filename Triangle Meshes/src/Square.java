/**
 * Subclass of Rectangle to represent a 2D square.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way all sides are of equal length and perpendicular to 
 * each adjacent side.
 * @see Quadrilateral
 */
public class Square extends Rectangle {

	/**
	 * Square constructor.
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         square.
	 */
	public Square(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!super.isMoreSpecific())
			throw new IllegalArgumentException("Is not a square.");
	}
	
	public String toString() {
		return "Square";
	}

}
