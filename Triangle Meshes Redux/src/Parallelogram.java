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
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         parallelogram.
	 */
	public Parallelogram(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!super.isParallelogram())
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	/**
	 * Checks whether this parallelogram is a rectangle.
	 * @return True if it is, false is its not.
	 */
	public boolean isRectangle() {
		double xA = getVertexA().getX();
		double yA = getVertexA().getY();
		double xB = getVertexB().getX();
		double yB = getVertexB().getY();
		double xC = getVertexC().getX();
		double yC = getVertexC().getY();
		double xD = getVertexD().getX();
		double yD = getVertexD().getY();
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
		Point vecAC = new Point(getVertexC().getX() - getVertexA().getX(), getVertexC().getY() - getVertexA().getY());
		Point vecBD = new Point(getVertexD().getX() - getVertexB().getX(), getVertexD().getY() - getVertexB().getY());
		return vecAC.getX() * vecBD.getX() + vecAC.getY() * vecBD.getY() == 0;
	}
	
	@Override
	public String toString() {
		return "Parallelogram";
	}

}
