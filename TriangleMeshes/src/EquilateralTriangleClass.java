
public class EquilateralTriangleClass extends TriangleClass implements EquilateralTriangle {

	public EquilateralTriangleClass(Point _a, Point _b, Point _c) throws IllegalArgumentException {
		super(_a, _b, _c);
		if(!(_a.getDist(_b) == _b.getDist(_c) || _a.getDist(_b) ==  _c.getDist(_a)))
			throw new IllegalArgumentException("Triangle is not equilateral");
	}

	@Override
	public String toString() {
		return "Equilateral " + super.toString();
	}
}
