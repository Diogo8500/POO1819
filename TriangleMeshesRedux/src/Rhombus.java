import java.util.List;

public class Rhombus extends Parallelogram {

	/**
	 * 
	 * @param _vertexes List of Points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         rhombus.
	 */
	public Rhombus(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isRhombus())
			throw new IllegalArgumentException("Is not a rectangle");
	}
	
	@Override
	public String toString() {
		return "Rhombus";
	}
}
