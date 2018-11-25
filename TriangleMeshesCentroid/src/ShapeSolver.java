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
			
			shape = new Triangle(hull);
			if(((Triangle)shape).isEquilateral())
				shape = new EquilateralTriangle(hull);
		}else {
			
			shape = new Quadrilateral(hull);
			if(((Quadrilateral)shape).isParallelogram()) {
				shape = new Parallelogram(hull);
				if(((Parallelogram)shape).isRhombus()) 
					shape = new Rhombus(hull);
				else if(((Parallelogram)shape).isRectangle()) {
						shape = new Rectangle(hull);
						if(((Rectangle)shape).isSquare())
							shape = new Square(hull);
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
	    
		Point indexZero = cloud.get(0); //Stores the first element of cloud (its the point with the lowest Y after cleaning the cloud)
		
		List<Point> cloudNoIndexZero = cloud.subList(1, cloud.size()); //Creates a sublist of cloud without the first element.
																	   //This is to make sure the first element stays the first element
																	   //during the next sort.
		
		cloudNoIndexZero.sort((_a, _b) -> indexZero.getCoTan(_a) > indexZero.getCoTan(_b) ? -1 : indexZero.getCoTan(_a) == indexZero.getCoTan(_b) ? 0 : 1);
		//Sorts cloud by ascending polar angle between the point with the lowest Y
		
		//Graham Scan Algorithm 
		hull.push(cloud.get(0));
		hull.push(cloud.get(1));
		hull.push(cloud.get(2));
		
		for (int i=3; i<cloud.size(); i++) {
			while(hull.get(hull.size()-2).isCcw(hull.get(hull.size()-1), cloud.get(i)) < 0) {
				hull.pop();
			}
			hull.push(cloud.get(i));
		}
	}
	
	/* Removes the Points of the hull that are not vertices (aka are collinear)*/
	private void cleanHull() {
		for(int i=2; i<=hull.size(); i++) {
			if(hull.get(i%hull.size()).isCcw(hull.get(i - 1), hull.get(i - 2)) == 0) {
				hull.remove(i - 1);
				i--;
			}
		}
	}
}
