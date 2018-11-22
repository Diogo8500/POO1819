/**
	 * This abstract class aims to be the superclass of all polygon type
	 * objects (like triangles, rectangles, etc.).<p>
	 * All classes looking to define a more concrete polygon should
	 * be a subclass of this class.
	 */
public abstract class Polygon {

	/**
	 * Returns the perimetre of this polygon as a double.
	 *
	 * @return  The perimetre of this polygon.
	 */
	public abstract double getPerimetre();

	/**
	 * Returns the area of this polygon as a double.
	 *
	 * @return  The area of this polygon.
	 */
	public abstract double getArea();
	
	/**
	 * Checks whether this polygon can be more specific. For example,
	 * checks if a parallelogram is a rectangle, etc.
	 * 
	 * @return True if yes, false if not.
	 */
	public abstract boolean isMoreSpecific();
	
	/**
	 * Returns a string containing the name of this Polygon.
	 * 
	 * @return Name of Polygon.
	 */
	@Override
	public abstract String toString();
}
