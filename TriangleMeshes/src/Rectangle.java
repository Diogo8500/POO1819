/**
 * Subclass of Parallelogram to represent a 2D rectangle.
 * It is defined by a set of four distinct vertexes arranged in
 * such a way opposite sides are parallel and all vertex angles
 * are 90 degrees.
 * @see Parallelogram
 */
public class Rectangle extends Parallelogram {

	/**
	 * Rectangle constructor.
	 * @param _a Vertex A
	 * @param _b Vertex B
	 * @param _c Vertex C
	 * @param _d Vertex D
	 * @throws IllegalArgumentException if the points do not form a 
	 *         rectangle.
	 */
	public Rectangle(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!super.isMoreSpecific())
			throw new IllegalArgumentException("Is not a rectangle");
			
	}
	
	/**
	 * Checks whether this rectangle is a square.
	 * @return True if it is, false is its not.
	 */
	@Override
	public boolean isMoreSpecific() {
		if(getVertexA().getDist(getVertexB()) == getVertexB().getDist(getVertexC())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Rectangle";
	}

}
