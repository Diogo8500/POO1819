import java.util.List;

/**
 * Subclass of Parallelogram to represent a 2D rhombus.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way both diagonals are perpendicular and of different
 * sizes.
 * 
 * @see Parallelogram
 */
public class Rhombus extends Parallelogram {

	/**
	 * @param _vertexes List of Points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         rhombus.
	 */
	public Rhombus(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isRhombus())
			throw new IllegalArgumentException("Is not a Rhombus.");
	}
	
	public Rhombus (String _s) {
		super(_s);
		if(!super.isRhombus())
			throw new IllegalArgumentException("Is not a Rhombus.");
	}
	
	@Override
	public String toString() {
		return "Rhombus";
	}
}
