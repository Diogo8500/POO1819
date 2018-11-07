
public class RectangleClass extends ParallelogramClass {

	public RectangleClass(PointClass _a, PointClass _b, PointClass _c, PointClass _d) {
		super(_a, _b, _c, _d);
		if(!((_b.getX() - _a.getX()) * (_b.getX() - _c.getX()) + (_b.getY() - _a.getY()) * (_b.getY() - _c.getY()) == 0 && (_d.getX() - _c.getX()) * (_d.getX() - _a.getX()) + (_d.getY() - _c.getY()) * (_d.getY() - _a.getY()) == 0))
			throw new IllegalArgumentException("Is not a rectangle");
	}
	
	@Override
	public String toString() {
		return "Rectangle";
	}

}
