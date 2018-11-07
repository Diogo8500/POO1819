
public class ParallelogramClass extends QuadrilateralClass {

	public ParallelogramClass(PointClass _a, PointClass _b, PointClass _c, PointClass _d) {
		super(_a, _b, _c, _d);
		if(!(_b.getX() - _a.getX() == _c.getX() - _d.getX() && _b.getY() - _a.getY() == _c.getY() - _d.getY()))
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	@Override
	public String toString() {
		return "Parallelogram";
	}

}
