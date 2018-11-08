/**
	 * This interface aims to define the basic methods of a polygon
	 * like the perimetre and the area.
	 */
public interface Polygon {
	
	/**
	 * Returns the perimetre of this polygon as a double.
	 *
	 * @return  The perimetre of this polygon.
	 */
	public double getPerimetre();
	
	/**
	 * Returns the area of this polygon as a double.
	 *
	 * @return  The area of this polygon.
	 */
	public double getArea();
	
	@Override
	public String toString();
}
