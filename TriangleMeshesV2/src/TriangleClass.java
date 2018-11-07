
public class TriangleClass extends PoligonClass {
	
	private PointClass a, b, c;

	public TriangleClass(PointClass _a, PointClass _b, PointClass _c) {
		if(_a.isCollinear(_b, _c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		a = _a;
		b = _b;
		c = _c;
	}
	
	public void setVertexA(PointClass _a) {
		if(_a.isCollinear(b, c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		a = _a;
	}

	public void setVertexB(PointClass _b) {
		if(_b.isCollinear(a, c))
			throw new IllegalArgumentException("Vertexes are collinear.");
		b = _b;
	}

	public void setVertexC(PointClass _c) {
		if(_c.isCollinear(a, b))
			throw new IllegalArgumentException("Vertexes are collinear.");
		c = _c;
	}

	public PointClass getVertexA() {
		return a;
	}

	public PointClass getVertexB() {
		return b;
	}

	public PointClass getVertexC() {
		return c;
	}
	
	public double getLenghtAB() {
		return getVertexA().getDist(getVertexB());
	}
	
	public double getLenghtBC() {
		return getVertexB().getDist(getVertexC());
	}
	
	public double getLenghtCA() {
		return getVertexC().getDist(getVertexA());
	}

	@Override
	public double getPerimetre() {
		return getLenghtAB() + getLenghtBC() + getLenghtCA();
	}
	
	@Override
	public double getArea() {
		double parc1 = a.getX() * (b.getY() - c.getY());
		double parc2 = b.getX() * (c.getY() - a.getY());
		double parc3 = c.getX() * (a.getY() - b.getY());
		return Math.abs((parc1 + parc2 + parc3)/2d);
	}
	
	@Override
	public String toString() {
		return "Triangle";
	}

}
