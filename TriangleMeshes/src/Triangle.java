
public interface Triangle extends Poligon {
	public void setVertexA(Point _p) throws IllegalArgumentException;
	
	public void setVertexB(Point _p) throws IllegalArgumentException;
	
	public void setVertexC(Point _p) throws IllegalArgumentException;
	
	public Point getVertexA();
	
	public Point getVertexB();
	
	public Point getVertexC();
	
	public double getLenghtAB();
	
	public double getLenghtBC();
	
	public double getLenghtCA();
}
