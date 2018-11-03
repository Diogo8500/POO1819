
public interface Quadrilateral extends Poligon {
	public void setVertexA(Point _a);
	public void setVertexB(Point _b);
	public void setVertexC(Point _c);
	public void setVertexD(Point _d);
	
	public Point getVertexA();
	public Point getVertexB();
	public Point getVertexC();
	public Point getVertexD();
	
	public double getLenghtAB();
	public double getLenghtBC();
	public double getLenghtCD();
	public double getLenghtDA();
}
