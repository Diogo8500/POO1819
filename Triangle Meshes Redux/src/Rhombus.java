
public class Rhombus extends Parallelogram {

	public Rhombus(Point _a, Point _b, Point _c, Point _d) {
		super(_a, _b, _c, _d);
		if(!super.isRhombus())
			throw new IllegalArgumentException("Is not a rectangle");
	}
	
	@Override
	public String toString() {
		return "Rhombus";
	}
}
