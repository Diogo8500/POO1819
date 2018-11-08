/**
	 * This abstract class aims to be the superclass of all polygon type
	 * objects (like triangles, rectangles, etc.).<p>
	 * All classes looking to define a more concrete polygon should
	 * be a subclass of this class.
	 */
public abstract class PolygonClass implements Polygon {

	@Override
	public abstract double getPerimetre();

	@Override
	public abstract double getArea();
	
	@Override
	public abstract String toString();
	
}
