import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Point> cloud = new ArrayList<Point>();
		Collections.addAll(cloud, new PointClass(0,3), new PointClass(4,3), new PointClass(0,0), new PointClass(4,0));
		ShapeSolver solver = new ShapeSolver(cloud);
		solver.solution();
	}
}
