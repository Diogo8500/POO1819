import java.util.Iterator;
import java.util.Set;

public abstract class PoligonClass implements Poligon {
	
	private Set<Point> vertices;
	
	public PoligonClass(Set<Point> _points) throws IllegalArgumentException {
		if(_points.size() < 3) throw new IllegalArgumentException("Poligon must have more than 2 vertices!");
		
		vertices = _points;
	}
	
	public double perimetre() {
		double perimetre = 0;
		Iterator<Point> verticesIterator = vertices.iterator();
		Point a = verticesIterator.next();
		Point b;
		while(verticesIterator.hasNext()) {
			perimetre += a.dist(b=verticesIterator.next());
			a = b;
		}
		return perimetre;
	}

	public abstract double area();

	public int numVertices() {
		return vertices.size();
	}
	
	public abstract int compareTo(Poligon _p);
}
