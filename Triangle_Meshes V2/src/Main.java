import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Point a = new PointClass(3, 3);
		Point b = new PointClass(3, 2);
		Point c = new PointClass(1, 1);
		Point d = new PointClass(5, 1);
		
		List<Point> points = new ArrayList<Point>(4);
		Collections.addAll(points, a, b, c, d);
		
		DelaunayTriangulator dt = new DelaunayTriangulator(points);
		try {
			dt.triangulate();
		} catch (NotEnoughPointsException e) {
			e.printStackTrace();
		}
		System.out.println(dt.getTriangles());
	}

}
