import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Point> cloud = new ArrayList<Point>();
		Collections.addAll(cloud, new PointClass(13,7), new PointClass(10,4), new PointClass(13,4), new PointClass(16,4), new PointClass(13,1));
		ShapeSolver solver = new ShapeSolver(cloud);
		solver.solution();
	}
}
