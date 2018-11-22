import java.util.List;

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
	 * @param _vertexes List of points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         square.
	 */
	public Square(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isSquare())
			throw new IllegalArgumentException("Is not a square.");
	}
	
	public String toString() {
		return "Square";
	}

}
