
public class QuadrilateralClass extends PoligonClass implements Quadrilateral {

	public QuadrilateralClass(Point _a, Point _b, Point _c, Point _d) {
		setVertexA(_a);
		setVertexB(_b);
		setVertexC(_c);
		setVertexD(_d);
	}
	
	@Override
	public void setVertexA(Point _a) {
		if(getVertexCount() == 0) addVertex(_a);
		else setVertex(_a, 0);

	}

	@Override
	public void setVertexB(Point _b) {
		if(getVertexCount() == 1) addVertex(_b);
		else setVertex(_b, 1);

	}

	@Override
	public void setVertexC(Point _c) {
		if(getVertexCount() == 2) addVertex(_c);
		else setVertex(_c, 2);

	}

	@Override
	public void setVertexD(Point _d) {
		if(getVertexCount() == 3) addVertex(_d);
		else setVertex(_d, 3);

	}

	@Override
	public Point getVertexA() {
		return getVertex(0);
	}

	@Override
	public Point getVertexB() {
		
		return getVertex(1);
	}

	@Override
	public Point getVertexC() {
		return getVertex(2);
	}

	@Override
	public Point getVertexD() {
		return getVertex(3);
	}

	@Override
	public double getLenghtAB() {
		return getVertexA().getDist(getVertexB());
	}

	@Override
	public double getLenghtBC() {
		return getVertexB().getDist(getVertexC());
	}

	@Override
	public double getLenghtCD() {
		return getVertexC().getDist(getVertexD());
	}

	@Override
	public double getLenghtDA() {
		return getVertexD().getDist(getVertexA());
	}

	@Override
	public double getArea() {
		Point vectorA = new PointClass(getVertex(1).getX() - getVertex(0).getX(), getVertex(1).getY() - getVertex(0).getY());
		Point vectorB = new PointClass(getVertex(2).getX() - getVertex(1).getX(), getVertex(2).getY() - getVertex(1).getY());
		Point vectorC = new PointClass(getVertex(3).getX() - getVertex(2).getX(), getVertex(3).getY() - getVertex(2).getY());
		//Point vectorD = new PointClass(getVertex(0).getX() - getVertex(3).getX(), getVertex(0).getY() - getVertex(3).getY());
		
		Point vectorP = new PointClass(vectorB.getX() + vectorC.getX(), vectorB.getY() + vectorC.getY());
		Point vectorQ = new PointClass(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
		
		double crossPQ = vectorP.getX() * vectorQ.getY() - vectorP.getY() * vectorQ.getX();
		return (1d/2d) * Math.abs(crossPQ);
	}
	
	@Override
	public String toString() {
		return "Quadrilateral";
	}
}
