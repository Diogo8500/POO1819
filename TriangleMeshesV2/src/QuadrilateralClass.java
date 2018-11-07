
public class QuadrilateralClass extends PoligonClass {

	private PointClass a, b, c, d;
	
	public QuadrilateralClass(PointClass _a, PointClass _b, PointClass _c, PointClass _d) {
		if(_a.isCollinear(_b, _c) || _a.isCollinear(_c, _d))
			throw new IllegalArgumentException("Points are collinear.");
		a = _a;
		b = _b;
		c = _c;
		d = _d;
	}
	
	public void setVertexA(PointClass _a) {
		if(_a.isCollinear(b, c) || _a.isCollinear(c, d))
			throw new IllegalArgumentException("Points are collinear.");
		a = _a;
	}

	public void setVertexB(PointClass _b) {
		if(_b.isCollinear(c, d) || _b.isCollinear(d, a))
			throw new IllegalArgumentException("Points are collinear.");
		b = _b;
	}

	public void setVertexC(PointClass _c) {
		if(_c.isCollinear(d, a) || _c.isCollinear(a, b))
			throw new IllegalArgumentException("Points are collinear.");
		c = _c;
	}

	public void setVertexD(PointClass _d) {
		if(_d.isCollinear(a, b) || _d.isCollinear(b, c))
			throw new IllegalArgumentException("Points are collinear.");
		d = _d;
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

	public PointClass getVertexD() {
		return d;
	}

	public double getLenghtAB() {
		return a.getDist(b);
	}

	public double getLenghtBC() {
		return b.getDist(c);
	}

	public double getLenghtCD() {
		return c.getDist(d);
	}

	public double getLenghtDA() {
		return d.getDist(a);
	}
	
	@Override
	public double getPerimetre() {
		return getLenghtAB() + getLenghtBC() + getLenghtCD() + getLenghtDA();
	}
	
	@Override
	public double getArea() {
		PointClass vectorA = new PointClass(b.getX() - a.getX(), b.getY() - a.getY());
		PointClass vectorB = new PointClass(c.getX() - b.getX(), c.getY() - b.getY());
		PointClass vectorC = new PointClass(d.getX() - c.getX(), d.getY() - c.getY());
		
		PointClass vectorP = new PointClass(vectorB.getX() + vectorC.getX(), vectorB.getY() + vectorC.getY());
		PointClass vectorQ = new PointClass(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
		
		double crossPQ = vectorP.getX() * vectorQ.getY() - vectorP.getY() * vectorQ.getX();
		return (1d/2d) * Math.abs(crossPQ);
	}
	
	@Override
	public String toString() {
		return "Quadrilateral";
	}
}
