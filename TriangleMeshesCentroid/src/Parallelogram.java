import java.util.List;

/**
 * Subclass of Quadrilateral to represent a 2D parallelogram.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way opposite sides are parallel.
 * 
 * @see Quadrilateral
 */
public class Parallelogram extends Quadrilateral {

	/**
	 * Parallelogram constructor.
	 * @param _vertexes List of Points
	 * @throws IllegalArgumentException if the points do not form a 
	 *         parallelogram.
	 */
	public Parallelogram(List<Point> _vertexes) {
		super(_vertexes);
		if(!super.isParallelogram())
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	public Parallelogram (String _s) {
		super(_s);
		if(!super.isParallelogram())
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	/**
	 * Checks whether this parallelogram is a rectangle.
	 * @return True if it is, false is its not.
	 */
	public boolean isRectangle() {
		double xA = getVertex(0).getX();
		double yA = getVertex(0).getY();
		double xB = getVertex(1).getX();
		double yB = getVertex(1).getY();
		double xC = getVertex(2).getX();
		double yC = getVertex(2).getY();
		double xD = getVertex(3).getX();
		double yD = getVertex(3).getY();
		if((xB - xA) * (xB - xC) + 
			(yB - yA) * (yB - yC) == 0 && 
			(xD - xC) * (xD - xA) + 
			(yD - yC) * (yD - yA) == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether this parallelogram is a rhombus.
	 * @return True if it is, false is its not.
	 */
	public boolean isRhombus() {
		double lengthAC = getVertex(2).getDist(getVertex(0));
		double lengthBD = getVertex(1).getDist(getVertex(3));
		Point vecAC = new Point(getVertex(2).getX() - getVertex(0).getX(), getVertex(2).getY() - getVertex(0).getY());
		Point vecBD = new Point(getVertex(3).getX() - getVertex(1).getX(), getVertex(3).getY() - getVertex(1).getY());
		return vecAC.getX() * vecBD.getX() + vecAC.getY() * vecBD.getY() == 0 && lengthAC != lengthBD;
	}
	
	@Override
	public String toString() {
		return "Parallelogram";
	}

}
