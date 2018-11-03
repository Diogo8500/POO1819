
public class TriangleClass extends PoligonClass implements Triangle {

	public TriangleClass(Point _a, Point _b, Point _c) throws IllegalArgumentException {
		setVertexA(_a);
		setVertexB(_b);
		setVertexC(_c);
	}
	
	@Override
	public void setVertexA(Point _p) throws IllegalArgumentException {
		if(getVertexCount() == 0) addVertex(_p);
		else setVertex(_p, 0);
	}

	@Override
	public void setVertexB(Point _p) throws IllegalArgumentException {
		if(getVertexCount() == 1) addVertex(_p);
		else setVertex(_p, 1);
	}

	@Override
	public void setVertexC(Point _p) throws IllegalArgumentException {
		if(getVertexCount() == 2) addVertex(_p);
		else setVertex(_p, 2);
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
	public double getLenghtAB() {
		return getVertexA().getDist(getVertexB());
	}
	
	@Override
	public double getLenghtBC() {
		return getVertexB().getDist(getVertexC());
	}
	
	@Override
	public double getLenghtCA() {
		return getVertexC().getDist(getVertexA());
	}

	@Override
	public double getArea() {
		double parc1 = getVertexA().getX() * (getVertexB().getY() - getVertexC().getY());
		double parc2 = getVertexB().getX() * (getVertexC().getY() - getVertexA().getY());
		double parc3 = getVertexC().getX() * (getVertexA().getY() - getVertexB().getY());
		return Math.abs((parc1 + parc2 + parc3)/2d);
	}
	
	@Override
	public String toString() {
		return "Triangle";
	}

}
