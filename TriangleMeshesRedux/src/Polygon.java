import java.util.ArrayList;
import java.util.List;

/**
	 * This abstract class aims to be the superclass of all polygon type
	 * objects (like triangles, rectangles, etc.).<p>
	 * All classes looking to define a more concrete polygon should
	 * be a subclass of this class.
	 */
public abstract class Polygon {
	
	private List<Point> vertexes;
	
	/**
	 * Define this polygon with a List of Points.
	 *
	 * @param _list List of Points.
	 */
	public void addPoints(List<Point> _list) {
		if(_list.isEmpty()) throw new IllegalStateException("List is empty.");
		vertexes = new ArrayList<Point>(_list);
	}
	
	/**
	 * Returns the vertex (Point) with index i.
	 *
	 * @return  The vertex at index i.
	 */
	public Point getVertex(int _i) {
		return vertexes.get(_i);
	}

	/**
	 * Returns the perimetre of this polygon as a double.
	 *
	 * @return  The perimetre of this polygon.
	 */
	public double getPerimetre() {
		double perimetre = 0;
		for(int i=0; i<vertexes.size(); i++) {
			perimetre += vertexes.get(i).getDist(vertexes.get((i+1)%vertexes.size()));
		}
		return perimetre;
	}

	/**
	 * Returns the area of this polygon as a double.
	 *
	 * @return  The area of this polygon.
	 */
	public abstract double getArea();
	
	/**
	 * Returns a string containing the name of this Polygon.
	 * 
	 * @return Name of Polygon.
	 */
	@Override
	public abstract String toString();
}
