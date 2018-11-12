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
	 *  @param _a The A vertex.
	 *  @param _b The B vertex.
	 *  @param _c The C vertex.
	 *  @throws IllegalArgumentException if the 3 vertexes do not form an
	 *  equilateral triangle.
	 */
	public EquilateralTriangle(Point _a, Point _b, Point _c) {
		super(_a, _b, _c);
		if(!super.isMoreSpecific())
			throw new IllegalArgumentException("Triangle is not equilateral");	
	}

	@Override
	public String toString() {
		return "Equilateral " + super.toString();
	}
}
