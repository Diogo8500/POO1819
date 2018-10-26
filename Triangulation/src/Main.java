import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		try {
			Vector2D a = new Vector2D(2,3);
			Vector2D b = new Vector2D(1,2);
			Vector2D c = new Vector2D(3,2);
			Vector2D d = new Vector2D(2,1);
			Vector2D e = new Vector2D(3,3);
			Vector2D g = new Vector2D(1,1);
			Vector2D h = new Vector2D(5,1);
		    Vector<Vector2D> pointSet = new Vector<Vector2D>();
		    Collections.addAll(pointSet, a, b, c, d, e, g, h);
		    
		    DelaunayTriangulator dt = new DelaunayTriangulator(pointSet);
		    dt.triangulate();
		    
		    List<Triangle> triangleSoup = dt.getTriangles();
		    for(Triangle triangle: triangleSoup) {
		    	System.out.println(triangle);
		    }
		    
		} catch (NotEnoughPointsException e) {
		}

	}

}
