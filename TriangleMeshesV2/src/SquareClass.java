
public class SquareClass extends RectangleClass {

	public SquareClass(PointClass _a, PointClass _b, PointClass _c, PointClass _d) {
		super(_a, _b, _c, _d);
		if(_a.getDist(_b) != _b.getDist(_c))
			throw new IllegalArgumentException("Is not a square.");
	}
	
	public String toString() {
		return "Square";
	}

}
