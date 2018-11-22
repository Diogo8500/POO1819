import java.util.List;

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
	 * @param _vertexes List of Points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         rectangle.
	 */
	public Rectangle(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isRectangle())
			throw new IllegalArgumentException("Is not a rectangle");
	}
	
	/**
	 * Checks whether this rectangle is a square.
	 * @return True if it is, false is its not.
	 */
	public boolean isSquare() {
		if(getVertex(0).getDist(getVertex(1)) == getVertex(1).getDist(getVertex(2))) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Rectangle";
	}

}
