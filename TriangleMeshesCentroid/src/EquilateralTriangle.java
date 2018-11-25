import java.util.List;

/**
	 * This class aims to represent a basic 2D equilateral triangle.
	 * This is a more specific triangle where all sides must be of equal
	 * length.
	 * <p>
	 * This class is a subclass of Triangle.
	 * 
	 *  @see Triangle
	 */
public class EquilateralTriangle extends Triangle {

	/**
	 * Creates a new EquilateralTriangleClass object.
	 * 
	 *  @param _vertexes List of Points
	 *  @throws IllegalArgumentException if the 3 vertexes do not form an
	 *  equilateral triangle.
	 */
	public EquilateralTriangle(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isEquilateral())
			throw new IllegalArgumentException("Triangle is not equilateral");	
	}
	
	public EquilateralTriangle(String _s) {
		super(_s);
		if(!super.isEquilateral())
			throw new IllegalArgumentException("Triangle is not equilateral");
	}

	@Override
	public String toString() {
		return "Equilateral " + super.toString();
	}
}
