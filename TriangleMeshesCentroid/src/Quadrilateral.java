import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Subclass of Polygon to represent a 2D convex quadrilateral.
 * It is defined by a set of four distinct vertexes.
 * 
 * @see Polygon
 */
public class Quadrilateral extends Polygon {
	
	/**
	 * Quadrilateral constructor. Arguments must be given either counterclockwise
	 * or clockwise, otherwise an {@link IllegalArgumentException} is thrown
	 * @param _vertexes List of Points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         quadrilateral.
	 */
	public Quadrilateral(List<Point> _vertexes) {
		addPoints(_vertexes);
		double angle1 = getVertex(0).isCcw(getVertex(1), getVertex(2));
		double angle2 = getVertex(1).isCcw(getVertex(2), getVertex(3));
		double angle3 = getVertex(2).isCcw(getVertex(3), getVertex(0));
		double angle4 = getVertex(3).isCcw(getVertex(0), getVertex(1));
		if(angle1 == 0 || angle2 == 0 || angle3 == 0 || angle4 == 0 || 
		   !(Math.signum(angle1) == Math.signum(angle2) && Math.signum(angle2) == Math.signum(angle3) && Math.signum(angle3) == Math.signum(angle4)))
			throw new IllegalArgumentException("Points do not define a quadrilateral.");
	}
	
	public Quadrilateral (String _s) {
		String[] split = _s.split(" ");
		Point a = new Point(Double.parseDouble(split[1]), Double.parseDouble(split[2]));
		Point b = new Point(Double.parseDouble(split[3]), Double.parseDouble(split[4]));
		Point c = new Point(Double.parseDouble(split[5]), Double.parseDouble(split[6]));
		Point d = new Point(Double.parseDouble(split[7]), Double.parseDouble(split[8]));
		List<Point> points = new ArrayList<Point>();
		Collections.addAll(points, a,b,c,d);
		addPoints(points);
		double angle1 = getVertex(0).isCcw(getVertex(1), getVertex(2));
		double angle2 = getVertex(1).isCcw(getVertex(2), getVertex(3));
		double angle3 = getVertex(2).isCcw(getVertex(3), getVertex(0));
		double angle4 = getVertex(3).isCcw(getVertex(0), getVertex(1));
		if(angle1 == 0 || angle2 == 0 || angle3 == 0 || angle4 == 0 || 
		   !(Math.signum(angle1) == Math.signum(angle2) && Math.signum(angle2) == Math.signum(angle3) && Math.signum(angle3) == Math.signum(angle4)))
			throw new IllegalArgumentException("Points do not define a quadrilateral.");
	}
	
	@Override
	public double getArea() {
		Point vectorA = new Point(getVertex(1).getX() - getVertex(0).getX(), getVertex(1).getY() - getVertex(0).getY());
		Point vectorB = new Point(getVertex(2).getX() - getVertex(1).getX(), getVertex(2).getY() - getVertex(1).getY());
		Point vectorC = new Point(getVertex(3).getX() - getVertex(2).getX(), getVertex(3).getY() - getVertex(2).getY());
		
		Point vectorP = new Point(vectorB.getX() + vectorC.getX(), vectorB.getY() + vectorC.getY());
		Point vectorQ = new Point(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
		
		double crossPQ = vectorP.getX() * vectorQ.getY() - vectorP.getY() * vectorQ.getX();
		return (1d/2d) * Math.abs(crossPQ);
	}
	
	/**
	 * Checks whether this quadrilateral is a parallelogram.
	 * @return True if it is, false is its not.
	 */
	public boolean isParallelogram() {
		if(getVertex(1).getX() - getVertex(0).getX() == getVertex(2).getX() - getVertex(3).getX() && getVertex(1).getY() - getVertex(0).getY() == getVertex(2).getY() - getVertex(3).getY()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Quadrilateral";
	}
}
