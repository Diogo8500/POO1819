
public class EquilateralTriangleClass extends TriangleClass {

	public EquilateralTriangleClass(PointClass _a, PointClass _b, PointClass _c) {
		super(_a, _b, _c);
		if(!(_a.getDist(_b) == _b.getDist(_c) && _b.getDist(_c) == _c.getDist(_a)))
			throw new IllegalArgumentException("Triangle is not equilateral");
			
	}

	@Override
	public String toString() {
		return "Equilateral " + super.toString();
	}
}
