import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Stack;

/**
 * This class purpose is to identify the Polygon shaped
 * by the outer vertices of a list (cloud) of Points.
 *
 */
public class ShapeSolver {
	private List<Point> cloud;
	private Stack<Point> hull;
	
	/**
	 * Constructor receives a list of Points.
	 * @param _cloud List of Points
	 * @throws IllegalStateException if there are less than 3 distinct Points in the list.
	 */
	public ShapeSolver(List<Point> _cloud) {
		setCloud(_cloud);
	}
	
	/**
	 * Sets a new list of points to be identified.
	 * @param _cloud List of Points
	 * @throws IllegalStateException if there are less than 3 distinct Points in the list or outer vertices.
	 */
	public void setCloud(List<Point> _cloud) {
		cloud = new ArrayList<Point>(_cloud);
		cleanCloud();
		if(cloud.size() < 3) throw new IllegalStateException("Less than 3 distinct points. (cloud)");
		hull = new Stack<Point>();
		calculateHull();
		cleanHull();
		if(hull.size() < 3) throw new IllegalStateException("Less than 3 distinct points. (hull)");
	}
	
	/**
	 * Returns the shape formed by the set of Points used to create this ShapeSolver
	 * or set by setCloud().
	 * @return The Polygon formed by the set of Points.
	 */
	public Polygon getShape() {
		Polygon shape;
		if(hull.size() == 3) {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			
			try {
				shape = new EquilateralTriangle(a, b, c);
			}catch(IllegalArgumentException _e) {
				shape = new Triangle(a, b, c);
			}
		}else {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			Point d = hull.get(3);
			
			try {
				shape = new Square(a, b, c, d);
			}catch(IllegalArgumentException _e) {
				try {
					shape = new Rectangle(a, b, c, d);
				}catch(IllegalArgumentException _f) {
					try {
						shape = new Parallelogram(a, b, c, d);
					}catch(IllegalArgumentException _g) {
						shape = new Quadrilateral(a, b, c, d);
					}
				}
			}
		}
		return shape;
	}
	
	/* Removes duplicate Points from the cloud if there are any using a property
	 * of a TreeSet class where there cant be any duplicate elements.
	 * We didnt use a HashSet since this class uses the equal() method to check for
	 * equality, while TreeSet uses compareTo() which we overrode in our class Point*/
	private void cleanCloud() {
		Set<Point> newCloud = new TreeSet<Point>();
		newCloud.addAll(cloud);
		cloud.clear();
		cloud.addAll(newCloud);
	}
	
	/* Calculates the outer Points of the cloud and stores them on hull*/
	private void calculateHull(){
		
		Collections.sort(cloud, (_a, _b) -> _a.getY() < _b.getY() ? -1 : _a.getY() == _b.getY() ? 0 : 1);
		Collections.sort(cloud, (_a, _b) -> cloud.get(0).getCoTan(_a) < cloud.get(0).getCoTan(_b) ? -1 : cloud.get(0).getCoTan(_a) == cloud.get(0).getCoTan(_b) ? 0 : 1);

		hull.push(cloud.get(0));
		hull.push(cloud.get(1));
		hull.push(cloud.get(2));
		
		for (int i=3; i<cloud.size(); i++) {
			while(ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), cloud.get(i)) < 0) {
				hull.pop();
			}
			hull.push(cloud.get(i));
		}
	}
	
	/* Removes the Points of the hull that are not vertices (aka are collinear)*/
	private void cleanHull() {
		for(int i=2; i<=hull.size(); i++) {
			if(hull.get(i%hull.size()).isCollinear(hull.get(i - 1), hull.get(i - 2))) {
				hull.remove(i - 1);
				i--;
			}
		}
	}
	
	/* Returns <0 if the Points are oriented clockwise
	 * Returns 0 if they are collinear
	 * Returns >0 if they are oriented countercockwise*/
	private double ccw(Point _a, Point _b, Point _c) {
		return (_b.getX() - _a.getX())*(_c.getY() - _a.getY()) - (_b.getY() - _a.getY())*(_c.getX() - _a.getX());
	}
}
