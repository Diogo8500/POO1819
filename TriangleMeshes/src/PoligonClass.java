import java.util.ArrayList;
import java.util.List;

/**
 * 
 * E importante que quando se esteja a criar uma subclasse de Poligon, os vertices sejam passados
 * aos respectivos constructores por ordem clockwise ou anti-clockwise. NUNCA cruzados!
 * @author Dridri
 * 
 */
public abstract class PoligonClass implements Poligon {

	private List<Point> vertexes = new ArrayList<Point>(3);
	
	protected void addVertex(Point _p) throws IllegalArgumentException {
		if(!isValidForAdd(_p)) throw new IllegalArgumentException("New point is either equal or colinear with 2 adjacent vertexes");
		vertexes.add(_p);
	}
	
	protected void setVertex(Point _p, int _index) throws IllegalArgumentException, IndexOutOfBoundsException {
		if(_index >= vertexes.size() || _index < 0) throw new IndexOutOfBoundsException(Integer.toString(_index));
		if(!isValidForSet(_p, _index)) throw new IllegalArgumentException("New point position is either equal or colinear with 2 adjacent vertexes");
		vertexes.set(_index, _p);
	}
	
	protected int getVertexCount() {
		return vertexes.size();
	}
	
	@Override
	public Point getVertex(int _index) throws IndexOutOfBoundsException {
		return vertexes.get(_index);
	}

	@Override
	public double getPerimetre() {
		double perimetre = 0;
		for(int i=0; i<vertexes.size(); i++) {
			perimetre += vertexes.get(i).getDist(vertexes.get((i + 1)%vertexes.size()));
		}
		return perimetre;
	}

	@Override
	public abstract double getArea();
	
	private boolean isValidForSet(Point _p, int _index) {
		for(int i=0; i<vertexes.size(); i++) 
			if(vertexes.get(i).compareTo(_p) == 0)
				return false;
		if(_p.isCollinear(vertexes.get((_index-1)%vertexes.size()), vertexes.get((_index+1)%vertexes.size())))
			return false;
		return true;
	}
	private boolean isValidForAdd(Point _p) {
		if(vertexes.isEmpty()) return true;
		for(int i=0; i<vertexes.size(); i++) 
			if(vertexes.get(i).compareTo(_p) == 0)
				return false;
		if(vertexes.size() >= 2 && _p.isCollinear(vertexes.get(0), vertexes.get(vertexes.size() - 1)))
			return false;
		return true;
	}
}
