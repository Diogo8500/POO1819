
public class SquareClass extends RectangleClass implements Square {

	public SquareClass(Point _a, Point _b, Point _c, Point _d) throws IllegalArgumentException {
		super(_a, _b, _c, _d);
		if(_a.getDist(_b) != _b.getDist(_c))
			throw new IllegalArgumentException("Is not a square.");
	}
	
	public String toString() {
		return "Square";
	}

}
