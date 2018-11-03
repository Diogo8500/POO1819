
public class ParallelogramClass extends QuadrilateralClass implements Parallelogram {

	public ParallelogramClass(Point _a, Point _b, Point _c, Point _d) throws IllegalArgumentException {
		super(_a, _b, _c, _d);
		if(!(_b.getX() - _a.getX() == _c.getX() - _d.getX() && _b.getY() - _a.getY() == _c.getY() - _d.getY()))
			throw new IllegalArgumentException("Points do not form a parallelogram.");
	}
	
	@Override
	public String toString() {
		return "Parallelogram";
	}

}
