import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
	 * This class aims to represent a basic 2D triangle. A triangle is
	 * defined by 3 vertexes. It has methods to
	 * calculate its area and perimetre and the length of its sides.
	 * <p>
	 * It's also possible to get each vertex independently as Point
	 * objects.
	 * <p>
	 * This class is a subclass of Polygon.
	 * 
	 * @see Polygon
	 */
public class Triangle extends Polygon {

	/**
	 * Creates a new TriangleClass object with the specified vertexes.
	 *
	 * @param   _vertexes List of Points
	 * @throws  IllegalArgumentException if the 3 vertexes do not form a triangle.
	 */
	public Triangle(List<Point> _vertexes) {
		if(_vertexes.get(0).isCcw(_vertexes.get(1), _vertexes.get(2)) == 0)
			throw new IllegalArgumentException("Vertexes are collinear.");
		addPoints(_vertexes);
	}
	
	public Triangle (String _s) {
		String[] split = _s.split(" ");
		Point a = new Point(Double.parseDouble(split[1]), Double.parseDouble(split[2]));
		Point b = new Point(Double.parseDouble(split[3]), Double.parseDouble(split[4]));
		Point c = new Point(Double.parseDouble(split[5]), Double.parseDouble(split[6]));
		List<Point> points = new ArrayList<Point>();
		Collections.addAll(points, a,b,c);
		if(points.get(0).isCcw(points.get(1), points.get(2)) == 0)
			throw new IllegalArgumentException("Vertexes are collinear.");
		addPoints(points);
	}
	
	@Override
	public double getArea() {
		return Math.abs(getVertex(0).isCcw(getVertex(1), getVertex(2))/2d);
	}
	
	/**
	 * Checks whether this triangle is equilateral.
	 * @return True if it is, false is its not.
	 */
	public boolean isEquilateral() {
		if(getVertex(0).getDist(getVertex(1)) == getVertex(1).getDist(getVertex(2)) && getVertex(1).getDist(getVertex(2)) == getVertex(2).getDist(getVertex(0)))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Triangle";
	}
}
